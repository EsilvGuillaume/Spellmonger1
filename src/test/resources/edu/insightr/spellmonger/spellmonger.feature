Feature: SpellmongerGame

  Scenario: GameInitialisation
    Given player "Alice" joins the game
    And player "Bob" joins the game
    Then Alice has 20 life points and 5 creatures and 1 energy point and 40 cards in his/her deck
    And Bob has 20 life points and 5 creatures and 1 energy point and 40 cards in his/her deck

  Scenario: GamePhase
    Given the "currentPlayer" is the one who clicks first the draw button
    And "oppositePlayer"'s draw button is diabled

    Then the "currentPlayer" gets a new card from his/her deck
    And the "currentPlayer" adds it to his/her existing creatures
    Then if the "currentPlayer" has enough energy points to summon a creature
    Then the "currentPlayer" choose the corresponding creature and summon it

    And if there are no creatures on the opposite field
    Then the creatures summoned by the the "currentPlayer" attack directly the "oppositePlayer"

    But if there are creatures on the opposite field
    Then the battle phase between creatures begins
    And after the battle phase the remaining creatures deal their remaining strength as damage

    But if the "currentPlayer" wants to summon a ritual
    Then he/she can sommun it directly and profits the effects of the ritual

    Then the "oppositePlayer" becomes the "currentPlayer" and his/her draw button is enabled
    And both "currentPlayer" and "oppositePlayer" get 1 energy point

  Scenario: EndGame
    When a "player"'s life points attain 0
    Then the "otherPlayer" is the winner

