package raster;

public class RasterArray implements Raster {

    private int width, height;
    private int[][] array;

    public RasterArray(int width, int height) {
        array = new int[width][height];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setBackgroundColor(int color) {

    }

    @Override
    public int getBackgroundColor() {
        return 0;
    }

    @Override
    public void setPixel(int x, int y, int color) {
        array[x][y] = color;
    }

    @Override
    public void setPixel(int x, int y) {

    }

    @Override
    public int getPixel() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
