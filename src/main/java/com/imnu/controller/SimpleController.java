package com.imnu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class SimpleController {
    private Map<String, String> routeMethods = new HashMap<String, String>() {
        {
            this.put("search", "get");
        }
    };
    protected HttpServletRequest request = null;
    protected HttpServletResponse response = null;
    private String errHandlerUrl = "/admin/error.jsp";
    private Boolean handleError = false;

    public SimpleController() {
    }

    public abstract void mapping(HttpServletRequest var1, HttpServletResponse var2);

    public void mappingMethod(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        String actionType = request.getParameter("actiontype");
        String doMethod = request.getParameter("domethod");
        if (actionType != null || doMethod != null) {
            String methodName = !this.isEmpty(doMethod) ? doMethod : actionType;
            if (this.isEmpty(methodName)) {
                System.out.println("调用" + this.getClass().getName() + "方法:" + methodName + "失败");
                System.out.println("方法名=" + methodName);
            } else {
                Method invokeMethod = null;
                if (this.routeMethods.containsKey(methodName)) {
                    invokeMethod = this.getMethod(this, (String)this.routeMethods.get(methodName));
                } else {
                    invokeMethod = this.getMethod(this, methodName);
                }

                if (invokeMethod != null) {
                    try {
                        Boolean flag = invokeMethod.isAccessible();
                        invokeMethod.setAccessible(true);
                        invokeMethod.invoke(this);
                        invokeMethod.setAccessible(flag);
                    } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException var8) {
                        var8.printStackTrace();
                    }
                }

            }
        }
    }

    public void dispatchParams(HttpServletRequest request, HttpServletResponse response) {
        Enumeration params = request.getParameterNames();

        while(params.hasMoreElements()) {
            String paramname = params.nextElement().toString();
            String value = request.getParameter(paramname);
            request.setAttribute(paramname, value);
        }

    }

    public void redirect(String url) {
        if (url != null) {
            try {
                this.response.sendRedirect(this.request.getContextPath() + url);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    public void forward(String url) {
        if (url != null) {
            try {
                this.request.getRequestDispatcher(url).forward(this.request, this.response);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    public String join(String join, String[] strAry) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < strAry.length; ++i) {
            if (i == strAry.length - 1) {
                sb.append(strAry[i]);
            } else {
                sb.append(strAry[i]).append(join);
            }
        }

        return new String(sb);
    }

    private Boolean isEmpty(Object str) {
        return str != null && !"".equals(str) ? false : true;
    }

    public String getErrHandlerUrl() {
        return this.errHandlerUrl;
    }

    private Method getMethod(Object target, String methodName) {
        Method invokeMethod = null;

        try {
            invokeMethod = this.getClass().getDeclaredMethod(methodName);
        } catch (Exception var5) {
            System.out.print("获取" + this.getClass().getName() + "." + methodName + "失败");
            var5.printStackTrace();
        }

        return invokeMethod;
    }


}
