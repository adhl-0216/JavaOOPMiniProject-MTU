import GUIs.gameplayWindow;
import GUIs.mainMenu;

public class runStart {
    public static void main(String[] args) {
        mainMenu menu = new mainMenu("A SECOND CHANCE");
        gameplayWindow main = new gameplayWindow("A SECOND CHANCE");
        menu.setVisible(true);
//        main.setVisible(true);
    }
}
