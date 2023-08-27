package com.CSP.postservice.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginCheckFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;

        String authHeader=httpServletRequest.getHeader("authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer"))
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            httpServletResponse.setContentType("application/json");
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write("Error : Unauthorized Access");
            writer.flush();
        }
        else
        {
            String tok=authHeader.substring(7); //Bearer asdfg.zxcvbnfgh.cvghbh
            Claims claims= Jwts.parser().setSigningKey("CSP_Auth").parseClaimsJws(tok).getBody();
            System.out.println("claims retrieved from token : "+claims);
            httpServletRequest.setAttribute("user_id",claims.get("Id"));
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}
