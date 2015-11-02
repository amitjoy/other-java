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
import com.example.SelfishConfigurator.model.Product;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ProductReference implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String color;

   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedDate;

   @ManyToOne
   private Product productID;

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
         return id.equals(((ProductReference) that).id);
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

   public String getColor()
   {
      return this.color;
   }

   public void setColor(final String color)
   {
      this.color = color;
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
      if (color != null && !color.trim().isEmpty())
         result += "color: " + color;
      return result;
   }

   public Product getProductID()
   {
      return this.productID;
   }

   public void setProductID(final Product productID)
   {
      this.productID = productID;
   }
}