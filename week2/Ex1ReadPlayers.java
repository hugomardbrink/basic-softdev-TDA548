package exercises;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 *  Utilities to input/output player data for a command line game
 *
 *  See:
 *  - UseAConstructor
 *  - ObjectArrMeth
 *
 */
public class Ex1ReadPlayers {
    public static void main(String[] args) {
        new Ex1ReadPlayers().program();
    }

    final Scanner sc = new Scanner(in);

    void program() {
        Player[] players;
        players = inputPlayers();
        outPlayers(players);
    }

    // This class captures the concept of a Player
    class Player {
        String name;   // A Player has a name and...
        int points;    // ... and points

        Player(){
        out.println("Input name >");
        name = sc.nextLine();
        points = 0;
        }
    }

    // ---------- Methods -------------------

     Player[] inputPlayers() {
        Player[] players = new Player[2];
        for (int i = 0; i < 2; i++){
            out.println("Name for player " + (i + 1) +" >");
            players[i] = new Player();
        }
        return players;
    }

    void outPlayers(Player[] players){ // Why not make this a class method?
        for(int i = 0; i < 2; i++) {
            out.print("Name: " + players[i].name + " Points: " + players[i].points);
        }
    }
}
