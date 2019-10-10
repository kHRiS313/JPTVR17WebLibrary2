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
import java.io.PrintWriter;
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

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "AdminController", urlPatterns = {
    "/newBook",
    "/addBook",
    "/editReader",
    "/changeReader",
    "/editBook",
    "/changeBook",
    "/listReaders",
    "/takeOnBooks",
})
public class AdminController extends HttpServlet {
    @EJB private BookFacade bookFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private HistoryFacade historyFacade;
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
        if(!"ivan".equals(user.getLogin())){
            request.setAttribute("info", "У вас нет прав доступа, войдите в систему");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; 
        }
        request.setAttribute("user", user);
        switch (path){ 
            case "/newBook":
                request.getRequestDispatcher("/newBook.jsp").forward(request, response);
                break;
            case "/addBook":
                String name = request.getParameter("name");
                String author = request.getParameter("author");
                String publichedYear = request.getParameter("publishedYear");
                String isbn = request.getParameter("isbn");
                String quantity = request.getParameter("quantity");
                String price = request.getParameter("price");
                Book book = null;
                try {
                    if(!"".equals(name) && !"".equals(author)){
                        book = new Book( name, author, isbn, new Integer(publichedYear),Integer.parseInt(quantity),Integer.parseInt(price));
                        bookFacade.create(book);
                        request.setAttribute("info", "Книга \"" + book.getName()+"\" добавлена");
                    }else{
                        request.setAttribute("info", "Книгу добавить не удалось (не корректные данные)"); 
                    }
                } catch (Exception e) {
                   request.setAttribute("info", "Книгу добавить не удалось (не корректные данные)"); 
                }
                request.getRequestDispatcher("/newBook.jsp").forward(request, response);
                break;
            case "/editBook":
                String bookId = request.getParameter("id");
                book = bookFacade.find(Long.parseLong(bookId));
                request.setAttribute("book", book);
                request.getRequestDispatcher("/editBook.jsp").forward(request, response);
                break;
            case "/changeBook":
                String id = request.getParameter("id");
                name = request.getParameter("name");
                author = request.getParameter("author");
                publichedYear = request.getParameter("publishedYear");
                isbn = request.getParameter("isbn");
                quantity = request.getParameter("quantity");
                book = bookFacade.find(Long.parseLong(id));
                book.setName(name);
                book.setAuthor(author);
                book.setPublishedYear(Integer.parseInt(publichedYear));
                book.setIsbn(isbn);
                book.setQuantity(Integer.parseInt(quantity));
                bookFacade.edit(book);
                request.setAttribute("book", book);
                request.setAttribute("info", "Книга изменена!");
                request.getRequestDispatcher("/editBook.jsp").forward(request, response);
                break;
            case "/listReaders":
                List<Reader> listReaders = readerFacade.findAll();
                request.setAttribute("listReaders", listReaders);
                request.getRequestDispatcher("/listReaders.jsp").forward(request, response);
                break;
            case "/editReader":
                id = request.getParameter("id");
                Reader reader = readerFacade.find(Long.parseLong(id));
                request.setAttribute("reader", reader);
                request.getRequestDispatcher("/editReader.jsp")
                        .forward(request, response);
                break;
            case "/changeReader":
                id = request.getParameter("id");
                name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String phone = request.getParameter("phone");
                reader = readerFacade.find(Long.parseLong(id));
                reader.setName(name);
                reader.setSurname(surname);
                reader.setPhone(phone);
                //Запись данных в базу
                readerFacade.edit(reader);
                request.setAttribute("reader", reader);
                request.getRequestDispatcher("/listReaders").forward(request, response);
                break;
            case "/takeOnBooks":
                List<History> listHistories = historyFacade.findNotReturnedBook();
                request.setAttribute("takeOnBooks", listHistories);
                request.getRequestDispatcher("/takeOnBooks.jsp")
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
