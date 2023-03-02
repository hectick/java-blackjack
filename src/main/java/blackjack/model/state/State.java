package blackjack.model.state;

public interface State {

    State draw(); //카드를 (상황에따라) 추가로 뽑는 것

    State stand();

    State bust();

    State blackjack();
}
