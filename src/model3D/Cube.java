package model3D;

import transforms.Point3D;

import java.util.ArrayList;

public class Cube extends Solid{
    public Cube() {
        super();
        vertexBuffer.add(new Point3D(0,0,0));
        //...
        indexBuffer.add(0);
        indexBuffer.add(7);
    }
}
