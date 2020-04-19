package com.imnu.controller;

import com.daowen.entity.Author;
import com.daowen.entity.Huiyuan;
import com.daowen.service.AuthorService;
import com.daowen.service.HuiyuanService;
import com.daowen.ssm.simplecrud.SimpleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

@Controller
public class QtloginController extends SimpleController {

	@Override
	@RequestMapping("/admin/qtlogin.do")
	public void mapping(HttpServletRequest request, HttpServletResponse response) {
		mappingMethod(request,response);
	}
	public void exit() {

		String usertype=request.getParameter("usertype");
		if (usertype!= null&&usertype.equals("0")) {

			System.out.println("系统退出");
			request.getSession().removeAttribute("huiyuan");

		}
		if (usertype!= null&&usertype.equals("1")) {

			System.out.println("作者退出");
			request.getSession().removeAttribute("author");

		}
		

	}
	public  void login(){
		
		String usertype = request.getParameter("usertype");

		if (usertype != null && usertype.equals("0")) {
			huiyuanLogin();
		}
		
		if (usertype != null && usertype.equals("1")) {
			authorLogin();
		}
		
	}

	private void huiyuanLogin() {

		String accountname = request.getParameter("accountname");
		String password = request.getParameter("password");
		String filter = MessageFormat.format(
				"where accountname=''{0}'' and password=''{1}''", accountname,
				password);

		Huiyuan huiyuan = huiyuanSrv.load(filter);
		String errorurl=request.getParameter("errorurl");

		if (huiyuan != null && huiyuan.getPassword().equals(password)) {
			huiyuan.setLogtimes(huiyuan.getLogtimes() + 1);
			huiyuanSrv.update(huiyuan);
			request.getSession().setAttribute("huiyuan", huiyuan);
			redirect("/e/huiyuan/accountinfo.jsp");
			
		} else {
			dispatchParams(request, response);
			request.setAttribute("errormsg", "<label class='error'>会员账户和密码不匹配</label>");
			forward(errorurl);
		}

	}
	
	
	private void authorLogin() {

		String accountname = request.getParameter("accountname");
		String password = request.getParameter("password");
		String errorurl=request.getParameter("errorurl");
		String filter = MessageFormat.format("where accountname=''{0}'' and password=''{1}''", accountname,
						password);
		Author author = authorSrv.load(filter);

		
		if (author != null && author.getPassword().equals(password)) {

			
			request.getSession().setAttribute("author", author);

			redirect("/e/author/accountinfo.jsp");
			
		} else {

			dispatchParams(request, response);
			request.setAttribute("errormsg", "<label class='error'>作家账户和密码不匹配</label>");
			forward(errorurl);

		}

	}
	@Autowired
	private HuiyuanService huiyuanSrv=null;

	@Autowired
	private AuthorService authorSrv=null;

}
