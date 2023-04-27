Feature: Mario-Items chained effect
  As a user who wants to know how different items affect Mario (in a row),
  I want to enter a string consisting of:
    - "nothing"
    - "mushroom"
    - "flower"
    - "feather"
    - "coin"
    - "enemy's bomb"
  And a string that defines the type of output:
    - "state" - the state of Mario (or "final_state")
    - "coin" - the number of coins Mario has (or "coins")
  The words of the first string are separated by " "; and all words' cases (and the number of spaces) are irrelevant.
  So that I can get one of the four strings which represent the following states of Mario:
    - "Dead"
    - "Mario"
    - "SuperMario"
    - "FireMario"
    - "CapeMario"

  Scenario Outline: Input and output
    Given I have a Java program
    When I enter the string "<input>" and "<return_type>" as input
    Then I should get "<output>" as output
    Examples:
      | input                                                   | return_type | output    |
      | flower nothing mushroom nothing feather flower          | final_state | FireMario |
      | mushroom mushroom feather flower mushroom mushroom      | final_coins | 0         |
      | mushroom flower mushroom nothing nothing feather        | final_state | CapeMario |
      | nothing nothing nothing nothing nothing nothing         | final_state | Mario     |
      | bomb coin coin coin coin coin coin coin                 | final_state | Dead      |
      | coin coin coin coin coin coin bomb coin coin coin       | final_coins | 6         |

    

