package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardScore;
import blackjack.model.card.Cards;
import blackjack.model.card.OwnedCards;

public class InitialState extends State {

    private static final int PICK_COUNT = 2;

    public InitialState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public State draw(Cards cardDeck) {
        for (int i = 0; i < PICK_COUNT; i++) {
            Card picked = cardDeck.pick();
            ownedCards.add(picked);
        }
        if (ownedCards.score().bigScore() == BLACKJACK_NUMBER) {
            return new BlackjackState(ownedCards);
        }
        return new DrawState(ownedCards);
    }

    @Override
    public CardScore getScore() {
        return super.getScore();
    }
}
