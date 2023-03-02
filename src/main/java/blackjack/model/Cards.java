package blackjack.model;

import blackjack.model.Card;

import java.util.*;

public class Cards {
    private static final Stack<Card> remains = new Stack<>();
    private final List<Card> ownedCards = new ArrayList<>();

    static{
        //52개 카드 생성해서 카드를 셔플하고, stack 에 넣기 -> 카드 52개는 생성비용이 비싸므로 미리 캐싱?
    }

    public void pick(){
        if(remains.empty()){
            //exception 던짐
        }
        ownedCards.add(remains.pop());
    }

    //점수 계산하는 메소드
    //기타 등등

    public int score(){
        return 1;
    }

    public int size(){
        return ownedCards.size();
    }

}
