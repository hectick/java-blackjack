package blackjack.controller;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.card.Cards;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Name;
import blackjack.model.participants.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){
        Cards cardDeck = new Cards();
        Dealer dealer = new Dealer();
        List<Player> players = initializePlayers();

        //카드 나누기
        distributeCards(cardDeck, dealer, players);
        showCardsDistribution(dealer, players);

        //HitOrStand
        for (Player player : players) {
            hitOrStandByPlayer(cardDeck, player);
        }
        hitOrStandByDealer(cardDeck, dealer);

        // 보유 카드. 숫자합 결과 출력
        showOwnedCardsAndScore(dealer, players); //아직 숫자합은 안함

        // 최종승패 출력
        // generateResult
        // showResult
    }

    private List<Player> initializePlayers() {
        List<String> playerNames = inputView.readNames();

        return playerNames.stream()
                .map(name -> new Player(new Name(name)))
                .collect(Collectors.toList());
    }

    private void distributeCards(Cards cardDeck, Dealer dealer, List<Player> players) {
        dealer.play(cardDeck);
        for (Player player : players) {
            player.play(cardDeck);
        }
    }

    private void showCardsDistribution(Dealer dealer, List<Player> players) {
        outputView.printCardDistribution(players.stream().map(Player::getName).collect(Collectors.toList()));
        outputView.printPlayerOwnedCards(dealerFirstOwnedCard(dealer));
        outputView.printPlayerOwnedCards(playerOwnedCards(players));
    }

    private Map<String, List<String>> dealerFirstOwnedCard(Dealer dealer) {
        Map<String, List<String>> dealerOwnedCards = new HashMap<>();
        String dealerName = dealer.getName();
        Card dealerCard = dealer.getOwnedCards().get(0);
        String dealerCards = cardUnit(dealerCard.getCardNumber(), dealerCard.getSuit());
        dealerOwnedCards.put(dealerName, List.of(dealerCards));
        return dealerOwnedCards;
    }

    private Map<String, List<String>> dealerOwnedCards(Dealer dealer) {
        Map<String, List<String>> dealerOwnedCards = new HashMap<>();
        String name = dealer.getName();
        List<String> ownedCards = dealer.getOwnedCards()
                .stream()
                .map(card -> cardUnit(card.getCardNumber(), card.getSuit()))
                .collect(Collectors.toList());
        dealerOwnedCards.put(name, ownedCards);
        return dealerOwnedCards;
    }

    public Map<String, List<String>> playerOwnedCards(List<Player> players) {
        HashMap<String, List<String>> playerCards = new HashMap<>();
        for (Player player : players) {
            String name = player.getName();
            List<String> ownedCards = player.getOwnedCards()
                    .stream()
                    .map(card -> cardUnit(card.getCardNumber(), card.getSuit()))
                    .collect(Collectors.toList());
            playerCards.put(name, ownedCards);
        }
        return playerCards;
    }

    private String cardUnit(CardNumber number, CardSuit suit) {
        return number.getSymbol() + suit.getSuit();
    }

    private void hitOrStandByPlayer(Cards cardDeck, Player player) {
        while (!player.isFinished()) {
            boolean isHit = inputView.readHitOrStand(player.getName());
            playOrFinish(cardDeck, player, isHit);
            outputView.printPlayerOwnedCards(playerOwnedCards(List.of(player)));
        }
    }

    private void playOrFinish(Cards cardDeck, Player player, boolean isHit) {
        if (isHit) {
            player.play(cardDeck);
            return;
        }
        player.changeToStand();
    }

    private void hitOrStandByDealer(Cards cardDeck, Dealer dealer) {
        while (!dealer.isFinished()) {
            dealer.play(cardDeck);
            outputView.printDealerGetOneMoreCard();
        }
    }

    private void showOwnedCardsAndScore(Dealer dealer, List<Player> players) {
        outputView.printPlayerOwnedCards(dealerOwnedCards(dealer));
        outputView.printPlayerOwnedCards(playerOwnedCards(players));
    }

}
