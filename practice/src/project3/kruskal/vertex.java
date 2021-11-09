package project3.kruskal;

public class vertex {

    public double x;
    public double y;
    public int node;

    public vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public vertex(double x, double y, int node) {
        this.x = x;
        this.y = y;
        this.node = node;
    }

    @Override
    public boolean equals(Object o) {
       vertex v = (vertex) o;
       if (this.x==v.x && this.y == v.y){
           return true;
       } else {
           return false;
       }
    };

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "("+x+" , "+y+") ---> "+node;
    }
}
