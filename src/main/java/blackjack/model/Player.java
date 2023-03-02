package blackjack.model;

import blackjack.model.state.Blackjack;
import blackjack.model.state.Bust;
import blackjack.model.state.State;

public class Player {
    private final State state;
    private final String name; //Name 클래스로 대체 예정

    public Player(State state, String name) {
        this.state = state;
        this.name = name;
    }

    public boolean isBlackjack(){
        return state instanceof Blackjack;
    }

    public boolean isBust(){
        return state instanceof Bust;
    }
}
