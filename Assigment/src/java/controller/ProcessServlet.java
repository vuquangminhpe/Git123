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
import java.util.List;
import model.Cart;
import model.Item;
import model.Pdetail;
import model.Product;


@WebServlet(name="ProcessServlet", urlPatterns={"/process"})
public class ProcessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       DAO d = new DAO();

        Cookie []arr = request.getCookies();
        String txt = "";
        if(arr!=null){
            for (Cookie o : arr) {
                if(o.getName().equals("cart")){
                    txt += o.getValue();    
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        } 
        List<Product> list = d.getPdetailCart();
        Cart cart = new Cart(txt, list);
        String num_raw = request.getParameter("num");
        String pid_raw = request.getParameter("pid");
        String sid_raw = request.getParameter("sid");
         
        int pid,sid, num = 0;
        try {           
            pid = Integer.parseInt(pid_raw);
           sid = Integer.parseInt(sid_raw);
           Product p= d.getPdetailCart(pid, sid);
           int numStore =p.getList().get(0).getQuantity();
            num = Integer.parseInt(num_raw);
            if(num == -1 && (cart.getQuantityById(pid, sid)<=1)){
                cart.removeItem(pid, sid);
            } else {
                if(num == 1 && cart.getQuantityById(pid, sid) >= numStore ){
                    num = 0;
                }
                double price = p.getPrice();
                Item t = new Item(p, num, price, d.getSizeBySid(sid));
                cart.addItem(t);
            }
        } catch (Exception e) {
        }
        List<Item> items = cart.getItems();
        txt = "";
        if(items.size() >0){
            txt = items.get(0).getProduct().getPid() + ":" + items.get(0).getQuantity() + ":" + items.get(0).getSize().getSid();
            for(int i = 1; i< items.size();i++){
                txt += "/" + items.get(i).getProduct().getPid() + ":" + items.get(i).getQuantity() + ":" + items.get(i).getSize().getSid();
            }
        }
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(2*24*60*60);
        response.addCookie(c);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
            
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
     DAO d = new DAO();
      List<Product> list = d.getPdetailCart();
        Cookie []arr = request.getCookies();
        String txt = "";
        if(arr!=null){
            for (Cookie o : arr) {
                if(o.getName().equals("cart")){
                    txt += o.getValue();    
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        } 
        String pid_raw = request.getParameter("pid");
        String sid_raw = request.getParameter("sid");
       
        int sid = Integer.parseInt(sid_raw);
       int id = Integer.parseInt(pid_raw); 
   
    String out = "";
       String [] s = txt.split("/");
                    for (String i : s) {
                        String n[] = i.split(":");
                        if(!n[0].equals(pid_raw) || !n[2].equals(sid_raw)){
                            if(out.isEmpty()){
                                out = i;
                            } else {
                                out += "/"+i;
                            }
                        }
                    }
       
        if(!out.isEmpty()){
            Cookie c = new Cookie("cart", out);
            c.setMaxAge(2*24*60*60);
            response.addCookie(c);
        }
       
        Cart cart = new Cart(out, list);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

   
}
