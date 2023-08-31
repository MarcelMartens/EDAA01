package game;

import static game.UserInterface.printMessage;

public class TakePinsGame {
  static void main(String[] args) {
    System.out.println("hejhej");
    boolean proceed = true;
    Board b = new Board(20);
    HumanPlayer hp = new HumanPlayer("human player");
    ComputerPlayer cp = new ComputerPlayer("Computer Player");
    printMessage("Welcome to\nStick Game 2: Electric bogaloo");

    while (proceed) {
      break;
    }
  }

  private int takePins(Board board, Player player) {
    return player.takePins(board);
  }
}