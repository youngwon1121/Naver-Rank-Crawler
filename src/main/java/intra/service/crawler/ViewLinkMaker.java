package intra.service.crawler;

public class ViewLinkMaker implements LinkMaker{
    public String[] get(String keyword) {
        String[][] params = {{"where", "view"}, {"query", keyword}};

        String baseURL = "https://search.naver.com/search.naver?";

        for (String[] param : params) {
            baseURL += param[0] + "=" + param[1] + "&";
        }

        String[] links = new String[3];
        int[] start = {1,31,61};
        for (int i=0;i<3;i++) {
            String link = baseURL + "start=" + start[i];
            links[i] = link;
        }
        return links;
    }
}
