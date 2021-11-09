package project3.kMeans;

import java.io.Serializable;
import java.util.ArrayList;

public class centroid implements Serializable, Cloneable {

    double x;
    double y;
    ArrayList<point> assignedPoints;

    double g1;
    double g2;
    double vol;
    double g3;
    double s1;
    double s2;
    double s3;

    public centroid(double x, double y, ArrayList<point> assignedPoints) {
        this.x = x;
        this.y = y;
        this.assignedPoints = assignedPoints;
    }

    public centroid(double g1, double g2, double vol, double g3, double s1, double s2, double s3, ArrayList<point> assignedPoints) {
        this.g1 = g1;
        this.g2 = g2;
        this.vol = vol;
        this.g3 = g3;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.assignedPoints = assignedPoints;
    }

    @Override
    protected centroid clone() throws CloneNotSupportedException {
        return (centroid) super.clone();
    }

    @Override
    public String toString() {
        if (x == 0 && y == 0) {
            return "(" + g1 + " , " + g2 + " , " + vol + " , " + g3 + " , " + s1 + " , " + s2 + " , " + s3 + ") --- "+assignedPoints.size();
        } else {
            return "(" + x + " , " + y + ") --- "+assignedPoints.size();
        }
    }
}
