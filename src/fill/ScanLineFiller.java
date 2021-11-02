package fill;

import model.Polyline;
import raster.Raster;

import java.util.List;

public class ScanLineFiller extends Filler{
    private int minY, maxY;
    private Polyline polyline;
    private List<BorderLine> borderLineList;
    private int ymin = Integer.MAX_VALUE;
    private int ymax = Integer.MIN_VALUE;

    public ScanLineFiller(Raster raster) {
        super(raster);
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;

        for(int i = 0; i<polyline.getPoints().size(); i++) {
            int x1 = polyline.getPoints().get(i).getX();
            int y1 = polyline.getPoints().get(i).getY();

            int index = (i+1)%polyline.getPoints().size();

            int x2 = polyline.getPoints().get(index).getX();
            int y2 = polyline.getPoints().get(index).getY();

            BorderLine borderLine = new BorderLine(x1,y1,x2,y2);

            if(!borderLine.isHorizontal()) {
                borderLine.sortY();
                borderLineList.add(borderLine);
                if(borderLine.y1 < ymin)
                    ymin = borderLine.y1;
                if(borderLine.y2 > ymax)
                    ymax = borderLine.y2;
            }
        }
    }

    @Override
    public void fill() {
        //nalezeni minY a maxY

    }
    private class BorderLine {
        int x1, y1, x2, y2;

        public BorderLine(int x1, int y1, int x2 , int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public boolean isHorizontal() {
            return y1 == y2;
        }

        public void sortY() {
            if(y2>y1) {
                int tempY = y1;
                y1 = y2;
                y2 = tempY;
                int tempX = x1;
                x1 = x2;
                x2 = tempX;
            }
        }
    }
}


//a=a+b
//b=a-b
//a=a-b