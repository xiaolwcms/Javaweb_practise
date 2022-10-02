package filter;


import entity.Staff;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 登录过滤器
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        //获取session
        HttpSession session= httpServletRequest.getSession();
        //获取请求的URL
        String path=httpServletRequest.getRequestURI();
        //无需过滤的资源
        if(path.contains("/login")||path.contains("/register")||path.contains("/css/")||path.contains("/js/")){
            filterChain.doFilter(servletRequest,servletResponse);//释放资源
            return;
        } else{
            Staff staff=(Staff) session.getAttribute("loginUser");
            if(staff!=null){
                filterChain.doFilter(servletRequest,servletResponse);//释放资源
            }else{
                //跳转到登录页面
                //servletRequest.getRequestDispatcher("/login.html").forward(servletRequest,servletResponse);
                httpServletResponse.sendRedirect("/login.html");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
