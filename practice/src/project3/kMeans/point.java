package project3.kMeans;

public class point {

    double x;
    double y;
    int cluster;

    double g1;
    double g2;
    double vol;
    double g3;
    double s1;
    double s2;
    double s3;

    public point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public point(double g1, double g2, double vol, double g3, double s1, double s2, double s3) {
        this.g1 = g1;
        this.g2 = g2;
        this.vol = vol;
        this.g3 = g3;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    @Override
    public String toString() {
        if (x == 0 && y == 0) {
            return "(" + g1 + " , " + g2 + " , " + vol + " , " + g3 + " , " + s1 + " , " + s2 + " , " + s3 + ")";
        } else {
            return "("+x+" , "+y+") --> "+cluster;
        }
    }
}
