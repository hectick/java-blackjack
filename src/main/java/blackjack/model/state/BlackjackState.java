package blackjack.model.state;

import blackjack.model.card.CardScore;
import blackjack.model.card.Cards;
import blackjack.model.card.OwnedCards;

public class BlackjackState extends State{
    public BlackjackState(OwnedCards ownedCards) {
        super(ownedCards);
    }

    @Override
    public State draw(Cards cardDeck) {
        return this;
    }

    @Override
    public CardScore getScore() {
        return super.getScore();
    }
}
