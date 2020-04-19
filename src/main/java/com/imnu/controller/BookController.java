package com.imnu.controller;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daowen.entity.*;
import com.daowen.service.*;
import com.daowen.ssm.simplecrud.SimpleController;
import com.daowen.webcontrol.PagerMetal;

/**************************
 *
 * 书籍控制
 *
 */
@Controller
public class BookController extends SimpleController {
    @Autowired
    private BookService bookSrv = null;

    @Autowired
    private SpcategoryService spcategorySrv = null;


    @Override
    @RequestMapping("/admin/bookmanager.do")
    public void mapping(HttpServletRequest request, HttpServletResponse response) {
        mappingMethod(request, response);
    }


    private void agree() {
        String id=request.getParameter("id");
        if (id == null)
            return;
        Book book = bookSrv.load("where id="+id);
        if (book == null)
            return;
        book.setAgreecount(book.getAgreecount()+1);
        bookSrv.update(book);
        try {
            response.getWriter().write(new Integer(book.getAgreecount()).toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    private void against() {
        String id=request.getParameter("id");
        if (id == null)
            return;
        Book book = bookSrv.load("where id="+id);
        if (book == null)
            return;
        book.setAgainstcount(book.getAgainstcount()+1);
        bookSrv.update(book);
        try {
            response.getWriter().write(new Integer(book.getAgainstcount()).toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    /********************************************************
     ******************信息注销监听支持*****************************
     *********************************************************/
    public void delete() {
        String[] ids = request.getParameterValues("ids");
        if (ids == null)
            return;
        String spliter = ",";
        String SQL = " where id in(" + join(spliter, ids)
                + ")";
        System.out.println("sql=" + SQL);
        bookSrv.delete(SQL);
    }

    public void over(){

       String id = request.getParameter("id");
       if (id == null)
           return;
        Book book = bookSrv.load("where id="+id);
        if (book == null)
            return;
        book.setStatus(2);
        bookSrv.update(book);



    }

    /*************************************************************
     ****************保存动作监听支持******************************
     **************************************************************/
    public void save() {
        String forwardurl = request.getParameter("forwardurl");
        // 验证错误url
        String errorurl = request.getParameter("errorurl");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String ydtype=request.getParameter("ydtype");
        String pubtime = request.getParameter("pubtime");
        String xtype = request.getParameter("xtype");
        String typeid=request.getParameter("typeid");
        String authorname = request.getParameter("authorname");
        String tuijian = request.getParameter("tuijian");
        String zuixin = request.getParameter("zuixin");
        String hot = request.getParameter("hot");
        String tupian = request.getParameter("tupian");
        String dwfee=request.getParameter("dwfee");
        String bkeyword = request.getParameter("bkeyword");
        String fileurl=request.getParameter("fileurl");
        String des = request.getParameter("des");
        SimpleDateFormat sdfbook = new SimpleDateFormat("yyyy-MM-dd");
        Book book = new Book();
        book.setName(name == null ? "" : name);
        book.setYdtype(ydtype==null?0:Integer.parseInt(ydtype));
        book.setAuthor(author == null ? "" : author);
        book.setAuthorname(authorname);
        book.setPubtime(new Date());
        book.setTypeid(typeid==null?0:Integer.parseInt(typeid));
        book.setXtype(xtype == null ? "" : xtype);
        book.setHot(hot != null ? 1 : 0);
        book.setTuijian(tuijian != null ? 1 : 0);
        book.setZuixin(zuixin != null ? 1 : 0);
        book.setClickcount(0);
        book.setReadcount(0);
        //书籍连载中
        book.setStatus(1);
        book.setDwfee(dwfee==null?0:Integer.parseInt(dwfee));
        book.setTupian(tupian == null ? "" : tupian);
        book.setBkeyword(bkeyword == null ? "" : bkeyword);
        book.setFileurl(fileurl==null?"":fileurl);
        book.setDes(des == null ? "" : des);
        bookSrv.save(book);
        if (forwardurl == null) {
            forwardurl = "/admin/bookmanager.do?actiontype=get";
        }
        redirect(forwardurl);
    }

    /******************************************************
     ***********************更新内部支持*********************
     *******************************************************/
    public void update() {
        String forwardurl = request.getParameter("forwardurl");
        String id = request.getParameter("id");
        if (id == null)
            return;
        Book book = bookSrv.load("where id="+id);
        if (book == null)
            return;
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String pubtime = request.getParameter("pubtime");
        String xtype = request.getParameter("xtype");
        String typeid=request.getParameter("typeid");
        String status = request.getParameter("status");
        String tuijian = request.getParameter("tuijian");
        String zuixin = request.getParameter("zuixin");
        String ydtype=request.getParameter("ydtype");
        String hot = request.getParameter("hot");
        String tupian = request.getParameter("tupian");
        String fileurl=request.getParameter("fileurl");
        String bkeyword = request.getParameter("bkeyword");
        String des = request.getParameter("des");
        SimpleDateFormat sdfbook = new SimpleDateFormat("yyyy-MM-dd");
        book.setName(name);
        book.setAuthor(author);
        book.setYdtype(ydtype==null?0:Integer.parseInt(ydtype));
        book.setXtype(xtype);
        book.setTypeid(typeid==null?0:Integer.parseInt(typeid));
        book.setTuijian(tuijian == null ? 0 : new Integer(tuijian));
        book.setZuixin(zuixin == null ? 0 : new Integer(zuixin));
        book.setHot(hot == null ? 0 : new Integer(hot));
        book.setFileurl(fileurl==null?"":fileurl);
        book.setTupian(tupian);
        book.setBkeyword(bkeyword);
        book.setDes(des);
        bookSrv.update(book);
        if (forwardurl == null) {
            forwardurl = "/admin/bookmanager.do?actiontype=get";
        }
        redirect(forwardurl);
    }

    /******************************************************
     ***********************加载内部支持*********************
     *******************************************************/
    public void load() {
        //
        String id = request.getParameter("id");
        String actiontype = "save";
        dispatchParams(request, response);
        if (id != null) {
            Book book = bookSrv.load("where id=" + id);
            if (book != null) {
                request.setAttribute("book", book);
            }
            actiontype = "update";
            request.setAttribute("id", id);
        }
        request.setAttribute("actiontype", actiontype);

        List<Object> typeid_datasource = spcategorySrv.getEntity("");
        request.setAttribute("typeid_datasource", typeid_datasource);
        String forwardurl = request.getParameter("forwardurl");
        System.out.println("forwardurl=" + forwardurl);
        if (forwardurl == null) {
            forwardurl = "/admin/bookadd.jsp";
        }
        forward(forwardurl);
    }

    /******************************************************
     ***********************数据绑定内部支持*********************
     *******************************************************/
    public void get() {
        String filter = "where 1=1 ";
        String name = request.getParameter("name");
        String pubren=request.getParameter("pubren");
        if (name!= null)
            filter += "  and name like '%" + name + "%'  ";
        //
        if(pubren!=null)
            filter+=" and author='"+pubren+"'";

        int pageindex = 1;
        int pagesize = 10;
        // 获取当前分页
        String currentpageindex = request.getParameter("currentpageindex");
        // 当前页面尺寸
        String currentpagesize = request.getParameter("pagesize");
        // 设置当前页
        if (currentpageindex != null)
            pageindex = new Integer(currentpageindex);
        // 设置当前页尺寸
        if (currentpagesize != null)
            pagesize = new Integer(currentpagesize);
        List<Book> listbook = bookSrv.getPageEntitys(filter+" order by id desc ", pageindex, pagesize);
        int recordscount = bookSrv.getRecordCount(filter == null ? "" : filter);
        request.setAttribute("listbook", listbook);
        PagerMetal pm = new PagerMetal(recordscount);
        //设置尺寸
        pm.setPagesize(pagesize);
        //设置当前显示页
        pm.setCurpageindex(pageindex);
        // 设置分页信息
        request.setAttribute("pagermetal", pm);
        //分发请求参数
        dispatchParams(request, response);
        String forwardurl = request.getParameter("forwardurl");
        System.out.println("forwardurl=" + forwardurl);
        if (forwardurl == null) {
            forwardurl = "/admin/bookmanager.jsp";
        }
        forward(forwardurl);
    }


}
