package intra.service.crawler;

import java.io.IOException;
import java.util.concurrent.Callable;

public abstract class Crawler{
    private String keyword;
    private String targetURI;


    public Crawler(String keyword, String targetURI) {
        this.keyword = keyword;
        this.targetURI = targetURI;
    }

    protected abstract Parser getParser();
    protected abstract LinkMaker getLinkMaker();


    public int start() {
        String[] links = this.getLinkMaker().get(keyword);
        int rank = -1;
        for (String link : links) {
            //비동기를 사용하면 1,2페이지에서 결과가 나오는 경우에도 끝까지 찾아야한다.
            //traffic증가에 따른 네트워크 비용증가.
            String html = null;
            try {
                html = Http.request(link);
                rank = this.getParser().parse(html, targetURI);
                if (rank != -1) break;
            } catch (IOException | InterruptedException e) {
                break;
            }
        }

        /*
         * TODO: db에 rank 저장
         */
        return rank;
    }

}
