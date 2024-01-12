import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.*;

public class CrossReference extends JFrame implements ActionListener{
	
	private JPanel nrth;
	private JTextField srchby, result;
	private JButton start, thisone;
	//private JTable d;
	MyTreeMap name250 = new MyTreeMap();
	MyTreeMap tagmap = new MyTreeMap();
	private int row = 0;
	private JComboBox scroll;
	
	
	
	
	public CrossReference(){
		//just change to MyTreeMap and wallah
		
		JScrollPane scrolly = new JScrollPane();
		scroll= new JComboBox();
		nrth = new JPanel(new GridLayout(4,1));
		srchby = new JTextField("Search");
		start = new JButton("Go");
		start.addActionListener(this);
		thisone = new JButton("Find similar titles");
		thisone.addActionListener(this);
		nrth.add(srchby);
		nrth.add(start);
		nrth.add(scroll);
		nrth.add(thisone);
		//d=lists;
		//scrolly.add(d);
		
		
		
		this.add(nrth,BorderLayout.NORTH);
		//this.add(scroll,BorderLayout.CENTER);
		
		
		
		try{
			FileReader r250 = new FileReader(new File("top250.txt"));
			BufferedReader b250 = new BufferedReader(r250);
			String line = b250.readLine();
			int start = 0,end=0;
			while(line!=null){
				
				for(int x =0;x<line.length();x++){
					if(line.charAt(x)==('.')){
						start = x+4;
						break;
					}
					}
				for(int x =0;x<line.length();x++){
					if(line.charAt(x)==(')'))
						end=x-6;
					}
				
				String f = line.substring(start,end);
				name250.put(f, null);
		
				line = b250.readLine();	
			}
			
		}
		catch(Exception ex){
			//System.out.println(ex.getStackTrace());
		}
		//System.out.println(name250);
		
		/********************************************************/
		//peruse tags and section off the tags for each movie before adding them to arraylists in name250
		int count=0;
		try{
			FileReader rtags = new FileReader(new File("tags.txt"));
			BufferedReader btags = new BufferedReader(rtags);
			String line = btags.readLine();
			int z=0;
			int a=0, b=0;
			
			while(line!=null){
				z=line.indexOf('(')-1;
				String nm =line.substring(0,z);
				//make substring of actual tag here
				a=line.indexOf(')')+1;
				//System.out.println("~"+nm+"~");
				String tg = line.substring(a);
				tg=tg.trim();
				//System.out.println("~"+tg+"~");
				if(name250.containsKey(nm)==true){
					name250.get(nm).add(tg);
					tagmap.put(tg, nm);
					count++;
				}
				line = btags.readLine();
		}
			}
		
		catch(Exception ex){
			//System.out.println(ex.getStackTrace());
		}
		//System.out.println("Total tags belonging to the top 250: "+count);
		//System.out.println(name250);
		try{
				FileWriter file = new FileWriter("refd250.txt");
				PrintWriter out = new PrintWriter(file);
				
				for(Comparable a: name250.keyList()){
					//out.println(a+":");
					for(Object b: name250.get(a).getSet()){
						out.println(a+":"+b);
					}
					//out.println("*");
				}
				out.close();
				}
			catch(Exception ex){
				System.out.println(ex.getStackTrace());
			}
			//name250.justPrint();
			
		/*	String search = "Vendetta";
			
			for(Object o:name250.hitList(search))
				System.out.println(o);
			*/
			this.setSize(500,500);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
			
			
	}
	
	public static void main(String[] args) {
		
		new CrossReference();
		
	
			}



	@Override
	public void actionPerformed(ActionEvent arg0) {

		Object pressed = arg0.getSource();
		
		if(pressed==start){
			String search = srchby.getText();
			CounterMap hs = name250.hitList(search);
			MyTreeMap m = hs.inversion();
			ArrayList hitcounts = new ArrayList();
			for(Comparable c: m.keyList())
				hitcounts.add(c);
	
			/*************
			 * replace for with for-each and go through top counts in hitted first
			 */
			
			for(Object o:hitcounts){
				
				for(Object c: m.get((Comparable) o).getSet()){
					String ans = c+": "+o;
					scroll.addItem(ans);
					
				}
				
			}
			/*for(int x=0;x<8;x++){
				String to = (String) hitted.first();
				to+=": "+hs.get(to);
				hitted.remove(hitted.first());
				
				lists.add(new JTextField(to));
				//d.add((Component)to);
			}*/
			this.paintComponents(this.getGraphics());
			
		}
	
	
	if(pressed==thisone){
		//System.out.println("selected brah");
		String name = (String)scroll.getSelectedItem();
		if(name.length()>=3)
			name=name.substring(0,name.length()-3);
			else name=null;
		//System.out.println(name);
		//System.out.println(tagmap);
		CounterMap common = this.recommend(name);
		System.out.println(common);
		MyTreeMap invrtd = common.inversion();
		System.out.println(invrtd);
		
	}
}
	
	public CounterMap recommend(String title){
		CounterMap narm = new CounterMap();
		//Add 
		//o= a tag in selected movie
		//c= a tag/key in tagmap
		//get movies in set with that key(c)
		for(Object o: name250.get(title).getSet())
			for(Comparable c: tagmap.keyList())
				if((((String)c).toLowerCase()).contains(((String) o).toLowerCase()))
					for(Object a:tagmap.get(c).getSet())
						narm.put((Comparable)a);
						
				
			
			
		

		
		return narm;
	}
	
}