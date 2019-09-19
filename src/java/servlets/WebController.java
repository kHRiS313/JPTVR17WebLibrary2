/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
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
@WebServlet(name = "WebController", urlPatterns = {
    "/newBook",
    "/addBook",
    "/newReader",
    "/listBooks",
    "/listReaders",
    "/takeBook",
    "/returnBook",
    
})
public class WebController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/newBook":
                request.getRequestDispatcher("/newBook.jsp").forward(request, response);
                break;
            case "/addBook":
                String name = request.getParameter("name");
                String author = request.getParameter("author");
                String publichedYear = request.getParameter("publishedYear");
                String isbn = request.getParameter("isbn");
                Book book = new Book(null, name, author, isbn, new Integer(publichedYear));
                //Запись данных в базу
                request.setAttribute("book", book);
                request.getRequestDispatcher("/newBook.jsp").forward(request, response);
                break;
            case "/newReader":
                request.getRequestDispatcher("/newReader.jsp").forward(request, response);
                break;
            case "/listBooks":
                request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
                break;
            case "/listReaders":
                request.getRequestDispatcher("/listReaders.jsp").forward(request, response);
                break;
            case "/takeBook":
                request.getRequestDispatcher("/takeBook.jsp").forward(request, response);
                break;
            case "/returnBook":
                request.getRequestDispatcher("/returnBook.jsp").forward(request, response);
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
