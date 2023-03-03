package blackjack.model.participants;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.card.OwnedCards;
import blackjack.model.state.InitialState;
import blackjack.model.state.State;

import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected State currentState;

    public Participant(Name name) {
        this.name = name;
        this.currentState = new InitialState(new OwnedCards());
    }

    public abstract void play(Cards cardDeck);

    public abstract boolean isFinished();

    public String getName() {
        return name.getName();
    }

    public List<Card> getOwnedCards() {
        return currentState.getOwnedCards();
    }
}
