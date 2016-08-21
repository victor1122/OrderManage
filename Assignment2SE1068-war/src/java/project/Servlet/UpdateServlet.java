/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import prject.error.tbl_orderDetailError;
import project.tbl_orderDetail.tbl_orderDetailSessionBeanRemote;
import project.tbl_product.tbl_productSessionBeanRemote;

/**
 *
 * @author anhnh
 */
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        try {
            String url = "CheckCookie";
            if (session != null) {
                if (session.getAttribute("USER") != null) {
                    int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                    float unitprice = Float.parseFloat(request.getParameter("txtUnitPrice"));
                    String proID = request.getParameter("productid"); //use to get remaining stock
                    String productName = request.getParameter("productName");
                    String id = request.getParameter("id"); //use to update

                    Context context = new InitialContext();
                    Object obj = context.lookup("product_remote");
                    tbl_productSessionBeanRemote poji = (tbl_productSessionBeanRemote) obj;
                    int remaining = poji.getStock(proID);

                    if (remaining < quantity) {
                        tbl_orderDetailError err = new tbl_orderDetailError();
                        err.setWrongQuantity("Your input quantity of "
                                + productName
                                + " is greater than the remaining stock: "
                                + remaining
                                + "");
                        request.setAttribute("ERR", err);
                    } else {
                        Object obj2 = context.lookup("detail_remote");
                        tbl_orderDetailSessionBeanRemote poji2 = (tbl_orderDetailSessionBeanRemote) obj2;
                        int iid = Integer.parseInt(id);
                        int originQuantity = poji2.getQuantity(iid);
                        boolean result = poji2.updateQuantity(quantity, unitprice, iid);
                        if (result) {
                            int newQuantity = remaining - quantity + originQuantity;
                            poji.setStock(proID, newQuantity);
                        }
                    }
                    url = "DetailServlet";
                }
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException ex) {
            log("UpdateServlet_Naming: " + ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
