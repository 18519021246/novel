package com.imnu.uibuilder;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daowen.entity.Book;
import com.daowen.entity.Huiyuan;
import com.daowen.entity.Spcategory;
import com.daowen.service.BookService;
import com.daowen.service.SpcategoryService;
import com.daowen.util.BeansUtil;

public class BookBuilder {

    private HttpServletRequest request=null;

    private BookService  bookSrv=null;
    private SpcategoryService spcSrv=null;
    public BookBuilder(HttpServletRequest request){

        bookSrv= BeansUtil.getBean("bookService",BookService.class);
        spcSrv= BeansUtil.getBean("spcategoryService",SpcategoryService.class);
        this.request=request;
    }

    public String build(int btypeId){

       List<Book> list=bookSrv.findBook(btypeId);

       Spcategory spc=spcSrv.load("where id="+btypeId);
       if(spc==null)
           return "<div>编号异常</div>";
       return build(list,spc.getMingcheng());

    }

    public String build(List<Book> list , String des){

        StringBuffer  sb=new StringBuffer();
        sb.append(" <div class=\"picture-list\">");
        sb.append(MessageFormat.format("<div class=\"title\">{0}</div>",des));
        sb.append("<ul>");
        for(Book book :list ){
            sb.append("<li>\r\n");
            sb.append(" <div class=\"item\">");
            sb.append("<div class=\"tag\">\n" +
                    "                                        <span>推荐</span>\n" +
                    "                                    </div>");//end tag
            sb.append(" <div class=\"img\">");
            sb.append(MessageFormat.format("<a href=\"{0}/e/bookinfo.jsp?id={1}\">",request.getContextPath(),book.getId()));//start a
            sb.append(MessageFormat.format(" <img src=\"{0}{1}\"/>",request.getContextPath(),book.getTupian()));
            sb.append("</a>");
            sb.append("</div>");//end imgdiv
            sb.append(MessageFormat.format("<div class=\"name\">{0}</div>",book.getName()));
            sb.append("<div class=\"price\"></div>");

            sb.append("</div>");//end item

            sb.append("</li>\r\n");
        }
        sb.append("</ul>");
        sb.append("</div>");
        return sb.toString();

    }
    
    
	public BookBuilder view(HttpServletResponse response,String id){
		Huiyuan huiyuan=(Huiyuan)request.getSession().getAttribute("huiyuan");
		if(huiyuan==null)
			return this;
 		String username=huiyuan==null?"":huiyuan.getAccountname();
 		//获取历史浏览
 		new ViewHistory("book_browser_"+username).view(request,response,id);
 		return this;
	}


	public String buildViewedList() {

		StringBuilder sb = new StringBuilder();
		Huiyuan huiyuan=(Huiyuan)request.getSession().getAttribute("huiyuan");
 		if(huiyuan==null)
 			return "";
 		String username=huiyuan==null?"":huiyuan.getAccountname();
 		
		// 获取历史浏览
		List<String> ids =new  ViewHistory("book_browser_"+username).historyView(request);
		String temids = "";
		int i = 0;
		for (String id : ids) {
			temids += id;
			if (i < ids.size() - 1) {
				temids += ",";
			}
			i++;

		}
		if (ids != null && ids.size() > 0) {
			List<Book> shangpinlist = bookSrv.getEntity(" where id in (" + temids + ")");
			// 得到最新书籍信息
			sb.append(build(shangpinlist, "猜你喜欢的书籍"));
		}
		return sb.toString();
	}



}
