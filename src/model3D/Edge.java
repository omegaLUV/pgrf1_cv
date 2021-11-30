package model3D;

import transforms.Point3D;

public class Edge extends Solid{
    public Edge() {
        super();
        vertexBuffer.add(new Point3D(0,0,0));
        vertexBuffer.add(new Point3D(1,1,1));
        //...
        indexBuffer.add(0);
        indexBuffer.add(1);
    }
}
