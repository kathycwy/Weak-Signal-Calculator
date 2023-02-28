import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Crawler {

    public static int countTotal = 1;
    public static int countSaved = 1;

    public static void crawl (int level, WebsiteDetailsClass site, String url, ArrayList<String> visited) {
        if (level <= 50) {
            Document doc = request(site, url, visited);
            if (doc != null) {
                for (Element link : doc.select("a[href]")) {
                    String next_link = link.absUrl("href");
                    if (next_link.startsWith(site.rootUrl)) {
                        if (!visited.contains(next_link)) {
                            crawl(level++, site, next_link, visited);
                        }
                    }

                }
            }
        }
    }

    public static Document request(WebsiteDetailsClass site, String url, ArrayList<String> v) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            if (con.response().statusCode() == 200) {
                if (url.matches(site.regex)) {
//                    System.out.println("----doc---- : " + doc);
                    Date date = Scraper.scrapeDate(site.dateSelector, doc, site.dateParser);
                    Date boundary = DateParser.finalFormatter.parse("2013-01-01");
                    if (date.after(boundary)) {
                        String title = Scraper.scrape(site.titleSelector, doc);
                        String content = Scraper.scrape(site.contentSelectors, doc);
                        Save.save(url, DateParser.finalFormatter.format(date), title, content, countSaved++);
                    }
//                } else {
//                    System.out.println("Trying the [" + countTotal++ + "] links: " + url);
//                    System.out.println(" - skip -");
                }

                System.out.println("Trying the [" + countTotal++ + "] links. Saved [" + (countSaved - 1) + "] links.");

                v.add(url);
                return doc;
            }

            return null;
        } catch (IOException | ParseException e) {
//            System.out.println(e);
            return null;
        }
    }

}