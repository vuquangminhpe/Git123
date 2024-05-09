/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name="SearchByTextServlet", urlPatterns={"/search"})
public class SearchByTextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
          request.setCharacterEncoding("UTF-8");
          DAO d = new DAO();
       String txt = request.getParameter("search");
       List<Product> listP = new ArrayList<>();
       List<Category> listC = new ArrayList<>();  
       listC = d.getAllCategory(0);
       int m = listP.size();
       int n = m/9;
       int i;
       if(m%9!=0){
          n++;
      }
      String i_raw = request.getParameter("index");
      if(i_raw == null){
         i_raw = "1";
      } 
         i = Integer.parseInt(i_raw);
          listP= d.getProByText(txt,i);
         request.setAttribute("n", n);
         request.setAttribute("search", txt);
      request.setAttribute("listP", listP);
      request.setAttribute("listC", listC);
      request.getRequestDispatcher("shop.jsp").forward(request, response);
    } 
  
}
