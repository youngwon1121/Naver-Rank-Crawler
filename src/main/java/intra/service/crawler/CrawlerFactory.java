package intra.service.crawler;

import intra.service.domain.Target;
import intra.service.domain.Type;

public class CrawlerFactory {
    public static Crawler get(Target target) {
        if (target.getType() == Type.KIN) {
            return new KinCrawler(target.getKeyword(), target.getUri());
        } else if (target.getType() == Type.VIEW) {
            return new ViewCrawler(target.getKeyword(), target.getUri());
        }
        return null;
    }
}
