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
import com.example.SelfishConfigurator.model.LineOfBusiness;
import javax.persistence.ManyToOne;
import com.example.SelfishConfigurator.model.Country;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ProductRange implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String rangeName;

   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedDate;

   @ManyToOne
   private LineOfBusiness lineofbusinessID;

   @ManyToOne
   private Country countryID;

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
         return id.equals(((ProductRange) that).id);
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

   public String getRangeName()
   {
      return this.rangeName;
   }

   public void setRangeName(final String rangeName)
   {
      this.rangeName = rangeName;
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
      if (rangeName != null && !rangeName.trim().isEmpty())
         result += "rangeName: " + rangeName;
      return result;
   }

   public LineOfBusiness getLineofbusinessID()
   {
      return this.lineofbusinessID;
   }

   public void setLineofbusinessID(final LineOfBusiness lineofbusinessID)
   {
      this.lineofbusinessID = lineofbusinessID;
   }

   public Country getCountryID()
   {
      return this.countryID;
   }

   public void setCountryID(final Country countryID)
   {
      this.countryID = countryID;
   }
}