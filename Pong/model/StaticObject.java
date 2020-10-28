package pong.model;


public class staticObject extends HitboxObject {
    private final double xPos;
    private final double yPos;

    public StaticObject(double xPos, double yPos, double width, double height) {
        super(width, height);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

}
