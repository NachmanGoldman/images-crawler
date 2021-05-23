package com.example.ex3_goldman_nachman;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

/**
 *This class implements Runnable and the implements Crawler functionality - by getPageImgs function, the function search for images in given URL.
 *
 */
public class Crawler implements Runnable {

    /**
     * The max depth of serching.
     */
    private int MAX_DEPTH;
    /**
     * Holds all the links in a page.
     */
    private HashSet<String> links;
    /**
     * The sum number of images.
     */
    private int imgNumber = 0;
    /**
     * The url to be looked for.
     */
    private String url;
    /**
     * Indicates if finish the images searching.
     */
    private boolean finished = false;

    /**
     * @param URL ,the initial url to be looked for his images by getPageImgs function.
     * @param depth ,the initial max depth to search of images.
     */
    public Crawler(String URL, int depth) {
        links = new HashSet<>();
        url = URL;
        MAX_DEPTH = depth;
    }

    /**
     * @param URL ,the current url to be looked for his images by getPageImgs function.
     * @param depth ,,the current max depth to search of images.
     * A recursive function that crawls for images, and summarize them.
     */
    public void getPageImgs(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            try {
                links.add(URL);
                System.out.println("Begin crawling " + url + " at depth " + depth);
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                Elements images = document.getElementsByTag("img");
                System.out.println(images.size() + " - number of images found for " + URL);
                imgNumber += images.size();
                depth++;
                for (Element page : linksOnPage) {
                    getPageImgs(page.attr("abs:href"), depth);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }

    }

    /**
     * @return the url that in process.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the number of images.
     */
    public int getImgNumber() {
        return imgNumber;
    }

    /**
     * @return true if finished to summarize images.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * The main function of each Thread class, this function turns the class on.
     */
    @Override
    public void run() {
        getPageImgs(url, 0);
        System.out.println("End crawling " + url + "at depth " + MAX_DEPTH);
        finished = true;
        System.out.println("End of thread for url " + url);
    }
}
