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
import session.UserFacade;
import util.EncriptPass;

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
    "/listAllBooks",
    
})
public class AdminController extends HttpServlet {
    @EJB private BookFacade bookFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private HistoryFacade historyFacade;
    @EJB private UserFacade userFacade;
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
                String countInLibrary = request.getParameter("countInLibrary");
                String price = request.getParameter("price");
                Book book = null;
                try {
                    if(!"".equals(name) && !"".equals(author)){
                        book = new Book( name, author, isbn, new Integer(publichedYear),Integer.parseInt(countInLibrary),Integer.parseInt(price),true);
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
                countInLibrary = request.getParameter("countInLibrary");
                String active = request.getParameter("active");
                book = bookFacade.find(Long.parseLong(id));
                book.setName(name);
                book.setAuthor(author);
                book.setPublishedYear(Integer.parseInt(publichedYear));
                book.setIsbn(isbn);
                book.setCountInLibrary(Integer.parseInt(countInLibrary));
                if("on".equals(active)){
                    book.setActive(true);
                }else{
                    book.setActive(false);
                }
                
                bookFacade.edit(book);
                request.setAttribute("book", book);
                request.setAttribute("info", "Книга изменена!");
                request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
                break;
            case "/listAllBooks":
                List<Book> listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
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
                User changeUser = userFacade.findByReader(reader);
                request.setAttribute("changeUser", changeUser);
                request.getRequestDispatcher("/editReader.jsp")
                        .forward(request, response);
                break;
            case "/changeReader":
                id = request.getParameter("id");
                name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String phone = request.getParameter("phone");
                String login = request.getParameter("login");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if(!password1.equals(password2)){
                    request.setAttribute("info", "Не совпадают пароли");
                    request.setAttribute("id", id);
                    request.getRequestDispatcher("/editReader")
                        .forward(request, response);
                    break;
                }
                changeUser = userFacade.findByLogin(login);
                if(changeUser == null){
                    request.setAttribute("info", "Нет такого пользователя");
                    request.setAttribute("id", id);
                    request.getRequestDispatcher("/editReader")
                        .forward(request, response);
                    break;
                }
                changeUser.setLogin(login);
                EncriptPass ep = new EncriptPass();
                String encriptPassword = ep.setEncriptPass(password1, changeUser.getSalts());
                changeUser.setPassword(encriptPassword);
                try {
                    userFacade.edit(changeUser);
                    reader = readerFacade.find(Long.parseLong(id));
                    reader.setName(name);
                    reader.setSurname(surname);
                    reader.setPhone(phone);
                    //Запись данных в базу
                    readerFacade.edit(reader);
                    request.setAttribute("reader", reader);
                    request.getRequestDispatcher("/listReaders").forward(request, response);
                } catch (Exception e) {
                   request.setAttribute("info", "Данные не изменены");
                    request.setAttribute("id", id);
                    request.getRequestDispatcher("/editReader")
                        .forward(request, response);
                }
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
