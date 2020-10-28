
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;
import static java.lang.Math.*;
import static java.lang.System.*;

/*
 *  Program to simulate segregation.
 *  See : http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/
 *
 * NOTE:
 * - JavaFX first calls method init() and then the method start() far below.
 * - The method updateWorld() is called periodically by a Java timer.
 * - To test uncomment call to test() first in init() method!
 *
 */
// Extends Application because of JavaFX (just accept for now)

public class Neighbours extends Application {
    enum Actor {BLUE, RED, NONE}                                           //Declares necessary enums
    enum State {SATISFIED, UNSATISFIED, NA}

    World world;                                                           //Declared world class

    @Override
    public void init() {
        double[] dist = {0.25, 0.25, 0.50};                                //0 is red, 1 is blue, 2 is empty
        int nLocations = 900;                                              //matrix size

        world = new World(dist, nLocations);                               //creates the world
        fixScreenSize(nLocations);                                         //sets screen-size
    }

    void updateWorld() {                                                   //updates world
        double threshold = 0.7;                                            //agent satisfactory threshold
        world.deployStates(threshold);
    }

    //---------------- Methods ----------------------------

    int getFraction(double dist, int size) {                               //function that makes double into a percentage of totalsize
        return ((int) (dist * size));
    }

    double getAdjacentRelation(int states, int totalStates){               //gets relation between adjacent agents
        return ((double) states / (double) totalStates);
    }

    int getAgentSum (int[] array) {                                        //sums amount of reds and blues in the adjacency
        return array[0] + array[1];
    }

    boolean isSatisfied(double roof, int[] found, Actor state) {           //method for checking if agent is satisfied
        double sameAdjacent;                                               //variable for amount of same agent adjacent
        int stateSum = getAgentSum(found);                                 //sum of agents in the adjacency

        sameAdjacent = switch (state) {                                    //checks adjacency depending on own agent type
            case RED -> getAdjacentRelation(found[0], stateSum);
            case BLUE -> getAdjacentRelation(found[1], stateSum);
            default -> 0.0;
        };

        return sameAdjacent >= roof;                                       //returns true false depending on relation with threshold
    }

    void reallocate(Actor[][] matrix, int row, int col){                   //reallocates a agent to a random empty space
        Random rand = new Random();                                        //init random
        while (true) {                                                     //loops until valid space found
            int randRow = rand.nextInt(matrix.length),                     //randomizes a row
                randCol = rand.nextInt(matrix[0].length);                  //randomizes a col

            if ((randRow != row) &&                                        //if current row or col is not the same
                (randCol != col) &&                                        //and current space is empty
                (matrix[randRow][randCol] == Actor.NONE)) {

                matrix[randRow][randCol] = matrix[row][col];               //assigns agent to empty space
                matrix[row][col] = Actor.NONE;                             //clears prior space
                return;                                                    //breaks loop
            }
        }
    }

    class World {                                                          //class for storing both maps
        Actor[][] agentMap;                                                //created for abstract methods
        State[][] stateMap;

        World (double[] dist, int size) {                                  //constructor; creates world
            int red = getFraction(dist[0], size),                          //gets amounts of reds
                blue = getFraction(dist[1], size),                         //gets amounts of blues
                vectorSize = (int) sqrt(size);                             //gets row and col length

            agentMap = new Actor[vectorSize][vectorSize];                  //creates agentMap
            stateMap = new State[vectorSize][vectorSize];                  //creates stateMap

            declareDist(red, blue);                                        //makes matrix with right amounts of red&blue
            shuffle();                                                     //shuffles the matrix
        }

        void declareDist(int red, int blue) {                              //create matrix with right num of red&blue
            for (int r = 0; r < agentMap.length; r++)                      //loop through agentMap
                for (int c = 0; c < agentMap[0].length; c++) {
                    if (red > 0) {                                         //fills with red until amount is empty
                        agentMap[r][c] = Actor.RED;
                        red--;
                    } else if (blue > 0) {                                 //fills with blue until amount is empty
                        agentMap[r][c] = Actor.BLUE;
                        blue--;
                    } else {                                               //sets rest to empty spaces
                        agentMap[r][c] = Actor.NONE;
                    }
                }
        }

        void shuffle() {                                                  //shuffles matrix to random spaces.
            Random rand = new Random();                                   //init rand
            Actor temp;                                                   //temp actor for exchange
            int randRow,                                                  //declares var for random row and col
                randCol;


            for (int r = 0; r < agentMap.length; r++)
                for (int c = 0; c < agentMap[0].length; c++) {            //loops through agentMap
                    randRow = rand.nextInt(agentMap.length);              //randomizes row and col
                    randCol = rand.nextInt(agentMap[0].length);

                    temp = agentMap[r][c];                                //switch places with place is for and random
                    agentMap[r][c] = agentMap[randCol][randRow];
                    agentMap[randCol][randRow] = temp;
                }
        }

        void assignState(boolean satisfied, int r, int c) {              //assigns correct state depending on parameter
            if (agentMap[r][c] != Actor.NONE) {                          //if agent exists assign based on boolean
                if (satisfied) {
                    stateMap[r][c] = State.SATISFIED;
                } else {
                    stateMap[r][c] = State.UNSATISFIED;
                }
            } else {                                                    //if empty set space to NA
                stateMap[r][c] = State.NA;
            }

        }

        void getStateMap(double threshold) {                            //gets||produces the stateMap
            boolean satisfied;

            for(int r = 0; r < world.agentMap.length; r++)
                for(int c = 0; c < world.agentMap[0].length; c++) {     //loops through agentMap
                    satisfied = isSatisfied(threshold, world.checkAdjacent(r, c), world.agentMap[r][c]);
                    assignState(satisfied, r, c);                       //assigns correct state
                }
        }

        void deployStates(double threshold) {                           //reallocates & updates the table based on state
            getStateMap(threshold);                                     //gets map based on threshold
            Actor[][] virtualAgentMap = agentMap;                       //creates placeholder map

            for(int r = 0; r < stateMap.length; r++)                    //loops through stateMap
                for(int c = 0; c < stateMap[0].length; c++){
                    if (stateMap[r][c] == State.UNSATISFIED){
                        reallocate(virtualAgentMap, r, c);              //reallocates agent if not satisfied
                    }
                }
            agentMap = virtualAgentMap;                                 //upd displayed table with corrected placeholder
        }

        int[] checkAdjacent(int row, int col) {                         //check neighbours to given place in matrix
            int[] found = {0, 0};                                       //index 0 is found red, index 1 is found blues
            int adjDist = 1;
            
            for (int r = 0; r < agentMap.length; r++)                   //loops through agentMap
                for (int c = 0; c < agentMap[0].length; c++) {
                    boolean check = ((abs(r - row) == adjDist) && (abs(c - col) <= adjDist)) || //checks if the absolute value of
                            (abs(c - col) == adjDist && (abs(r - row) <= adjDist));             //the distance is 1 in either r/c

                    if (check) {                                        //if true then upd according agent
                        switch (agentMap[r][c]) {
                            case RED -> found[0]++;
                            case BLUE -> found[1]++;
                        }
                    }
                }
            return found;                                               //return found agents
        }
    }




    // ###########  NOTHING to do below this row, it's JavaFX stuff  ###########

    double width = 400;   // Size for window
    double height = 400;
    long previousTime = nanoTime();
    final long interval = 450000000;
    double dotSize;
    final double margin = 50;

    void fixScreenSize(int nLocations) {
        // Adjust screen window depending on nLocations
        dotSize = (width - 2 * margin) / sqrt(nLocations);
        if (dotSize < 1) {
            dotSize = 2;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Build a scene graph
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().addAll(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Create a timer
        AnimationTimer timer = new AnimationTimer() {
            // This method called by FX, parameter is the current time
            public void handle(long currentNanoTime) {
                long elapsedNanos = currentNanoTime - previousTime;
                if (elapsedNanos > interval) {
                    updateWorld();
                    renderWorld(gc, world.agentMap);
                    previousTime = currentNanoTime;
                }
            }
        };

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Segregation Simulation");
        primaryStage.show();

        timer.start();  // Start simulation
    }
    // Render the state of the world to the screen
    public void renderWorld(GraphicsContext g, Actor[][] world) {
        g.clearRect(0, 0, width, height);
        int size = world.length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                double x = dotSize * col + margin;
                double y = dotSize * row + margin;

                if (world[row][col] == Actor.RED) {
                    g.setFill(Color.RED);
                } else if (world[row][col] == Actor.BLUE) {
                    g.setFill(Color.BLUE);
                } else {
                    g.setFill(Color.WHITE);
                }
                g.fillOval(x, y, dotSize, dotSize);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
