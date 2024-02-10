package nextstep.subway.unit;


import nextstep.subway.application.PathService;
import nextstep.subway.application.dto.PathResponse;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static nextstep.subway.acceptance.SectionSteps.구간을_등록한다;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PathServiceMockTest {
    private Station 교대역;
    private Station 강남역;
    private Station 양재역;
    private Station 남부터미널역;
    private Line 이호선;
    private Line 신분당선;
    private Line 삼호선;

    @BeforeEach
    public void setUp() {
        교대역 = new Station(1L, "교대역");
        강남역 = new Station(2L, "강남역");
        양재역 = new Station(3L, "양재역");
        남부터미널역 = new Station(4L, "남부터미널역");

        이호선 = new Line(1L, "2호선", "green", 교대역, 강남역, 10);
        신분당선 = new Line(2L, "신분당선", "red", 강남역, 양재역, 10);
        삼호선 = new Line(3L, "3호선", "orange", 교대역, 남부터미널역, 2);
        삼호선.addSection(남부터미널역, 양재역, 3);
    }
    @DisplayName("최단거리 경로를 조회한다.")
    @Test
    void findPath() {
        // Given

        // When
        final PathService pathService = new PathService();
        final PathResponse pathResponse = pathService.findPath(교대역.getId(), 양재역.getId());

        // Then
        final List<Station> stations = pathResponse.getStations();
        assertThat(stations.get(0).getName()).isEqualTo("교대역");
        assertThat(stations.get(1).getName()).isEqualTo("남부터미널역");
        assertThat(stations.get(2).getName()).isEqualTo("양재역");
        assertThat(pathResponse.getDistance()).isEqualTo(5);
    }
}
