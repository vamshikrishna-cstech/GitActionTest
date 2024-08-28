Feature: Vcare Login Feature

  Scenario Outline: validate vcare login scenario
    Given user launches the url
    When user enters "<username>" and "<password>"
    Then user tries to logged into the application
    And validate the Alerts "<Alert>"

    Examples: 
      | username              | password | Alert                       |
      | krishna4360@gmail.com | Pass@123 | User is on Dashborad page   |