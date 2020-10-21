package pong.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class hitboxObject implements IPositionable {
    public final double WIDTH;
    public final double HEIGHT;
    private Rectangle2D.Double hitBox;

    hitboxObject(double WIDTH, double HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public double getWidth() {
        return WIDTH;
    }

    public double getHeight() { return HEIGHT; }

    public void setCollisionHitbox () {
        this.hitBox = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    boolean hitboxCollision(Object o) {
        hitboxObject body = (hitboxObject) o;

        body.setCollisionHitbox();
        this.setCollisionHitbox();

        return this.hitBox.intersects(body.hitBox);
    }
}
