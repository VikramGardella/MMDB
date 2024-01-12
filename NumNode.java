
public class NumNode{
	private Comparable key;
	private Integer count;
	private NumNode left, right;
	
	public NumNode(Comparable k){
		key = k;
		count = 1;
	}
	
	public Integer getCount(){return count;}
	public Comparable getKey(){return key;}
	public void increment(){count++;}
	
	public NumNode getLeft(){return left;}
	public NumNode getRight(){return right;}
	
	public void setLeft(NumNode l){left = l;}
	public void setRight(NumNode r){right =r;}
	
	public String toString(){
		return key + " --> "+ count;
	}
}
