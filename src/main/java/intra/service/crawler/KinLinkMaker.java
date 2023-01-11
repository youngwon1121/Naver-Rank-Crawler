package intra.service.crawler;

public class KinLinkMaker implements LinkMaker {

    public String[] get(String keyword) {
        String[][] params = {{"where", "kin"}, {"query", keyword}};

        String baseURL = "https://search.naver.com/search.naver?";

        for (String[] param : params) {
            baseURL += param[0] + "=" + param[1] + "&";
        }

        String[] links = new String[10];
        for (int i=0;i<10;i++) {
            String link = baseURL + "kin_start=" + (i*10+1);
            links[i] = link;
        }
        return links;
    }
}
