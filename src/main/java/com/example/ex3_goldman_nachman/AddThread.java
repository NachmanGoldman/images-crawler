package com.example.ex3_goldman_nachman;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

/**
 * This class used like a controller, that mean - it's handles any request to crawl a URL by create a Crawler thread for him.
 */
@WebServlet(name = "AddThread", value = "/AddThread")
public class AddThread extends HttpServlet {
    /**
     * Holds the context to print on the HTML page.
     */
    StringBuilder toHTML = new StringBuilder();

    /**
     * @param request, the request of the user.
     * @param response, the response to the user.
     * opss... Redirect to main/index page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }

    /**
     * @param request, the request of the user.
     * @param response, the response to the user.
     * The main function in this class, get a url, create Crawler thread instance and add him to crawlerMap.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = request.getParameter("URL");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("/head.html").include(request, response);

        int responseCode = 0;
        try {
            URL checkUrl = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) checkUrl.openConnection();
            huc.setRequestMethod("HEAD");
            responseCode = huc.getResponseCode();
        } catch (IOException e) {
            faildURL("The URL is incorrect, Please back to the form...");
            printEnd(request, response, out);
            return;
        }

        if (HttpURLConnection.HTTP_OK != responseCode) {
            faildURL("URL doesn't work...");
            return;
        } else {
            int depth = Integer.parseInt(getServletContext().getInitParameter("maxdepth"));
            Crawler crawler = new Crawler(url, depth);
            Thread t = new Thread(crawler);
            t.start();
            System.out.println("Starting Thread for url " + url);
            CrawlerMap crawlerMap = (CrawlerMap) request.getServletContext().getAttribute("CrawlerMap");
            crawlerMap.addCrawler(session.getId(), crawler);

            toHTML.append(MessageFormat.format("<p>Crawling of \"{0}\" started... </p><br>", url));
            toHTML.append("<p>Visit <a href=\"/SeeResult\">This page</a> to track results.</p>");
        }
        printEnd(request, response, out);
    }

    /**
     * @param request, the request of the user.
     * @param response, the response to the user.
     * @param out, the PrintWriter
     * Print the end of the HTML page.
     */
    private void printEnd(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
        out.print(toHTML);
        toHTML.setLength(0);
        request.getRequestDispatcher("/end.html").include(request, response);
        out.close();
    }

    /**
     * @param s, the exception/warning massage.
     * Handles the exception/warning situation.
     */
    private void faildURL(String s) {
        toHTML.append("<p>" + s + "</p><br>");
        toHTML.append("<p>Return <a href=\"/\">to the main page</a></p>.");
    }
}
