package blackjack;

import blackjack.controller.MainController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MainController controller = new MainController(InputView.getInstance(), OutputView.getInstance());
        controller.run();
    }

}
