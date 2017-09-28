package KMeans;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class KMeans2 {
	//Number of Clusters. This metric should be related to the number of points
    protected int _num_clusters2;    
    //Number of Points
    protected int _num_points2;
    // Number of iterations
    protected int _num_iterations2;
    
    // moving tolerance for centroids
    protected double _tolerance2;
    
    protected LinkedList<Point> _points2;
    protected ArrayList<Cluster> _clusters2;
    protected ArrayList<Centroid> _centroids2;
    protected int _num2;  // Record the number of elements
    protected int _dim2;

    
    public KMeans2() {
    	this._points2 = new LinkedList<Point>();
    	this._clusters2 = new ArrayList<Cluster>();  
    	this._centroids2 = new ArrayList<Centroid>();
    }
    
    // Constructor, Initialize the KMeans class
    public KMeans2(LinkedList<Point> points, int clusters, int iterations, double tolerance){
    	this._points2 = points;
    	this._clusters2 = new ArrayList<Cluster>();  
    	this._centroids2 = new ArrayList<Centroid>();
    	this._num_clusters2 = clusters;
    	this._num_iterations2 = iterations;
    	this._num2 = points.size();
    	this._dim2 = points.get(0).length();
    	this._tolerance2 = tolerance;
    }
    
    public void setInitialPointStatus(LinkedList<Point> points) {
    	this._points2 = points;
    }
    
    public void setInitialClusterNumber(int clusters) {
    	this._num_clusters2 = clusters;
    }
    
    public void setIterationNumber(int iterations) {
    	this._num_iterations2 = iterations;
    }
    
    public void setConvergenceTolerance(double tolerance) {
    	this._tolerance2 = tolerance;
    }
    
    
    public void init(boolean print) {
    	// Compute the range of the points
    	
    	// Initialize the min and max coordinates of the points
    	double min_coordinate = _points2.get(0).min();
    	double max_coordinate = _points2.get(0).max();
    	
    	// Compute the maximum square region that can encapsulate all points
    	for (int j_1 = 0; j_1 < _num2; ++j_1) {
    		if (min_coordinate > _points2.get(j_1).min()) {
    			min_coordinate = _points2.get(j_1).min();
    		}
    		if (max_coordinate < _points2.get(j_1).max()) {
    			max_coordinate = _points2.get(j_1).max();
    		}
    	}
    	
    	//Create Clusters
    	//Set Random Centroids
    	for (int i = 0; i < _num_clusters2; i++) {
    		Cluster cluster = new Cluster(i);  // create a new cluster indexed i
    		RandomPoint r1 = new RandomPoint();  // generate a random number generator
    		
    		// Set the initial random value of centroid in interval min_coordinate,max_coordinate with dimension _dim 
    		Point centroid_c = r1.Single(min_coordinate,max_coordinate,_dim2);
    		
    		// Initialize a centroid member and assign it to this cluster
    		Centroid _centroid = new Centroid(i,centroid_c);
    		cluster.setCentroid(_centroid);
    		_clusters2.add(cluster);
    		_centroids2.add(_centroid);
    	}
    	
    	// If print is true, we return the explanation of the cluster
    	if (print == true) {
    		
    		Iterator<Cluster> clusterIterator = _clusters2.iterator();
    		
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
    		
    		// Returns the summary of centroids each step if required
    		if (print == true) {
    			System.out.printf("The centroids in the %d th iterations are:\n",iteration);
    			for(int i = 0; i < newcentroids.size(); i++) {
    				System.out.println(newcentroids.get(i).toString());
    				//System.out.println("\n");
    			}
    		}
    		
            // Give a criteria whether we need to go to the next step.
    		// If the shifts in centroids are less than the tolerance, we stop
    		double distance = 0;
    		for (int j_1 = 0; j_1 < newcentroids.size(); j_1++) {
    			distance += newcentroids.get(j_1).distanceTo(lastcentroids.get(j_1));
    		}
    		
    		if (distance < _tolerance2 | iteration >= _num_iterations2) {
    			finish = true;
    		}
    		
    	}
    	
    	
    }
    
    // clear cluster 
    public void ClearCluster() {
		for (int i_1 = 0; i_1 <_num_clusters2; i_1++) {
			_clusters2.get(i_1).clear();
			
		}
    }
    
    // Obtain the centroid
    protected ArrayList<Centroid> getCentroids() {
    	ArrayList<Centroid> centroids = new ArrayList<Centroid>();
    	for(int i_1 = 0; i_1 < _num_clusters2; i_1++) {
    		Centroid aux = _clusters2.get(i_1).getCentroid();
    		centroids.add(aux);
    	}
    	
    	return centroids;
    }
    
    // Update the centroid
    protected ArrayList<Centroid> updateCentroids() {
    	ArrayList<Centroid> centroids = new ArrayList<Centroid>();
    	for(int i_1 = 0; i_1 < _num_clusters2; i_1++) {
    		if (_clusters2.get(i_1).getPoints().size() != 0) {
    			Centroid update = _clusters2.get(i_1).updateCentrod();
    			centroids.add(update);
    		} else {
    			centroids.add(_clusters2.get(i_1).getCentroid());
    		}
    		
    		
    	}
    	_centroids2 = centroids;
    	return centroids;
    }
    
    
    // For each point assign its cluster.
    @SuppressWarnings("unchecked")
	public void assignCluster() {
    	
    	int _num_per_cluster = _points2.size()/_num_clusters2; 
    	LinkedList<Pair<Point,Double>> record = new LinkedList<Pair<Point,Double>>();
    	
    	// In what follows, we traverse every 
    	for(int i_1 = 0; i_1 < _clusters2.size(); i_1++) {
    		// Find the centroid of this cluster
    		Centroid centroid = _clusters2.get(i_1).getCentroid();
    		
    		// Special case for the first loop, we should generate record
    		if (i_1 == 0) {
    			for(Point point : _points2) {
    				// Record the distance with each point
					@SuppressWarnings("rawtypes")
					Pair pair_p_d = new Pair(point,point.distanceTo(centroid));
    				record.add(pair_p_d);
    			}
    			
    		} else {
    			for (Pair pair: record) {
    				// Update the second argument
    				pair.setSecondObject(((Point) pair.getFirstObject()).distanceTo(centroid));
    			}
    		}
    		
    		// Sort the linkedlist
    		Collections.sort(record,
    				new Comparator<Pair<Point,Double>>(){
    			@Override
    			public int compare(Pair<Point, Double> o1, Pair<Point, Double> o2) {
    				return doubleComparator.compare(o1.getSecondObject(), o2.getSecondObject(),0.0001);
    			}
    			
    		});
    		
    		// remove the first _num_per_cluster elements and add to the specified cluster
    		for (int j_1 = 0; j_1 < _num_per_cluster; j_1++) {
    			Point point_2 = record.remove().getFirstObject();
    			point_2.SetClusterID(i_1);
    			_clusters2.get(i_1).addPoint(point_2);
    		}
    		
    	}
	
    }
    
    // Get clusters
    public ArrayList<Cluster> getCluster(){
    	return _clusters2;
    }
    
    // Get cluster summary
    public void getSummary() {
    	for (Cluster cluster : _clusters2) {
    		System.out.printf("The %d th cluster is summarized as:\n",cluster.getId());
    		cluster.returnCluster();
    	}
    }
    
    // Get centroids summary
    public void getCentroidSummary() {
    	for (Centroid centroid : _centroids2) {
    		StdOut.printf("The centroid of %d th cluster is summarized as:\n",centroid.getID());
    		StdOut.println(centroid.toString());
    		StdOut.printf(", %d points in this cluster.\n", _clusters2.get(centroid.GetClusterID()).getPoints().size());
    	}
    }
      

}
