/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;

/**
 *
 * @author leoam14
 */
public class BlobImage {
    int minX;
    int minY;
    int maxX;
    int maxY;
    int area;
    int Label;
    int parent;
    int colour;
    int perimeter;

    public int getLabel() {
        return Label;
    }

    public void setLabel(int Label) {
        this.Label = Label;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colout) {
        this.colour = colout;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }
    
    public double[] getCentro(){
        double xy[] = new double[2];
        xy[0] = minX+((maxX-minX)/2);
        xy[1] = minY+((maxY-minY)/2);
        return xy;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
    
}
