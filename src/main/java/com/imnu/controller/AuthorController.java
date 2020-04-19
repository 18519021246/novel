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
 * 作家控制
 *
 */
@Controller
public class AuthorController extends SimpleController {
    @Autowired
    private AuthorService authorSrv = null;

    @Override
    @RequestMapping("/admin/authormanager.do")		//请求路径
    public void mapping(HttpServletRequest request, HttpServletResponse response) {
        mappingMethod(request, response);
    }

    public void modifyPw() {
        String password1 = request.getParameter("password1");
        String repassword1 = request.getParameter("repassword1");
        String repassword2 = request.getParameter("repassword2");
        String forwardurl = request.getParameter("forwardurl");
        String errorpageurl = request.getParameter("errorpageurl");
        String id = request.getParameter("id");
        if (id == null || id == "")
            return;
        Author author = authorSrv.load(new Integer(id));
        if (author != null) {
            if (!author.getPassword().equals(password1)) {
                request.setAttribute("errormsg", "<label class='error'>原始密码不正确，不能修改</label>");
                forward(errorpageurl);
            } else {
                author.setPassword(repassword1);
                authorSrv.update(author);
                request.getSession().setAttribute("author", author);
                if (forwardurl != null)
                    forward(forwardurl);
            }
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
        authorSrv.delete(SQL);
    }

    /*************************************************************
     ****************保存动作监听支持******************************
     **************************************************************/
    public void save() {
        String forwardurl = request.getParameter("forwardurl");
        //验证错误url
        String errorurl = request.getParameter("errorurl");
        //获取账户名
        String accountname = request.getParameter("accountname");
        //获取登陆密码
        String password = request.getParameter("password");
        //获取姓名
        String name = request.getParameter("name");
        //获取性别
        String sex = request.getParameter("sex");
        //获取出生年月
        String age = request.getParameter("age");
        //获取籍贯
        String jiguan = request.getParameter("jiguan");
        //获取民族
        String nation = request.getParameter("nation");
        //获取特长
        String techang = request.getParameter("techang");
        //获取电话
        String mobile = request.getParameter("mobile");
        //获取qq
        String qq = request.getParameter("qq");
        //获取备注
        String des = request.getParameter("des");
        //获取相片
        String photo = request.getParameter("photo");
        SimpleDateFormat sdfauthor = new SimpleDateFormat("yyyy-MM-dd");
        Author author = new Author();
        // 设置账户名
        author.setAccountname(accountname == null ? "" : accountname);
        // 设置登陆密码
        author.setPassword(password == null ? "" : password);
        // 设置姓名
        author.setName(name == null ? "" : name);
        // 设置性别
        author.setSex(sex == null ? "" : sex);
        // 设置出生年月
        if (age != null) {
            try {
                author.setAge(sdfauthor.parse(age));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            author.setAge(new Date());
        }
        // 设置籍贯
        author.setJiguan(jiguan == null ? "" : jiguan);
        // 设置民族
        author.setNation(nation == null ? "" : nation);
        // 设置特长
        author.setTechang(techang == null ? "" : techang);
        // 设置电话
        author.setMobile(mobile == null ? "" : mobile);
        // 设置qq
        author.setQq(qq == null ? "" : qq);
        // 设置备注
        author.setDes(des == null ? "" : des);
        // 设置相片
        author.setPhoto(photo == null ? "" : photo);
        authorSrv.save(author);
        if (forwardurl == null) {
            forwardurl = "/admin/authormanager.do?actiontype=get";
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
        Author author = authorSrv.load(new Integer(id));
        if (author == null)
            return;
        //获取账户名
        String accountname = request.getParameter("accountname");
        //获取登陆密码
        String password = request.getParameter("password");
        //获取姓名
        String name = request.getParameter("name");
        //获取性别
        String sex = request.getParameter("sex");
        //获取出生年月
        String age = request.getParameter("age");
        //获取籍贯
        String jiguan = request.getParameter("jiguan");
        //获取民族
        String nation = request.getParameter("nation");
        //获取特长
        String techang = request.getParameter("techang");
        //获取电话
        String mobile = request.getParameter("mobile");
        //获取qq
        String qq = request.getParameter("qq");
        //获取备注
        String des = request.getParameter("des");
        //获取相片
        String photo = request.getParameter("photo");
        SimpleDateFormat sdfauthor = new SimpleDateFormat("yyyy-MM-dd");
// 设置账户名
        author.setAccountname(accountname);
        // 设置登陆密码
        author.setPassword(password);
        // 设置姓名
        author.setName(name);
        // 设置性别
        author.setSex(sex);
        // 设置出生年月
        if (age != null) {
            try {
                author.setAge(sdfauthor.parse(age));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // 设置籍贯
        author.setJiguan(jiguan);
        // 设置民族
        author.setNation(nation);
        // 设置特长
        author.setTechang(techang);
        // 设置电话
        author.setMobile(mobile);
        // 设置qq
        author.setQq(qq);
        // 设置备注
        author.setDes(des);
        // 设置相片
        author.setPhoto(photo);
        authorSrv.update(author);
        if (forwardurl == null) {
            forwardurl = "/admin/authormanager.do?actiontype=get";
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
            Author author = authorSrv.load("where id=" + id);
            if (author != null) {
                request.setAttribute("author", author);
            }
            actiontype = "update";
            request.setAttribute("id", id);
        }
        request.setAttribute("actiontype", actiontype);
        String forwardurl = request.getParameter("forwardurl");
        System.out.println("forwardurl=" + forwardurl);
        if (forwardurl == null) {
            forwardurl = "/admin/authoradd.jsp";		//映射的界面路径
        }
        forward(forwardurl);
    }

    /******************************************************
     ***********************数据绑定内部支持*********************
     *******************************************************/
    public void get() {
        String filter = "where 1=1 ";
        String accountname = request.getParameter("accountname");
        if (accountname != null)
            filter += "  and accountname like '%" + accountname + "%'  ";
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
        List<Author> listauthor = authorSrv.getPageEntitys(filter, pageindex, pagesize);
        int recordscount = authorSrv.getRecordCount(filter == null ? "" : filter);
        request.setAttribute("listauthor", listauthor);
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
            forwardurl = "/admin/authormanager.jsp";
        }
        forward(forwardurl);
    }


}
