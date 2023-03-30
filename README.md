# vending-machine
자판기 프로그램 만들기

1. 환경설정
JDK : 1.8
 WAS : Tomcat 8.5
 Framework : Spring MVC

2. 기능 요건
< 사용자 페이지 >
- 판매물품(음료) 목록을 가격 및 재고 수와 함께 표시 : 하나의 음료당 최대 30개
- 투입금액 입력 후 물품을 선택하고, "구매" 버튼 클릭
- 투입금액에서 구매 물품 가격을 뺀 나머지 금액을 잔액 항목에 표시(기타 기본적인 Validation은 알아서 처리)

< 관리자 페이지 >
- 판매물품 목록을 엑셀로 업로드 할 수 있어야 함.
   : 업로드한 내용이 사용자 페이지의 판매물품 목록으로 나타나야 함.(단, 하나의 음료당 30개를 넘을 수 없음)
- 판매 이력을 조건별로 조회할 수 있어야 함.
   : 날짜별, 물품별 등
- 판매 이력을 엑셀로 다운로드 할 수 있어야 함.
- 통계 조회를 할 수 있어야 함.
  : 전체 판매량, 전체 판매액, 일별 평균 판매량, 일별 평균 판매액, 월별 평균 판매량, 월별 평균 판매액
    (Graph로 표시되면 더 좋음. -> 선택 옵션)

3. 제약 조건
- 반드시 모든 연산과 관련된 부분은 BigDecimal 클래스를 사용하여야 한다.