package blackjack.model.card;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OwnedCards {
    private final List<Card> cards;

    public OwnedCards() {
        this.cards = new ArrayList<>();
    }

    public OwnedCards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public CardScore score(){
        List<CardNumber> ownedNumbers = cards.stream().map(Card::getCardNumber).collect(Collectors.toList());
        return new CardScore(ownedNumbers);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
