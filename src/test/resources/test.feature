Feature: Input and output
  As a user
  I want to enter a string consisting of "nothing", "mushroom", "flower", "feather" separated by " "
  So that I can get one of the four integers  "Mario", "SuperMario", "FireMario", "CapeMario" according to some logic
  Scenario Outline: Input and output
    Given I have a Java program
    When I enter the string "<input>" as input
    Then I should get "<output>" as output
    Examples:
      | input                                              | output    |
      | flower nothing mushroom nothing feather flower     | FireMario |
      | mushroom mushroom feather flower mushroom mushroom | FireMario |
      | mushroom flower mushroom nothing nothing feather   | CapeMario |

    

