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

import com.example.SelfishConfigurator.model.ProductReference;
import com.example.SelfishConfigurator.model.Product;

/**
 * Backing bean for ProductReference entities.
 * <p>
 * This class provides CRUD functionality for all ProductReference entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class ProductReferenceBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving ProductReference entities
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

   private ProductReference productReference;

   public ProductReference getProductReference()
   {
      return this.productReference;
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
         this.productReference = this.example;
      }
      else
      {
         this.productReference = findById(getId());
      }
   }

   public ProductReference findById(Long id)
   {

      return this.entityManager.find(ProductReference.class, id);
   }

   /*
    * Support updating and deleting ProductReference entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.productReference);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.productReference);
            return "view?faces-redirect=true&id=" + this.productReference.getId();
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
    * Support searching ProductReference entities with pagination
    */

   private int page;
   private long count;
   private List<ProductReference> pageItems;

   private ProductReference example = new ProductReference();

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

   public ProductReference getExample()
   {
      return this.example;
   }

   public void setExample(ProductReference example)
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
      Root<ProductReference> root = countCriteria.from(ProductReference.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<ProductReference> criteria = builder.createQuery(ProductReference.class);
      root = criteria.from(ProductReference.class);
      TypedQuery<ProductReference> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<ProductReference> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String color = this.example.getColor();
      if (color != null && !"".equals(color))
      {
         predicatesList.add(builder.like(root.<String> get("color"), '%' + color + '%'));
      }
      Product productID = this.example.getProductID();
      if (productID != null)
      {
         predicatesList.add(builder.equal(root.get("productID"), productID));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<ProductReference> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back ProductReference entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<ProductReference> getAll()
   {

      CriteriaQuery<ProductReference> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(ProductReference.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(ProductReference.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final ProductReferenceBean ejbProxy = this.sessionContext.getBusinessObject(ProductReferenceBean.class);

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

            return String.valueOf(((ProductReference) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private ProductReference add = new ProductReference();

   public ProductReference getAdd()
   {
      return this.add;
   }

   public ProductReference getAdded()
   {
      ProductReference added = this.add;
      this.add = new ProductReference();
      return added;
   }
}