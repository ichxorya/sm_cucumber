Feature: Mario-Items interactions
  As a player who wants to know how different items affect Mario,
  I want to enter a string consisting of:
  - "nothing"
  - "mushroom"
  - "flower"
  - "feather"
  - "coin"
  - "enemy's bomb"
  And a string that defines the type of output:
  - "final_state" - the state of Mario
  - "final_coins" - the number of coins Mario has collected
  The words of the first string are separated by " "; and all words' cases (and the number of spaces) are irrelevant.
  So that I can get one of the four strings which represent the following states of Mario:
  - "Dead"
  - "Mario"
  - "SuperMario"
  - "FireMario"
  - "CapeMario"

  Scenario Outline: One-item interactions when Mario is alive (Return the final state).
    Given Mario is alive and we want to know his final state
    When Mario touches "<item>"
    Then Mario is "<final_state>"

    Examples:
      | item             | final_state      |
      | nothing          | Mario            |
      | mushroom         | SuperMario       |
      | flower           | FireMario        |
      | feather          | CapeMario        |
      | bomb             | Dead             |
      | coin             | Mario            |

    Scenario Outline: Many-items interactions when Mario is alive (Return the final state).
      Given Mario is alive and we want to know his final state
      When Mario touches "<items>"
      Then Mario is "<final_state>"

      Examples:
        | items                          | final_state      |
        | nothing nothing nothing        | Mario            |
        | mushroom bomb mushroom         | SuperMario       |
        | flower feather coin flower     | FireMario        |
        | feather flower feather coin    | CapeMario        |
        | bomb mushroom feather          | Dead             |
        | coin coin coin                 | Mario            |

    Scenario Outline: Many-items interactions when Mario is alive (Return the final number of coins).
        Given Mario is alive and we want to know his final coins
        When Mario touches "<items>"
        Then Mario has "<final_coins>" coins

        Examples:
          | items                           | final_coins      |
          | nothing nothing nothing         | 0                |
          | mushroom bomb mushroom          | 0                |
          | flower feather coin flower      | 1                |
          | coin coin coin                  | 3                |
          | coin flower bomb bomb bomb coin | 1                |
          | coin feather bomb coin bomb     | 2                |

    Scenario Outline: One-item interactions when Mario is dead (Return the final state).
      Given Mario is dead and we want to know his final state
      When Mario touches "<item>"
      Then Mario is "<final_state>"

      Examples:
        | item             | final_state      |
        | nothing          | Dead             |
        | mushroom         | Dead             |
        | flower           | Dead             |
        | feather          | Dead             |
        | bomb             | Dead             |
        | coin             | Dead             |

  Scenario Outline: Many-items interactions when Mario is (certainly) dead (at some point).
    Given Mario will be dead and we want to know his final value of "<return_type>"
    When Mario touches "<items>"
    Then What we get is "<return_value>"

    Examples:
      | items                               | return_type      | return_value     |
      | bomb nothing nothing nothing        | final_state      | Dead             |
      | bomb mushroom bomb mushroom         | final_state      | Dead             |
      | bomb flower feather coin flower     | final_state      | Dead             |
      | bomb feather flower feather coin    | final_state      | Dead             |
      | bomb mushroom feather               | final_state      | Dead             |
      | bomb coin coin coin                 | final_state      | Dead             |
      | bomb nothing nothing nothing        | final_coins      | 0                |
      | bomb mushroom bomb mushroom         | final_coins      | 0                |
      | bomb flower feather coin flower     | final_coins      | 0                |
      | coin coin coin bomb coin coin       | final_coins      | 3                |
      | coin flower bomb bomb bomb coin     | final_coins      | 1                |
      | coin feather bomb bomb coin bomb    | final_coins      | 2                |
