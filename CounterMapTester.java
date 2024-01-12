
public class CounterMapTester {


	public static void main(String[] args) {
		String[] words = {"here","are","some","random","words","i","hope","your","code","works","words","are","fun","when","your","code","works","i","hope","you","don't","fail","i","am","tired","of","making","up","random","words"};
		CounterMap mappy = new CounterMap();
		for(String w: words)
			mappy.put(w);
		
		for(Comparable key: mappy.keyList())
			System.out.println(key+"-->"+mappy.get(key));
		
		System.out.println("INVERSION");
		MyTreeMap m =mappy.inversion();
		m.inOrderPrint(m.getRoot());
		

	}

}
