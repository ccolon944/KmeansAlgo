package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class Cluster {

    /**
     *@param ClusterIV indivdual cluster IV
     *@param centroid  centroid for cluster
     *@param ClusterID the identity of cluster
     *@param pointSet set of points that are assgned to cluster
     *@param Centroid_History list of centroids for the cluster throughou tthe iterations
     */
    private double ClusterIV;
    private Point centroid;
    private HashSet<Point> pointSet;
    private int clusterID;
    private ArrayList <Point> Centroid_History;

    public Cluster(int clusterID) {
        this.clusterID = clusterID;
        pointSet = new HashSet<Point>();
        Centroid_History= new ArrayList<>();
    }

    public HashSet<Point> getPointSet() {
        return pointSet;
    }

    /**
     * Calcluates Centroid
     */
    public void calculateCentroid(){
        double avgs[] = new double[7];
        for(int i = 0; i < avgs.length;i++)
            avgs[i] = 0;
        for(Point p : pointSet){
            avgs[0] += p.getArea();
            avgs[1] += p.getPerimeter();
            avgs[2] += p.getCompactness();
            avgs[3] += p.getLength_of_kernel();
            avgs[4] += p.getWidth_of_kernel();
            avgs[5] += p.getAsymmetry_coefficient();
            avgs[6] += p.getLength_of_kernel_groove();
        }
        centroid = new Point(avgs[0]/pointSet.size(),avgs[1]/pointSet.size(),
                avgs[2]/pointSet.size(),avgs[3]/pointSet.size(),
                avgs[4]/pointSet.size(),avgs[5]/pointSet.size(),
                avgs[6]/pointSet.size());

        centroid.setCluster(clusterID);
        Centroid_History.add(centroid);
    }

    public Point getCentroid() {
        return centroid;
    }

    public void addPoint(Point datum){
        datum.setCluster(clusterID);
        pointSet.add(datum);

    }

    public void removePoint(Point datum){
        pointSet.remove(datum);
    }

    /**
     * calculates clusterIV
     */
    public void calculateClusterIV(){
            ClusterIV = 0;
            for(Point datum : pointSet){
                ClusterIV += EuclideanDistance(datum,centroid);
            }
    }

    public double getClusterIV(){
        return ClusterIV;
    }
    public ArrayList<Point> getCentroid_History(){
        return Centroid_History;
    }


    public double EuclideanDistance(Point P1, Point P2){
        return Math.sqrt(Math.pow(P1.getArea()-P2.getArea(),2) +
                Math.pow(P1.getAsymmetry_coefficient()-P2.getAsymmetry_coefficient(),2)+
                Math.pow(P1.getCompactness()-P2.getCompactness(),2)+
                Math.pow(P1.getLength_of_kernel()-P2.getLength_of_kernel(),2)+
                Math.pow(P1.getLength_of_kernel_groove()-P2.getLength_of_kernel_groove(),2) +
                Math.pow(P1.getPerimeter()-P2.getPerimeter(),2)+
                Math.pow(P1.getWidth_of_kernel()-P2.getWidth_of_kernel(),2));
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "Centroid_History=" + Centroid_History +
                '}';
    }
}

