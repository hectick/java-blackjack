package blackjack.model.participants;

import blackjack.model.card.Cards;
import blackjack.model.state.DealerDrawState;
import blackjack.model.state.DrawState;

public class Dealer extends Participant {
    public Dealer() {
        super(new Name("딜러"));
    }

    public void play(Cards cardDeck) {
        this.currentState = currentState.draw(cardDeck);
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).turnDealerDrawState();
        }
    }

    public boolean isFinished() {
        return !(this.currentState instanceof DealerDrawState);
    }
}
