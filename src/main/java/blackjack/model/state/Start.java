package blackjack.model.state;

import blackjack.model.Cards;

public class Start implements State {
    private Cards cards;

    public Start(Cards cards) {
        this.cards = cards;
    }

    public State initialize(){

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
