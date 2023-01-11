package intra.service.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

public class KinParser implements Parser {
    public int parse(String html, String targetURI) {
        //URI의 dirId와 docId만 같으면 됨
        HashMap<String, String> params = Parser.parseUriParams(targetURI);
        if (params == null) return -1;

        Document doc = Jsoup.parse(html);
        Elements lists = doc.select("._svp_item");
        for (Element element : lists) {
            String uri = element.select(".question_text").first().attr("href");
            if (uri.contains(params.get("dirId")) && uri.contains(params.get("docId"))) {
                int rank = Integer.parseInt(params.get("rank"));
                return rank;
            }
        }
        return -1;
    }
}
