package com.imnu.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daowen.entity.Book;
import com.daowen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daowen.entity.Xinxi;
import com.daowen.service.XinxiService;
import com.daowen.ssm.simplecrud.SimpleController;
import com.daowen.util.BeansUtil;
import com.daowen.webcontrol.PagerMetal;

@Controller
public class SitesearchController extends SimpleController {

	@Override
	@RequestMapping("/admin/sitesearch.do")
	public void mapping(HttpServletRequest req, HttpServletResponse resp) {
		mappingMethod(req,resp);

	}
	
	private void find() {

		String searchtype = request.getParameter("searchtype");
        String  title=request.getParameter("title");
        System.out.println("searchtype="+searchtype);

		if(searchtype!=null&&searchtype.equals("1")){
             searchBook(title);
		}
	}


	private void  searchBook(String title){

		List<Book> listBook=bookSrv.getEntity("where name like '%"+title+"%'");
		if(listBook!=null)
			request.setAttribute("listBook",listBook);

		dispatchParams(request,response);
		forward("/e/searchbook.jsp");
	}

	@Autowired
	private BookService bookSrv=null;
	
}
