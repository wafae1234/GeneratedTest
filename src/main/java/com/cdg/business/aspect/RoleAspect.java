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

import com.cdg.business.model.AppRole;
import com.cdg.business.model.Audit;
import com.cdg.business.repository.AuditRepository;
import com.cdg.business.service.RoleServiceImpl;
import com.cdg.business.service.audit.Operation;

import static com.cdg.business.service.audit.Operation.INSERTE_ROLE;
import static com.cdg.business.service.audit.Operation.UPDATE_ROLE;;

@Aspect
@Component
public class RoleAspect {

	@Autowired
    private Javers javers;
	
	@Autowired
	AuditRepository auditRepository;
	
	@Autowired
	RoleServiceImpl service;
	
	@Pointcut(value = "execution(* com.cdg.business.service.RoleServiceImpl.save(..))")
    public void mySavePointcut(){ }
	
	@Pointcut(value = "execution(* com.cdg.business.service.RoleServiceImpl.update(..)) && args(id,appRole,..)")
    public void myUpdatePointcut(Long id, AppRole appRole){ }
	
	@AfterReturning(pointcut = "mySavePointcut()",returning= "result")
    public void logAfterReturningUsers(JoinPoint joinPoint, Object result) throws Throwable{
		AppRole appRole = (AppRole) result;
		Long objectID = appRole.getId();
    	String objectType = appRole.getClass().getName();
		Date date = new Date();
    	String changes = appRole.toString();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String user = authentication.getName();
    	saveAudit(user, objectID, objectType, date,INSERTE_ROLE,changes);
    }
	
	@Around("myUpdatePointcut(id,appRole)")
    public Object applicationLogger (ProceedingJoinPoint proceedingJoinPoint, Long id, AppRole appRole) throws Throwable {
        
		Long objectID = id;
    	String objectType = appRole.getClass().getName();
		Date date = new Date();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String user = authentication.getName();
    	AppRole appRoleAudited = service.findOne(id);    	
    	javers.commit(user,appRoleAudited);
		
        Object object = proceedingJoinPoint.proceed();
        
        appRoleAudited = service.findOne(id);
    	javers.commit(user,appRoleAudited);
    	String changes = javers.findChanges( QueryBuilder.byInstance(appRoleAudited).build()).toString();
    	saveAudit(user, objectID, objectType, date, UPDATE_ROLE, changes);
        
		
        return object;
    }
	
	public void saveAudit(String user,Long objectID,String objectType,Date date,Operation operation,String changes) {
		Audit audit = new Audit(user, objectID, objectType, date, operation, changes);
		auditRepository.save(audit);
	}
}
