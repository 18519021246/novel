package com.imnu.controller;

import com.daowen.dto.JsonResult;
import com.daowen.entity.Fans;
import com.daowen.service.FansService;
import com.daowen.ssm.simplecrud.SimpleController;
import com.daowen.webcontrol.PagerMetal;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**************************
 *
 * 粉丝控制
 *
 */
@Controller
public class FansController extends SimpleController {
    @Autowired
    private FansService fansSrv = null;

    @Override
    @RequestMapping("/admin/fansmanager.do")
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
        fansSrv.delete(SQL);
    }

    /**
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value="/admin/fans/guanzhu",method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
    public String guanzhu(HttpServletRequest request,HttpServletResponse response) {
        String forwardurl = request.getParameter("forwardurl");
        //获取关注账号
        String staraccount = request.getParameter("staraccount");
        //获取粉丝账号
        String fansaccount = request.getParameter("fansaccount");
        //获取关注时间
        SimpleDateFormat sdffans = new SimpleDateFormat("yyyy-MM-dd");
        Fans fans = new Fans();
        // 设置关注账号
        fans.setStaraccount(staraccount == null ? "" : staraccount);
        // 设置粉丝账号
        fans.setFansaccount(fansaccount == null ? "" : fansaccount);

        fans.setCreatetime(new Date());

        String filter= MessageFormat.format("where staraccount=''{0}'' and  fansaccount=''{1}''",staraccount,fansaccount);
        //产生验证
        Boolean validateresult = fansSrv.isExist(filter);
        if (validateresult) {
            JsonResult res=new JsonResult(-1,"你已经关注过");
            return JSONObject.fromObject(res).toString();
        }
        fansSrv.save(fans);
        JsonResult res=new JsonResult(1,"参加成功");
        return JSONObject.fromObject(res).toString();

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
            Fans fans = fansSrv.load("where id=" + id);
            if (fans != null) {
                request.setAttribute("fans", fans);
            }
            actiontype = "update";
            request.setAttribute("id", id);
        }
        request.setAttribute("actiontype", actiontype);
        String forwardurl = request.getParameter("forwardurl");
        System.out.println("forwardurl=" + forwardurl);
        if (forwardurl == null) {
            forwardurl = "/admin/fansadd.jsp";
        }
        forward(forwardurl);
    }

    /******************************************************
     ***********************数据绑定内部支持*********************
     *******************************************************/
    public void get() {
        String filter = "where 1=1 ";
        String zbaccount = request.getParameter("zbaccount");
        if (zbaccount != null)
            filter += "  and zbaccount like '%" + zbaccount + "%'  ";
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
        List<Fans> listfans = fansSrv.getPageEntitys(filter, pageindex, pagesize);
        int recordscount = fansSrv.getRecordCount(filter == null ? "" : filter);
        request.setAttribute("listfans", listfans);
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
            forwardurl = "/admin/fansmanager.jsp";
        }
        forward(forwardurl);
    }

    public void query() {
        String sql = " select * from fans    ";
        List<HashMap<String, Object>> listFans = fansSrv.queryToMap(sql);
        request.setAttribute("listFans", listFans);
        // 分发请求参数
        dispatchParams(request, response);
        String forwardurl = request.getParameter("forwardurl");
        System.out.println("forwardurl=" + forwardurl);
        if (forwardurl == null) {
            forwardurl = "/admin/fansmanager.jsp";
        }
        forward(forwardurl);
    }
}
