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

import com.example.SelfishConfigurator.model.LineOfBusiness;

/**
 * Backing bean for LineOfBusiness entities.
 * <p>
 * This class provides CRUD functionality for all LineOfBusiness entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class LineOfBusinessBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving LineOfBusiness entities
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

   private LineOfBusiness lineOfBusiness;

   public LineOfBusiness getLineOfBusiness()
   {
      return this.lineOfBusiness;
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
         this.lineOfBusiness = this.example;
      }
      else
      {
         this.lineOfBusiness = findById(getId());
      }
   }

   public LineOfBusiness findById(Long id)
   {

      return this.entityManager.find(LineOfBusiness.class, id);
   }

   /*
    * Support updating and deleting LineOfBusiness entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.lineOfBusiness);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.lineOfBusiness);
            return "view?faces-redirect=true&id=" + this.lineOfBusiness.getId();
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
    * Support searching LineOfBusiness entities with pagination
    */

   private int page;
   private long count;
   private List<LineOfBusiness> pageItems;

   private LineOfBusiness example = new LineOfBusiness();

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

   public LineOfBusiness getExample()
   {
      return this.example;
   }

   public void setExample(LineOfBusiness example)
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
      Root<LineOfBusiness> root = countCriteria.from(LineOfBusiness.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<LineOfBusiness> criteria = builder.createQuery(LineOfBusiness.class);
      root = criteria.from(LineOfBusiness.class);
      TypedQuery<LineOfBusiness> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<LineOfBusiness> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String LOBName = this.example.getLOBName();
      if (LOBName != null && !"".equals(LOBName))
      {
         predicatesList.add(builder.like(root.<String> get("LOBName"), '%' + LOBName + '%'));
      }
      String LOBDescription = this.example.getLOBDescription();
      if (LOBDescription != null && !"".equals(LOBDescription))
      {
         predicatesList.add(builder.like(root.<String> get("LOBDescription"), '%' + LOBDescription + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<LineOfBusiness> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back LineOfBusiness entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<LineOfBusiness> getAll()
   {

      CriteriaQuery<LineOfBusiness> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(LineOfBusiness.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(LineOfBusiness.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final LineOfBusinessBean ejbProxy = this.sessionContext.getBusinessObject(LineOfBusinessBean.class);

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

            return String.valueOf(((LineOfBusiness) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private LineOfBusiness add = new LineOfBusiness();

   public LineOfBusiness getAdd()
   {
      return this.add;
   }

   public LineOfBusiness getAdded()
   {
      LineOfBusiness added = this.add;
      this.add = new LineOfBusiness();
      return added;
   }
}