/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAO;
import dal.DaoAccount;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Admin
 */
@WebServlet(name="SingupServlet", urlPatterns={"/singup"})
public class SingupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
          request.setCharacterEncoding("UTF-8");

     String u = request.getParameter("input");
     String p = request.getParameter("password");
     String r_p = request.getParameter("re-password");
        DaoAccount d = new DaoAccount();
        Account a = new Account();
        a=d.checkAccountExsit(u);       
        if(a != null){
              request.setAttribute("error3", "Username exsit!!!");
                  request.getRequestDispatcher("login.jsp").forward(request, response);
            }  else {
                if(!p.equals(r_p)){
                request.setAttribute("error3", "Password must be same!!!");
                   request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {        
                d.signUp(u, p);  
                 request.setAttribute("error3", "SIGNUP SUCCESFULLY!!! PLEASE LOGIN");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    } 

  
}
