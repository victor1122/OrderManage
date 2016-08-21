/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.entity.TblorderDetail;
import project.tbl_order.tbl_orderSessionBeanRemote;
import project.tbl_orderDetail.tbl_orderDetailSessionBeanRemote;

/**
 *
 * @author anhnh
 */
public class DetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String login = "CheckCookie";
    private final String search = "SearchServlet";
    private final String detailPage = "detail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        try {
            String url = login;
            if (session != null) {
                String orderID = request.getParameter("OrderID");

                Context context = new InitialContext();
                Object obj = context.lookup("detail_remote");
                tbl_orderDetailSessionBeanRemote poji = (tbl_orderDetailSessionBeanRemote) obj;
                Map<String, TblorderDetail> list = (Map<String, TblorderDetail>) poji.showDetail(orderID);
                if (list == null) {
                    Object obj2 = context.lookup("order_remote");
                    tbl_orderSessionBeanRemote poji2 = (tbl_orderSessionBeanRemote) obj2;
                    poji2.deleteOrder(orderID);
                    url = search;
                } else {
                    url = detailPage;
                    request.setAttribute("DETAIL", list);
                    Object obj2 = context.lookup("order_remote");
                    tbl_orderSessionBeanRemote poji2 = (tbl_orderSessionBeanRemote) obj2;
                    double total = poji2.getTotal(orderID);
                    request.setAttribute("TOTAL", total);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException ex) {
            log("DetailServlet_Naming " + ex);
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
