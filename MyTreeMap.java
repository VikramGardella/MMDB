import java.util.*;

public class MyTreeMap{
	private SetNode root;
	private String tooo="";
	
	public MyTreeMap(){
		root = null;
	}
	
	public MyTreeMap(SetNode r){
		root = r;
	}
	
	
	public SetNode find(Comparable key){
		if(this.isEmpty())
			return null;
		SetNode curr = root;
		while(curr!=null){
			if(curr.getKey().equals(key))
				return curr;
			if(key.compareTo(curr.getKey())>0)
				curr=curr.getRight();
			else if(key.compareTo(curr.getKey())<0)
				curr=curr.getLeft();
		}
		
		return null;
	}
	
	public void put(Comparable key, String value) {
			SetNode at= this.find(key);
			
			//System.out.println( this.find(key)+": "+this.containsKey(key));
			SetNode curr=root;
			
			if(curr==null){
				root = new SetNode(key,value);
				return;
			}
			
			if(this.containsKey(key)==true&&at!=null){
				//System.out.println("already had key :"+key);
				at.add(value);
				//System.out.println(at);
			}
			
			else{//start else
				while(curr!=null){//start while
					if(key.compareTo(curr.getKey())<0){
						if(curr.getLeft()!=null)
							curr=curr.getLeft();
						else{
							if(value!=null)
								curr.setLeft(new SetNode(key,value));
							else
								curr.setLeft(new SetNode(key));
							return;
						}
					}
					else if(key.compareTo(curr.getKey())>=0){
						if(curr.getRight()!=null)
							curr=curr.getRight();
						else{
							if(value!=null)
							curr.setRight(new SetNode(key,value));
							else
								curr.setRight(new SetNode(key));
							return;
						}
					}
				}//end while
			}//end else
			
	}
	
	public SetNode get(Comparable key) {
		SetNode container = this.find(key);
		return container;
	}
	
	public boolean containsKey(Comparable key) {
		return (find(key)!=null);		
	}
	public boolean isEmpty() {
		return root == null;
	}
	
	//problem brah
	/******************************/
	public CounterMap hitList(String s){
		CounterMap counted = new CounterMap();
		String[] st = s.split(" ");
		for(int x=0;x<st.length;x++)
			for(Comparable c: this.keyList()){
				//System.out.println("CHECKING: "+c+" FOR "+st[x]);
				//if(this.get(c).hits(st[x])>0){
				if((((String)c).toLowerCase()).contains(st[x].toLowerCase()))
					counted.put(c);
					//counted.put(this.get(c).getKey());
					
			}
	
		
		return counted;
		/*
		ArrayList<Comparable> all = this.keyList();
		SortedMap<Integer,String> map = new TreeMap();
		Queue ret = new LinkedList();
		for(Comparable k:all)
			if(!map.containsValue(k)){
				int hts = this.find(k).hits(s);
				map.put(hts,(String)k);
			}
		for(int i:map.keySet())
			ret.add("'"+map.get(i)+"' with " + i + " hits");
				return ret;
		*/
	}
	public ArrayList<Comparable> keyList() {
		//Set<Comparable> setty = new
		ArrayList<Comparable> list = new ArrayList();
		keyListHelper(root, list);
		return list;
	}
	
	//preconditions: r is the root of a substree
	//postconditions: all of the keys of the subtree rooted at r are now in l
	public void keyListHelper(SetNode r, ArrayList l){
		if(r==null)
			return;
		keyListHelper(r.getRight(),l);
		l.add(r.getKey());
		keyListHelper(r.getLeft(),l);
		
		
	}
	public int size() {
		return this.keyList().size();
	}
	public SetNode getRoot(){return root;}
	
	public String toString(){
		return toStringHelp(root);
	}
	public String toStringHelp(SetNode r){
		if(r==null)
			return "";
		return toStringHelp(r.getLeft()) + " " +r+" "+toStringHelp(r.getRight());
		/*
		if(r.getLeft()!=null){
			tooo+=toStringHelp(r.getLeft());
		tooo+=r.getKey();}
		else{
		tooo+=r.getKey();
		tooo+=toStringHelp(r.getRight());
		}
		return tooo;
		*/
	}
	
	public void inOrderPrint(SetNode r){
		if(r==null)
			return;
		
		inOrderPrint(r.getLeft());
		System.out.println(r.getKey()+": "+r.getSet());
		inOrderPrint(r.getRight());
		
		
	}
	public void justPrint(){
		for(Comparable c:this.keyList()){
			System.out.print(c+"-> ");
			System.out.println(this.get(c));
		}
		
		
	}
	
	


}
