import GUIs.gameplayWindow;
import GUIs.mainMenu;
import map.Room;
import map.allRooms;

public class runStart {
    public static void main(String[] args) {
        mainMenu menu = new mainMenu("A SECOND CHANCE");
        gameplayWindow main = new gameplayWindow("A SECOND CHANCE");
        menu.setContentPane(main);
        menu.setVisible(true);

        Room tutorial = allRooms.tutRoom();
        Room forest1 = allRooms.newForest1();
        Room forest2 = allRooms.newForest2();
        Room cabin = allRooms.newCabin();
        Room cave = allRooms.newCave();
        Room forest3 = allRooms.newForest3();


    }
}
