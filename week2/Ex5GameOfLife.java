package exercises;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.System.exit;
import static java.lang.System.out;

/*
 * Program for Conway's game of life.
 * See https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 * This is a graphical program using JavaFX to draw on the screen.
 * There's a bit of "drawing" code to make this happen (far below).
 * You don't need to implement (or understand) any of it.
 * NOTE: To run tests must uncomment in init() method, see comment
 *
 * Use smallest step development + functional decomposition!
 *
 * See:
 * - UseEnum9
 * - BasicJavaFX (don't need to understand, just if you're curious)
 */
public class Ex5GameOfLife extends Application {

    final Random rand = new Random();

    // Enum type for state of Cells
    enum Cell {
        DEAD, ALIVE;
    }

    // This is the *only* accepted modifiable instance variable in program.
    Cell[][] world;

    @Override
    public void init() {
      //  test();        // <--------------- Uncomment to test!
        int nLocations = 10000;
        double distribution = 0.15;   // % of locations holding a Cell

        world = setSize(nLocations);

        for(int c = 0; c < world.length; c++)
            for(int r = 0; r < world[0].length; r++){
                world[c][r] = randomizeState(distribution);
            }

    }

    void update() {
        for(int c = 0; c < world.length; c++)
            for(int r = 0; r < world[0].length; r++){
                world[r][c] = findNeighbour(world, r, c);
            }
    }




    // -------- Write methods below this --------------

    Cell randomizeState (double dist){
        int random = rand.nextInt(100);

        if (random < 15) {
            return Cell.ALIVE;
        } else {
            return Cell.DEAD;
        }
    }

    Cell[][] setSize(int size){
        int arraySize = (int) sqrt(size);
        Cell[][] matrix = new Cell[arraySize][arraySize];
        return matrix;
    }

    Cell findNeighbour(Cell[][] matrix, int row, int col) {
            int neighbourAlive = 0,
                neighbourDead = 0,
                rowStart = row - 2,
                colStart = col - 2,
                rowStop = row + 2,
                colStop = col + 2;

        if (rowStart < 0) {
            rowStart = 0;
        } if (rowStop > matrix.length){
            rowStop = matrix.length;
        } if (colStart < 0){
            colStart = 0;
        } if (colStop > matrix[0].length){
            colStop = matrix[0].length;
        }

        for (int r = rowStart; r < rowStop; r++) {
            for (int c = colStart; c < colStop; c++) {
                if (matrix[r][c] == Cell.ALIVE) {
                    neighbourAlive++;
                }
            }
        }

        if (neighbourAlive == 3 && matrix[row][col] == Cell.DEAD) {
            return Cell.ALIVE;
        } else if ((neighbourAlive == 3 || neighbourAlive == 2) && matrix[row][col] == Cell.ALIVE ) {
            return Cell.ALIVE;
        } else {
            return Cell.DEAD;
        }
    }
    // -------- Below is JavaFX stuff, nothing to do --------------

    void render() {
        gc.clearRect(0, 0, width, height);
        int size = world.length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int x = 3 * col + 50;
                int y = 3 * row + 50;
                renderCell(x, y, world[row][col]);
            }
        }
    }

    void renderCell(int x, int y, Cell cell) {
        if (cell == Cell.ALIVE) {
            gc.setFill(Color.RED);
        } else {
            gc.setFill(Color.WHITE);
        }
        gc.fillOval(x, y, 3, 3);
    }

    final int width = 400;   // Size of window
    final int height = 400;
    GraphicsContext gc;

    // Must have public before more later.
    @Override
    public void start(Stage primaryStage) throws Exception {

        // JavaFX stuff
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().addAll(canvas);
        gc = canvas.getGraphicsContext2D();

        // Create a timer
        AnimationTimer timer = new AnimationTimer() {

            long timeLastUpdate;

            // This method called by FX at a certain rate, parameter is the current time
            public void handle(long now) {
                if (now - timeLastUpdate > 300_000_000) {
                    update();
                    render();
                    timeLastUpdate = now;
                }
            }
        };
        // Create a scene and connect to the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
        timer.start();  // Start simulation
    }

    public static void main(String[] args) {
        launch(args);   // Launch JavaFX
    }
}