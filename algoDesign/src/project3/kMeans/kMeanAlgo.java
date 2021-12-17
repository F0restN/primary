package project3.kMeans;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class kMeanAlgo {

    static ArrayList<point> read() throws IOException {
        Reader inG1 = new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/project3/inputFiles/Project3_Test_Case.csv");
        CSVParser parser = CSVParser.parse(inG1, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        ArrayList<point> allPoints = new ArrayList<>();
        for (CSVRecord record : parser) {
            double c1 = Double.parseDouble(record.get(0));
            double c2 = Double.parseDouble(record.get(1));
            allPoints.add(new point(c1, c2));
        }

        return allPoints;
    }

    static double distance(point a, centroid b) {
        double x = a.x - b.x;
        double y = a.y - b.y;
        return x * x + y * y;
    }

    static double distortionCalculate(centroid centroid) {
        ArrayList<point> points = centroid.assignedPoints;
        double sum = 0;
        for (point p : points) {
            sum += distance(p, centroid);
        }

        return sum / points.size();
    }

    static ArrayList[] assignVertex(ArrayList<point> allPoints, ArrayList<centroid> centroids) {
        for (int i = 0; i < allPoints.size(); i++) {
            point obj = allPoints.get(i);

            double minD = 999999;
            int index = 0;
            for (int i1 = 0; i1 < centroids.size(); i1++) {
                centroid cen = centroids.get(i1);
                double distance = distance(obj, cen);
                if (minD > distance) {
                    minD = distance;
                    index = i1;
                }
            }
            obj.cluster = index;
            centroids.get(index).assignedPoints.add(obj);
        }

        ArrayList[] list = new ArrayList[2];
        list[0] = allPoints;
        list[1] = centroids;

        return list;
    }

    static void kMeansForTestData(int k) throws IOException {

        ArrayList<point> allPoints = read();
        ArrayList<centroid> centroids = new ArrayList<>();

        // Randomly initial the start node
        {
            double xMax = 0, xMin = 0, yMax = 0, yMin = 0;
            for (point ele : allPoints) {
                xMax = Math.max(ele.x, xMax);
                yMax = Math.max(ele.x, yMax);
                xMin = Math.min(ele.x, xMin);
                yMin = Math.min(ele.y, yMin);
            }

            for (int i = 0; i < k; i++) {
                double x = Math.random() * ((xMax - xMin) + xMin);
                double y = Math.random() * ((yMax - yMin) + yMin);
                centroids.add(new centroid(x, y, new ArrayList()));
            }
        }

        // Stop function
        int[] stopList = new int[2];
        Boolean flag = true;

        while (flag) {
            // Renew flags
            for (int i = 0; i < stopList.length; i++) {
                stopList[i] = 0;
            }
            flag = false;

            //Assign each vertex into clusters
            ArrayList[] result = assignVertex(allPoints, centroids);
            allPoints = result[0];
            centroids = result[1];

            // Calculate the cost
            double distortion = 0;
            for (centroid centroid : centroids) {
                distortion += distortionCalculate(centroid);
            }
            System.out.println("The distortion is --- "+distortion / k);

            //Renew the centroids
            for (int i = 0; i < centroids.size(); i++) {
                centroid cen = centroids.get(i);
                ArrayList<point> pointsList = cen.assignedPoints;
                double xAll = 0;
                double yAll = 0;
                for (point point : pointsList) {
                    xAll += point.x;
                    yAll += point.y;
                }
                double newX = xAll / pointsList.size();
                double newY = yAll / pointsList.size();

                if (newX == cen.x && newY == cen.y) {
                    stopList[i] = 1;
                }

                cen.x = newX;
                cen.y = newY;
                System.out.println(cen);
            }

            // Stop criterion
            for (int i : stopList) {
                if (i == 0) {
                    flag = true;
                    break;
                }
            }

            for (int i = 0; i < centroids.size(); i++) {
                centroid cen = centroids.get(i);
                cen.assignedPoints = new ArrayList();
            }

            System.out.println();

        }


//        System.out.println("Centroids is :");
//        centroids.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        kMeansForTestData(2);
    }
}