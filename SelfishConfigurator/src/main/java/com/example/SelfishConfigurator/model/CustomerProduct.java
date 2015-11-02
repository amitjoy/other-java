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
import com.example.SelfishConfigurator.model.User;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class CustomerProduct implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String customerProductcol;

   @Column
   private Float purchasedPrice;

   @Column
   private String refCol1;

   @Column
   private String refCol2;

   @Column
   private Float units;

   @Temporal(TemporalType.TIMESTAMP)
   private Date purchaseDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedDate;

   @ManyToOne
   private ProductReference referenceID;

   @ManyToOne
   private Country countryID;

   @ManyToOne
   private User customerId;

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
         return id.equals(((CustomerProduct) that).id);
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

   public String getCustomerProductcol()
   {
      return this.customerProductcol;
   }

   public void setCustomerProductcol(final String customerProductcol)
   {
      this.customerProductcol = customerProductcol;
   }

   public Float getPurchasedPrice()
   {
      return this.purchasedPrice;
   }

   public void setPurchasedPrice(final Float purchasedPrice)
   {
      this.purchasedPrice = purchasedPrice;
   }

   public String getRefCol1()
   {
      return this.refCol1;
   }

   public void setRefCol1(final String refCol1)
   {
      this.refCol1 = refCol1;
   }

   public String getRefCol2()
   {
      return this.refCol2;
   }

   public void setRefCol2(final String refCol2)
   {
      this.refCol2 = refCol2;
   }

   public Float getUnits()
   {
      return this.units;
   }

   public void setUnits(final Float units)
   {
      this.units = units;
   }

   public Date getPurchaseDate()
   {
      return this.purchaseDate;
   }

   public void setPurchaseDate(final Date purchaseDate)
   {
      this.purchaseDate = purchaseDate;
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
      if (customerProductcol != null && !customerProductcol.trim().isEmpty())
         result += "customerProductcol: " + customerProductcol;
      if (purchasedPrice != null)
         result += ", purchasedPrice: " + purchasedPrice;
      if (refCol1 != null && !refCol1.trim().isEmpty())
         result += ", refCol1: " + refCol1;
      if (refCol2 != null && !refCol2.trim().isEmpty())
         result += ", refCol2: " + refCol2;
      if (units != null)
         result += ", units: " + units;
      return result;
   }

   public ProductReference getReferenceID()
   {
      return this.referenceID;
   }

   public void setReferenceID(final ProductReference referenceID)
   {
      this.referenceID = referenceID;
   }

   public Country getCountryID()
   {
      return this.countryID;
   }

   public void setCountryID(final Country countryID)
   {
      this.countryID = countryID;
   }

   public User getCustomerId()
   {
      return this.customerId;
   }

   public void setCustomerId(final User customerId)
   {
      this.customerId = customerId;
   }
}