package com.example.ex3_goldman_nachman;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * This is a listener to context initialing, when this happens the listener create CrawlerMap instance.
 */
@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    /**
     * The default constructor.
     */
    public Listener() {
    }

    /**
     * @param sce , the servlet context event that indicates that context is created
     * The function create CrawlerMap instance that will used all over the program.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("CrawlerMap", new CrawlerMap());
    }
}
