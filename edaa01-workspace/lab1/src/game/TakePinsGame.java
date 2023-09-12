package game;

// imports för att slippa skriva paket+klass + libs som behövs
import static game.UserInterface.printMessage;
import static game.UserInterface.askForBool;
import java.util.Random;

/**
 * Huvudklass för spelet. Innehåller main-metod som kör och hanterar spellogik
 * De flesta klasser som används har ingen vald synlighet så att deras metoder
 * ej kan anropas från metoder utanför detta paketet.
 */
// todox add smartComputerPlayer
// todo add way of choosing smart or normal computer at start of game
public class TakePinsGame {
  public static void main(String[] args) {

    // skapar board-objekt och visar välkommsttext
    Board b = new Board(new Random().nextInt(15) + 8);
    printMessage(
        "Welcome to:\nStick Game 2: Electric bogaloo\n \n The one and only rule is: whoever takes the last pin wins!");
    boolean[] initValues = retryReset(b);

    // skapar spelar-objekt och skriver ut att spelet har startat + antalet pins i
    // spelet
    // ComputerPlayer cp = new ComputerPlayer("CP");
    SmartComputerPlayer cp = new SmartComputerPlayer("SCP");
    HumanPlayer hp = new HumanPlayer("HP");

    // huvudspelloop, körs tills användaren väljer att ej fortsätta
    // todox add randomly chosen starting positions
    while (initValues[0]) {
      if (initValues[1]) {
        printMessage("the computer took " + cp.takePins(b) + " Pins\n \n" + b.getTotPins() +
            " pins remaining");
        initValues[1] = false;
      }

      // Kör mänskliga spelarens runda, kollar efter om antal pins är < 0
      printMessage("you took " + hp.takePins(b) + " Pins\n" + b.getTotPins() +
          " pins remaining");
      if (b.getTotPins() <= 0) {
        printMessage("You took the last pin\n \nYou Won!!!");
        initValues = retryReset(b);
        continue;
      }
      // Kör datorns runda, kollar efter om antal pins är < 0
      printMessage("the computer took " + cp.takePins(b) + " Pins\n" +
          b.getTotPins() + " pins remaining");
      if (b.getTotPins() <= 0) {
        printMessage("The computer took the last pin\n \nYou Lost!!!");
        initValues = retryReset(b);
        continue;
      }
    }
  }

  /**
   * Metod för att fråga användare om de vill fortsätta spelet eller avsluta
   * samt att resetta bordet och välja antal pins och vem som börjar.
   * Om yesOption är vald väljs slumpmässigt hur många pins det ska vara i nya
   * spelet
   * och true returneras som första element i vektorn initValues
   * Om noOption är vald avslutas spelet och false returneras som första element
   * Om yesOption först väljs och inputen sedan inte är ett positivt heltal
   * körs loopen om från början och låter användaren välja igen.
   * Returnar även en slumpmässigt vald boolean som väljer vilken spelare som
   * börjar först
   */
  // todox fix so method can be used at init
  static boolean[] retryReset(Board board) {
    while (true) {

      // Frågar om de vill avsluta eller ej och sätter initValues[0] till true/false
      boolean proceed = askForBool("Do you want to start a new round?",
          "New Round", "End game");
      if (!(proceed)) {
        printMessage("Ending game, thanks for playing!");
        boolean[] initValues = { proceed, false };
        return initValues;
      }

      // om spelaren vill fortsätta väljs nytt antal pins mellan 10 och 25
      // Spelet startas sedan om på rätt antal pins och meddelar användaren
      // om antal pins och vem som börjar
      Random r = new Random();
      boolean computerBegins = r.nextBoolean();
      String whoBegins;
      boolean[] initValues = { proceed, computerBegins };
      int nbrOfPins = r.nextInt(15) + 10;
      board.setUp(nbrOfPins);
      if (computerBegins) {
        whoBegins = "Computer has the first turn";
      } else {
        whoBegins = "You have the first turn";
      }
      printMessage("Created new game with " + nbrOfPins + " pins!\n \n" + whoBegins + ", Good luck!");
      return initValues;
    }
  }
}