package fill;

import raster.Raster;

public abstract class Filler {
    final protected Raster raster;
    protected int fillColor = 0xff;

    public Filler(Raster raster) {
        this.raster = raster;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }


    public abstract void fill();
}
