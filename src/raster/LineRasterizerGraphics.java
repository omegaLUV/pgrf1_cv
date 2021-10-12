package raster;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LineRasterizerGraphics extends LineRasterizer{

    public LineRasterizerGraphics(Raster raster) {
        super.raster = raster;
    }

    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        BufferedImage img = ((RasterBufferedImage)raster).getImg();
        Graphics gr = img.getGraphics();
        gr.setColor(new Color(color));
        gr.drawLine(x1,y1,x2,y2);
    }
}
