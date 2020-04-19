package com.imnu.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Fans
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
   private String staraccount ;
   public String getStaraccount() 
   {
      return staraccount;
  }
   public void setStaraccount(String staraccount) 
   {
      this.staraccount= staraccount;
  }
   private String fansaccount ;
   public String getFansaccount() 
   {
      return fansaccount;
  }
   public void setFansaccount(String fansaccount) 
   {
      this.fansaccount= fansaccount;
  }
   private Date createtime ;
   public Date getCreatetime() 
   {
      return createtime;
  }
   public void setCreatetime(Date createtime) 
   {
      this.createtime= createtime;
  }
}
