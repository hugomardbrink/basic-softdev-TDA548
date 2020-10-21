package pong.model;


public class staticObject extends hitboxObject {
    private final double xPos;
    private final double yPos;

    public staticObject(double xPos, double yPos, double width, double height) {
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
