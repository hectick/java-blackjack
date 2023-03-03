package blackjack.model.state;

import blackjack.model.card.CardScore;
import blackjack.model.card.Cards;
import blackjack.model.card.OwnedCards;

public class BustState extends State {
    public BustState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public CardScore getScore() {
        return super.getScore();
    }

    @Override
    public State draw(Cards cardDeck) {
        return this;
    }
}
