package com.imnu.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Bookcontent
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
   private String dcontent ;
   public String getDcontent() 
   {
      return dcontent;
  }
   public void setDcontent(String dcontent) 
   {
      this.dcontent= dcontent;
  }
   private int bookid ;
   public int getBookid() 
   {
      return bookid;
  }
   public void setBookid(int bookid) 
   {
      this.bookid= bookid;
  }
}
