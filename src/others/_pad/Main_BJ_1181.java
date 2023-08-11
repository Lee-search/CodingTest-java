package tmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main_BJ_1181 {
	
	static int N;
	static ArrayList<String> s_list;
	static Set<String> s_set;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		s_list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) s_list.add(br.readLine());
		
		s_set = new HashSet<>(s_list);
		s_list = new ArrayList<>(s_set);

		Collections.sort(s_list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				if(o1.length() == o2.length()) return o1.compareTo(o2);
				else return o1.length() - o2.length();
			}
			
		});
		
		for(int i = 0; i < s_list.size(); i++) sb.append(s_list.get(i)).append("\n");
		System.out.println(sb);
	}
}
