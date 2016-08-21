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
import prject.error.tbl_accountError;
import project.entity.TblAccount;
import project.tbl_account.tbl_accountSessionBeanRemote;

/**
 *
 * @author anhnh
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String home = "login.html";
    private final String again = "register.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        tbl_accountError error = new tbl_accountError();
        String url = again;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtName");
        String email = request.getParameter("txtEmail");
        try {
            if (confirm.equals(password)) {
                Context context = new InitialContext();
                Object obj = context.lookup("account_remote");
                tbl_accountSessionBeanRemote poji = (tbl_accountSessionBeanRemote) obj;
                TblAccount account = new TblAccount(username, fullname, password, email);
                String result = poji.insertAccount(account);
                if (result.equals("UNIQUE")) {
                    error.setDupEmail("The email '"
                            + email
                            + "' has already existed");
                }
                if (result.equals("PRIMARY")) {
                    error.setDupEmail("The user '"
                            + username
                            + "' has already existed");
                }
                if (result.equals("SUCCESS")) {
                    url = home;
                }
            } else {
                error.setWrongConfirm("Wrong confirm password!!!");
            }
        } catch (NamingException ex) {
            log("RegisterServlet_Naming " + ex.getMessage());
        }finally {
            request.setAttribute("ERROR", error);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
