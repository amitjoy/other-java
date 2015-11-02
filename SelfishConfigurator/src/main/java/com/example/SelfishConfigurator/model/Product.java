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
import com.example.SelfishConfigurator.model.SubCategory;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Product implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String productName;

   @Column
   private String description;

   @Column
   private String longDescription;

   @Column
   private String imagePath;

   @Column
   private String pDFPath;

   @Column
   private String refCol1;

   @Column
   private String refCol2;

   @Column
   private String installationVideoPath;

   @Column
   private String barcode;

   @Column
   private Byte isUpdate;

   @Column
   private Byte isActive;

   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedDate;

   @ManyToOne
   private SubCategory subCategoryId;

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
         return id.equals(((Product) that).id);
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

   public String getProductName()
   {
      return this.productName;
   }

   public void setProductName(final String productName)
   {
      this.productName = productName;
   }

   public String getDescription()
   {
      return this.description;
   }

   public void setDescription(final String description)
   {
      this.description = description;
   }

   public String getLongDescription()
   {
      return this.longDescription;
   }

   public void setLongDescription(final String longDescription)
   {
      this.longDescription = longDescription;
   }

   public String getImagePath()
   {
      return this.imagePath;
   }

   public void setImagePath(final String imagePath)
   {
      this.imagePath = imagePath;
   }

   public String getPDFPath()
   {
      return this.pDFPath;
   }

   public void setPDFPath(final String pDFPath)
   {
      this.pDFPath = pDFPath;
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

   public String getInstallationVideoPath()
   {
      return this.installationVideoPath;
   }

   public void setInstallationVideoPath(final String installationVideoPath)
   {
      this.installationVideoPath = installationVideoPath;
   }

   public String getBarcode()
   {
      return this.barcode;
   }

   public void setBarcode(final String barcode)
   {
      this.barcode = barcode;
   }

   public Byte getIsUpdate()
   {
      return this.isUpdate;
   }

   public void setIsUpdate(final Byte isUpdate)
   {
      this.isUpdate = isUpdate;
   }

   public Byte getIsActive()
   {
      return this.isActive;
   }

   public void setIsActive(final Byte isActive)
   {
      this.isActive = isActive;
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
      if (productName != null && !productName.trim().isEmpty())
         result += "productName: " + productName;
      if (description != null && !description.trim().isEmpty())
         result += ", description: " + description;
      if (longDescription != null && !longDescription.trim().isEmpty())
         result += ", longDescription: " + longDescription;
      if (imagePath != null && !imagePath.trim().isEmpty())
         result += ", imagePath: " + imagePath;
      if (pDFPath != null && !pDFPath.trim().isEmpty())
         result += ", pDFPath: " + pDFPath;
      if (refCol1 != null && !refCol1.trim().isEmpty())
         result += ", refCol1: " + refCol1;
      if (refCol2 != null && !refCol2.trim().isEmpty())
         result += ", refCol2: " + refCol2;
      if (installationVideoPath != null
            && !installationVideoPath.trim().isEmpty())
         result += ", installationVideoPath: " + installationVideoPath;
      if (barcode != null && !barcode.trim().isEmpty())
         result += ", barcode: " + barcode;
      if (isUpdate != null)
         result += ", isUpdate: " + isUpdate;
      if (isActive != null)
         result += ", isActive: " + isActive;
      return result;
   }

   public SubCategory getSubCategoryId()
   {
      return this.subCategoryId;
   }

   public void setSubCategoryId(final SubCategory subCategoryId)
   {
      this.subCategoryId = subCategoryId;
   }
}