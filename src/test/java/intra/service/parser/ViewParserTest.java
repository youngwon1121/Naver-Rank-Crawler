package intra.service.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ViewParserTest {
    ViewParser parser = new ViewParser();

    @Test
    @DisplayName("targetURI가 html에 있는경우")
    void targetURI_o () throws IOException {
        //given
        String targetURI = "https://blog.naver.com/poohhoo/";
        String html = Files.readString(Path.of("src/test/resources/view0"));

        //when
        int rank = parser.parse(html, targetURI);

        //then
        Assertions.assertThat(rank).isEqualTo(4);
    }

    @Test
    @DisplayName("targetURI가 html에 없는경우")
    void targetURI_x () throws IOException {
        //given
        String targetURI = "https://blog.naver.com/NONO/";
        String html = Files.readString(Path.of("src/test/resources/view0"));

        //when
        int rank = parser.parse(html, targetURI);

        //then
        Assertions.assertThat(rank).isEqualTo(-1);
    }
}
