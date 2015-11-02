package com.example.SelfishConfigurator.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.SelfishConfigurator.model.MapUserRole;
import com.example.SelfishConfigurator.model.Role;
import com.example.SelfishConfigurator.model.User;

/**
 * Backing bean for MapUserRole entities.
 * <p>
 * This class provides CRUD functionality for all MapUserRole entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class MapUserRoleBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving MapUserRole entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private MapUserRole mapUserRole;

   public MapUserRole getMapUserRole()
   {
      return this.mapUserRole;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
      }

      if (this.id == null)
      {
         this.mapUserRole = this.example;
      }
      else
      {
         this.mapUserRole = findById(getId());
      }
   }

   public MapUserRole findById(Long id)
   {

      return this.entityManager.find(MapUserRole.class, id);
   }

   /*
    * Support updating and deleting MapUserRole entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.mapUserRole);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.mapUserRole);
            return "view?faces-redirect=true&id=" + this.mapUserRole.getId();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         this.entityManager.remove(findById(getId()));
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching MapUserRole entities with pagination
    */

   private int page;
   private long count;
   private List<MapUserRole> pageItems;

   private MapUserRole example = new MapUserRole();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public MapUserRole getExample()
   {
      return this.example;
   }

   public void setExample(MapUserRole example)
   {
      this.example = example;
   }

   public void search()
   {
      this.page = 0;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<MapUserRole> root = countCriteria.from(MapUserRole.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<MapUserRole> criteria = builder.createQuery(MapUserRole.class);
      root = criteria.from(MapUserRole.class);
      TypedQuery<MapUserRole> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<MapUserRole> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      User userID = this.example.getUserID();
      if (userID != null)
      {
         predicatesList.add(builder.equal(root.get("userID"), userID));
      }
      Role roleID = this.example.getRoleID();
      if (roleID != null)
      {
         predicatesList.add(builder.equal(root.get("roleID"), roleID));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<MapUserRole> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back MapUserRole entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<MapUserRole> getAll()
   {

      CriteriaQuery<MapUserRole> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(MapUserRole.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(MapUserRole.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final MapUserRoleBean ejbProxy = this.sessionContext.getBusinessObject(MapUserRoleBean.class);

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((MapUserRole) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private MapUserRole add = new MapUserRole();

   public MapUserRole getAdd()
   {
      return this.add;
   }

   public MapUserRole getAdded()
   {
      MapUserRole added = this.add;
      this.add = new MapUserRole();
      return added;
   }
}