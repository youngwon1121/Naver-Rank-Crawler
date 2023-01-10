package intra.service.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parseUriParams() {
        //given
        String uri = "https://search.naver.com/search.naver?where=kin&query=자바&kin_start=1";

        //when
        HashMap<String, String> params = Parser.parseUriParams(uri);

        //then
        Assertions.assertThat(params.get("where")).isEqualTo("kin");
        Assertions.assertThat(params.get("query")).isEqualTo("자바");
        Assertions.assertThat(params.get("kin_start")).isEqualTo("1");
    }
}