package com.imnu.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Aimessage
{
@Id
@GeneratedValue(strategy =GenerationType.AUTO)
   private int id ;
   public int getId() 
   {
      return id;
  }
   public void setId(int id) 
   {
      this.id= id;
  }
   private String title ;
   public String getTitle() 
   {
      return title;
  }
   public void setTitle(String title) 
   {
      this.title= title;
  }
   private String pubren ;
   public String getPubren() 
   {
      return pubren;
  }
   public void setPubren(String pubren) 
   {
      this.pubren= pubren;
  }
   private Date pubtime ;
   public Date getPubtime() 
   {
      return pubtime;
  }
   public void setPubtime(Date pubtime) 
   {
      this.pubtime= pubtime;
  }
   private String content ;
   public String getContent() 
   {
      return content;
  }
   public void setContent(String content) 
   {
      this.content= content;
  }
}
