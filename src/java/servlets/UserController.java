/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;
import util.RoleManager;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "UserController", urlPatterns = {
    "/index",
    "/showBook",
    "/takeBook",
    "/doTakeBook",
    
})
public class UserController extends HttpServlet {
@EJB BookFacade bookFacade;
@EJB ReaderFacade readerFacade;
@EJB HistoryFacade historyFacade;
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
        HttpSession session = request.getSession(false);
        if (null == session){
            request.setAttribute("info", "У вас нет прав доступа, войдите в систему");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;   
        }
        if(null == session.getAttribute("user")){
            request.setAttribute("info", "У вас нет прав доступа, войдите в систему");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; 
        }
        User user = (User) session.getAttribute("user");
        RoleManager rm = new RoleManager();
        if(!rm.isRoleUser("USER", user)){
            request.setAttribute("info", "У вас нет прав доступа, войдите в систему");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; 
        }
        Reader reader = null;
        request.setAttribute("userRole", rm.getTopRoleName(user));
        switch (path) {
            case "/index":
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/showBook":
                String bookId = request.getParameter("id");
                Book book = bookFacade.find(Long.parseLong(bookId));
                request.setAttribute("book", book);
                request.getRequestDispatcher("/showBook.jsp").forward(request, response);
                break;
            case "/takeBook":
                List<Book> listBooks = bookFacade.findTakeBook();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher("/showBook.jsp").forward(request, response);
                break;
            case "/doTakeBook":
                bookId = request.getParameter("bookId");
                try {
                    book = bookFacade.find(Long.parseLong(bookId));
                    if(book.getCountInLibrary()>0){
                        if(user.getReader().getMoney() > user.getReader().getMoney()-book.getPrice()){
                            book.setCountInLibrary(book.getCountInLibrary()-1);
                            bookFacade.edit(book);
                            History history = new History();
                            history.setBook(book);
                            history.setReader(user.getReader());
                            history.setTakeBook(new Date());
                            historyFacade.create(history);
                            request.setAttribute("info", "Книга куплена");
                        }else{
                            request.setAttribute("info", "Не хватает денег!");
                        }
                    }else{
                        request.setAttribute("info", "Книги нет в наличии");
                    }
                } catch (Exception e) {
                    request.setAttribute("info", "Не корректные данные");
                }
                request.getRequestDispatcher("/listBooks")
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
