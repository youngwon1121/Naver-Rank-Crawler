package intra.service.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ViewParser implements Parser{
    public int parse(String html, String targetURI) {
        Document doc = Jsoup.parse(html);

        Elements lists = doc.select("._svp_item");
        for (Element element : lists) {
            String uri = element.select(".total_tit").first().attr("href");
            if (uri.contains(targetURI)) {
                int rank = Integer.parseInt(element.attr("data-cr-rank"));
                return rank;
            }
        }
        return -1;
    }
}
