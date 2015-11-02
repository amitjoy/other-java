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

import com.example.SelfishConfigurator.model.ProductRange;
import com.example.SelfishConfigurator.model.Country;
import com.example.SelfishConfigurator.model.LineOfBusiness;

/**
 * Backing bean for ProductRange entities.
 * <p>
 * This class provides CRUD functionality for all ProductRange entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class ProductRangeBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving ProductRange entities
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

   private ProductRange productRange;

   public ProductRange getProductRange()
   {
      return this.productRange;
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
         this.productRange = this.example;
      }
      else
      {
         this.productRange = findById(getId());
      }
   }

   public ProductRange findById(Long id)
   {

      return this.entityManager.find(ProductRange.class, id);
   }

   /*
    * Support updating and deleting ProductRange entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.productRange);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.productRange);
            return "view?faces-redirect=true&id=" + this.productRange.getId();
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
    * Support searching ProductRange entities with pagination
    */

   private int page;
   private long count;
   private List<ProductRange> pageItems;

   private ProductRange example = new ProductRange();

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

   public ProductRange getExample()
   {
      return this.example;
   }

   public void setExample(ProductRange example)
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
      Root<ProductRange> root = countCriteria.from(ProductRange.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<ProductRange> criteria = builder.createQuery(ProductRange.class);
      root = criteria.from(ProductRange.class);
      TypedQuery<ProductRange> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<ProductRange> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String rangeName = this.example.getRangeName();
      if (rangeName != null && !"".equals(rangeName))
      {
         predicatesList.add(builder.like(root.<String> get("rangeName"), '%' + rangeName + '%'));
      }
      LineOfBusiness lineofbusinessID = this.example.getLineofbusinessID();
      if (lineofbusinessID != null)
      {
         predicatesList.add(builder.equal(root.get("lineofbusinessID"), lineofbusinessID));
      }
      Country countryID = this.example.getCountryID();
      if (countryID != null)
      {
         predicatesList.add(builder.equal(root.get("countryID"), countryID));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<ProductRange> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back ProductRange entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<ProductRange> getAll()
   {

      CriteriaQuery<ProductRange> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(ProductRange.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(ProductRange.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final ProductRangeBean ejbProxy = this.sessionContext.getBusinessObject(ProductRangeBean.class);

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

            return String.valueOf(((ProductRange) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private ProductRange add = new ProductRange();

   public ProductRange getAdd()
   {
      return this.add;
   }

   public ProductRange getAdded()
   {
      ProductRange added = this.add;
      this.add = new ProductRange();
      return added;
   }
}