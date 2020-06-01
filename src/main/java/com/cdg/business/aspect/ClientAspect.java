package com.cdg.business.aspect;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.javers.core.Javers;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.cdg.business.service.audit.Operation.INSERTE_CLIENT;
import static com.cdg.business.service.audit.Operation.UPDATE_CLIENT;

import com.cdg.business.model.Audit;
import com.cdg.business.model.Client;
import com.cdg.business.repository.AuditRepository;
import com.cdg.business.service.ClientServiceImpl;
import com.cdg.business.service.audit.Operation;



@Aspect
@Component
public class ClientAspect {
	
	@Autowired
    private Javers javers;
	
	@Autowired
	AuditRepository auditRepository;
	
	@Autowired
    private ClientServiceImpl service;


	
	@Pointcut(value = "execution(* com.cdg.business.service.ClientServiceImpl.save(..))")
    public void mySavePointcut(){ }
	
	@Pointcut(value = "execution(* com.cdg.business.service.ClientServiceImpl.update(..)) && args(id,client,..)")
    public void myUpdatePointcut(Long id, Client client){ }
	
	@AfterReturning(pointcut = "mySavePointcut()",returning= "result")
    public void logAfterReturningUsers(JoinPoint joinPoint, Object result) throws Throwable{
		Client client = (Client) result;
		Long objectID = client.getId();
    	String objectType = client.getClass().getName();
		Date date = new Date();
    	String changes = client.toString();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String user = authentication.getName();
    	saveAudit(user, objectID, objectType, date,INSERTE_CLIENT,changes);
    }
	
	@Around("myUpdatePointcut(id,client)")
    public Object applicationLogger (ProceedingJoinPoint proceedingJoinPoint, Long id, Client client) throws Throwable {
		
		
		Long objectID = id;
    	String objectType = client.getClass().getName();
		Date date = new Date();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String user = authentication.getName();
    	
    	Client clientAudited = service.findOne(id); 
    	System.out.println("**************\n"+clientAudited.getId()+"\n"+clientAudited.toString()+"\n*******************\n");
    	javers.commit("hamada",clientAudited);
    	
        Object object = proceedingJoinPoint.proceed();
        
        clientAudited = service.findOne(id);
    	javers.commit("hamada",clientAudited);
    	String changes = javers.findChanges( QueryBuilder.byInstance(clientAudited).build()).toString();
    	System.out.println("\n \n *************************** \n\n"+changes+"\n \n *************************** \n\n");

    	saveAudit(user, objectID, objectType, date, UPDATE_CLIENT, changes);
        
		
        return object;
    }
	
	public void saveAudit(String user,Long objectID,String objectType,Date date,Operation operation,String changes) {
		Audit audit = new Audit(user, objectID, objectType, date, operation, changes);
		auditRepository.save(audit);
	}
	
	
}
