package ma.dxc.service.EntityListener;

import static ma.dxc.service.audit.Operation.DELETE_CONTACT;
import static ma.dxc.service.audit.Operation.INSERTE_CONTACT;
import static ma.dxc.service.audit.Operation.UPDATE_CONTACT;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
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

import ma.dxc.model.AppUser;
import ma.dxc.model.Audit;
import ma.dxc.model.Contact;
import ma.dxc.repository.UserRepository;
import ma.dxc.repository.specs.SearchCriteria;
import ma.dxc.repository.specs.SearchOperation;
import ma.dxc.repository.specs.UserSpecification;
import ma.dxc.security.AuthenticationRequest;
import ma.dxc.service.AccountService;
import ma.dxc.service.AccountServiceImpl;
import ma.dxc.service.ContactServiceImpl;
import ma.dxc.service.audit.BeanUtil;
import ma.dxc.service.audit.Operation;


@Component
public class ContactEntityListener{
	
	
	
	private static AccountServiceImpl accountService;
	
	Javers javers = JaversBuilder.javers().build();
	
	private static ContactServiceImpl contactServiceImpl;
	
	private static Contact contactOld = null;
	
	
	
	
	
	@Autowired
    public void setMyService (AccountServiceImpl accountService,ContactServiceImpl contactServiceImpl) {
        this.accountService=accountService;
        this.contactServiceImpl=contactServiceImpl;
        
    }
	
	
	 @PostLoad
	    public void postLoad (Contact contact) {
		 this.contactOld = contact;
	 }
	 
	/* @PostUpdate
	 	public void postUpdate (Contact contact) {
		 this.contactOld = contactServiceImpl.findOne(contact.getId());
	 }*/
	 
	 
	 @PrePersist
	    public void prePersist(Contact contact) {
		 	contact.setId(Long.parseLong("1"));
	    	String changes = contact.toString();
	        perform(contact,INSERTE_CONTACT,changes);
	 }
	 @PreUpdate
	 	public void PreUpdate(Contact contactNew) {
		 	long id = contactNew.getId();
		 	System.out.println(id);
		 	System.out.println("contactNew : "+contactNew.toString());
		 	Contact contact = this.contactOld;
		 	System.out.println("contactold : "+contact.toString());
		 	javers.commit("hamada",contact);
		 	contact = contactNew;
		 	javers.commit("hamada",contact);
	    	String changes = javers.findChanges( QueryBuilder.byInstance(contact).build()).toString();
	    	System.out.println("changes : "+changes);
	    	perform(contact,UPDATE_CONTACT,changes);
	        
	 }
	 @PreRemove
	 public void preRemove(Contact contact) {
		 	javers.commit("hamada",contact);
	    	String changes = javers.findChanges( QueryBuilder.byInstance(contact).build()).toString();
	        perform(contact,DELETE_CONTACT,changes);
	 }
	 
	 @Transactional(Transactional.TxType.MANDATORY)
	    private void perform(Contact contact, Operation operation, String changes){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		 Date currentDate = new Date();
		 String date = formatter.format(currentDate); 
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	String currentPrincipalName = authentication.getName();
	    	System.out.println("currentPrincipalName : "+currentPrincipalName);
	    	
		 
	        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
	        entityManager.persist(new Audit(currentPrincipalName,operation,changes,date));
	    }

}