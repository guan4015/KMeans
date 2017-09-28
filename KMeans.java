package KMeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class KMeans {
	
	//Number of Clusters. This metric should be related to the number of points
    protected int _num_clusters;    
    //Number of Points
    protected int _num_points;
    // Number of iterations
    protected int _num_iterations;
    
    // moving tolerance for centroids
    protected double _tolerance;
    
    protected LinkedList<Point> _points;
    protected ArrayList<Cluster> _clusters;
    protected ArrayList<Centroid> _centroids;
    protected int _num;  // Record the number of elements
    protected int _dim;  // Record the dimension of elements
    
    public KMeans() {
    	this._points = new LinkedList<Point>();
    	this._clusters = new ArrayList<Cluster>();  
    	this._centroids = new ArrayList<Centroid>();
    }
    
    // Constructor, Initialize the KMeans class
    public KMeans(LinkedList<Point> points, int clusters, int iterations, double tolerance){
    	this._points = points;
    	this._clusters = new ArrayList<Cluster>();  
    	this._centroids = new ArrayList<Centroid>();
    	this._num_clusters = clusters;
    	this._num_iterations = iterations;
    	this._num = points.size();
    	this._dim = points.get(0).length();
    	this._tolerance = tolerance;
    }
    
    public void setInitialPointStatus(LinkedList<Point> points) {
    	this._points = points;
    }
    
    public void setInitialClusterNumber(int clusters) {
    	this._num_clusters = clusters;
    }
    
    public void setIterationNumber(int iterations) {
    	this._num_iterations = iterations;
    }
    
    public void setConvergenceTolerance(double tolerance) {
    	this._tolerance = tolerance;
    }
    
    
    
    public void init(boolean print) {
    	// Compute the range of the points
    	
    	// Initialize the min and max coordinates of the points
    	double min_coordinate = _points.get(0).min();
    	double max_coordinate = _points.get(0).max();
    	
    	// Compute the maximum square region that can encapsulate all points
    	for (int j_1 = 0; j_1 < _num; ++j_1) {
    		if (min_coordinate > _points.get(j_1).min()) {
    			min_coordinate = _points.get(j_1).min();
    		}
    		if (max_coordinate < _points.get(j_1).max()) {
    			max_coordinate = _points.get(j_1).max();
    		}
    	}
    	
    	//Create Clusters
    	//Set Random Centroids
    	for (int i = 0; i < _num_clusters; i++) {
    		Cluster cluster = new Cluster(i);  // create a new cluster indexed i
    		RandomPoint r1 = new RandomPoint();  // generate a random number generator
    		
    		// Set the initial random value of centroid in interval min_coordinate,max_coordinate with dimension _dim 
    		Point centroid_c = r1.Single(min_coordinate,max_coordinate,_dim);
    		
    		// Initialize a centroid member and assign it to this cluster
    		Centroid _centroid = new Centroid(i,centroid_c);
    		cluster.setCentroid(_centroid);
    		_clusters.add(cluster);
    		_centroids.add(_centroid);
    	}
    	
    	// If print is true, we return the explanation of the cluster
    	if (print == true) {
    		
    		Iterator<Cluster> clusterIterator = _clusters.iterator();
    		
    		// Now look for something better
    		while( clusterIterator.hasNext() ) {
    			Cluster cluster = clusterIterator.next();
    			cluster.returnCluster();

    		}
    	}
    		
    }
    
    // This method will fit the Kmeans method
    public void fit(boolean print) {
    	
    	boolean finish = false;
    	int iteration = 0;
    	// After initialization, the second step is to assign each point to its cluster
    	while (finish != true) {
    		
    		// get rid of the points in cluster
    		ClearCluster();
    		
    		// Obtain the original centroid
    		ArrayList<Centroid> lastcentroids = getCentroids();
    		// For each point, assign its cluster
    		assignCluster();
    		
    		// iteration +1
    		iteration++;
    		
    		// update centroids
    		ArrayList<Centroid> newcentroids = updateCentroids();
    		
    		// Judge if we would like the returns of centroids in each step
    		if (print == true) {
    			System.out.printf("The centroids in the %d th iterations are:\n",iteration);
    			for(int i = 0; i < newcentroids.size(); i++) {
    				System.out.println(newcentroids.get(i).toString());
    				//System.out.println("\n");
    			}
    		}
    		
            // Judge if the shifts of the centroids are large or not.
    		double distance = 0;
    		for (int j_1 = 0; j_1 < newcentroids.size(); j_1++) {
    			distance += newcentroids.get(j_1).distanceTo(lastcentroids.get(j_1));
    		}
    		
    		if (distance < _tolerance | iteration >= _num_iterations) {
    			finish = true;
    		}
    		
    	}
    	
    	
    }
    
    // clear cluster, namely clear the points in each cluster.
    public void ClearCluster() {
		for (int i_1 = 0; i_1 <_num_clusters; i_1++) {
			_clusters.get(i_1).clear();
			
		}
    }
    
    // Obtain the centroid
    protected ArrayList<Centroid> getCentroids() {
    	ArrayList<Centroid> centroids = new ArrayList<Centroid>();
    	for(int i_1 = 0; i_1 < _num_clusters; i_1++) {
    		Centroid aux = _clusters.get(i_1).getCentroid();
    		centroids.add(aux);
    	}
    	
    	return centroids;
    }
    
    // Update the centroid 
    // Once finishing the points assignment, we update the centroids for each cluster.
    protected ArrayList<Centroid> updateCentroids() {
    	ArrayList<Centroid> centroids = new ArrayList<Centroid>();
    	for(int i_1 = 0; i_1 < _num_clusters; i_1++) {
    		if (_clusters.get(i_1).getPoints().size() != 0) {
    			Centroid update = _clusters.get(i_1).updateCentrod();
    			centroids.add(update);
    		} else {
    			centroids.add(_clusters.get(i_1).getCentroid());
    		}
    		
    		
    	}
    	_centroids = centroids;
    	return centroids;
    }
    
    
    // For each point assign its cluster. Traverse each point and compute the distance between this point and 
    // the centroids and find the closest one. 
    public void assignCluster() {

    	for(Point point : _points) {
        	Centroid centroid = point.findClosest(_centroids);
        	int id = centroid.GetClusterID();
        	point.SetClusterID(id);
        	this._clusters.get(id).addPoint(point);
        }
    	
    }
    
    // Get clusters
    public ArrayList<Cluster> getCluster(){
    	return _clusters;
    }
    
    // Get cluster summary
    public void getSummary() {
    	for (Cluster cluster : _clusters) {
    		System.out.printf("The %d th cluster is summarized as:\n",cluster.getId());
    		cluster.returnCluster();
    	}
    }
    
    // Get centroid summary
    public void getCentroidSummary() {
    	for (Centroid centroid : _centroids) {
    		System.out.printf("The centroid of %d th cluster is summarized as:\n",centroid.getID());
    		System.out.println(centroid.toString());
    		StdOut.printf(", %d points in this cluster.\n", _clusters.get(centroid.GetClusterID()).getPoints().size());
    	}
    }

}
