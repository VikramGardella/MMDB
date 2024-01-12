import java.util.*;
 
public class SetNode{
	private Comparable key;
	private Set<String> setty;
	private SetNode left, right;
	
	public SetNode(Comparable k){
		key = k;
		setty = new HashSet();
	}
	
	public SetNode(Comparable k, String setElement){
		super();
		key = k;
		setty = new HashSet();
		setty.add(setElement);		
	}
	
	public Set getSet(){return setty;}
	public Comparable getKey(){return key;}
	public void add(String s){
		setty.add(s);
		
	}
	
	public int hits(String s){
		int h=0;
		String k = (String)key;
		String[]kw = k.split(" ");
		String []sw = s.split(" ");
		for(int ke=0;ke<kw.length;ke++)
			for(int se=0;se<sw.length;se++)
				if(kw[ke].equals(sw[se]))
					h++;
		return h;
	}
	
	public SetNode getLeft(){return left;}
	public SetNode getRight(){return right;}
	
	
	public void setLeft(SetNode l){left = l;}
	public void setRight(SetNode r){right =r;}
	
	public String toString(){
		return key + " --> "+ setty;
	}
	
	
}
