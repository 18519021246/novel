package com.imnu.entity;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Author		//作家
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
   private String accountname ;		//账号
   public String getAccountname() 
   {
      return accountname;
  }
   public void setAccountname(String accountname) 
   {
      this.accountname= accountname;
  }
   private String password ;
   public String getPassword() 
   {
      return password;
  }
   public void setPassword(String password) 
   {
      this.password= password;
  }
   private String name ;
   public String getName() 
   {
      return name;
  }
   public void setName(String name) 
   {
      this.name= name;
  }
   private String sex ;
   public String getSex() 
   {
      return sex;
  }
   public void setSex(String sex) 
   {
      this.sex= sex;
  }
   private Date age ;		//出生年月
   public Date getAge() 
   {
      return age;
  }
   public void setAge(Date age) 
   {
      this.age= age;
  }
   private String jiguan ;
   public String getJiguan() 
   {
      return jiguan;
  }
   public void setJiguan(String jiguan) 
   {
      this.jiguan= jiguan;
  }
   private String nation ;
   public String getNation() 
   {
      return nation;
  }
   public void setNation(String nation) 
   {
      this.nation= nation;
  }
   private String techang ;
   public String getTechang() 
   {
      return techang;
  }
   public void setTechang(String techang) 
   {
      this.techang= techang;
  }
   private String mobile ;
   public String getMobile() 
   {
      return mobile;
  }
   public void setMobile(String mobile) 
   {
      this.mobile= mobile;
  }
   private String qq ;
   public String getQq() 
   {
      return qq;
  }
   public void setQq(String qq) 
   {
      this.qq= qq;
  }
   private String des ;		//备注
   public String getDes() 
   {
      return des;
  }
   public void setDes(String des) 
   {
      this.des= des;
  }
   private String photo ;
   public String getPhoto() 
   {
      return photo;
  }
   public void setPhoto(String photo) 
   {
      this.photo= photo;
  }
}
