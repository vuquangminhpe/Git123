/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

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
import model.AccountDetail;

/**
 *
 * @author Admin
 */
@WebServlet(name="PfileServlet", urlPatterns={"/p"})
public class PfileServlet extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
           response.setContentType("text/html;charset=UTF-8");
          request.setCharacterEncoding("UTF-8");
       // processRequest(request, response);
      
       AccountDetail a = new AccountDetail();
    DaoAccount d = new DaoAccount();
     HttpSession session = request.getSession();
      Account a1 =  (Account) session.getAttribute("user");
     
     if(a1 == null){
         response.sendRedirect("login.jsp");
     } else {
          int aid = a1.getUid();
     a = d.getAById(aid);
          request.setAttribute("a", a);
     request.getRequestDispatcher("profile.jsp").forward(request, response);
     }
    
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
          request.setCharacterEncoding("UTF-8");
       String name = request.getParameter("name");
       String add = request.getParameter("address");
       String phone = request.getParameter("phone");
       String email = request.getParameter("email");
       String dob = request.getParameter("dob");   
       HttpSession session = request.getSession();
               DaoAccount d = new DaoAccount();
       Account a1 =  (Account) session.getAttribute("user");
       if(a1 == null){
           response.sendRedirect("login.jsp");
       }else if(name.contains(dob)){
           {
            int aid = a1.getUid();
                d.upDate(name, add, phone, email, email, dob, aid);
         AccountDetail a = d.getAById(aid);
           request.setAttribute("a", a);         
      request.getRequestDispatcher("profile.jsp").forward(request, response);     
       }
        }
    }
       
   
}
