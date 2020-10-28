package pong.model;

import java.util.Random;

public abstract class MovableObject extends HitboxObject {
    public double xPos;
    public double yPos;
    public double speed;
    public double ySpeed;
    public double xSpeed;
    public double angle;

    MovableObject(double xPos, double yPos, double width, double height, double speed) {
        super(width, height);
        this.speed = speed;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void updatePos() {
        xPos += xSpeed;
        yPos += ySpeed;
    }

    public void setSpeedVectors() {
        xSpeed = speed * Math.cos(angle);
        ySpeed = speed * Math.sin(angle);
    }

    public double getRandomAngle() {
        Random rand = new Random();
        if(rand.nextBoolean())
            return Math.toRadians(rand.nextInt(60));
        else
            return Math.toRadians(rand.nextInt(60) * -1);
    }

    public void setRandomDirection() {
        Random rand = new Random();
        if(rand.nextBoolean())
            reverseSpeed();
    }

    public void reverseSpeed() {
        speed *= -1;
    }

    public void reverseAngle() {
        angle *= -1;
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }
}
