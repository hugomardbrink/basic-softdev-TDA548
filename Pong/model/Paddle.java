package pong.model;


import static pong.model.Pong.GAME_HEIGHT;
import pong.event.EventBus;

/*
 * A Paddle for the Pong game
 * A model class
 *
 */
public class Paddle extends movableObject {
    public static final double PADDLE_WIDTH = 10;
    public static final double PADDLE_HEIGHT = 60;
    public static final double PADDLE_SPEED = 5;
    public static final double PADDLE_IDLE_SPEED = 0;
    public static final double PADDLE_Y_SPAWN = GAME_HEIGHT/2;
    public static final double PADDLE_ANGLE = Math.toRadians(90);


    public Paddle (double spawnX) {
        super(spawnX, PADDLE_Y_SPAWN, PADDLE_WIDTH,PADDLE_HEIGHT, PADDLE_IDLE_SPEED);
        this.angle = PADDLE_ANGLE;
    }

    public void hitCeiling() {
        if(speed < 0)
            speed = 0;
    }

    public void hitFloor() {
        if(speed > 0)
            speed = 0;
    }

    public void manageCollision (staticObject ceiling, staticObject floor) {
        if (this.hitboxCollision(ceiling))
            this.hitCeiling();
        if (this.hitboxCollision(floor))
            this.hitFloor();
    }
}
