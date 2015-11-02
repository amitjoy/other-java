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

import com.example.SelfishConfigurator.model.MapProductCountry;
import com.example.SelfishConfigurator.model.Country;
import com.example.SelfishConfigurator.model.Product;

/**
 * Backing bean for MapProductCountry entities.
 * <p>
 * This class provides CRUD functionality for all MapProductCountry entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class MapProductCountryBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving MapProductCountry entities
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

   private MapProductCountry mapProductCountry;

   public MapProductCountry getMapProductCountry()
   {
      return this.mapProductCountry;
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
         this.mapProductCountry = this.example;
      }
      else
      {
         this.mapProductCountry = findById(getId());
      }
   }

   public MapProductCountry findById(Long id)
   {

      return this.entityManager.find(MapProductCountry.class, id);
   }

   /*
    * Support updating and deleting MapProductCountry entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.mapProductCountry);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.mapProductCountry);
            return "view?faces-redirect=true&id=" + this.mapProductCountry.getId();
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
    * Support searching MapProductCountry entities with pagination
    */

   private int page;
   private long count;
   private List<MapProductCountry> pageItems;

   private MapProductCountry example = new MapProductCountry();

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

   public MapProductCountry getExample()
   {
      return this.example;
   }

   public void setExample(MapProductCountry example)
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
      Root<MapProductCountry> root = countCriteria.from(MapProductCountry.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<MapProductCountry> criteria = builder.createQuery(MapProductCountry.class);
      root = criteria.from(MapProductCountry.class);
      TypedQuery<MapProductCountry> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<MapProductCountry> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      Product productId = this.example.getProductId();
      if (productId != null)
      {
         predicatesList.add(builder.equal(root.get("productId"), productId));
      }
      Country countryId = this.example.getCountryId();
      if (countryId != null)
      {
         predicatesList.add(builder.equal(root.get("countryId"), countryId));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<MapProductCountry> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back MapProductCountry entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<MapProductCountry> getAll()
   {

      CriteriaQuery<MapProductCountry> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(MapProductCountry.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(MapProductCountry.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final MapProductCountryBean ejbProxy = this.sessionContext.getBusinessObject(MapProductCountryBean.class);

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

            return String.valueOf(((MapProductCountry) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private MapProductCountry add = new MapProductCountry();

   public MapProductCountry getAdd()
   {
      return this.add;
   }

   public MapProductCountry getAdded()
   {
      MapProductCountry added = this.add;
      this.add = new MapProductCountry();
      return added;
   }
}