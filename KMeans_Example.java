package KMeans;

import java.util.LinkedList;

public class KMeans_Example {
	
	public static void main(String[] arg) throws Exception {
		

//		// Test 1
//		// In what follows, we manually generate three clusters with each one
//		// centered at some point and some points surrounding the centroid.
//		// It is obvious that if the initial conditions are good, the algorithms
//		// will converge very quickly for both. However, this is the extreme case
//		// that the second K-means algorithms 
		LinkedList<Point> points_list = new LinkedList<Point>();
		LinkedList<Point> centroids_list = new LinkedList<Point>();
		double[] coor1 = {100.0,100.0};
		double[] coor2 = {200.0,200.0};
		double[] coor3 = {350.0,200.0};
		Point p1 = new Point(coor1);
		Point p2 = new Point(coor2);
		Point p3 = new Point(coor3);
		centroids_list.add(p1);
		centroids_list.add(p2);
		centroids_list.add(p3);
		for (Point point : centroids_list) {
			for (int i = 0 ; i < 10 ; i++) {
				double[] coor = {((double)i)*1.5,-((double)i)*1.5};
				Point shift = new Point(coor);
				Point newPoint = new Point(shift.plus(point).elements());
				points_list.add(newPoint);
			}
		}
		points_list.add(p1);
		points_list.add(p2);
		points_list.add(p3);
		
		// Test the KMeans1
		KMeans class1 = new KMeans(points_list,3,20,0.01);
		class1.init(true);
		class1.fit(true);
		class1.getSummary();
		
		// Test the KMeans2
		KMeans2 class2 = new KMeans2(points_list, 3, 80, 0.01);
		class2.init(true);
		class2.fit(true);
		class2.getSummary();
		
		
		// In what follows, we will deal with the case where the data are sparely distributed. 
		// In this test, the points are distributed in grids with dimension 99*99. In other words,
		// we have 100*100 points to be classified. 
		// create points list
		LinkedList<Point> points_list2 = new LinkedList<Point>();
		int grid_size = 100;
		for (int i_1 = 0; i_1 < grid_size; i_1++) {
			for (int i_2 = 0; i_2 < grid_size; i_2++) {
				double[] c = {i_1,i_2};
				Point p = new Point(c);
				points_list2.add(p);
			}
		}
		
		// create kmeans object and test it
		// The cluster number is 3 and the number of iterations is 80 
		KMeans grid_cluster1 = new KMeans(points_list2, 8, 80, 0.01);
		grid_cluster1.init(true);
		grid_cluster1.fit(true);
		grid_cluster1.getCentroidSummary();
		
		// create kmeans2 object and test it
		// The cluster number is 500 and the number of iterations is 80
		KMeans2 grid_cluster2 = new KMeans2(points_list2, 500, 80, 0.01);
		grid_cluster2.init(true);
		grid_cluster2.fit(true);
		grid_cluster2.getCentroidSummary();

	}

}
