package boj_1484_다이어트;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int g = sc.nextInt();
		
		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		int left = 1;
		int right = 2;
		
		while(left < right) {
			int gap = (right * right) - (left * left);
			
			if(gap == g) {
				answer.add(right);
			}
			
			if(gap <= g) {
				right++;
			} else {
				left++;
			}
		}
		
		if(answer.isEmpty()) {
			System.out.println("-1");
		} else {
			for(int i=0; i<answer.size(); i++) {
				System.out.println(answer.get(i));
			}
		}
		
		sc.close();
		
	}

}
