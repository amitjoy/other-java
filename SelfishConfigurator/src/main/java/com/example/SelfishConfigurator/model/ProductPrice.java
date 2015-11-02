package com.example.SelfishConfigurator.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.example.SelfishConfigurator.model.ProductReference;
import javax.persistence.ManyToOne;
import com.example.SelfishConfigurator.model.Country;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ProductPrice implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String productPricecol;

   @Column
   private Double price;

   @Temporal(TemporalType.TIMESTAMP)
   private Date startDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date endDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedDate;

   @ManyToOne
   private ProductReference refernceID;

   @ManyToOne
   private Country countryd;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((ProductPrice) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public String getProductPricecol()
   {
      return this.productPricecol;
   }

   public void setProductPricecol(final String productPricecol)
   {
      this.productPricecol = productPricecol;
   }

   public Double getPrice()
   {
      return this.price;
   }

   public void setPrice(final Double price)
   {
      this.price = price;
   }

   public Date getStartDate()
   {
      return this.startDate;
   }

   public void setStartDate(final Date startDate)
   {
      this.startDate = startDate;
   }

   public Date getEndDate()
   {
      return this.endDate;
   }

   public void setEndDate(final Date endDate)
   {
      this.endDate = endDate;
   }

   public Date getModifiedDate()
   {
      return this.modifiedDate;
   }

   public void setModifiedDate(final Date modifiedDate)
   {
      this.modifiedDate = modifiedDate;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (productPricecol != null && !productPricecol.trim().isEmpty())
         result += "productPricecol: " + productPricecol;
      if (price != null)
         result += ", price: " + price;
      return result;
   }

   public ProductReference getRefernceID()
   {
      return this.refernceID;
   }

   public void setRefernceID(final ProductReference refernceID)
   {
      this.refernceID = refernceID;
   }

   public Country getCountryd()
   {
      return this.countryd;
   }

   public void setCountryd(final Country countryd)
   {
      this.countryd = countryd;
   }
}