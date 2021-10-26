package fill;

import model.Polyline;
import raster.Raster;

public class ScanLineFiller extends Filler{
    private int minY, maxY;
    private Polyline polyline;

    public ScanLineFiller(Raster raster) {
        super(raster);
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    @Override
    public void fill() {
        //nalezeni minY a maxY
    }
}
