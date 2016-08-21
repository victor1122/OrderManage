/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.tbl_orderDetail.tbl_orderDetailSessionBeanRemote;
import project.tbl_product.tbl_productSessionBeanRemote;

/**
 *
 * @author anhnh
 */
public class DeleteServlet extends HttpServlet {

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
                String id = request.getParameter("id");
                String proID = request.getParameter("productid"); //use to get remaining stock
                Context context = new InitialContext();

                //Get the quantity of product to sum with stock
                Object obj2 = context.lookup("detail_remote");
                tbl_orderDetailSessionBeanRemote poji2 = (tbl_orderDetailSessionBeanRemote) obj2;
                int iid = Integer.parseInt(id);
                int originQuantity = poji2.getQuantity(iid);

                //get remaining in stock
                Object obj = context.lookup("product_remote");
                tbl_productSessionBeanRemote poji = (tbl_productSessionBeanRemote) obj;
                int remaining = poji.getStock(proID);

                int newStock = remaining + originQuantity;

                poji2.deleteProduct(iid);
                poji.setStock(proID, newStock);
                url = "DetailServlet";
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException ex) {
            log("DeleteServlet_Naming " + ex);
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
