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

import com.example.SelfishConfigurator.model.SubCategory;
import com.example.SelfishConfigurator.model.Category;

/**
 * Backing bean for SubCategory entities.
 * <p>
 * This class provides CRUD functionality for all SubCategory entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class SubCategoryBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving SubCategory entities
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

   private SubCategory subCategory;

   public SubCategory getSubCategory()
   {
      return this.subCategory;
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
         this.subCategory = this.example;
      }
      else
      {
         this.subCategory = findById(getId());
      }
   }

   public SubCategory findById(Long id)
   {

      return this.entityManager.find(SubCategory.class, id);
   }

   /*
    * Support updating and deleting SubCategory entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.subCategory);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.subCategory);
            return "view?faces-redirect=true&id=" + this.subCategory.getId();
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
    * Support searching SubCategory entities with pagination
    */

   private int page;
   private long count;
   private List<SubCategory> pageItems;

   private SubCategory example = new SubCategory();

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

   public SubCategory getExample()
   {
      return this.example;
   }

   public void setExample(SubCategory example)
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
      Root<SubCategory> root = countCriteria.from(SubCategory.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<SubCategory> criteria = builder.createQuery(SubCategory.class);
      root = criteria.from(SubCategory.class);
      TypedQuery<SubCategory> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<SubCategory> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String subCategoryName = this.example.getSubCategoryName();
      if (subCategoryName != null && !"".equals(subCategoryName))
      {
         predicatesList.add(builder.like(root.<String> get("subCategoryName"), '%' + subCategoryName + '%'));
      }
      Category categoryId = this.example.getCategoryId();
      if (categoryId != null)
      {
         predicatesList.add(builder.equal(root.get("categoryId"), categoryId));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<SubCategory> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back SubCategory entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<SubCategory> getAll()
   {

      CriteriaQuery<SubCategory> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(SubCategory.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(SubCategory.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final SubCategoryBean ejbProxy = this.sessionContext.getBusinessObject(SubCategoryBean.class);

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

            return String.valueOf(((SubCategory) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private SubCategory add = new SubCategory();

   public SubCategory getAdd()
   {
      return this.add;
   }

   public SubCategory getAdded()
   {
      SubCategory added = this.add;
      this.add = new SubCategory();
      return added;
   }
}