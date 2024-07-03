package com.exa;  // Make sure this matches your project's package structure

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CochraneCrawler {

    public static void main(String args[]) throws IOException {
        // Initialize URLHandler object
        URLHandler urlHandler = new URLHandler();

        // Initiate session and get the first topic element
        Document homePage = urlHandler.intiateSession();
        Element topicElement = urlHandler.getTopic(homePage);

        // Loop through each topic
        while (topicElement != null) {
            String topicUrl = topicElement.select("a[href]").attr("href");
            String topic = topicElement.text();

            // Get the reviews page for the current topic
            Document reviewPage = urlHandler.getReviewsPage(topicUrl);

            // Process the reviews page and write data to file
            processReviewsPage(reviewPage, topic);

            // Get the next topic element
            topicElement = urlHandler.getTopic(homePage);
        }
    }

    // Helper method to process reviews from a webpage
    public static void processReviewsPage(Document reviewPage, String topic) throws IOException {
        // Extract review elements from reviewPage
        Elements reviewElements = reviewPage.getElementsByClass("search-result");

        // Use FileHandler to write data
        FileHandler.createFile(reviewElements, topic);
    }
}
