package ma.dxc.service.EntityListener;

import static ma.dxc.service.audit.Operation.DELETE_ROLE;
import static ma.dxc.service.audit.Operation.INSERTE_ROLE;
import static ma.dxc.service.audit.Operation.UPDATE_ROLE;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ma.dxc.model.Audit;
import ma.dxc.model.AppRole;
import ma.dxc.service.AccountServiceImpl;
import ma.dxc.service.RoleServiceImpl;
import ma.dxc.service.audit.BeanUtil;
import ma.dxc.service.audit.Operation;
@Component
public class RoleEntityListener {
	
	
	
	private static AccountServiceImpl accountService;
	
	Javers javers = JaversBuilder.javers().build();
	
	private static RoleServiceImpl RoleServiceImpl;
	
	private static AppRole roleOld = null;
	
	
	
	
	
	@Autowired
    public void setMyService (AccountServiceImpl accountService,RoleServiceImpl RoleServiceImpl) {
        this.accountService=accountService;
        this.RoleServiceImpl=RoleServiceImpl;
        
    }
	
	
	 @PostLoad
	    public void postLoad (AppRole role) {
		 this.roleOld = role;
	 }
	 
	 
	 @PrePersist
	    public void prePersist(AppRole role) {
		 	role.setId(Long.parseLong("1"));
	    	String changes = role.toString();
	        perform(role,INSERTE_ROLE,changes);
	 }
	 @PreUpdate
	 	public void PreUpdate(AppRole roleNew) {
		 	long id = roleNew.getId();
		 	System.out.println(id);
		 	System.out.println("roleNew : "+roleNew.toString());
		 	AppRole role = this.roleOld;
		 	System.out.println("roleold : "+role.toString());
		 	javers.commit("hamada",role);
		 	role = roleNew;
		 	javers.commit("hamada",role);
	    	String changes = javers.findChanges( QueryBuilder.byInstance(role).build()).toString();
	    	System.out.println("changes : "+changes);
	    	perform(role,UPDATE_ROLE,changes);
	        
	 }
	 @PreRemove
	 public void preRemove(AppRole role) {
		 	javers.commit("hamada",role);
	    	String changes = javers.findChanges( QueryBuilder.byInstance(role).build()).toString();
	        perform(role,DELETE_ROLE,changes);
	 }
	 
	 @Transactional(Transactional.TxType.MANDATORY)
	    private void perform(AppRole role, Operation operation, String changes){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		 Date currentDate = new Date();
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	String currentPrincipalName = authentication.getName();
	    	
	    	Long objectID = role.getId();
	    	String objectType = role.getClass().getName();
	    	
	        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
	        entityManager.persist(new Audit(currentPrincipalName,objectID,objectType,currentDate,operation,changes));
	    }

}