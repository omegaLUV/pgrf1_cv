package model;

import java.util.ArrayList;
import java.util.List;

public class Polyline {
    private List<Point> points = new ArrayList<>();
    private Point a,b;

    public Polyline(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Polyline() {

    }

    public List<Point> getPoints() {
        return points;
    }
}
