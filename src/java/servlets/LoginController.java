/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.Reader;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookFacade;
import session.ReaderFacade;
import session.UserFacade;
import util.EncriptPass;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "LoginController", urlPatterns = {
    "/showLogin",
    "/login",
    "/logout",
    "/newReader",
    "/addReader",
    "/listBooks",
    

})
public class LoginController extends HttpServlet {
   @EJB UserFacade userFacade;
   @EJB ReaderFacade readerFacade;
   @EJB BookFacade bookFacade;
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
        EncriptPass ep = new EncriptPass();
        String path = request.getServletPath();
        switch (path) {
            case "/showLogin":
                request.getRequestDispatcher("/showLogin.jsp")
                        .forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/showLogin.jsp")
                        .forward(request, response);
                }
                password = ep.setEncriptPass(password,user.getSalts());
                if(!password.equals(user.getPassword())){
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/showLogin.jsp")
                        .forward(request, response);
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                request.setAttribute("info", "Вы вошли в систему как "+login);
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(null != session){
                    session.invalidate();
                }
                request.setAttribute("info", "Вы вышли из системы");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/newReader":
                request.getRequestDispatcher("/newReader.jsp").forward(request, response);
                break;
            case "/addReader":
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if(!(password1 != null && password1.equals(password2))){
                    request.setAttribute("info", 
                            "Читателя добавить не удалось (не корректные данные");
                    request.getRequestDispatcher("/newReader.jsp").forward(request, response);
                    break;
                }
                Reader reader = null;
                try {
                    if(!"".equals(name) && name != null 
                            && !"".equals(surname) && surname != null 
                            && !"".equals(phone) && phone != null
                            && !"".equals(login) && login != null
                            && !"".equals(password1) && password1 != null
                            ){
                        reader = new Reader(null, name, surname, phone);
                        readerFacade.create(reader);
                        String salts = ep.createSalts();
                        password = ep.setEncriptPass(password1,salts);
                        user = new User(login, salts, password, reader);
                        try {
                            userFacade.create(user);
                        } catch (Exception e) {
                           readerFacade.remove(reader);
                           throw new Exception(e);
                        }
                        request.setAttribute("info", "Читатель "
                                + reader.getName()
                                +" "
                                +reader.getSurname()
                                +" добавлен."
                        ); 
                    }else{
                        request.setAttribute("info", 
                            "Читателя добавить не удалось (не корректные данные");
                        request.getRequestDispatcher("/newReader.jsp").forward(request, response);
                        break;
                    }
                } catch (Exception e) {
                    request.setAttribute("info", 
                            "Читателя добавить не удалось (не корректные данные");
                    request.getRequestDispatcher("/newReader.jsp").forward(request, response);
                    break;
                }
                request.getRequestDispatcher("/newReader.jsp").forward(request, response);
                break;
            case "/listBooks":
                List<Book> listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
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
