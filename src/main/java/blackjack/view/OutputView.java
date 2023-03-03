package blackjack.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static OutputView instance;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (instance == null) {
            instance = new OutputView();
            return instance;
        }
        return instance;
    }

    public void printDistributionMessage(List<String> players) {
        String names = String.join(",", players);
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public void printNameAndHand(Map<String, List<String>> namesAndHands) {
        for (Map.Entry<String, List<String>> entry : namesAndHands.entrySet()) {
            String name = entry.getKey();
            String hand = String.join(",", entry.getValue());
            System.out.println(name + ": " + hand);
        }
    }

    public void printDealerHitMessage(int hitCount) {
        System.out.println();
        for(int i = 0; i < hitCount; i++){
            System.out.println("딜러가 16이하라 한장의 카드를 더 받았습니다.");
        }
        System.out.println();
    }

    public void printScoreResult(Map<String, List<String>> namesAndHands, int score, boolean isBlackjack) {
        for (Map.Entry<String, List<String>> entry : namesAndHands.entrySet()) {
            String name = entry.getKey();
            String cards = String.join(",", entry.getValue());
            String result = Integer.toString(score);
            if(isBlackjack){
                result += " (블랙잭)";
            }
            System.out.println(name + ": " + cards + " - 결과: " + result);
        }
    }

    public void printWinningResultMessage() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public void printDealerWinningResult(long win, long tie, long lose) {
        System.out.println("딜러: " + win + "승 " + tie + "무 " + lose + "패");
    }

    public void printPlayersWinningResult(Map<String, String> playerResult) {
        for (Map.Entry<String, String> entry : playerResult.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
