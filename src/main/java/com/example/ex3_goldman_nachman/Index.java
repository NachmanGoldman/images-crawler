package com.example.ex3_goldman_nachman;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Introduces the index.html page, this is the first Servlet.
 */
@WebServlet(name = "Index", value = "")
public class Index extends HttpServlet {
    /**
     * @param request, the request of the user.
     * @param response, the response to the user.
     * The main function in this class, introduces the index.html page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Index.html").include(request, response);
    }

    /**
     * @param request, the request of the user.
     * @param response, the response to the user.
     * opss... Redirect to main/index page.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }
}
