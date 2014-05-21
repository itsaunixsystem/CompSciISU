package com.github.itsaunixsystem.chunks;

public class AxisAlignedBoundingBox extends AxisAlignedPolygon {
    private float x, y, width, height;

    public AxisAlignedBoundingBox(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
    }

    public AxisAlignedBoundingBox(AxisAlignedPolygon poly) {
        this.x = poly.minX();
        this.y = poly.minY();
        this.width = poly.maxX() - x;
        this.height = poly.maxY() - y;
    }

    public AxisAlignedBoundingBox(float width, float height) {
        this(0, 0, width, height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean intersects(AxisAlignedPolygon poly) {
        if(poly instanceof AxisAlignedBoundingBox) {
            return rectangleIntersection((AxisAlignedBoundingBox) poly);
        }

        AxisAlignedBoundingBox other = new AxisAlignedBoundingBox(poly);
        if(!rectangleIntersection(other))
            return false;


        //TODO Polygon intersection
        return true;
    }

    private boolean rectangleIntersection(AxisAlignedBoundingBox other) {
        //Thirsty for classes
        //But really only because it makes the last line clearer, basically just a temporary rectangle
        class TempRect {
            public float x1, x2, y1, y2;
            public TempRect(float x1, float x2, float y1, float y2) {
                this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2;
            }
        }

        //Named 'a' and 'b' for spareness of algorithm below
        TempRect a = new TempRect(
                this.getX(),
                this.getX() + this.getWidth(),
                this.getY(),
                this.getY() + this.getHeight()
        );

        TempRect b = new TempRect(
                other.getX(),
                other.getX() + other.getWidth(),
                other.getY(),
                other.getY() + other.getHeight()
        );

        //Quick and dirty collision check
        return (a.x1 < b.x2 && a.x2 > b.x1 && a.y1 < b.y2 && a.y2 > b.y1);
    }
}
