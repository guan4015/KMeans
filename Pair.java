package KMeans;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;


/**The purpose of this structure is to store point associated with its distance from some centroid
 * @author xiaog 
 * 
 * @param <Type1>
 * @param <Type2>
 */
public class Pair<Type1,Type2> {
	
	protected Type1 _o1;
	protected Type2 _o2;
	
	public Pair (Type1 o1, Type2 o2) {
		_o1 = o1;
		_o2 = o2;
	}
	
	public Type1 getFirstObject() { return _o1; }
	public Type2 getSecondObject() { return _o2; }
	
	public void setFirstObject(Type1 o1) { _o1 = o1;}
	public void setSecondObject(Type2 o2) { _o2 = o2;}
	
	
	public static void main(String[] args) {
		Pair<String,Integer> pair = new Pair<String, Integer>("Ronnie",28);
		LinkedList<Pair<String,Integer>> list = new LinkedList<Pair<String,Integer>>();
		list.addLast( pair );
		Collections.sort(list,
				new Comparator<Pair<String,Integer>>(){
			@Override
			public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
				return o1.getSecondObject().compareTo( o2.getSecondObject() );
			}
			
		}
			
		);
		
	}

}
