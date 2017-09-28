package KMeans;

public class Centroid extends Point {
	
	// Default Constructor
	public Centroid() {}
	
	public Centroid(double[] list) {
		super(list);
	}

	public Centroid(int id, double[] list) {
		super(list);
		this._cluster_id = id;
	}

	public Centroid(int id, Point p) {
		super(p);
		this._cluster_id = id;
	}
	
	public Centroid(int id, Vector v) {
		super(v);
		this._cluster_id = id;
	}
	
	public Centroid(Centroid c) {
		super(c.elements());
		this._cluster_id = c._cluster_id;
	}
	
	public int getID() {
		return this._cluster_id;
	}
	
	public void setID(int id) {
		this._cluster_id = id ;
	}

	@Override
	public String toString() {
		return _cluster_id + " " + super.toString();
	}

}
