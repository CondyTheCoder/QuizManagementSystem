package com.weiting.QuizApp.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        HttpSession session = request.getSession(false);

        if (request.getRequestURI().endsWith("/register")) {
//            System.out.println("In Registeration");
            // If it's the registration page, allow the request to proceed
            filterChain.doFilter(request, response);
        }else if(request.getRequestURI().endsWith("/contactus")) {
//            System.out.println("In Contact us");
            // If it's the registration page, allow the request to proceed
            filterChain.doFilter(request, response);
        }else if(request.getRequestURI().endsWith("/thankyou")){
//            System.out.println("In thank you");
            // If it's the registration page, allow the request to proceed
            filterChain.doFilter(request, response);

        }else if (request.getRequestURI().endsWith("/send-message")){
//                System.out.println("In Send Message");
                // Allow the request to proceed
                filterChain.doFilter(request, response);

        }else if(request.getRequestURI().endsWith("/quiz")){
            filterChain.doFilter(request, response);
        }else{
//            System.out.println("In LoginFilter");
//              System.out.println(" " + session.getId() + " " + session.getAttribute("user"));
            if (session != null && session.getAttribute("user") != null) {
//                System.out.println("Quiz?");
                filterChain.doFilter(request, response);
            } else {
                System.out.println("Something wrong");
                // redirect back to the login page if user is not logged in
                response.sendRedirect("/login");
            }
        }
    }



    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/login".equals(path);
    }
}
