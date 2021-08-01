[Java Doc](api/com/example/ex3_goldman_nachman/package-summary.html)
## A Java program searches for and summarizes the images number in a given URL.

I built map to store the thread called Crawler, the index (or key) is the session ID
and the value is my own class that have a Crawler class that is actually thread.  
    the Servlets and classes that manage the program.  
    1. Listener (Web Listener) - when the context initialized creates a CrawlerMap instance.  
    2. CrawlerMap (class) - holds a (String, Crawler) map the main data structure in this program, the key is session ID  
    3. Crawler (class) - the thread that summarize images number of given URL.  
    4. Index (index-servlet) - display the index.html - a form that reads url from user.  
    5. AddThread (servlet) - create a Crawler thread to handle the given url, and add him to CrawlerMap.  
    6. SeeResult (servlet) - introduce the results of images searching process.  
