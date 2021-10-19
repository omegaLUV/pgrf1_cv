package raster;

public interface Raster {
    int getWidth();
    int getHeight();
    void setBackgroundColor(int color);
    int getBackgroundColor();
    void setPixel(int x, int y, int color);
    void setPixel(int x, int y);
    int getPixel(int x, int y);
    void clear();
}
