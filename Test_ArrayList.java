package KMeans;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class Test_ArrayList extends TestCase {

	@Test
	public void ArrayList() {
		String _sortField = "CRH" ;
		System.out.println(String.format( "SortField(%s)", _sortField ));
		ArrayList<String> d = new ArrayList<String>(5);
		d.add(0, "my");
		d.add(1,"name");
		d.add(2, "is");
		d.add(3, "what");
		d.add(4, "crh");
		System.out.println(d.get(0));
	}

}
