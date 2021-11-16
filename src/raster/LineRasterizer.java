package raster;
import model.Line;
import model.Point;
import model.Polyline;

public abstract class LineRasterizer {
    protected Raster raster;


    public void rasterize(int x1, int y1, int x2, int y2) {
        rasterize(x1, y1, x2, y2, 0xffff00);
    }

   public void rasterize(Point a, Point b) {
       rasterize(a.getX(),a.getY(),b.getX(), b.getY());
   }
    public void rasterize(Point a, Point b, int color) {
        rasterize(a.getX(),a.getY(),b.getX(), b.getY(), color);
    }

    public void rasterize(Line line) {
        rasterize(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    public void rasterize(Line line, int color) {
        rasterize(line.getX1(), line.getY1(), line.getX2(), line.getY2(), color);
    }

//    public void rasterize(Line polyline) {
//        for (int i = 0; i < polyline.getSize()-1; i++) {
//            Point a = polyline.getPoint(i);
//            Point b = polyline.getPoint(i+1);
//            System.out.println(a.getX() + " " + a.getY());
//            rasterize(a.getX(),a.getY(),b.getX(), b.getY());
//
//        }
//    }
    public void rasterize(Polyline polyline) {

    }


    public void rasterize(int x1, int y1, int x2, int y2, int color) {

    }
}
