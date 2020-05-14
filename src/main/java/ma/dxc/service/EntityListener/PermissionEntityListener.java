package ma.dxc.service.EntityListener;

import static ma.dxc.service.audit.Operation.DELETE_PERMISSION;
import static ma.dxc.service.audit.Operation.INSERTE_PERMISSION;
import static ma.dxc.service.audit.Operation.UPDATE_PERMISSION;

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
import ma.dxc.model.Permission;
import ma.dxc.service.AccountServiceImpl;
import ma.dxc.service.PermissionServiceImpl;
import ma.dxc.service.audit.BeanUtil;
import ma.dxc.service.audit.Operation;

@Component
public class PermissionEntityListener {

	
	private static AccountServiceImpl accountService;
	
	Javers javers = JaversBuilder.javers().build();
	
	private static PermissionServiceImpl PermissionServiceImpl;
	
	private static Permission PermissionOld = null;
	
	
	
	
	
	@Autowired
    public void setMyService (AccountServiceImpl accountService,PermissionServiceImpl PermissionServiceImpl) {
        this.accountService=accountService;
        this.PermissionServiceImpl=PermissionServiceImpl;
        
    }
	
	
	 @PostLoad
	    public void postLoad (Permission Permission) {
		 this.PermissionOld = Permission;
	 }
	 
	 
	 @PrePersist
	    public void prePersist(Permission permission) {
		 	permission.setId(Long.parseLong("1"));
	    	String changes = permission.toString();
	        perform(permission,INSERTE_PERMISSION,changes);
	 }
	 @PreUpdate
	 	public void PreUpdate(Permission permissionNew) {
		 	long id = permissionNew.getId();
		 	System.out.println(id);
		 	System.out.println("PermissionNew : "+permissionNew.toString());
		 	Permission permission = this.PermissionOld;
		 	System.out.println("Permissionold : "+permission.toString());
		 	javers.commit("hamada",permission);
		 	permission = permissionNew;
		 	javers.commit("hamada",permission);
	    	String changes = javers.findChanges( QueryBuilder.byInstance(permission).build()).toString();
	    	System.out.println("changes : "+changes);
	    	perform(permission,UPDATE_PERMISSION,changes);
	        
	 }
	 @PreRemove
	 public void preRemove(Permission permission) {
		 	javers.commit("hamada",permission);
	    	String changes = javers.findChanges( QueryBuilder.byInstance(permission).build()).toString();
	        perform(permission,DELETE_PERMISSION,changes);
	 }
	 
	 @Transactional(Transactional.TxType.MANDATORY)
	    private void perform(Permission permission, Operation operation, String changes){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		 Date currentDate = new Date();
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	String currentPrincipalName = authentication.getName();
	    	System.out.println("currentPrincipalName : "+currentPrincipalName);
	    	
	    	Long objectID = permission.getId();
	    	String objectType = permission.getClass().getName();
	    	
	        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
	        entityManager.persist(new Audit(currentPrincipalName,objectID,objectType,currentDate,operation,changes));
	    }

}