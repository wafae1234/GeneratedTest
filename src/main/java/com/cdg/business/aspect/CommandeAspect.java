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

import static com.cdg.business.service.audit.Operation.INSERTE_COMMANDE;
import static com.cdg.business.service.audit.Operation.UPDATE_COMMANDE;

import com.cdg.business.model.Audit;
import com.cdg.business.model.Commande;
import com.cdg.business.repository.AuditRepository;
import com.cdg.business.service.CommandeServiceImpl;
import com.cdg.business.service.audit.Operation;



@Aspect
@Component
public class CommandeAspect {
	
	@Autowired
    private Javers javers;
	
	@Autowired
	AuditRepository auditRepository;
	
	@Autowired
    private CommandeServiceImpl service;


	
	@Pointcut(value = "execution(* com.cdg.business.service.CommandeServiceImpl.save(..))")
    public void mySavePointcut(){ }
	
	@Pointcut(value = "execution(* com.cdg.business.service.CommandeServiceImpl.update(..)) && args(id,commande,..)")
    public void myUpdatePointcut(Long id, Commande commande){ }
	
	@AfterReturning(pointcut = "mySavePointcut()",returning= "result")
    public void logAfterReturningUsers(JoinPoint joinPoint, Object result) throws Throwable{
		Commande commande = (Commande) result;
		Long objectID = commande.getId();
    	String objectType = commande.getClass().getName();
		Date date = new Date();
    	String changes = commande.toString();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String user = authentication.getName();
    	saveAudit(user, objectID, objectType, date,INSERTE_COMMANDE,changes);
    }
	
	@Around("myUpdatePointcut(id,commande)")
    public Object applicationLogger (ProceedingJoinPoint proceedingJoinPoint, Long id, Commande commande) throws Throwable {
		
		
		Long objectID = id;
    	String objectType = commande.getClass().getName();
		Date date = new Date();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String user = authentication.getName();
    	
    	Commande commandeAudited = service.findOne(id); 
    	System.out.println("**************\n"+commandeAudited.getId()+"\n"+commandeAudited.toString()+"\n*******************\n");
    	javers.commit("hamada",commandeAudited);
    	
        Object object = proceedingJoinPoint.proceed();
        
        commandeAudited = service.findOne(id);
    	javers.commit("hamada",commandeAudited);
    	String changes = javers.findChanges( QueryBuilder.byInstance(commandeAudited).build()).toString();
    	System.out.println("\n \n *************************** \n\n"+changes+"\n \n *************************** \n\n");

    	saveAudit(user, objectID, objectType, date, UPDATE_COMMANDE, changes);
        
		
        return object;
    }
	
	public void saveAudit(String user,Long objectID,String objectType,Date date,Operation operation,String changes) {
		Audit audit = new Audit(user, objectID, objectType, date, operation, changes);
		auditRepository.save(audit);
	}
	
	
}
