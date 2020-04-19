package com.imnu.controller;

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
 * 小说内容控制
 *
 */
@Controller
public class BookcontentController extends SimpleController {
    @Autowired
    private BookcontentService bookcontentSrv = null;

    @Autowired
    private BookService  bookSrv=null;

    @Override
    @RequestMapping("/admin/bookcontentmanager.do")
    public void mapping(HttpServletRequest request, HttpServletResponse response) {
        mappingMethod(request, response);
    }

    /********************************************************
     ******************信息注销监听支持*****************************
     *********************************************************/
    public void delete() {
        String id = request.getParameter("id");
        if (id == null)
            return;
        String sql = " where id="+id;
        bookcontentSrv.delete(sql);
    }

    /*************************************************************
     ****************保存动作监听支持******************************
     **************************************************************/
    public void save() {
        String forwardurl = request.getParameter("forwardurl");
        //验证错误url
        String errorurl = request.getParameter("errorurl");
        //获取章节
        String title = request.getParameter("title");
        //获取内容
        String dcontent = request.getParameter("dcontent");
        //获取书籍
        String bookid = request.getParameter("bookid");
        SimpleDateFormat sdfbookcontent = new SimpleDateFormat("yyyy-MM-dd");
        Bookcontent bookcontent = new Bookcontent();
        // 设置章节
        bookcontent.setTitle(title == null ? "" : title);
        // 设置内容
        bookcontent.setDcontent(dcontent == null ? "" : dcontent);
        // 设置书籍
        bookcontent.setBookid(bookid == null ? 0 : new Integer(bookid));
        bookcontentSrv.save(bookcontent);
        if (forwardurl == null) {
            forwardurl = "/admin/bookcontentmanager.do?actiontype=get";
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
        Bookcontent bookcontent = bookcontentSrv.load(new Integer(id));
        if (bookcontent == null)
            return;
        //获取章节
        String title = request.getParameter("title");
        //获取内容
        String dcontent = request.getParameter("dcontent");
        //获取书籍
        String bookid = request.getParameter("bookid");
        SimpleDateFormat sdfbookcontent = new SimpleDateFormat("yyyy-MM-dd");
// 设置章节
        bookcontent.setTitle(title);
        // 设置内容
        bookcontent.setDcontent(dcontent);
        // 设置书籍
        bookcontent.setBookid(bookid == null ? 0 : new Integer(bookid));
        bookcontentSrv.update(bookcontent);
        if (forwardurl == null) {
            forwardurl = "/admin/bookcontentmanager.do?actiontype=get";
        }
        redirect(forwardurl);
    }

    /******************************************************
     ***********************加载内部支持*********************
     *******************************************************/
    public void load() {
        //
        String id = request.getParameter("id");
        String bookid=request.getParameter("bookid");
        String actiontype = "save";
        dispatchParams(request, response);
        if (id != null) {
            Bookcontent bookcontent = bookcontentSrv.load("where id=" + id);
            if (bookcontent != null) {
                request.setAttribute("bookcontent", bookcontent);
            }
            actiontype = "update";
            request.setAttribute("id", id);
        }
        if(bookid!=null){
            Book book=bookSrv.load("where id="+bookid);
            if(book!=null)
                request.setAttribute("book",book);

        }

        request.setAttribute("actiontype", actiontype);
        String forwardurl = request.getParameter("forwardurl");
        System.out.println("forwardurl=" + forwardurl);
        if (forwardurl == null) {
            forwardurl = "/admin/bookcontentadd.jsp";
        }
        forward(forwardurl);
    }

    /******************************************************
     ***********************数据绑定内部支持*********************
     *******************************************************/
    public void get() {
        String filter = "where 1=1 ";
        String title = request.getParameter("title");
        if (title != null)
            filter += "  and title like '%" + title + "%'  ";
        //
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
        List<Bookcontent> listbookcontent = bookcontentSrv.getPageEntitys(filter, pageindex, pagesize);
        int recordscount = bookcontentSrv.getRecordCount(filter == null ? "" : filter);
        request.setAttribute("listbookcontent", listbookcontent);
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
            forwardurl = "/admin/bookcontentmanager.jsp";
        }
        forward(forwardurl);
    }

    public void query() {
        String sql = " select * from bookcontent    ";
        List<HashMap<String, Object>> listBookcontent = bookcontentSrv.queryToMap(sql);
        request.setAttribute("listBookcontent", listBookcontent);
        // 分发请求参数
        dispatchParams(request, response);
        String forwardurl = request.getParameter("forwardurl");
        System.out.println("forwardurl=" + forwardurl);
        if (forwardurl == null) {
            forwardurl = "/admin/bookcontentmanager.jsp";
        }
        forward(forwardurl);
    }
}
