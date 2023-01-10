package intra.service.parser;

public class KinCrawler extends Crawler {
    public KinCrawler(String keyword, String targetURI) {
        super(keyword, targetURI);
    }

    @Override
    protected Parser getParser() {
        return new KinParser();
    }

    @Override
    protected LinkMaker getLinkMaker() {
        return new KinLinkMaker();
    }
}
