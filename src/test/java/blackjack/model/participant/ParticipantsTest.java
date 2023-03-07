package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.state.InitialState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParticipantsTest {

    @Test
    @DisplayName("다음으로 hit 또는 stand 입력을 받을 플레이어가 남아있는지 확인한다.")
    void has_nex_player() {
        //given
        Dealer dealer = new Dealer(new InitialState(new Hand()));
        Player player1 = new Player(new Name("도치"), new InitialState(new Hand()));
        Player player2 = new Player(new Name("이리내"), new InitialState(new Hand()));
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.ACE);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.JACK);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.EIGHT);
        Card card4 = Card.of(CardSuit.DIAMOND, CardNumber.JACK);
        Card card5 = Card.of(CardSuit.SPADE, CardNumber.SEVEN);
        List<Card> cards = List.of(card5, card3, card4, card1, card2);
        CardDeck cardDeck = new CardDeck(cards);
        Participants participants = new Participants(dealer, new ArrayList<>(List.of(player1, player2)));

        //when
        player1.play(cardDeck);
        player2.play(cardDeck);
        boolean isExist1 = participants.hasNextPlayer();
        player2.play(cardDeck);
        boolean isExist2 = participants.hasNextPlayer();

        //then
        assertThat(isExist1).isTrue();
        assertThat(isExist2).isFalse();
    }

    @Test
    @DisplayName("다음으로 hit 또는 stand 입력을 받을 플레이어를 반환할 때 Blackjack 상태의 플레이어는 건너뛰고 반환한다.")
    void get_next_player() {
        //given
        Dealer dealer = new Dealer(new InitialState(new Hand()));
        Player player1 = new Player(new Name("도치"), new InitialState(new Hand()));
        Player player2 = new Player(new Name("이리내"), new InitialState(new Hand()));
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.ACE);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.JACK);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.EIGHT);
        Card card4 = Card.of(CardSuit.DIAMOND, CardNumber.JACK);
        List<Card> cards = List.of(card3, card4, card1, card2);
        CardDeck cardDeck = new CardDeck(cards);
        Participants participants = new Participants(dealer, new ArrayList<>(List.of(player1, player2)));

        //when
        player1.play(cardDeck);
        player2.play(cardDeck);
        Player nextPlayer = participants.getNextPlayer();

        //then
        assertThat(nextPlayer).isEqualTo(player2);
    }

}