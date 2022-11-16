import GUIs.gameplayWindow;
import GUIs.mainMenu;

public class runStart {
    public static void main(String[] args) {
        mainMenu menu = new mainMenu("IN LE HEAD");
        gameplayWindow main = new gameplayWindow("IN LE HEAD");
        menu.setVisible(true);
//        main.setVisible(true);
    }
}
