package intra.service.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public interface Parser {
    public int parse(String html, String targetURI);

     static HashMap<String, String> parseUriParams (String uri) {
         HashMap<String, String> paramsMap = new HashMap<>();
         try {
             URL url = new URL(uri);
             String query = url.getQuery();
             String[] params = query.split("&");
             for (String param : params) {
                 String[] p = param.split("=");
                 paramsMap.put(p[0], p[1]);
             }
         } catch (MalformedURLException e) {

         }
         return paramsMap;
    }
}
