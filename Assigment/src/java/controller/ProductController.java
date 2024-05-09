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
import java.util.List;
import model.Category;
import model.Product;


@WebServlet(name="HomeController", urlPatterns={"/product"})
public class ProductController extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      DAO d = new DAO();
      int m = d.getTotalPro();
      int n = (m/9);
      int i;
      if(m%9!=0){
          n++;
      }
      String i_raw = request.getParameter("index");
      if(i_raw == null){
         i_raw = "1";
      } 
         i = Integer.parseInt(i_raw);
         List<Product> listaa = d.getAllProduct();
    List<Product> listP = d.paging(i);
      List<Category> listC = d.getAllCategory(0);
      request.setAttribute("listP", listP);
      request.setAttribute("listC", listC);
      request.setAttribute("size",  listaa.size());
      request.setAttribute("n", n);
      request.getRequestDispatcher("shop.jsp").forward(request, response);
    } 

   
}
