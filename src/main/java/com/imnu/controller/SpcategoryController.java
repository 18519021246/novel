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
 * 小说类别控制
 *
 */
@Controller
public class SpcategoryController extends SimpleController {
    @Autowired
    private SpcategoryService spcategorySrv = null;

    @Override
    @RequestMapping("/admin/spcategorymanager.do")
    public void mapping(HttpServletRequest request, HttpServletResponse response) {
        mappingMethod(request, response);
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
        spcategorySrv.delete(SQL);
    }

    /*************************************************************
     ****************保存动作监听支持******************************
     **************************************************************/
    public void save() {
        String forwardurl = request.getParameter("forwardurl");
        //验证错误url
        String errorurl = request.getParameter("errorurl");
        //获取名称
        String mingcheng = request.getParameter("mingcheng");
        //获取是否叶子节点
        String isleaf = request.getParameter("isleaf");
        //获取父节点
        String parentid = request.getParameter("parentid");
        SimpleDateFormat sdfspcategory = new SimpleDateFormat("yyyy-MM-dd");
        Spcategory spcategory = new Spcategory();
        // 设置名称
        spcategory.setMingcheng(mingcheng == null ? "" : mingcheng);
        // 设置是否叶子节点
        spcategory.setIsleaf(isleaf == null ? 0 : new Integer(isleaf));
        // 设置父节点
        spcategory.setParentid(parentid == null ? 0 : new Integer(parentid));
        spcategorySrv.save(spcategory);
        if (forwardurl == null) {
            forwardurl = "/admin/spcategorymanager.do?actiontype=get";
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
        Spcategory spcategory = spcategorySrv.load(new Integer(id));
        if (spcategory == null)
            return;
        //获取名称
        String mingcheng = request.getParameter("mingcheng");
        //获取是否叶子节点
        String isleaf = request.getParameter("isleaf");
        //获取父节点
        String parentid = request.getParameter("parentid");
        SimpleDateFormat sdfspcategory = new SimpleDateFormat("yyyy-MM-dd");
// 设置名称
        spcategory.setMingcheng(mingcheng);
        // 设置是否叶子节点
        spcategory.setIsleaf(isleaf == null ? 0 : new Integer(isleaf));
        // 设置父节点
        spcategory.setParentid(parentid == null ? 0 : new Integer(parentid));
        spcategorySrv.update(spcategory);
        if (forwardurl == null) {
            forwardurl = "/admin/spcategorymanager.do?actiontype=get";
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
            Spcategory spcategory = spcategorySrv.load("where id=" + id);
            if (spcategory != null) {
                request.setAttribute("spcategory", spcategory);
            }
            actiontype = "update";
            request.setAttribute("id", id);
        }
        request.setAttribute("actiontype", actiontype);
        String forwardurl = request.getParameter("forwardurl");
        System.out.println("forwardurl=" + forwardurl);
        if (forwardurl == null) {
            forwardurl = "/admin/spcategoryadd.jsp";
        }
        forward(forwardurl);
    }

    /******************************************************
     ***********************数据绑定内部支持*********************
     *******************************************************/
    public void get() {
        String filter = "where 1=1 ";
        String mingcheng = request.getParameter("mingcheng");
        if (mingcheng != null)
            filter += "  and mingcheng like '%" + mingcheng + "%'  ";
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
        List<Spcategory> listspcategory = spcategorySrv.getPageEntitys(filter, pageindex, pagesize);
        int recordscount = spcategorySrv.getRecordCount(filter == null ? "" : filter);
        request.setAttribute("listspcategory", listspcategory);
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
            forwardurl = "/admin/spcategorymanager.jsp";
        }
        forward(forwardurl);
    }


}
