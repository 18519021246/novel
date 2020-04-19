package com.imnu.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Airead
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
   private String accountname ;
   public String getAccountname() 
   {
      return accountname;
  }
   public void setAccountname(String accountname) 
   {
      this.accountname= accountname;
  }
   private String messageid ;
   public String getMessageid() 
   {
      return messageid;
  }
   public void setMessageid(String messageid) 
   {
      this.messageid= messageid;
  }
}
