package KMeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author xiaog
 *
 */
public class Point extends Vector{
	
	// protected int _cluster_id;
	protected static double TOLERANCE = 0.0001;
	protected int _cluster_id;
	
	// Default Constructor
	public Point()  {}
	
	// Constructor that receives length
	public Point(int length) { super(length); }
	
	
	// Constructor that overrides the vector class
	public Point(double[] list) {
		super(list);
	}
	
	public Point(Vector v) {
		super(v);
	}
	
	public Point(Point p) {
		super(p);
	}
	
	// Selecters
	public int GetClusterID() {  return _cluster_id;   }
	
	// Modifiers
	public void SetClusterID(int id) { this._cluster_id = id;  }
	
	// Operators
	// Equaling operator
	public boolean equalsTo(Object object) {
		if (this == object) {
			return true;
		}
		if(!(object instanceof Point)) {
			return false;
		}
		
		// Note that the inputs of Point types should be primitive
		Point _point1 = (Point) object;

		double _distance = this.distanceTo(_point1);
		return (_distance < TOLERANCE);
	}
	

	
	/**
	 * @param list 
	 * @return the closet centroid with respect to this point
	 */
	public Centroid findClosest( ArrayList<Centroid> list ) {
		
		// Make first one best for now
		Iterator<Centroid> centroidIterator = list.iterator();
		Centroid bestCentroid = centroidIterator.next();
		double bestDistance = this.distanceTo( bestCentroid );
		
		// Now look for something better
		while( centroidIterator.hasNext() ) {
			Centroid centroid = centroidIterator.next();
			double distance = this.distanceTo( centroid );
			if( distance < bestDistance ) {
				bestCentroid = centroid;
				bestDistance = distance;
			}
		}
		
		// Return best
		return( bestCentroid );
	}
    
}
