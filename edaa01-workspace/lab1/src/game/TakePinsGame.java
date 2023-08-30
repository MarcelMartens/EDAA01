package game;

public class TakePinsGame {

  public static void main(String[] args) {
    Board b = new Board(20);
    HumanPlayer hp = new HumanPlayer("human player");
    ComputerPlayer cp = new ComputerPlayer("Computer Player");
    // UserInterface.printMessage("hejhejhej");
    int input = UserInterface.askForInt("skriv ett positivt heltal mellan 1 och 2");
    int chosenInt = 0;
    if (input == -1) {
      UserInterface.printMessage("kan ej tolkas som positivt heltal");
    }
    if (input == -2) {
      UserInterface.printMessage("Användaren tryckte på avbryt");
    }
    if (input == -3) {
      UserInterface.printMessage("Angivet tal är inte 1 eller 2");
    } else {
      chosenInt = input;
    }
    b.takePins(chosenInt);
    UserInterface.printMessage("total number of pins:" + Integer.toString(b.getTotPins()));
  }
}