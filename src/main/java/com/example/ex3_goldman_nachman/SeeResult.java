package com.example.ex3_goldman_nachman;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

/**
 * This servlet introduce the results of images searching process.
 */
@WebServlet(name = "SeeResult", value = "/SeeResult")
public class SeeResult extends HttpServlet {
    /**
     * @param request, the request of the user.
     * @param response, the response to the user.
     * The main function in this class, introduce the results of images searching process.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        CrawlerMap crawlerMap = (CrawlerMap) request.getServletContext().getAttribute("CrawlerMap");
        Crawler crawler = crawlerMap.getCrawler(session.getId());

        PrintWriter out = response.getWriter();
        StringBuilder toHTML = new StringBuilder();

        request.getRequestDispatcher("/head.html").include(request, response);
        toHTML.append(MessageFormat.format("Crawling {0}<br>", crawler.getUrl()));
        toHTML.append("The number of images is: " + crawler.getImgNumber() + "<br>");

        if (!crawler.isFinished()) {
            toHTML.append("crawling... please reload <a href=\"/SeeResult\">this page</a> later for final results<br>");
        } else {
            toHTML.append("crawling is finished!<br>");
        }
        toHTML.append("<a href=\"/\">back to main page</a><br>");
        out.print(toHTML);
        request.getRequestDispatcher("/end.html").include(request, response);
        out.close();
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
