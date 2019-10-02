/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.History;
import entity.Reader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "WebController", urlPatterns = {
    "/newBook",
    "/addBook",
    "/newReader",
    "/addReader",
    "/editReader",
    "/changeReader",
    "/listBooks",
    "/showBook",
    "/editBook",
    "/changeBook",
    "/listReaders",
    "/takeBook",
    "/doTakeBook",
    "/returnBook",
    "/doReturnBook",
    
})
public class WebController extends HttpServlet {
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
        switch (path) {
            case "/newBook":
                request.getRequestDispatcher("/newBook.jsp").forward(request, response);
                break;
            case "/addBook":
                String name = request.getParameter("name");
                String author = request.getParameter("author");
                String publichedYear = request.getParameter("publishedYear");
                String isbn = request.getParameter("isbn");
                String quantity = request.getParameter("quantity");
                Book book = null;
                try {
                    if(!"".equals(name) && !"".equals(author)){
                        book = new Book( name, author, isbn, new Integer(publichedYear),Integer.parseInt(quantity));
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
            case "/newReader":
                request.getRequestDispatcher("/newReader.jsp").forward(request, response);
                break;
            case "/addReader":
                name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String phone = request.getParameter("phone");
                Reader reader = null;
                try {
                    if(!"".equals(name) && name != null && !"".equals(surname) && surname != null && !"".equals(phone) && phone != null){
                        reader = new Reader(null, name, surname, phone);
                        readerFacade.create(reader);
                        request.setAttribute("info", "Читатель "
                                + reader.getName()
                                +" "
                                +reader.getSurname()
                                +" добавлен."
                        ); 
                    }else{
                        request.setAttribute("info", 
                            "Читателя добавить не удалось (не корректные данные");
                    }
                } catch (Exception e) {
                    request.setAttribute("info", 
                            "Читателя добавить не удалось (не корректные данные");
                }
                request.getRequestDispatcher("/newReader.jsp").forward(request, response);
                break;

            case "/listBooks":
                List<Book> listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
                break;
            case "/showBook":
                String bookId = request.getParameter("id");
                book = bookFacade.find(Long.parseLong(bookId));
                request.setAttribute("book", book);
                request.getRequestDispatcher("/showBook.jsp").forward(request, response);
                break;
            case "/editBook":
                bookId = request.getParameter("id");
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
                reader = readerFacade.find(Long.parseLong(id));
                request.setAttribute("reader", reader);
                request.getRequestDispatcher("/editReader.jsp")
                        .forward(request, response);
                break;
            case "/changeReader":
                id = request.getParameter("id");
                name = request.getParameter("name");
                surname = request.getParameter("surname");
                phone = request.getParameter("phone");
                reader = readerFacade.find(Long.parseLong(id));
                reader.setName(name);
                reader.setSurname(surname);
                reader.setPhone(phone);
                //Запись данных в базу
                readerFacade.edit(reader);
                request.setAttribute("reader", reader);
                request.getRequestDispatcher("/listReaders").forward(request, response);
                break;
            case "/takeBook":
                listBooks = bookFacade.findTakeBook();
                listReaders = readerFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.setAttribute("listReaders", listReaders);
                request.getRequestDispatcher("/takeBook.jsp").forward(request, response);
                break;
            case "/doTakeBook":
                String readerId = request.getParameter("readerId");
                bookId = request.getParameter("bookId");
                try {
                    reader = readerFacade.find(Long.parseLong(readerId));
                    book = bookFacade.find(Long.parseLong(bookId));
                    if(book.getCountInLibrary()>0){
                        book.setCountInLibrary(book.getCountInLibrary()-1);
                        bookFacade.edit(book);
                        History history = new History();
                        history.setBook(book);
                        history.setReader(reader);
                        history.setTakeBook(new Date());
                        historyFacade.create(history);
                        request.setAttribute("info", "Книга выдана");
                    }else{
                        request.setAttribute("info", "Книги нет в наличии");
                    }
                } catch (Exception e) {
                    request.setAttribute("info", "Не корректные данные");
                }
                request.getRequestDispatcher("/takeBook")
                        .forward(request, response);
                break;
            case "/returnBook":
                List<History> listHistories = historyFacade.findNotReturnedBook();
                request.setAttribute("listHistories", listHistories);
                request.getRequestDispatcher("/returnBook.jsp")
                        .forward(request, response);
                break;
            case "/doReturnBook":
                String historyId = request.getParameter("historyId");
                try {
                    History history = historyFacade.find(Long.parseLong(historyId));
                    history.setReturnBook(new Date());
                    book = history.getBook();
                    if(book.getQuantity() > book.getCountInLibrary()){
                        book.setCountInLibrary(book.getCountInLibrary()+1);
                        bookFacade.edit(book);
                        historyFacade.edit(history);
                        request.setAttribute("info", "Книга \""+book.getName()+"\"возвращена.");
                    }else{
                        request.setAttribute("info", "Книга \""+book.getName()+"\" уже была возвращена раньше");
                    }
                } catch (Exception e) {
                     request.setAttribute("info", "Не корректные данные");

                }
                request.getRequestDispatcher("/returnBook")
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
