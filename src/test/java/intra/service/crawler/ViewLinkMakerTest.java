package intra.service.crawler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ViewLinkMakerTest {
    ViewLinkMaker linkMaker = new ViewLinkMaker();
    @Test
    @DisplayName("링크가 정상적으로 만들어지는지 확인")
    void getLinks() {
        //given
        String keyword = "자바";

        //when
        String[] links = linkMaker.get(keyword);

        //then
        Assertions.assertThat(links[0]).isEqualTo("https://search.naver.com/search.naver?where=view&query=자바&start=1");
        Assertions.assertThat(links[1]).isEqualTo("https://search.naver.com/search.naver?where=view&query=자바&start=31");
        Assertions.assertThat(links[2]).isEqualTo("https://search.naver.com/search.naver?where=view&query=자바&start=61");
    }
}