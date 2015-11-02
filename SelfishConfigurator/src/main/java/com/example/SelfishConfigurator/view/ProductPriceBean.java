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

import com.example.SelfishConfigurator.model.ProductPrice;
import com.example.SelfishConfigurator.model.Country;
import com.example.SelfishConfigurator.model.ProductReference;

/**
 * Backing bean for ProductPrice entities.
 * <p>
 * This class provides CRUD functionality for all ProductPrice entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class ProductPriceBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving ProductPrice entities
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

   private ProductPrice productPrice;

   public ProductPrice getProductPrice()
   {
      return this.productPrice;
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
         this.productPrice = this.example;
      }
      else
      {
         this.productPrice = findById(getId());
      }
   }

   public ProductPrice findById(Long id)
   {

      return this.entityManager.find(ProductPrice.class, id);
   }

   /*
    * Support updating and deleting ProductPrice entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.productPrice);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.productPrice);
            return "view?faces-redirect=true&id=" + this.productPrice.getId();
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
    * Support searching ProductPrice entities with pagination
    */

   private int page;
   private long count;
   private List<ProductPrice> pageItems;

   private ProductPrice example = new ProductPrice();

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

   public ProductPrice getExample()
   {
      return this.example;
   }

   public void setExample(ProductPrice example)
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
      Root<ProductPrice> root = countCriteria.from(ProductPrice.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<ProductPrice> criteria = builder.createQuery(ProductPrice.class);
      root = criteria.from(ProductPrice.class);
      TypedQuery<ProductPrice> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<ProductPrice> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String productPricecol = this.example.getProductPricecol();
      if (productPricecol != null && !"".equals(productPricecol))
      {
         predicatesList.add(builder.like(root.<String> get("productPricecol"), '%' + productPricecol + '%'));
      }
      ProductReference refernceID = this.example.getRefernceID();
      if (refernceID != null)
      {
         predicatesList.add(builder.equal(root.get("refernceID"), refernceID));
      }
      Country countryd = this.example.getCountryd();
      if (countryd != null)
      {
         predicatesList.add(builder.equal(root.get("countryd"), countryd));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<ProductPrice> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back ProductPrice entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<ProductPrice> getAll()
   {

      CriteriaQuery<ProductPrice> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(ProductPrice.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(ProductPrice.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final ProductPriceBean ejbProxy = this.sessionContext.getBusinessObject(ProductPriceBean.class);

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

            return String.valueOf(((ProductPrice) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private ProductPrice add = new ProductPrice();

   public ProductPrice getAdd()
   {
      return this.add;
   }

   public ProductPrice getAdded()
   {
      ProductPrice added = this.add;
      this.add = new ProductPrice();
      return added;
   }
}