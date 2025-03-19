package pac1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static int N;
	static String[] words;
	static List<Character> letters = new ArrayList<>();
	static boolean[] visited = new boolean[10];
	static int maxSum = 0;
	static Map<Character, Integer> letterToDigit = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		words = new String[N];
		
		for(int i=0; i<N; i++) {
			words[i] = sc.next();
			
			for(char c : words[i].toCharArray()) {
				if(!letters.contains(c)) {
					letters.add(c);
				}
			}
		}
		
		permutation(0, new int[letters.size()]);
		System.out.println(maxSum);
	}

	private static void permutation(int depth, int[] nums) {
		// TODO Auto-generated method stub
		if(depth == letters.size()) {
			for(int i =0; i<letters.size(); i++) {
				letterToDigit.put(letters.get(i), nums[i]);
				
			}
			
			int sum =0;
			for (String word : words) {
				int num =0;
				for(char c: word.toCharArray()) {
					num = num *10 + letterToDigit.get(c);
				}
				sum += num;
			}
			
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(!visited[i]) {
				visited[i] = true;
				nums[depth] = i;
				permutation(depth+1, nums);
				visited[i] = false;
			}
		}
	}
}