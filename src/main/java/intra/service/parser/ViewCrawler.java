package intra.service.parser;

public class ViewCrawler extends Crawler {
    public ViewCrawler(String keyword, String targetURI) {
        super(keyword, targetURI);
    }

    @Override
    protected Parser getParser() {
        return new ViewParser();
    }

    @Override
    protected LinkMaker getLinkMaker() {
        return new ViewLinkMaker();
    }
}
