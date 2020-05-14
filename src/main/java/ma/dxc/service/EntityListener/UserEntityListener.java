package ma.dxc.service.EntityListener;

import static ma.dxc.service.audit.Operation.DELETE_USER;
import static ma.dxc.service.audit.Operation.INSERTE_USER;
import static ma.dxc.service.audit.Operation.UPDATE_USER;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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
import ma.dxc.model.AppUser;
import ma.dxc.service.AccountServiceImpl;
import ma.dxc.service.UserServiceImpl;
import ma.dxc.service.audit.BeanUtil;
import ma.dxc.service.audit.Operation;


@Component
public class UserEntityListener {


	private static AccountServiceImpl accountService;
	
	Javers javers = JaversBuilder.javers().build();
	
	private static UserServiceImpl userServiceImpl;
	
	private static AppUser userOld = null;
	
	
	
	
	
	@Autowired
    public void setMyService (AccountServiceImpl accountService,UserServiceImpl userServiceImpl) {
        this.accountService=accountService;
        this.userServiceImpl=userServiceImpl;
        
    }
	
	
	 @PostLoad
	    public void postLoad (AppUser user) {
		 this.userOld = user;
	 }
	 
	/* @PostPersist
	    public void postPersist(AppUser user) {
		 	user.setId(Long.parseLong("0"));
	    	String changes = user.toString();
	        perform(user,INSERTE_USER,changes);
	 }*/
	 
	 @PrePersist
	    public void prePersist(AppUser user) {
		 	user.setId(Long.parseLong("1"));
	    	String changes = user.toString();
	        perform(user,INSERTE_USER,changes);
	 }
	 
	 @PreUpdate
	 	public void PreUpdate(AppUser userNew) {
		 	long id = userNew.getId();
		 	System.out.println(id);
		 	System.out.println("userNew : "+userNew.toString());
		 	AppUser user = this.userOld;
		 	System.out.println("userold : "+user.toString());
		 	javers.commit("hamada",user);
		 	user = userNew;
		 	javers.commit("hamada",user);
	    	String changes = javers.findChanges( QueryBuilder.byInstance(user).build()).toString();
	    	System.out.println("changes : "+changes);
	    	perform(user,UPDATE_USER,changes);
	        
	 }
	 @PreRemove
	 public void preRemove(AppUser user) {
		 	javers.commit("hamada",user);
	    	String changes = javers.findChanges( QueryBuilder.byInstance(user).build()).toString();
	        perform(user,DELETE_USER,changes);
	 }
	 
	 @Transactional(Transactional.TxType.MANDATORY)
	 //@PersistenceContext(type = PersistenceContextType.EXTENDED)
	    private void perform(AppUser user, Operation operation, String changes){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		 Date currentDate = new Date();
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	String currentPrincipalName = authentication.getName();
	    	System.out.println("currentPrincipalName : "+currentPrincipalName);
	    	
	    	Long objectID = user.getId();
	    	String objectType = user.getClass().getName();
	    	
	        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
	        entityManager.persist(new Audit(currentPrincipalName,objectID,objectType,currentDate,operation,changes));
	    }

}