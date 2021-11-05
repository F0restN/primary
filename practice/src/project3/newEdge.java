package project3;

import java.util.Objects;

public class newEdge {


    public int startPoint;
    public int endPoint;
    public int len;
    public double x;
    public double y;

    public newEdge(int startPoint, int endPoint, double x, double y, int len) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.len = len;
        this.x = x;
        this.y = y;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    @Override
    public boolean equals(Object o) {
        newEdge obj = (newEdge) o;
        if (this.x == obj.x && this.y == this.y){
            return true;
        } else {
            return false;
        }
    };

    @Override
    public int hashCode() {

        return this.startPoint;
    }

    @Override
    public String toString() {
        return startPoint+" ---> "+endPoint+" = "+len;
    }

}
