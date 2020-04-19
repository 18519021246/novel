package com.imnu.controller;
import java.util.List;
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
 * 阅读记录控制
 *
 */
 @Controller
public class AireadController extends  SimpleController{
     @Autowired
       private AireadService  aireadSrv=null;
       @Override
	@RequestMapping("/admin/aireadmanager.do")
	public void mapping(HttpServletRequest request, HttpServletResponse response) {
		mappingMethod(request,response);
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
		aireadSrv.delete(SQL);
	}
    /*************************************************************
     ****************保存动作监听支持******************************
    **************************************************************/
	public void save() {
			String forwardurl=request.getParameter("forwardurl");
			//验证错误url
			String errorurl=request.getParameter("errorurl");
				        String accountname=request.getParameter("accountname");
				        String messageid=request.getParameter("messageid");
	    SimpleDateFormat  sdfairead=new SimpleDateFormat("yyyy-MM-dd");
             Airead airead=new Airead();
				        airead.setAccountname(accountname==null?"":accountname);
				        airead.setMessageid(messageid==null?"":messageid);
		  aireadSrv.save(airead);
		 if(forwardurl==null){
			forwardurl="/admin/aireadmanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}
   	/******************************************************
	***********************更新内部支持*********************
	*******************************************************/
	public void update() {
           String forwardurl=request.getParameter("forwardurl");
		   String id=request.getParameter("id");
		   if(id==null)
			   return;
		   Airead  airead=aireadSrv.load(new Integer(id));
		    if(airead==null)
			   return;
				       String accountname=request.getParameter("accountname");
				       String messageid=request.getParameter("messageid");
			  SimpleDateFormat  sdfairead=new SimpleDateFormat("yyyy-MM-dd");
					        airead.setAccountname(accountname);
					        airead.setMessageid(messageid);
		    aireadSrv.update(airead);
			 if(forwardurl==null){
				forwardurl="/admin/aireadmanager.do?actiontype=get";
			}
			redirect(forwardurl);
	}
   	/******************************************************
	***********************加载内部支持*********************
	*******************************************************/
	public void load() {
	        //
		    String id=request.getParameter("id");
		    String actiontype="save";
		    dispatchParams(request, response);
			if(id!=null) 
			{
			   Airead  airead=aireadSrv.load("where id="+id);
				if(airead!=null) 
				{
				    request.setAttribute("airead", airead);
				}
				actiontype="update";
				request.setAttribute("id", id);
			}
		    request.setAttribute("actiontype", actiontype);
		    String forwardurl=request.getParameter("forwardurl");
		    System.out.println("forwardurl="+forwardurl);
		    if(forwardurl==null){
		     	forwardurl="/admin/aireadadd.jsp";
		   }
		   forward(forwardurl);
	}
   	/******************************************************
	***********************数据绑定内部支持*********************
	*******************************************************/
	public void   get(){
		 String filter="where 1=1 ";
String accountname=request.getParameter("accountname");
	              if(accountname!=null)
	                     filter+="  and accountname like '%"+accountname+"%'  ";
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
		List<Airead> listairead = aireadSrv.getPageEntitys( filter,pageindex, pagesize);
		int recordscount = aireadSrv.getRecordCount(filter == null ? "" : filter);
		request.setAttribute("listairead", listairead);
		PagerMetal pm = new PagerMetal(recordscount);
		//设置尺寸
		pm.setPagesize(pagesize);
		//设置当前显示页
		pm.setCurpageindex(pageindex);
		// 设置分页信息
		request.setAttribute("pagermetal", pm);
		//分发请求参数
		dispatchParams(request, response);
		String forwardurl=request.getParameter("forwardurl");
		System.out.println("forwardurl="+forwardurl);
		if(forwardurl==null){
			forwardurl="/admin/aireadmanager.jsp";
		}
forward(forwardurl);
	}
}
