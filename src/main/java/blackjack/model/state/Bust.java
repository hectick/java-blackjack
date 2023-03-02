package blackjack.model.state;

import blackjack.model.Cards;

public class Bust implements State {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public State draw() {
        return null;
    }

    @Override
    public State stand() {
        return null;
    }

    @Override
    public State bust() {
        return null;
    }

    @Override
    public State blackjack() {
        return null;
    }
}
