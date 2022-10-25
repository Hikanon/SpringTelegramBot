package com.tgbot.springtelegrambot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Anekdots {

    public static String getAnekdot(int rand){
        try {
            Document document = Jsoup.connect("http://anekdotov.net/anekdot/one/random" + rand + ".html")
                                     .userAgent("Chrome/4.0.249.0 Safari/532.5")
                                     .referrer("http://www.google.com").get();
            return document.select(".txt2imgtd").text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
