package blackjack.model.participants;

import blackjack.model.card.Cards;
import blackjack.model.participants.Player;

import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void play(Cards cardDeck) {
        for (Player player : players) {
            player.play(cardDeck);
        }
    }

}
