package blackjack.model.state;

import blackjack.model.Cards;

public class Hit implements State {
    public Hit(Cards cards) {
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
