/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "MySerblet2", urlPatterns = {
    "/page2",
})
public class MySerblet2 extends HttpServlet {

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
        request.setAttribute("info", "Привет из MyServlet2");
        String path = request.getServletPath();
        switch (path) {
            case "/page2":
                String num1 = request.getParameter("num1");
                String num2 = request.getParameter("num2");
                String znak = request.getParameter("znak");
                double res=0.0;
                if(num1 != null || num2 != null){
                    switch (znak) {
                        case "+":
                            res =(double)( new Double(num1) + new Double(num2));
                            break;
                        case "-":
                            res = (double)(new Double(num1) - new Double(num2));
                            break;
                        case "*":
                            res = (double)(new Double(num1) * new Double(num2));
                            break;
                        case "/":
                            res = new Double(num1) / new Double(num2);
                            break;
                    }
                    
                    request.setAttribute("num1", num1);
                    request.setAttribute("num2", num2);
                    request.setAttribute("res", res);
                    request.setAttribute("znak", znak);
                }
                
                request.getRequestDispatcher("/WEB-INF/page2.jsp")
                        .forward(request, response);
                break;
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
