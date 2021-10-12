package raster;

public class LineRasterizerTrivial extends LineRasterizer{

    public LineRasterizerTrivial(Raster raster) {
        super.raster = raster;
    }

    public void setRaster(Raster raster) {
        super.raster = raster;
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        float k = (float)(y2-y1)/(x2-x1);
        float q = y1-(k*x1);

        for (int i = x1; i <= x2; i++) {
            int y = (int)(k*i+q);
            raster.setPixel(i, y, 0xffff00);
        }
    }
}
