package KMeans;

 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cluster {
	
	protected LinkedList<Point> _points;
	protected Centroid _centroid;
	protected int _id; // Record the id of the cluster
	
	//Creates a new Cluster
	public Cluster() {
		
	}
	
	public Cluster(int id) {
		this._id = id;
		this._points = new LinkedList<Point>();
		this._centroid = null;
	}
 
	public LinkedList<Point> getPoints() {
		return _points;
	}
	
	public void addPoint(Point point) {
		_points.add(point);
	}
 
	public void setPoints(LinkedList<Point> points) {
		this._points = points;
	}
 
	public Centroid getCentroid() {
		return _centroid;
	}
 
	public void setCentroid(Centroid centroid) {
		this._centroid = centroid;
	}
	
	public int size() {
		return this._points.size();
	}
 
	public int getId() {
		return _id;
	}
	
	public void setId(int id) {
		this._id = id;
	}
	
	// The following method update the centroid for this cluster
	public Centroid updateCentrod() {
		
		int size = _centroid.length();
		Point sum = new Point(size);
		// Sum the values
		for(Point point : _points) {
			sum.plusV(point);
		}
		// Scale by the number of elements
		double scale = 1.0/((double) _points.size());
		sum.scaleV(scale);
		Point _new_centroid = new Point(sum.elements());
		_centroid = new Centroid(_id, _new_centroid);
		return _centroid;
	}
	
	public void clear() {
		_points.clear();
	}
	
	// return the explanation of the cluster
	public void returnCluster() {
		System.out.println("[Cluster: " + _id+"]");
		System.out.println("[Centroid: " + _centroid + "]");
		System.out.println("[Points: \n");
		for(Point p : _points) {
			System.out.println(p);
		}
		System.out.println("]");
	}

}
