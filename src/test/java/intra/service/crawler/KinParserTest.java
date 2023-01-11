package intra.service.crawler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class KinParserTest {
    KinParser parser = new KinParser();

    @Test
    @DisplayName("targetURI가 html에 있는경우")
    void targetURI_o () throws IOException {
        //given
        String targetURI = "https://kin.naver.com/qna/detail.naver?d1id=1&dirId=1040201&docId=435824967&qb=7J6Q67CU&enc=utf8&section=kin.ext&rank=2&search_sort=0&spq=0";
        String html = Files.readString(Path.of("src/test/resources/kin0"));

        //when
        int rank = parser.parse(html, targetURI);

        //then
        Assertions.assertThat(rank).isEqualTo(2);
    }

    @Test
    @DisplayName("targetURI가 html에 없는경우")
    void targetURI_x () throws IOException {
        //given
        String targetURI = "https://blog.naver.com/NONO/";
        String html = Files.readString(Path.of("src/test/resources/kin0"));

        //when
        int rank = parser.parse(html, targetURI);

        //then
        Assertions.assertThat(rank).isEqualTo(-1);
    }
}
