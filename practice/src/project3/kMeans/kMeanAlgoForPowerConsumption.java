package project3.kMeans;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.zip.Inflater;

@SuppressWarnings("deprecation")
public class kMeanAlgoForPowerConsumption {


    static ArrayList<point> readPowerConsumption() throws IOException {
        Reader inG1 = new FileReader("/Users/drakezhou/IdeaProjects/primary/practice/src/project3/inputFiles/Project3_Power_Consumption.csv");
        CSVParser parser = CSVParser.parse(inG1, CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true));
        ArrayList<point> allPoints = new ArrayList<>();
        for (CSVRecord record : parser) {
            double c1 = Double.parseDouble(record.get(0));
            double c2 = Double.parseDouble(record.get(1));
            double c3 = Double.parseDouble(record.get(2));
            double c4 = Double.parseDouble(record.get(3));
            double c5 = Double.parseDouble(record.get(4));
            double c6 = Double.parseDouble(record.get(5));
            double c7 = Double.parseDouble(record.get(6));

            allPoints.add(new point(c1, c2, c3, c4, c5, c6, c7));
        }

        return allPoints;
    }

    static double distanceForPowerConsumption(point a, centroid b) {
        double g1 = a.g1 - b.g1;
        double g2 = a.g2 - b.g2;
        double g3 = a.g3 - b.g3;
        double s1 = a.s1 - b.s1;
        double s2 = a.s2 - b.s2;
        double s3 = a.s3 - b.s3;
        double vol = a.vol - b.vol;

        return g1 * g1 + g2 * g2 + g3 * g3 + vol * vol + s1 * s1 + s2 * s2 + s3 * s3;
    }

    static double distortionCalculate(centroid centroid) {
        ArrayList<point> points = centroid.assignedPoints;
        if (points.size() == 0){
            return 0;
        }

        double sum = 0;
        for (point p : points) {
            sum += distanceForPowerConsumption(p, centroid);
        }

        return sum / points.size();
    }

    static ArrayList[] assignVertexForPowerConsumption(ArrayList<point> allPoints, ArrayList<centroid> centroids) {

        for (int i = 0; i < allPoints.size(); i++) {
            point obj = allPoints.get(i);

            double minD = 999999;
            int index = -1;
            for (int i1 = 0; i1 < centroids.size(); i1++) {
                centroid cen = centroids.get(i1);
                double distance = distanceForPowerConsumption(obj, cen);
                if (distance < minD) {
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

    static void kMeansForElectricalConsumption(int k) throws IOException, CloneNotSupportedException {
        ArrayList<point> allPoints = readPowerConsumption();

        double aveDistortion = 99999;
//        double aveDistortion = 0;
        ArrayList<centroid> target = new ArrayList<>();

//        int determine = 20;
        int determine = 1;
        if (k == 2){
            determine = 1;
        }

        for (int count = 0; count < determine; count++) {
            int counter = 1;
            ArrayList<centroid> centroids = new ArrayList<>();

            // Randomly initial the start node
            {
                double g1Max = 0, g1Min = 99999, g2Max = 0, g2Min = 99999, g3Max = 0, g3Min = 99999;
                double volMax = 0, volMin = 99999, s1Max = 0, s1Min = 99999, s2Max = 0, s2Min = 99999, s3Max = 0, s3Min = 99999;
                for (point ele : allPoints) {
                    g1Max = Math.max(g1Max, ele.g1);
                    g2Max = Math.max(g2Max, ele.g2);
                    g3Max = Math.max(g3Max, ele.g3);
                    s1Max = Math.max(s1Max, ele.s1);
                    s2Max = Math.max(s2Max, ele.s2);
                    s3Max = Math.max(s3Max, ele.s3);
                    volMax = Math.max(volMax, ele.vol);

                    g1Min = Math.min(g1Min, ele.g1);
                    g2Min = Math.min(g2Min, ele.g2);
                    g3Min = Math.min(g3Min, ele.g3);
                    s1Min = Math.min(s1Min, ele.s1);
                    s2Min = Math.min(s2Min, ele.s2);
                    s3Min = Math.min(s3Min, ele.s3);
                    volMin = Math.min(volMin, ele.vol);
                };

                for (int i = 0; i < k; i++) {
                    double g1 = Math.random() * ((g1Max - g1Min) + g1Min);
                    double g2 = Math.random() * ((g2Max - g2Min) + g2Min);
                    double g3 = Math.random() * ((g1Max - g1Min) + g1Min);
                    double s1 = Math.random() * ((s1Max - s1Min) + s1Min);
                    double s2 = Math.random() * ((s2Max - s2Min) + s2Min);
                    double s3 = Math.random() * ((s3Max - s3Min) + s3Min);
                    double vol = Math.random() * ((volMax - volMin) + volMin);
                    centroids.add(new centroid(g1, g2, vol, g3, s1, s2, s3, new ArrayList<point>()));
                }
            }

            int[] stopList = new int[k];
            boolean flag = true;

            // k-means
            double finalDistortionForSinge = 99999;
            while (flag && counter <= 150) {
                if (k == 2) System.out.println("The "+counter+"th : ");
                // Renew flags
                for (int i = 0; i < stopList.length; i++) {
                    stopList[i] = 0;
                }
                flag = false;

                //Assign each vertex into clusters
                ArrayList[] result = assignVertexForPowerConsumption(allPoints, centroids);
                allPoints = result[0];
                centroids = result[1];

                // Calculate the cost
                double distortion = 0;
                for (int i = 0; i < centroids.size(); i++) {
                    distortion += distortionCalculate(centroids.get(i));
                }

                finalDistortionForSinge = distortion / k;
                if (k == 2) {
                    System.out.println("The distortion is --- "+finalDistortionForSinge);
                }

                //Renew the centroids
                for (int i = 0; i < centroids.size(); i++) {
                    centroid cen = centroids.get(i);
                    ArrayList<point> pointsList = cen.assignedPoints;
                    if (pointsList.size() == 0){
                        stopList[i] = 1;
                        if ( k == 2 ){
                            System.out.println(cen);
                        }
                        continue;
                    }
                    double g1All = 0;
                    double g2All = 0;
                    double g3All = 0;
                    double s1All = 0;
                    double s2All = 0;
                    double s3All = 0;
                    double volAll = 0;
                    for (point point : pointsList) {
                        g1All += point.g1;
                        g2All += point.g2;
                        g3All += point.g3;
                        s1All += point.s1;
                        s2All += point.s2;
                        s3All += point.s3;
                        volAll += point.vol;
                    }
                    double newG1 = g1All / pointsList.size();
                    double newG2 = g2All / pointsList.size();
                    double newG3 = g3All / pointsList.size();
                    double newS1 = s1All / pointsList.size();
                    double newS2 = s2All / pointsList.size();
                    double newS3 = s3All / pointsList.size();
                    double newVol = volAll / pointsList.size();
                    if (newG1 == cen.g1 && newG2 == cen.g2 && newG3 == cen.g3 && newS1 == cen.s1 && newS2 == cen.s2 && newS3 == cen.s3 && newVol == cen.vol) {
                        stopList[i] = 1;
                    }
                    cen.g1 = newG1;
                    cen.g2 = newG2;
                    cen.g3 = newG3;
                    cen.s1 = newS1;
                    cen.s2 = newS2;
                    cen.s3 = newS3;
                    cen.vol = newVol;

                    if (k == 2){
                        System.out.println(cen);
                    }
                }

                // Designed to get the average distortion of all 10 times for each K
                if (finalDistortionForSinge < aveDistortion){
                    aveDistortion = finalDistortionForSinge;
                    target = new ArrayList<centroid>();
                    for (centroid centroid : centroids) {
                        target.add(centroid.clone());
                    }
                }

                for (int i = 0; i < centroids.size(); i++) {
                    centroid cen = centroids.get(i);
                    cen.assignedPoints = new ArrayList<>();
                }

                // Stop criterion
                for (int i : stopList) {
                    if (i == 0) {
                        flag = true;
                        break;
                    }
                }

                counter++;

                if (k==2){
                    System.out.println();
                }
            } // while loop

        } //for

        if (k != 2){
            System.out.println("Centroids is : "+ aveDistortion);
            target.forEach(System.out::println);
        }

    }

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        // K range from 3 to 20
        for (int i = 3; i <= 20; i++) {
            System.out.println("When K == "+i);
            kMeansForElectricalConsumption(i);
            System.out.println();
        }

        // When K = 2
        kMeansForElectricalConsumption(2);

    }
}
