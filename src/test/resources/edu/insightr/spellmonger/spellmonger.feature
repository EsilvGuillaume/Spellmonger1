Feature: SpellmongerGame

  Scenario: GameInitialisation
    Given player "Alice" joins the game
    And player "Bob" joins the game
    Then Alice has 20 life points and 5 creatures and 1 energy point and 40 cards in his/her deck
    And Bob has 20 life points and 5 creatures and 1 energy point and 40 cards in his/her deck

  Scenario: GamePhase
    Given the "currentPlayer" is the one who clicks first the draw button
    And "oppositePlayer"'s draw button is disabled

    Then the "currentPlayer" gets a new card from his/her deck
    Then the "currentPlayer" adds it to his/her existing creatures
    Then if the "currentPlayer" has enough energy points to summon a creature
    Then the "currentPlayer" choose the corresponding creature and summon it

    Then if there are no creatures on the opposite field
    Then the creatures summoned by the the "currentPlayer" attack directly the "oppositePlayer"

    Then if there are creatures on the opposite field
    Then the battle phase between creatures begins
    Then if there is a draw between creatures strenght then corresponding creatures make no move
    Then after the battle phase the remaining creatures deal their remaining strength as damage

    Then if the "currentPlayer" wants to summon a ritual
    Then he/she can summon it directly and profits the effects of the ritual

    Then the "oppositePlayer" becomes the "currentPlayer" and his/her draw button is enabled
    Then both "currentPlayer" and "oppositePlayer" get 1 energy point

  Scenario: EndGame
    When a "player"'s life points attain 0
    Then the "otherPlayer" is the winner

