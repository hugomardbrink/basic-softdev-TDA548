import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
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


    enum Actor {
        BLUE, RED, NONE
    }

    Actor[][] world;

    @Override
    public void init() {
        double[] dist = {0.25, 0.25, 0.50};
        int nLocations = 900;

        world = setSize(nLocations);
        createWorld(world, dist, nLocations);

        fixScreenSize(nLocations);
    }


    void updateWorld() {
        final double threshold = 0.6;
        boolean satisfied;

        for(int r = 0; r < world.length; r++)
            for(int c = 0; c < world[0].length; c++) {
                satisfied = isSatisfied(threshold, checkAdjacent(world, r, c), world[r][c]);
                if(!satisfied && (world[r][c] != Actor.NONE)) {
                    reallocate(world, r, c);
                }
            }
    }

    //---------------- Methods ----------------------------

    Actor[][] setSize(int locations) {
        int vectorSize = (int) sqrt(locations);
        return new Actor[vectorSize][vectorSize];
    }

    void createWorld(Actor[][] matrix, double[] dist, int size) {
        int red = getFraction(dist[0], size),
                blue = getFraction(dist[1], size);

        declareDist(matrix, red, blue);
        shuffle(matrix);

    }

    void declareDist(Actor[][] matrix, int red, int blue) {
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[0].length; c++) {
                if (red > 0) {
                    matrix[r][c] = Actor.RED;
                    red--;
                } else if (blue > 0) {
                    matrix[r][c] = Actor.BLUE;
                    blue--;
                } else {
                    matrix[r][c] = Actor.NONE;
                }
            }
    }

    void shuffle(Actor[][] matrix) {
        Random rand = new Random();
        Actor temp;
        int randRow,
                randCol;


        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[0].length; c++) {
                randRow = rand.nextInt(matrix.length);
                randCol = rand.nextInt(matrix[0].length);

                temp = matrix[r][c];
                matrix[r][c] = matrix[randCol][randRow];
                matrix[randCol][randRow] = temp;
            }
    }

    void reallocate(Actor[][] matrix, int row, int col){
        Random rand = new Random();
        while (true) {
            int randRow = rand.nextInt(matrix.length),
                randCol = rand.nextInt(matrix[0].length);

            if ((randRow != row) && (randCol != col) && (matrix[randRow][randCol] == Actor.NONE)) {
                matrix[randRow][randCol] = matrix[row][col];
                matrix[row][col] = Actor.NONE;
                return;
            }
        }
    }

    int getFraction(double dist, int size) {
        return ((int) (dist * size));
    }

    double getAdjacentRelation(int states, int totalStates){
        return ((double) states / (double) totalStates);
    }

    int getSum (int[] array) {
        int sum = 0;

        for (int i : array) {
            sum += i;
        }
        return sum;
    }

    int[] checkAdjacent(Actor[][] matrix, int row, int col) {
        int[] found = {0, 0, 0};


        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[0].length; c++) {
                boolean check = ((abs(r - row) == 1) && (abs(c - col) <= 1)) ||
                                (abs(c - col) == 1 && (abs(r - row) <= 1));

                if (check) {
                    switch (matrix[r][c]) {
                        case RED:
                            found[0]++;
                            break;
                        case BLUE:
                            found[1]++;
                            break;
                        default:
                            found[2]++;
                            break;
                    }
                }
            }
        return found;
    }

    boolean isSatisfied(double roof, int[] found, Actor state) {
        double sameAdjacent;
        int stateSum = getSum(found);


        switch(state){
            case RED:
                sameAdjacent = getAdjacentRelation(found[0], stateSum);
                break;
            case BLUE:
                sameAdjacent = getAdjacentRelation(found[1], stateSum);
                break;
            default:
                sameAdjacent = getAdjacentRelation(found[2], stateSum);
                break;
        }

        return sameAdjacent >= roof;
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
                    renderWorld(gc, world);
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
