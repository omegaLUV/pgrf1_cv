package fill;

import raster.Raster;

public class SeedFiller extends Filler {
    private int seedX, seedY;


    public SeedFiller(Raster raster) {
        super(raster);
    }

    public void setSeedX(int seedX) {
        this.seedX = seedX;
    }

    public void setSeedY(int seedY) {
        this.seedY = seedY;
    }

    public boolean isInside(int seedX, int seedY) {
        return true;
    }

    private void seedFill(int x, int y, int color) {
        if(isInside(x,y)) {
            raster.setPixel(x,y, color);
            seedFill(x+1, y, color);
            seedFill(x-1, y, color);
            seedFill(x, y+1, color);
            seedFill(x, y-1, color);
        }
    }

    @Override
    public void fill() {
        seedFill(seedX, seedY, fillColor);
    }
}
