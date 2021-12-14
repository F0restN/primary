package project4;

import java.io.Serializable;
import java.util.ArrayList;

public class problem3 {

    static class node implements Serializable, Cloneable {
        int x;
        int y;
        int z;

        public node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int[] getValues(){
            int[] result = new int[3];
            result[0] = x;
            result[1] = y;
            result[2] = z;
            return result;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "("+x+" , "+y+" , "+z+")";
        }
    }

    static boolean isBiggerThanBefore(node a, ArrayList<node> queen){
        if (queen.isEmpty()){
            return true;
        }

        node b = queen.get(queen.size()-1);
        if (a.z > b.z){
            return true;
        } else if (a.z == b.z){
            if(a.y > b.y){
                return true;
            } else if (a.y == b. y){
                if (a.x > b.x){
                    return true;
                }
            }
        }

        return false;
    }

    static boolean isConflictDiagnose(node a, node b){
        int[] arr1 = a.getValues();
        int[] arr2 = b.getValues();
        // Subtraction Array
        int[] arr3 = new int[3];

        for (int i = 0; i < arr1.length; i++) {
            arr3[i] = Math.abs(arr1[i] - arr2[i]);
        }

        for (int i = 0; i < arr3.length; i++) {
            for (int j = 0; j < arr3.length; j++) {
                // Check whether there are two same number
                if ( i != j && arr3[i] == arr3[j] ){
                    if (arr3[0] == arr3[1] && arr3[1] == arr3[2]){
                        return true;
                    }
                    if (arr3[3-j-i] == 0){
                        return true;
                    }
                }

            }
        }

        return false;
    }

    static boolean isValidate(node node, ArrayList<node>queen) {
        // For the first node
        if (queen.size() == 1){
            return true;
        }

        for (int i = 0; i < queen.size()-1; i++) {
            node pasNode = queen.get(i);

            // Whether they are on the same layer
            if (pasNode.z == node.z){
                if (pasNode.x == node.x || pasNode.y == node.y || Math.abs(pasNode.x - node.x) == Math.abs(pasNode.y-node.y)) {
                    return false;
                }
            } else {
                if ( (pasNode.x == node.x && pasNode.y == node.y) || (isConflictDiagnose(pasNode, node))  ){
                    return false;
                }
            }

        }

        return true;
    }

    static int setNextQueen(int n, int level, int count, ArrayList<node> queen) throws CloneNotSupportedException {
        if (level >= n) {
            count++;
            return count;
        }

        for (int z = 0; z < n; z++) {
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {

                    // To avoid duplication
                    if (isBiggerThanBefore(new node(x,y,z), queen)){
                        queen.add(new node(x,y,z));

                        // Validation checkoutput
                        if (isValidate(new node(x,y,z), queen)) {
                            count = setNextQueen(n, level+1, count, queen);
                        }
                        queen.remove(queen.size()-1);
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("When N = 2  # of solution is = "+setNextQueen(2, 0, 0, new ArrayList<>()) );
        System.out.println("When N = 3  # of solution is = "+setNextQueen(3, 0, 0, new ArrayList<>()) );
        System.out.println("When N = 4  # of solution is = "+setNextQueen(4, 0, 0, new ArrayList<>()) );
        System.out.println("When N = 5  # of solution is = "+setNextQueen(5, 0, 0, new ArrayList<>()) );
    }

}
