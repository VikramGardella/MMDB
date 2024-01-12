import java.util.*;
public class CounterMap {
	private NumNode root;
	
	public CounterMap(){root = null;}
	public NumNode getRoot(){return root;}
	
	public void put(Comparable key){
		if(root==null)
			root= new NumNode(key);
		else{
			NumNode curr =root;
			NumNode prev = curr;
			while(curr!=null){
				prev=curr;
				if(curr.getKey().compareTo(key)==0){
					curr.increment();
					return;
				}
					
				if(curr.getKey().compareTo(key)>0)
					curr=curr.getLeft();
				else
					curr=curr.getRight();
		}
			if(key.compareTo(prev.getKey())<0)
				prev.setLeft(new NumNode(key));
			else
				prev.setRight(new NumNode(key));
		}
	}
	
	public NumNode recSearch(NumNode n, Comparable k){
		if(n.getKey().equals(k))
			return n;
		if(n==null)
			return null;
		
		if(k.compareTo(n.getKey())<0)
			return recSearch(n.getLeft(),k);
		else
			return recSearch(n.getRight(),k);
		
	}
	
	public Integer get(Comparable k){
		return recSearch(root,k).getCount();
	}
	
	//there appears to be a problem here		BRAHHHHHHHHH iz good now
	public ArrayList<Comparable> keyList(){
		ArrayList<Comparable> list = new ArrayList();
		keyHelper(root, list);
		return list;
	}
	public void keyHelper(NumNode r, ArrayList l){
		if(r!=null){
		l.add((String)r.getKey());
		if(r.getLeft()!=null)
			keyHelper(r.getLeft(),l);
		if(r.getRight()!=null)
			keyHelper(r.getRight(),l);
		}
	}
	public void inOrderPrint(NumNode r){
		if(r==null)
			return;
		
		inOrderPrint(r.getLeft());
		System.out.println(r.getKey()+": "+r.getCount());
		inOrderPrint(r.getRight());
		
		
	}
	
	
	//nuther wun hur
	public MyTreeMap inversion(){
		MyTreeMap mtm = new MyTreeMap();
		inversionRecursion( root, mtm);
		return mtm;
	}
	public void inversionRecursion( NumNode r, MyTreeMap m){
		if(r==null)
			return;
		inversionRecursion(r.getLeft(),m);
		m.put(r.getCount(), (String)r.getKey());
		inversionRecursion(r.getRight(),m);
	}
	
	public String toString(){
		return toStringHelp(root);
	}
	public String toStringHelp(NumNode r){
		if(r==null)
			return "";
		return toStringHelp(r.getLeft()) + " " +r+" "+toStringHelp(r.getRight());
	}
}
