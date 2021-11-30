package render;

import model3D.Solid;
import raster.LineRasterizer;
import raster.Raster;
import transforms.Mat4;
import transforms.Point3D;

import java.util.ArrayList;
import java.util.List;

public class WireframeEngine extends Renderer{

    public WireframeEngine(Raster raster, LineRasterizer rasterizer) {
        super(raster, rasterizer);
    }

    @Override
    public void render(Solid solid) {
        Mat4 trans = solid.getModel().mul(view).mul(projection);
        List<Point3D> tempVB = new ArrayList<>();

        for(Point3D vbPoint : solid.getVertexBuffer()) {
            tempVB.add(vbPoint.mul(trans));
        }

        for(int i = 0; i < solid.getIndexBuffer().size(); i+=2) {
            int indexA = solid.getIndexBuffer().get(i);
            int indexB = solid.getIndexBuffer().get(i + 1);

            Point3D a = tempVB.get(indexA);
            Point3D b = tempVB.get(indexB);

//            a = a.mul(solid.getModel());
//            a = a.mul(view);
//            a = a.mul(projection);
//
//            b = b.mul(solid.getModel()).mul(view).mul(projection);

            //a = a.mul(trans);
            //b = b.mul(trans);

            render(a, b);
        }
    }

    private void render(Point3D a, Point3D b) {
        //clip
        if(a.getX() < -a.getW()) return;
        //...

        //dehomog

        //3D -> 2D

        //viewport
        int x1 = (int)(a.getX() + 1)*(raster.getWidth()/2);
        int y1 = ((int)((-a.getY() + 1)*(raster.getHeight()/2)));
        int x2 = (int)(b.getX() + 1)*(raster.getWidth()/2);
        int y2 = ((int)((-b.getX() + 1)*(raster.getHeight()/2)));
        //rasterize
        rasterizer.rasterize(x1,y1,x2,y2);
    }
}
