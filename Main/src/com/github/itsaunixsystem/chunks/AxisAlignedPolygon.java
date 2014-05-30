package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.math.Polygon;

public class AxisAlignedPolygon {
    private Point2D[] points;

    public AxisAlignedPolygon(Point2D... points) {
        this.points = points;
    }

    public AxisAlignedPolygon(float x, float y, float width, float height) {
        this(new Point2D(x, y),
                new Point2D(x + width, y),
                new Point2D(x + width, y + height),
                new Point2D(x, y + height));
    }

    public AxisAlignedPolygon(Polygon polygon) {
        this.points = new Point2D[polygon.getVertices().length / 2];
    }

    public float maxX() {
        float x = Float.MIN_VALUE;
        for(Point2D point : points) {
            x = Float.max(x, point.x);
        }
        return x;
    }

    public float maxY() {
        float y = Float.MIN_VALUE;
        for(Point2D point : points) {
            y = Float.max(y, point.y);
        }
        return y;
    }

    public float minX() {
        float x = Float.MAX_VALUE;
        for(Point2D point : points) {
            x = Float.min(x, point.x);
        }
        return x;
    }

    public float minY() {
        float y = Float.MAX_VALUE;
        for(Point2D point : points) {
            y = Float.min(y, point.y);
        }
        return y;
    }

    public boolean testVertex(Point2D[] verts, Point2D testPoint)
    {
        boolean check = false;
        for(int i = 0, j = verts.length-1; i < verts.length; j = i++)
        {
            if (((verts[i].y > testPoint.x) != (verts[j].y > testPoint.y)) &&
                    (testPoint.x < (verts[j].x - verts[i].x) * (testPoint.y - verts[i].y) / (verts[j].y - verts[i].y) + verts[i].x))
                check = !check;
        }
        return check;
    }
    //Didn't use vector for speed reasons
    public static class Point2D {
        public float x, y;
        public Point2D(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
