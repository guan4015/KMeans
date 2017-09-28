package KMeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPoint {
	
	// default constructor
	public RandomPoint() {};
	
    public Point Single(double min, double max, int dim) {
    	Random _r = new Random();
    	double[] _point_values = new double[dim];
    	for (int i_1 = 0 ; i_1 < dim ; i_1++) {
    		_point_values[i_1] = min + (max - min) * _r.nextDouble();
    	}
    	return new Point(_point_values);
    }
    
    public List<Point> createRandomPoints(double min, double max, int dim, int number) {
    	List<Point> _points = new ArrayList<Point>(number);
    	for(int i = 0; i < number; i++) {
    		_points.add(Single(min,max,dim));
    	}
    	return _points;
    }
	
    
    

}
