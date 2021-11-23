package model3D;

import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;

import java.util.ArrayList;
import java.util.List;

public abstract class Solid {
    protected List<Point3D> vertexBuffer;

    protected List<Integer> indexBuffer;

    protected Mat4 modelTransformation = new Mat4Identity();

    public Solid() {
        vertexBuffer = new ArrayList<>();
        indexBuffer = new ArrayList<>();
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public List<Point3D> getVertexBuffer() {
        return vertexBuffer;
    }

    public Mat4 getModel() {
        return modelTransformation;
    }
}
