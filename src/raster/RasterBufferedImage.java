package raster;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster{
    BufferedImage img;
    private int foregroundColor;
    private int backgroundColor;

    public RasterBufferedImage(int width, int height) {
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public RasterBufferedImage(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage getImg() {
        return img;
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

    @Override
    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
    }

    @Override
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override
    public void setPixel(int x, int y, int color) {
        this.img.setRGB(x,y,color);
    }

    @Override
    public void setPixel(int x, int y) {
        this.img.setRGB(x,y,this.foregroundColor);
    }

    @Override
    public int getPixel() {
        return 0;
    }

    @Override
    public void clear() {
        Graphics gr = img.getGraphics();
        gr.setColor(new Color(backgroundColor));
        //gr.fillRect(0, 0, img.getWidth(), img.getHeight());
        gr.fillRect(0, 0, getWidth(), getHeight());
    }
}
