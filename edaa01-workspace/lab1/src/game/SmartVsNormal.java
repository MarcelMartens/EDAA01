package game;

import java.util.ArrayList;
import java.util.Random;

public class SmartVsNormal {
    // todo check if it can go faster
    // todo add way of running multiple tests with specific values
    public static void main(String[] args) {
        // variabler som används för att hålla information till slutet av testet
        // eller värden som kan ändras för att testa andra fall
        ComputerPlayer cp = new ComputerPlayer("CP");
        SmartComputerPlayer scp = new SmartComputerPlayer("SCP");
        int normalWins = 0;
        int smartWins = 0;
        int nbrOfGames = 0;
        int randomBound = 15;
        int lowBoundLimit = 10;
        ArrayList<Integer> gameDetails = new ArrayList<>();
        Random r = new Random();
        Board b = new Board(0);

        // loop för att köra igenom alla matcherna.
        // körs i antal gånger med två matcher varje loop så båda spelare får börja
        // ökar antalet vinster för respektive spelare varje vinst
        // Om smarta förlorar skrivs rundans detaljer ut i konsollen
        for (int i = 1; i <= 500000; i++) {

            // sparar antalet loops för att kunna räkna ut procent senare
            nbrOfGames = i;

            // förbereder variabler för en ny runda
            // lägger till antalet pins i en arraylist för att sparas
            gameDetails.clear();
            b.setUp(r.nextInt(randomBound) + lowBoundLimit);
            gameDetails.add(b.getTotPins());

            // while loop med normal dator som startspelare
            while (true) {

                // sparar antalet tagna pins i en ArrayList
                gameDetails.add(cp.takePins(b));
                if (b.getTotPins() <= 0) {
                    normalWins++;
                    System.out.println("CP started: " + gameDetails.toString());
                    break;
                }
                gameDetails.add(scp.takePins(b));
                if (b.getTotPins() <= 0) {
                    smartWins++;
                    break;
                }
            }
            // förbereder variabler inför en ny runda
            // lägger till antalet pins i en arraylist för att sparas
            gameDetails.clear();
            b.setUp(r.nextInt(randomBound) + lowBoundLimit);
            gameDetails.add(b.getTotPins());

            // while loop med smart dator som startspelare
            while (true) {
                gameDetails.add(scp.takePins(b));
                if (b.getTotPins() <= 0) {
                    smartWins++;
                    break;
                }
                gameDetails.add(cp.takePins(b));
                if (b.getTotPins() <= 0) {
                    normalWins++;
                    System.out.println("SCP started: " + gameDetails.toString());
                    break;
                }
            }
        }

        // Skriver ut all info efter att testet körts till slut
        System.out.println("Normal wins = " + normalWins);
        System.out.println("Smart wins = " + smartWins);
        System.out.println("number of games = " + nbrOfGames * 2);
        float winPercent = 100 * ((float) smartWins / (nbrOfGames * 2));
        System.out.println("Smart computer had a win percentage of: " + winPercent + "%");
    }
}
