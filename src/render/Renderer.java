package render;

import model3D.Scene;
import model3D.Solid;
import raster.LineRasterizer;
import raster.Raster;
import transforms.Mat4;
import transforms.Mat4Identity;

abstract class Renderer {
    protected Raster raster;
    protected LineRasterizer rasterizer;
    protected Mat4 view = new Mat4Identity();
    protected Mat4 projection = new Mat4Identity();

    public void render(Solid solid) {}
    public void render(Scene scene){}
    public Renderer(Raster raster, LineRasterizer rasterizer) {}



}
