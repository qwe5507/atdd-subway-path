# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

## Step1
### 프로그래밍 요구사항
- 인수 테스트 주도 개발 프로세스
- 인수 조건은 인수 테스트 메소드 상단에 주석으로 작성
- 인수 테스트 이후 기능 구현은 **TDD**
    - 도메인 레이어는 테스트 필수
    - 서비스 레이어는 테스트 선택

### 기능 요구사항
#### 역 사이에 새로운 역 등록
- 기존 구간 사이에 역 등록
  - `A -(10)- C`에 `A -(3)- B`추가 -> `A -(3)- B -(7)- C`
  - `A -(10)- C`에 `B -(3)- C`추가 -> `A -(7)- B -(3)- C`
- 새로운 역을 상행 종점역으로 등록
  - `A -(10)- C`에 `B -(3)- A`추가 -> `B -(3)- A -(10)- C`
- 새로운 역을 하행 종점역으로 등록
  - `A -(10)- C`에 `C -(3)- B`추가 -> `A -(10)- C -(3)- B`
- 예외
  - 상행역과 하행역 중 하나라도 노선에 포함되어 있어야 함
  - 상행역과 하행역 모두 같을 수 없음
  - 기존 구간과 신규 구간의 길이가 같을 수 없음
