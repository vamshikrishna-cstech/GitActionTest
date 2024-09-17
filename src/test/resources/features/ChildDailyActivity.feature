Feature: Childs Daily Activity Feature

  Background: User logged into the application
    Given user launches the url
    When user enters "krishna4360@gmail.com" and "Pass@123"
    Then user tries to logged into the application
    Then user will click on Reports Feature
@regression
  Scenario Outline: User tries to Genarate Childs Daily Activity Report
    Given User will click on View Report button of Childs Daily Activity
    Then User tries to Generate the Childs Daily Activity Report "<Class>" "<Child>" "<Select Date>"
    And User will validate the Childs Daily Activity Report Alerts "<Alert>"

    Examples: 
      | Class | Child        | Select Date | Alert                                         |
      | CANDY | Harry Potter |    19012023 | No Activity of Child Found for selected Date! |
      #| CANDY | Harry Potter |    19012023 | Report Genarated Successfully                 |
