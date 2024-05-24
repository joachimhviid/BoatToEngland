package flowfield;

import javafx.geometry.Point2D;

public class Cell {
    private double cost;
    private Point2D direction;

    public Cell() {
        this.direction = new javafx.geometry.Point2D(0, 0);
        this.cost = 1;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }
}
