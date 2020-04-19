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
 * 账单流水控制
 *
 */
 @Controller
public class BillrecordController extends  SimpleController{
     @Autowired
       private BillrecordService  billrecordSrv=null;
       @Override
	@RequestMapping("/admin/billrecordmanager.do")
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
		billrecordSrv.delete(SQL);
	}
    /*************************************************************
     ****************保存动作监听支持******************************
    **************************************************************/
	public void save() {
			String forwardurl=request.getParameter("forwardurl");
			//验证错误url
			String errorurl=request.getParameter("errorurl");
				        String accountname=request.getParameter("accountname");
				        String name=request.getParameter("name");
				        String fee=request.getParameter("fee");
				        String createtime=request.getParameter("createtime");
				        String xtype=request.getParameter("xtype");
				        String operdes=request.getParameter("operdes");
				        String zyid=request.getParameter("zyid");
				        String zytitle=request.getParameter("zytitle");
	    SimpleDateFormat  sdfbillrecord=new SimpleDateFormat("yyyy-MM-dd");
             Billrecord billrecord=new Billrecord();
				        billrecord.setAccountname(accountname==null?"":accountname);
				        billrecord.setName(name==null?"":name);
				       billrecord.setFee(fee==null?0:new Integer(fee));
					          if(createtime!=null)
					          {
							        try {
										billrecord.setCreatetime(sdfbillrecord.parse(createtime));
									} catch (ParseException e) {
										e.printStackTrace();
									}
								}else{
								   billrecord.setCreatetime(new Date());
								}
				       billrecord.setXtype(xtype==null?0:new Integer(xtype));
				        billrecord.setOperdes(operdes==null?"":operdes);
				       billrecord.setZyid(zyid==null?0:new Integer(zyid));
				        billrecord.setZytitle(zytitle==null?"":zytitle);
		  billrecordSrv.save(billrecord);
		 if(forwardurl==null){
			forwardurl="/admin/billrecordmanager.do?actiontype=get";
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
		   Billrecord  billrecord=billrecordSrv.load(new Integer(id));
		    if(billrecord==null)
			   return;
				       String accountname=request.getParameter("accountname");
				       String name=request.getParameter("name");
				       String fee=request.getParameter("fee");
				       String createtime=request.getParameter("createtime");
				       String xtype=request.getParameter("xtype");
				       String operdes=request.getParameter("operdes");
				       String zyid=request.getParameter("zyid");
				       String zytitle=request.getParameter("zytitle");
			  SimpleDateFormat  sdfbillrecord=new SimpleDateFormat("yyyy-MM-dd");
					        billrecord.setAccountname(accountname);
					        billrecord.setName(name);
					        billrecord.setFee(fee==null?0:new Integer(fee));
					           if(createtime!=null)
					           {
							        try {
										billrecord.setCreatetime(sdfbillrecord.parse(createtime));
									} catch (ParseException e) {
										e.printStackTrace();
									}
								}
					        billrecord.setXtype(xtype==null?0:new Integer(xtype));
					        billrecord.setOperdes(operdes);
					        billrecord.setZyid(zyid==null?0:new Integer(zyid));
					        billrecord.setZytitle(zytitle);
		    billrecordSrv.update(billrecord);
			 if(forwardurl==null){
				forwardurl="/admin/billrecordmanager.do?actiontype=get";
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
			   Billrecord  billrecord=billrecordSrv.load("where id="+id);
				if(billrecord!=null) 
				{
				    request.setAttribute("billrecord", billrecord);
				}
				actiontype="update";
				request.setAttribute("id", id);
			}
		    request.setAttribute("actiontype", actiontype);
		    String forwardurl=request.getParameter("forwardurl");
		    System.out.println("forwardurl="+forwardurl);
		    if(forwardurl==null){
		     	forwardurl="/admin/billrecordadd.jsp";
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
		List<Billrecord> listbillrecord = billrecordSrv.getPageEntitys( filter,pageindex, pagesize);
		int recordscount = billrecordSrv.getRecordCount(filter == null ? "" : filter);
		request.setAttribute("listbillrecord", listbillrecord);
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
			forwardurl="/admin/billrecordmanager.jsp";
		}
forward(forwardurl);
	}
}
