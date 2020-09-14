package exercises;

import java.util.Arrays;
import java.util.Random;

import static java.lang.System.out;

/*
 *  Simulation of LCR game. See, https://en.wikipedia.org/wiki/LCR_(dice_game)
 */
public class Ex7LCRSimulation {

    public static void main(String[] args) {
        new Ex7LCRSimulation().program();
    }

    final Random rand = new Random();

    void program() {

        // Hard coded data
        boolean state = true;
        int currentIndex = 0;
        final Player[] players = {
                new Player("olle", 3),
                new Player("fia", 3),
                new Player("pelle", 3)};

        Player current = players[currentIndex];
        out.println("Simulation starts");
        displayPlayers(players);

        while (true) {
            if (currentIndex < 0)
                break;
            else
                roundLogic(players, currentIndex, players[currentIndex].rollDice());

            currentIndex = nextIndex(players, currentIndex);
        }
        out.println("Game over!\nWinner is " + getWinner(players).name);
    }



    // ---- Logical methods -----------------
    void roundLogic(Player[] players, int currentIndex, char[] result){
        for (int i = 0; i < result.length; i++) {
            switch(result[i]) {
                case 'L':
                    players[currentIndex].chips--;
                    players[(currentIndex+2) % 3].chips++;
                    break;
                case 'C':
                    players[currentIndex].chips--;
                    break;
                case 'R':
                    players[currentIndex].chips--;
                    players[(currentIndex+1) % 3].chips++;
                    break;
                default:
                    break;
            }
        }
        displayState(players[currentIndex], players);
    }

    int nextIndex(Player[] players, int currentIndex) {
        int tryIndex;

        for (int i = 0; i < 4; i++) {
            tryIndex = (currentIndex+1) % 3;
            if (players[tryIndex].chips > 0)
                return tryIndex;
        }
        return -1;
    }

    Player getWinner(Player[] players){
        for(int i = 0; i < players.length; i++)
            if (players[i].chips > 0)
                return players[i];
        return null;
    }

    // --- IO methods ------------------

    void displayState(Player actual, Player[] players) {
        out.print(actual.name + " got ");
        out.println(Arrays.toString(actual.rolls));
        displayPlayers(players);
    }

    void displayPlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            out.print(players[i].name + ":" + players[i].chips + " ");
        }
        out.println();
    }

    // ------- Class to hold player data -----------

    class Player {
        String name;
        char[] rolls;
        int chips;

        public Player(String name, int chips) {
            this.name = name;
            this.chips = chips;
        }

        char[] rollDice(){
            int roll;
            if (chips > 3)
                rolls = new char[3];
            else
                rolls = new char[chips];

            for(int i = 0; i < rolls.length; i++){
                roll = rand.nextInt(6) + 1;
                switch(roll) {
                    case 4:
                        rolls[i] = 'L';
                        break;
                    case 5:
                        rolls[i] = 'C';
                        break;
                    case 6:
                        rolls[i] = 'R';
                        break;
                    default:
                        rolls[i] = '*';
                        break;
                }
            }
        return rolls;
        }
    }
}


