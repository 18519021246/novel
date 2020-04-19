package com.imnu.controller;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imnu.entity.Aimessage;
import com.imnu.service.AimessageService;
import com.daowen.ssm.simplecrud.SimpleController;
import com.daowen.webcontrol.PagerMetal;

/**************************
 * 
 * 消息提醒控制
 *
 */
@Controller
public class AimessageController extends SimpleController {
	@Autowired
	private AimessageService aimessageSrv = null;

	@Override
	@RequestMapping("/admin/aimessagemanager.do")
	public void mapping(HttpServletRequest request, HttpServletResponse response) {
		mappingMethod(request, response);
	}

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String[] ids = request.getParameterValues("ids");
		if (ids == null)
			return;
		String spliter = ",";
		String SQL = " where id in(" + join(spliter, ids) + ")";
		System.out.println("sql=" + SQL);
		aimessageSrv.delete(SQL);
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		// 验证错误url
		String errorurl = request.getParameter("errorurl");
		String title = request.getParameter("title");
		String pubren = request.getParameter("pubren");
		String pubtime = request.getParameter("pubtime");
		String content = request.getParameter("content");
		SimpleDateFormat sdfaimessage = new SimpleDateFormat("yyyy-MM-dd");
		Aimessage aimessage = new Aimessage();
		aimessage.setTitle(title == null ? "" : title);
		aimessage.setPubren(pubren == null ? "" : pubren);
		if (pubtime != null) {
			try {
				aimessage.setPubtime(sdfaimessage.parse(pubtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			aimessage.setPubtime(new Date());
		}
		aimessage.setContent(content == null ? "" : content);
		aimessageSrv.save(aimessage);
		if (forwardurl == null) {
			forwardurl = "/admin/aimessagemanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}

	/******************************************************
	 *********************** 更新内部支持*********************
	 *******************************************************/
	public void update() {
		String forwardurl = request.getParameter("forwardurl");
		String id = request.getParameter("id");
		if (id == null)
			return;
		Aimessage aimessage = aimessageSrv.load(new Integer(id));
		if (aimessage == null)
			return;
		String title = request.getParameter("title");
		String pubren = request.getParameter("pubren");
		String pubtime = request.getParameter("pubtime");
		String content = request.getParameter("content");
		SimpleDateFormat sdfaimessage = new SimpleDateFormat("yyyy-MM-dd");
		aimessage.setTitle(title);
		aimessage.setPubren(pubren);
		
		aimessage.setPubtime(new Date());
		
		aimessage.setContent(content);
		aimessageSrv.update(aimessage);
		if (forwardurl == null) {
			forwardurl = "/admin/aimessagemanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}

	/******************************************************
	 *********************** 加载内部支持*********************
	 *******************************************************/
	public void load() {
		//
		String id = request.getParameter("id");
		String actiontype = "save";
		dispatchParams(request, response);
		if (id != null) {
			Aimessage aimessage = aimessageSrv.load("where id=" + id);
			if (aimessage != null) {
				request.setAttribute("aimessage", aimessage);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/aimessageadd.jsp";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
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
		List<Aimessage> listaimessage = aimessageSrv.getPageEntitys(filter,
				pageindex, pagesize);
		int recordscount = aimessageSrv.getRecordCount(filter == null ? ""
				: filter);
		request.setAttribute("listaimessage", listaimessage);
		PagerMetal pm = new PagerMetal(recordscount);
		// 设置尺寸
		pm.setPagesize(pagesize);
		// 设置当前显示页
		pm.setCurpageindex(pageindex);
		// 设置分页信息
		request.setAttribute("pagermetal", pm);
		// 分发请求参数
		dispatchParams(request, response);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/aimessagemanager.jsp";
		}
		forward(forwardurl);
	}
	
public void getMessageBytype() {
		
		String filter = "  select * from aimessage aim  where 1=1 ";
		String title = request.getParameter("title");
		String type=request.getParameter("type");
		String accountname=request.getParameter("accountname");
		if (title != null)
			filter += "  and title like '%" + title + "%'  ";
		//
		if(accountname!=null){
			if(type.equals("1"))
			    filter+=MessageFormat.format(" and  NOT EXISTS(select *  from airead air where   air.accountname=''{0}'' and air.messageid=aim.id) ",accountname);
			if(type.equals("2"))
			    filter+=MessageFormat.format(" and   EXISTS(select *  from airead air where   air.accountname=''{0}'' and air.messageid=aim.id) ",accountname);
			
		}
			
		
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
		List<Aimessage> listaimessage = aimessageSrv.query(filter);
		request.setAttribute("listaimessage", listaimessage);
		
		// 分发请求参数
		dispatchParams(request, response);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/aimessagemanager.jsp";
		}
		forward(forwardurl);
	}
	
	
}
