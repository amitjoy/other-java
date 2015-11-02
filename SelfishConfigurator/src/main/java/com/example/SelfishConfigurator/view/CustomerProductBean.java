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

import com.example.SelfishConfigurator.model.CustomerProduct;
import com.example.SelfishConfigurator.model.Country;
import com.example.SelfishConfigurator.model.ProductReference;

/**
 * Backing bean for CustomerProduct entities.
 * <p>
 * This class provides CRUD functionality for all CustomerProduct entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class CustomerProductBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving CustomerProduct entities
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

   private CustomerProduct customerProduct;

   public CustomerProduct getCustomerProduct()
   {
      return this.customerProduct;
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
         this.customerProduct = this.example;
      }
      else
      {
         this.customerProduct = findById(getId());
      }
   }

   public CustomerProduct findById(Long id)
   {

      return this.entityManager.find(CustomerProduct.class, id);
   }

   /*
    * Support updating and deleting CustomerProduct entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.customerProduct);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.customerProduct);
            return "view?faces-redirect=true&id=" + this.customerProduct.getId();
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
    * Support searching CustomerProduct entities with pagination
    */

   private int page;
   private long count;
   private List<CustomerProduct> pageItems;

   private CustomerProduct example = new CustomerProduct();

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

   public CustomerProduct getExample()
   {
      return this.example;
   }

   public void setExample(CustomerProduct example)
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
      Root<CustomerProduct> root = countCriteria.from(CustomerProduct.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<CustomerProduct> criteria = builder.createQuery(CustomerProduct.class);
      root = criteria.from(CustomerProduct.class);
      TypedQuery<CustomerProduct> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<CustomerProduct> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String customerProductcol = this.example.getCustomerProductcol();
      if (customerProductcol != null && !"".equals(customerProductcol))
      {
         predicatesList.add(builder.like(root.<String> get("customerProductcol"), '%' + customerProductcol + '%'));
      }
      String refCol1 = this.example.getRefCol1();
      if (refCol1 != null && !"".equals(refCol1))
      {
         predicatesList.add(builder.like(root.<String> get("refCol1"), '%' + refCol1 + '%'));
      }
      String refCol2 = this.example.getRefCol2();
      if (refCol2 != null && !"".equals(refCol2))
      {
         predicatesList.add(builder.like(root.<String> get("refCol2"), '%' + refCol2 + '%'));
      }
      ProductReference referenceID = this.example.getReferenceID();
      if (referenceID != null)
      {
         predicatesList.add(builder.equal(root.get("referenceID"), referenceID));
      }
      Country countryID = this.example.getCountryID();
      if (countryID != null)
      {
         predicatesList.add(builder.equal(root.get("countryID"), countryID));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<CustomerProduct> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back CustomerProduct entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<CustomerProduct> getAll()
   {

      CriteriaQuery<CustomerProduct> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(CustomerProduct.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(CustomerProduct.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final CustomerProductBean ejbProxy = this.sessionContext.getBusinessObject(CustomerProductBean.class);

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

            return String.valueOf(((CustomerProduct) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private CustomerProduct add = new CustomerProduct();

   public CustomerProduct getAdd()
   {
      return this.add;
   }

   public CustomerProduct getAdded()
   {
      CustomerProduct added = this.add;
      this.add = new CustomerProduct();
      return added;
   }
}