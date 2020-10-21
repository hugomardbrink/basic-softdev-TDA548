package pong.model;


import pong.event.ModelEvent;
import pong.event.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Logic for the Pong Game
 * Model class representing the "whole" game
 * Nothing visual here
 *
 */
public class Pong {

    public static final double GAME_WIDTH = 600;
    public static final double GAME_HEIGHT = 400;
    public static final long HALF_SEC = 500_000_000;
    public Ball ball;
    public Paddle leftPaddle;
    public Paddle rightPaddle;
    public staticObject ceiling;
    public staticObject floor;

    private int pointsLeft;
    private int pointsRight;

    private long timeForLastHit;         // To avoid multiple collisions

    public Pong(Ball ball, Paddle leftPaddle, Paddle rightPaddle, staticObject ceiling, staticObject floor) {
        this.ball = ball;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.ceiling = ceiling;
        this.floor = floor;
    }


    // --------  Game Logic -------------

    public void update(long now) {
        manageBallBounds();
        manageAllVectors(now);
}

    private void manageBallBounds() {
        if(ball.getX() > GAME_WIDTH + ball.getWidth()) {
            pointsLeft++;
            newBall();
        } else if(ball.getX() < 0 - ball.getWidth()) {
            pointsRight++;
            newBall();
        }
    }

    private void newBall() {
        ball = new Ball();
        EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.NEW_BALL));
    }

    // --- Used by GUI  -----------------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> drawables = new ArrayList<>();
        drawables.add(ball);
        drawables.add(leftPaddle);
        drawables.add(rightPaddle);
        return drawables;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public int getPointsRight() {
        return pointsRight;
    }

    private void manageCollisions(long now) {
        leftPaddle.manageCollision(ceiling, floor);
        rightPaddle.manageCollision(ceiling, floor);
        if (now - timeForLastHit > HALF_SEC && ball.managePaddleCollision(leftPaddle, rightPaddle))
            timeForLastHit = now;
        ball.manageBorderCollision(ceiling, floor);
    }

    private void manageAllVectors(long now) {
        manageCollisions(now);

        leftPaddle.setSpeedVectors();
        rightPaddle.setSpeedVectors();
        ball.setSpeedVectors();

        leftPaddle.updatePos();
        rightPaddle.updatePos();
        ball.updatePos();
    }
}