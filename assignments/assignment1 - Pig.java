import java.util.Random; // Imports relevant libraries
import java.util.Scanner;
import static java.lang.System.*;

/*
 * The Pig game
 * See http://en.wikipedia.org/wiki/Pig_%28dice_game%29
 */

public class Pig { // Declare program class
    final Scanner sc = new Scanner(in);  //Initiates "Scanner" class
    final Random rand = new Random(); //Initiates "Random" class


    public static void main(String[] args) { // Declare main
        new Pig().program(); // Executes "program"
    }

    void program() {
        final int winPts = 20;    // Points to win
        int playerCount = 0;      // Amount of player objects
        Player current;           // Current player for round
        boolean aborted = false;  // Variable if game is quit
        Player players[] = new Player[3]; // Declared player array

        for (int i = 0; i < 2; i++) { // Creates the 2 players in a loop
            playerCount++; // Adds 1 to player objects
            players[i] = new Player(getPlayerName(playerCount), playerCount); // Creates player via constructor
        }
        players[2] = new Player("Computer", 3); // Creates computer

        welcomeMsg(winPts); // Prompts welcome message
        statusMsg(players); // Prompts player scores
        current = players[random(3)]; // Randomizes starting player 1-3

        while(true){ // Gameplay loop
            if (current != players[2]) { // If player is human
                current = round(current, current.getPlayerChoice(), players); // Does round for current player and returns next player
            } else { // If player is computer
                current = round(current, current.getComputerChoice(), players); // Does round through computer specific method.
            }
            if (current == null){ // If null is return quit game
                aborted = true; // Set aborted to true
                break; // Break gameplay loop
            } else if ((current.totalPts + current.roundPts) >= winPts){ // If current player reached winning condition; quit
                break; // Break gameplay loop
            }
        }
        gameOverMsg(current, aborted); // Prompts game over message
    }


    Player round(Player player, char choice, Player[] players) { // Executes round and return next player
        int result; // Declares variable for storing results

        if (choice == 'r') { // If choice parameter is = 'r' then roll the dice
            result = (random(6) + 1); // Result is a number between 1-6
            if (result == 1) {  // If result is 1 then execute "lose" sequence
                player.roundPts = 0; // Sets this rounds score to 0
                roundMsg(result, player); // Display that player lost
                statusMsg(players); // Display scores
                return players[((player.playerIndex + 1) % 3)]; // Switches to next player
            } else { // If player got between 2-6
                player.roundPts += result; // Adds result to round total
                roundMsg(result, player); // Displays round score
                return player; // Return same player
            }

        } else if (choice == 'n') { // If choice is = 'n' then next turn
            player.totalPts += player.roundPts; // Add round total to game total
            player.roundPts = 0; // Reset round total
            statusMsg(players); // Display new score standing
            return players[((player.playerIndex + 1) % 3)]; // Return next player
        } else { // If choice is = 'q' because all else is impossible
            return null; //  return null which quits the game
        }
    }

    int random(int roof){ // Method that return a value between 0-roof
        return (rand.nextInt(roof)); // Uses random class and rood to return randomized number
        }

    String getPlayerName(int playerCount){ // Lets players decide name
        out.println("What name is player " + (playerCount)); // Prompts player x to answer question
        return sc.nextLine(); // Let user input name
    }

    void statusMsg(Player[] players) { // Shows players points
        out.print("Points: "); // Print point label
        for (int i = 0; i < players.length; i++) { // For every player
            out.print(players[i].playerName + " = " + players[i].totalPts + " "); // Print name and associated score
        }
        out.println(); // Print new line
    }

    void roundMsg(int result, Player current) { // Display round score
        if (result > 1) { // If result is better than losing condition
            out.println("Got " + result + " running total are " + current.roundPts); // Print round score and round total
        } else { // If losing condition is met
            out.println("Got 1, lost it all!"); // Print that player lost
        }
    }

    void gameOverMsg(Player player, boolean aborted) { // Used when game is over
        if (aborted) { // If aborted is true than display quit condition
            out.println("Aborted"); // Message that game is quit
        } else { // If game is won by any player
            out.println("Game over! Winner is player " + player.playerName + " with " // Print player name and total score
                    + (player.totalPts + player.roundPts) + " points");
        }
    }

    void welcomeMsg(int winPoints) { // Used to welcome user into game
        out.println("Welcome to PIG!"); // Welcomes player
        out.println("First player to get " + winPoints + " points will win!"); // Explains winning condition
        out.println("Commands are: r = roll , n = next, q = quit"); // Explains controls
        out.println(); // Prints new line
    }

    class Player { // Declares class for Player
        String playerName; // Declare player name variable
        int totalPts; // Declare player total points variable
        int roundPts; // Declare player round points variable
        int playerIndex; // Declare player index number in player array
        int roundRolls;

    Player(String name, int playerCount){ // Declare constructor for Player
            playerName = name; // Set players name
            roundPts = 0; // Set round points to 0
            totalPts = 0; // Set round points to 0
            roundRolls = 0; // Set roundRolls to 0
            playerIndex = (playerCount - 1);  // Set variable to array index
        }

    char getPlayerChoice() { // Prompts user-input and returns data
            char choice; // Declare variable for storing user choice
            out.print("Player is " + playerName + " > "); // Prints which player is prompted

            while (true) { // Input loop to secure safe answer
                choice = sc.next().charAt(0); // Get user input
                if (choice == 'r' || choice ==  'n' || choice == 'q'){ // If user input is legal
                    return choice; // Return correct choice
                } else { //If user input is illegal
                    out.println("Bad input, type 'r', 'n' or 'q'"); // Prompt user to retype correct input
                }
            }
        }

    char getComputerChoice(){
            int choice; // Declare variable for storing computer choice
            out.println("Player is Computer"); // Announces computers turn

            if (roundRolls <= 0){ // If computers first turn then force roll for balancing
                roundRolls++; // Add 1 turn to variable
                out.println("Computer chose: 'r'"); // Announce computers choice
                return 'r'; // Return dice roll option
            } else { // If more than 1 turn is taken then proceed to randomize
                choice = random(2); // Randomize choice
                if (choice == 0) { // If randomizer hits 0 then roll dice
                    out.println("Computer chose: 'r'"); // Announce computers choice
                    return 'r'; // Return dice roll option
                } else { // If randomizer hits 1 then next turn
                    out.println("Computer chose: 'n'"); // Announce computers choice
                    return 'n'; // Return next turn option
                }
            }
        }
    }
}




