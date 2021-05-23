package com.example.ex3_goldman_nachman;

import java.util.HashMap;

/**
 * The main container of this program, holds the session ID as a key, and Crawler as a value.
 */
public class CrawlerMap {
    /**
     * The main container of this program.
     */
    private HashMap<String, Crawler> map = new HashMap<>();

    /**
     * @param id, the key to requested Crawler.
     * @return Crawler.
     * This function return the Crawler to be performances.
     */
    public synchronized Crawler getCrawler(String id) {
        return map.get(id);
    }

    /**
     * @param id, key to add.
     * @param crawler, value to add.
     * This function add fields to the map.
     */
    public synchronized void addCrawler(String id, Crawler crawler) {
        map.put(id, crawler);
    }

}
