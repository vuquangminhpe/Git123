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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Product;


@WebServlet(name="ShowCart", urlPatterns={"/show"})
public class ShowCart extends HttpServlet {
  
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       DAO d = new DAO();
       List<Product> list = new ArrayList<>();
       list=d.getPdetailCart();
        Cookie []arr = request.getCookies();
        String txt = "";
        if(arr != null){
            for (Cookie o : arr) {
                if(o.getName().equals("cart")){
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, list);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    } 

}
