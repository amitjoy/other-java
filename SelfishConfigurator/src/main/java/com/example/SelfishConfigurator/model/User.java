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
import com.example.SelfishConfigurator.model.Country;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String firstName;

   @Column
   private String lastName;

   @Column
   private String emailId;

   @Column
   private String mobileNo;

   @Column
   private String phoneNo;

   @Column
   private String address1;

   @Column
   private String address2;

   @Column
   private String city;

   @Column
   private String state;

   @Column
   private String zip;

   @Column
   private String refCol1;

   @Column
   private String refCol2;

   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedDate;

   @ManyToOne
   private Country countryId;

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
         return id.equals(((User) that).id);
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

   public String getFirstName()
   {
      return this.firstName;
   }

   public void setFirstName(final String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return this.lastName;
   }

   public void setLastName(final String lastName)
   {
      this.lastName = lastName;
   }

   public String getEmailId()
   {
      return this.emailId;
   }

   public void setEmailId(final String emailId)
   {
      this.emailId = emailId;
   }

   public String getMobileNo()
   {
      return this.mobileNo;
   }

   public void setMobileNo(final String mobileNo)
   {
      this.mobileNo = mobileNo;
   }

   public String getPhoneNo()
   {
      return this.phoneNo;
   }

   public void setPhoneNo(final String phoneNo)
   {
      this.phoneNo = phoneNo;
   }

   public String getAddress1()
   {
      return this.address1;
   }

   public void setAddress1(final String address1)
   {
      this.address1 = address1;
   }

   public String getAddress2()
   {
      return this.address2;
   }

   public void setAddress2(final String address2)
   {
      this.address2 = address2;
   }

   public String getCity()
   {
      return this.city;
   }

   public void setCity(final String city)
   {
      this.city = city;
   }

   public String getState()
   {
      return this.state;
   }

   public void setState(final String state)
   {
      this.state = state;
   }

   public String getZip()
   {
      return this.zip;
   }

   public void setZip(final String zip)
   {
      this.zip = zip;
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
      if (firstName != null && !firstName.trim().isEmpty())
         result += "firstName: " + firstName;
      if (lastName != null && !lastName.trim().isEmpty())
         result += ", lastName: " + lastName;
      if (emailId != null && !emailId.trim().isEmpty())
         result += ", emailId: " + emailId;
      if (mobileNo != null && !mobileNo.trim().isEmpty())
         result += ", mobileNo: " + mobileNo;
      if (phoneNo != null && !phoneNo.trim().isEmpty())
         result += ", phoneNo: " + phoneNo;
      if (address1 != null && !address1.trim().isEmpty())
         result += ", address1: " + address1;
      if (address2 != null && !address2.trim().isEmpty())
         result += ", address2: " + address2;
      if (city != null && !city.trim().isEmpty())
         result += ", city: " + city;
      if (state != null && !state.trim().isEmpty())
         result += ", state: " + state;
      if (zip != null && !zip.trim().isEmpty())
         result += ", zip: " + zip;
      if (refCol1 != null && !refCol1.trim().isEmpty())
         result += ", refCol1: " + refCol1;
      if (refCol2 != null && !refCol2.trim().isEmpty())
         result += ", refCol2: " + refCol2;
      return result;
   }

   public Country getCountryId()
   {
      return this.countryId;
   }

   public void setCountryId(final Country countryId)
   {
      this.countryId = countryId;
   }
}