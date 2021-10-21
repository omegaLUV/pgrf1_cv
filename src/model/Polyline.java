package model;

import java.util.ArrayList;
import java.util.List;

public class Polyline {
    private List<Point> points = new ArrayList<>();

    public void add(int x, int y) {
        points.add(new Point(x, y));
    }

    public Point getPoint(int i) {
        return points.get(i);
    }

    public int getSize() {
        return points.size();
    }

}
