package fill;

import raster.Raster;

public class SeedFiller extends Filler {
    private int seedX, seedY;
    private int background;


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
        return raster.getPixel(seedX,seedY) == background;
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
        background = raster.getPixel(seedX, seedY);
        seedFill(seedX, seedY, fillColor);
    }
}
