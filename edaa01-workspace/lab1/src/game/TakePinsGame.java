package game;

// imports för att slippa skriva paket+klass + libs som behövs
import static game.UserInterface.printMessage;
import static game.UserInterface.askForBool;
import java.util.Random;

/**
 * Huvudklass för spelet. Innehåller main-metod som kör och hanterar spellogik
 * De flesta klasser som används har ingen vald synlighet så att deras metoder
 * ej kan anropas från utanför detta paketet.
 */
public class TakePinsGame {
  public static void main(String[] args) {

    // skapar board-objekt och visar välkommsttext
    // todo lägg till smart dator
    boolean proceed = true;
    Board b = new Board(new Random().nextInt(15) + 10);
    printMessage("Welcome to:\nStick Game 2: Electric bogaloo\n The one and only rule is: dont take the last pin!");

    // skapar spelar-objekt och skriver ut att spelet har startat + antalet pins i
    // spelet
    // ComputerPlayer cp = new ComputerPlayer("CP");
    SmartComputerPlayer cp = new SmartComputerPlayer("SCP");
    HumanPlayer hp = new HumanPlayer("HP");
    printMessage("The game has started!\nThe amount of pins is" + b.getTotPins() + "\nGood Luck!");

    // huvudspelloop, körs tills användaren väljer att ej fortsätta
    while (proceed) {

      // Kör mänskliga spelarens runda, kollar efter om antal pins är < 0
      printMessage("you took " + hp.takePins(b) + " Pins\n" + b.getTotPins() +
          " pins remaining");
      if (b.getTotPins() <= 0) {
        printMessage("You took the last pin\nYou Won!!!");
        proceed = retryReset(b);
        continue;
      }
      // Kör datorns runda, kollar efter om antal pins är < 0
      printMessage("the computer took " + cp.takePins(b) + " Pins\n" +
          b.getTotPins() + " pins remaining");
      if (b.getTotPins() <= 0) {
        printMessage("The computer took the last pin\nYou Lost!!!");
        proceed = retryReset(b);
        continue;
      }
    }
  }

  /**
   * Metod för att fråga användare om de vill fortsätta spelet eller avsluta.
   * Om yesOption är vald frågas användare om hur många pins de vill ha i nya
   * spelet
   * och true returneras
   * Om noOption är vald avslutas spelet och false returneras
   * Om yesOption först väljs och inputen sedan inte är ett positivt heltal
   * körs loopen om från början och låter användaren välja igen.
   */
  private static boolean retryReset(Board board) {
    while (true) {

      // Frågar om de vill avsluta eller ej och sätter proceed till true/false
      boolean proceed = askForBool("Do you want to play another round?",
          "Continue", "End game");
      if (!(proceed)) {
        printMessage("Ending game, thanks for playing!");
        return proceed;
      }

      // om spelaren vill fortsätta väljs nytt antal pins mellan 10 och 25
      // Spelet startas sedan om på rätt antal pins och meddelar användaren
      Random r = new Random();
      int nbrOfPins = r.nextInt(15) + 10;
      board.setUp(nbrOfPins);
      printMessage("Created new game with " + nbrOfPins + " pins!\nGood luck!");
      return proceed;
    }
  }
}