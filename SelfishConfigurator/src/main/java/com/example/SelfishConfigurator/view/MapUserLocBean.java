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

import com.example.SelfishConfigurator.model.MapUserLoc;
import com.example.SelfishConfigurator.model.User;

/**
 * Backing bean for MapUserLoc entities.
 * <p>
 * This class provides CRUD functionality for all MapUserLoc entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class MapUserLocBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving MapUserLoc entities
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

   private MapUserLoc mapUserLoc;

   public MapUserLoc getMapUserLoc()
   {
      return this.mapUserLoc;
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
         this.mapUserLoc = this.example;
      }
      else
      {
         this.mapUserLoc = findById(getId());
      }
   }

   public MapUserLoc findById(Long id)
   {

      return this.entityManager.find(MapUserLoc.class, id);
   }

   /*
    * Support updating and deleting MapUserLoc entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.mapUserLoc);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.mapUserLoc);
            return "view?faces-redirect=true&id=" + this.mapUserLoc.getId();
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
    * Support searching MapUserLoc entities with pagination
    */

   private int page;
   private long count;
   private List<MapUserLoc> pageItems;

   private MapUserLoc example = new MapUserLoc();

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

   public MapUserLoc getExample()
   {
      return this.example;
   }

   public void setExample(MapUserLoc example)
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
      Root<MapUserLoc> root = countCriteria.from(MapUserLoc.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<MapUserLoc> criteria = builder.createQuery(MapUserLoc.class);
      root = criteria.from(MapUserLoc.class);
      TypedQuery<MapUserLoc> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<MapUserLoc> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      User userid = this.example.getUserid();
      if (userid != null)
      {
         predicatesList.add(builder.equal(root.get("userid"), userid));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<MapUserLoc> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back MapUserLoc entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<MapUserLoc> getAll()
   {

      CriteriaQuery<MapUserLoc> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(MapUserLoc.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(MapUserLoc.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final MapUserLocBean ejbProxy = this.sessionContext.getBusinessObject(MapUserLocBean.class);

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

            return String.valueOf(((MapUserLoc) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private MapUserLoc add = new MapUserLoc();

   public MapUserLoc getAdd()
   {
      return this.add;
   }

   public MapUserLoc getAdded()
   {
      MapUserLoc added = this.add;
      this.add = new MapUserLoc();
      return added;
   }
}