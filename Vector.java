package KMeans;

// vector class
// This is the vector class adopted by Princeton algorithm class.
public class Vector {
	
	protected int n;         // length of the vector
    protected double[] data;       // array of vector's components
    
    // Default constructor
    public Vector() {}

    // create the zero vector of length n
    public Vector(int n) {
        this.n = n;
        this.data = new double[n];
    }

    // create a vector from an array
    public Vector(double[] data) {
        n = data.length;

        // defensive copy so that client can't alter our copy of data[]
        this.data = new double[n];
        for (int i = 0; i < n; i++)
            this.data[i] = data[i];
    }
    
    // copy constructor
    public Vector(Vector v) {
    	n = v.length();
    	this.data = v.elements();

    }
    
    public double[] elements() {
    	return this.data;
    }
    
    public double getCoordinate(int i) {
    	if (i > n-1) 
    		throw new IndexOutOfBoundsException("index outofbounds");
    	return data[i];
    	
    }
    
    // compute maximum elements
    public double max() {
    	double max = data[0];
    	for(int i_1 = 0; i_1 < n; ++i_1) {
    		if (max < data[i_1]) {
    			max = data[i_1];
    		}
    	}
    	return max;
    }
    
    public double min() {
    	double min = data[0];
    	for(int i_1 = 0; i_1 < n; ++i_1) {
    		if (min > data[i_1]) {
    			min = data[i_1];
    		}
    	}
    	return min;
    }


    // return the length of the vector
    public int length() {
        return n;
    }

    // return the inner product of this Vector a and b
    public double dot(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        double sum = 0.0;
        for (int i = 0; i < n; i++)
            sum = sum + (this.data[i] * that.data[i]);
        return sum;
    }

    // return the Euclidean norm of this Vector
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    // return the Euclidean distance between this and that
    public double distanceTo(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        return this.minus(that).magnitude();
    }

    // return this + that
    public Vector plus(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = this.data[i] + that.data[i];
        return c;
    }

    // return this - that
    public Vector minus(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = this.data[i] - that.data[i];
        return c;
    }
    
    public Vector plusS(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        for (int i = 0; i < n; i++)
            this.data[i] = this.data[i] + that.data[i];
        return this;
    }
    
    public void plusV(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        for (int i = 0; i < n; i++)
            this.data[i] = this.data[i] + that.data[i];
    }
    
    public Vector minusS(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        for (int i = 0; i < n; i++)
            this.data[i] = this.data[i] - that.data[i];
        return this;
    }

    // return the corresponding coordinate
    public double cartesian(int i) {
        return data[i];
    }

    // create and return a new object whose value is (this * factor)

    // create and return a new object whose value is (this * factor)
    public Vector scale(double factor) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = factor * data[i];
        return c;
    }
    
    public void scaleV(double factor) {
        for (int i = 0; i < n; i++)
            this.data[i] = factor * this.data[i];
    }


    // return the corresponding unit vector
    public Vector direction() {
        if (this.magnitude() == 0.0)
            throw new ArithmeticException("zero-vector has no direction");
        return this.scale(1.0 / this.magnitude());
    }

    // return a string representation of the vector
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('(');
        for (int i = 0; i < n; i++) {
            s.append(String.format("%.3f", data[i]));
            if (i < n-1) s.append(", ");
        }
        s.append(')');
        return s.toString();
    }


    // test client
    public static void main(String[] args) {
        double[] xdata = { 1.0, 2.0, 3.0, 4.0 };
        double[] ydata = { 5.0, 2.0, 4.0, 1.0 };

        Vector x = new Vector(xdata);
        Vector y = new Vector(ydata);

        StdOut.println("x        =  " + x);
        StdOut.println("y        =  " + y);
        StdOut.println("x + y    =  " + x.plus(y));
        StdOut.println("10x      =  " + x.scale(10.0));
        StdOut.println("|x|      =  " + x.magnitude());
        StdOut.println("<x, y>   =  " + x.dot(y));
        StdOut.println("|x - y|  =  " + x.minus(y).magnitude());
    }

}
