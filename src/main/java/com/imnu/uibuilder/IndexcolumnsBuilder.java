package com.imnu.uibuilder;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.imnu.entity.Indexcolumns;
import com.imnu.service.IndexcolumnsService;
import com.imnu.util.BeansUtil;

public class IndexcolumnsBuilder {

	private IndexcolumnsService icSrv=null;
	
	private HttpServletRequest request=null;

	public IndexcolumnsBuilder(HttpServletRequest request){
		this.request=request;
		icSrv=BeansUtil.getBean("indexcolumnsService", IndexcolumnsService.class);
	}
    public String buildColumns(){
		
        BookBuilder  bookBuilder=new BookBuilder(request);
		List<Indexcolumns> listIc=icSrv.getEntity("");
		StringBuffer sb=new StringBuffer();
		for(Indexcolumns ic :listIc){
          sb.append(bookBuilder.build(ic.getColid()));
          sb.append("\r\n");

		}
		return sb.toString();
	}
	

	
}
