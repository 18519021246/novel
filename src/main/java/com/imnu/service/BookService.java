package com.imnu.service;

import com.daowen.entity.*;
import com.daowen.mapper.BookMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookService extends SimpleBizservice<BookMapper> implements  IDownloadService,IPlayService{


    @Autowired
    private HuiyuanService huiyuanSrv=null;

    @Autowired
    private BillrecordService billrecordSrv=null;
    //通过类别获取书籍信息
    public List<Book> findBook(String typeId){

          String sql= MessageFormat.format("select * from  book where typeid={0} or typeid in (select id from spcategory where parentid={0}) ",typeId);
          return query(sql);
    }

    public List<Book> findBook(int bookId){
        return findBook(String.valueOf(bookId));
    }


    public List<Book> findBook(String typeId,int pageIndex,int pageSize){

        String sql= MessageFormat.format("select * from  book where typeid={0} or typeid in (select id from spcategory where parentid={0}) limit ({0}-1)*{1}+1,{1} ",typeId,pageIndex,pageSize);
        return query(sql);
    }




    public List<Book> findBook(String typeId,int count){
       String sql= MessageFormat.format("select * from  book where typeid={0} or typeid in (select id from spcategory where parentid={0}) limit {1} ",typeId,count);
       return query(sql);

    }


    @Override
    public Downrecord buildRecord(int id) {
        Book book= load(" where id="+id);
        if(book==null)
            return null;

        Downrecord dc=new Downrecord();
        dc.setCreatetime(new Date());
        dc.setZyid(id);
        dc.setZytitle(book.getName());
        return dc;
    }

    @Override
    public int afteredDownload(int id, boolean result) {
        return 0;
    }

    @Override
    public int playOver(int id) {
        return this.executeUpdate("update book set readcount=readcount+1 where id="+id);
    }

    @Override
    public boolean prePlay(int id, String accountname) {
        Huiyuan huiyuan=huiyuanSrv.load("where accountname='"+accountname+"'");
        Book book=this.load("where id="+id);


        if(book==null||huiyuan==null)
            return false;

        if(book.getDwfee()>0) {

            boolean hasIn=billrecordSrv.isExist(MessageFormat.format("where accountname=''{0}'' and zyid={1}",huiyuan.getAccountname(),book.getId()));
            if(hasIn)
                return true;
        }

        if(book.getDwfee()>huiyuan.getYue())
            return false;

        huiyuan.setYue(huiyuan.getYue()-book.getDwfee());
        huiyuanSrv.update(huiyuan);
        Billrecord bc=new Billrecord();
        bc.setAccountname(huiyuan.getAccountname());
        bc.setName(huiyuan.getName());
        bc.setCreatetime(new Date());
        bc.setZyid(book.getId());
        bc.setFee(book.getDwfee());
        bc.setZytitle(book.getName());
        bc.setXtype(2);
        bc.setOperdes("阅读扣款");
        billrecordSrv.save(bc);
        return true;
    }
}
