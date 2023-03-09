package blackjack.model.result;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.participant.*;
import blackjack.model.state.InitialState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultTest {

    @Nested
    @DisplayName("플레이어가 버스트일 때")
    class player_bust {

        @Nested
        @DisplayName("딜러가 버스트라면")
        class dealer_bust {
            @Test
            @DisplayName("플레이어가 패배한다")
            void player_lose() {
                //given
                Card card1 = Card.of(CardSuit.SPADE, CardNumber.SIX);
                Card card2 = Card.of(CardSuit.HEART, CardNumber.NINE);
                Card card3 = Card.of(CardSuit.CLUB, CardNumber.QUEEN);
                Card card4 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card5 = Card.of(CardSuit.DIAMOND, CardNumber.SIX);
                Card card6 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4, card5, card6));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, true);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

        @Nested
        @DisplayName("딜러가 블랙잭이라면")
        class dealer_blackjack {
            @Test
            @DisplayName("플레이어가 패배한다")
            void player_lose() {
                //given
                Card card1 = Card.of(CardSuit.HEART, CardNumber.FIVE);
                Card card2 = Card.of(CardSuit.CLUB, CardNumber.QUEEN);
                Card card3 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card4 = Card.of(CardSuit.DIAMOND, CardNumber.ACE);
                Card card5 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4, card5));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, true);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isBlackjack()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

        @Nested
        @DisplayName("딜러가 스탠드라면")
        class dealer_stand {
            @Test
            @DisplayName("플레이어가 패배한다")
            void player_lose() {
                //given
                Card card1 = Card.of(CardSuit.HEART, CardNumber.FIVE);
                Card card2 = Card.of(CardSuit.CLUB, CardNumber.QUEEN);
                Card card3 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card4 = Card.of(CardSuit.DIAMOND, CardNumber.SEVEN);
                Card card5 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4, card5));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, true);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

    }

    @Nested
    @DisplayName("플레이어가 블랙잭일 때")
    class player_blackjack {

        @Nested
        @DisplayName("딜러가 버스트라면")
        class dealer_bust {
            @Test
            @DisplayName("플레이어가 승리한다")
            void player_win() {
                //given
                Card card1 = Card.of(CardSuit.HEART, CardNumber.NINE);
                Card card2 = Card.of(CardSuit.CLUB, CardNumber.ACE);
                Card card3 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card4 = Card.of(CardSuit.DIAMOND, CardNumber.SIX);
                Card card5 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4, card5));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBlackjack()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(result).isEqualTo(Result.WIN);
            }
        }

        @Nested
        @DisplayName("딜러가 블랙잭이라면")
        class dealer_blackjack {
            @Test
            @DisplayName("플레이어는 무승부다")
            void player_tie() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.ACE);
                Card card2 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card3 = Card.of(CardSuit.DIAMOND, CardNumber.ACE);
                Card card4 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBlackjack()).isTrue();
                assertThat(dealer.isBlackjack()).isTrue();
                assertThat(result).isEqualTo(Result.TIE);
            }
        }

        @Nested
        @DisplayName("딜러가 스탠드라면")
        class dealer_stand {
            @Test
            @DisplayName("플레이어가 승리한다")
            void player_win() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.ACE);
                Card card2 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card3 = Card.of(CardSuit.DIAMOND, CardNumber.SEVEN);
                Card card4 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBlackjack()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.WIN);
            }
        }

    }

    @Nested
    @DisplayName("플레이어가 스탠드일 때")
    class player_stand {

        @Nested
        @DisplayName("딜러가 버스트라면")
        class dealer_bust {
            @Test
            @DisplayName("플레이어가 승리한다")
            void player_win() {
                //given
                Card card1 = Card.of(CardSuit.HEART, CardNumber.NINE);
                Card card2 = Card.of(CardSuit.CLUB, CardNumber.NINE);
                Card card3 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card4 = Card.of(CardSuit.DIAMOND, CardNumber.SIX);
                Card card5 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4, card5));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(result).isEqualTo(Result.WIN);
            }
        }

        @Nested
        @DisplayName("딜러가 블랙잭이라면")
        class dealer_blackjack {
            @Test
            @DisplayName("플레이어는 패배한다")
            void player_lose() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.NINE);
                Card card2 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card3 = Card.of(CardSuit.DIAMOND, CardNumber.ACE);
                Card card4 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isBlackjack()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

        @Nested
        @DisplayName("딜러가 스탠드라면")
        class dealer_stand {

            @Test
            @DisplayName("플레이어의 총점이 딜러보다 높으면 플레이어가 승리한다.")
            void player_win() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.NINE);
                Card card2 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card3 = Card.of(CardSuit.DIAMOND, CardNumber.SEVEN);
                Card card4 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("플레이어의 총점과 딜러의 총점이 같으면 무승부다.")
            void player_tie() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.SEVEN);
                Card card2 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card3 = Card.of(CardSuit.DIAMOND, CardNumber.SEVEN);
                Card card4 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.TIE);
            }

            @Test
            @DisplayName("플레이어의 총점이 딜러의 총점보다 작으면 패배한다.")
            void player_lose() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.SEVEN);
                Card card2 = Card.of(CardSuit.HEART, CardNumber.TEN);
                Card card3 = Card.of(CardSuit.DIAMOND, CardNumber.NINE);
                Card card4 = Card.of(CardSuit.SPADE, CardNumber.TEN);
                CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4));
                Dealer dealer = new Dealer(new InitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }

        }

    }

}