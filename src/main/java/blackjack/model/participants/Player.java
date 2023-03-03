package blackjack.model.participants;

import blackjack.model.card.Cards;
import blackjack.model.state.DrawState;

public class Player extends Participant {

    public Player(Name name) {
        super(name);
    }

    public void play(Cards cards) {
        this.currentState = currentState.draw(cards);
    }

    public boolean isFinished() {
        return !(this.currentState instanceof DrawState);
    }

    public void changeToStand() {
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).turnStandState();
        }
    }

}
