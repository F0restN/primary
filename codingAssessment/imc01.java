public class imc01 {

    public boolean isValid(int c, int x1, int y1, int x2, int y2){
        if (x1 == 0 && y1 == 0 && c==0){
            return false;
        }
        return true;
    }

    public boolean isObstacle(int x1, int y1){
        double a = 0;
        try {
            a = Math.sqrt(x1+y1);
        } catch (Exception e){
            return false;
        }
        int b = (int) a;
        return a-b == 0;
    }

    public boolean isReachable(int c, int x1, int y1, int x2, int y2){
        if (x1 > x2 || y1 > y2 || isObstacle(x1, y1) || !isValid(c, x1, y1, x2, y2)){
            return false;
        }
        if (x1 == x2 && y1 == y2){
            return true;
        }
        return (isReachable(c, x1+y1, y1, x2, y2) || isReachable(c, x1, y1+x1, x2, y2) || isReachable(c, x1+c, y1+c, x2, y2));
    }

    public void canReach(int c, int x1, int y1, int x2, int y2){
        if (isReachable(c, x1, y1, x2, y2)){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static void main (String[] args){
        imc01 test = new imc01();
        test.canReach(1, 2, 1, 3, 5);
    }

}
