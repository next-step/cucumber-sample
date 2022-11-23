Feature: 가게 기능

  Scenario: 가게 입점 신청
    When 사장님이 입점을 신청한다
    Then 어드민이 입점 신청 목록에서 확인할 수 있다

  Scenario: 가게 입점 승인
    Given 사장님이 입점을 신청한다
    When 어드민이 입점을 승인한다
    Then 사장님이 입점 승인 상태를 확인할 수 있다

  Scenario: 가게 입점 거절
    Given 사장님이 입점을 신청한다
    When 어드민이 입점을 거절한다
    Then 사장님이 입점 거절 상태를 확인할 수 있다