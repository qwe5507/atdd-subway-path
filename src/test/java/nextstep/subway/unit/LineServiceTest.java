package nextstep.subway.unit;

import nextstep.subway.applicaion.LineService;
import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.applicaion.exception.domain.SectionException;
import nextstep.subway.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class LineServiceTest {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineService lineService;

    Station 강남역;
    Station 양재역;
    Station 정자역;
    Line 신분당선;

    @BeforeEach
    void setUp() {
        강남역 = new Station("강남역");
        양재역 = new Station("양재역");
        정자역 = new Station("정자역");
        신분당선 = new Line("신분당선", "green");

        stationRepository.save(강남역);
        stationRepository.save(양재역);
        stationRepository.save(정자역);
        lineRepository.save(신분당선);
    }

    @DisplayName("지하철 노선에 새로운 역 추가.")
    @Test
    void addSection() {
        // given
        // stationRepository와 lineRepository를 활용하여 초기값 셋팅
        신분당선.addSection(new Section(신분당선, 강남역, 양재역, 7));

        // when
        // lineService.addSection 호출
        SectionRequest sectionRequest = new SectionRequest(강남역.getId(), 정자역.getId(), 3);
        lineService.addSection(신분당선.getId(), sectionRequest);

        // then
        // line.getSections 메서드를 통해 검증
        Assertions.assertThat(신분당선.getSections()).hasSize(2);
    }

    @DisplayName("구간이 하나인 노선에서 구간 삭제 요청을 하면 예외가 발생한다.")
    @Test
    void removeSectionSavedOneSection() {
        // when
        HttpStatus result = HttpStatus.OK;

        try {
            lineService.deleteSection(신분당선.getId(), 강남역.getId());
        } catch (SectionException e) {
            result = e.getStatus();
        }

        // then
        Assertions.assertThat(result.value()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}