package blackjack.model;

import blackjack.model.state.Blackjack;
import blackjack.model.state.Bust;
import blackjack.model.state.Hit;
import blackjack.model.state.State;

public class Dealer {
    private final State state;

    public Dealer(State state) {
        this.state = state;
    }

    public boolean isBlackjack(){
        return state instanceof Blackjack;
    }

    public boolean isBust(){
        return state instanceof Bust;
    }

    public boolean get
}
