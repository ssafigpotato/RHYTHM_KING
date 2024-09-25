package boj_2467_용액;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int [n];
		
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int leftIdx = 0;
		int rightIdx = n-1;
		
		int answerNum1 = -1;
		int answerNum2 = -1;
		int answerGap = Integer.MAX_VALUE;
		
		while(leftIdx < rightIdx) {
			int num1 = arr[leftIdx];
			int num2 = arr[rightIdx];
			int gap = num1 + num2;
			
			if(Math.abs(gap) < answerGap) {
				answerNum1 = num1;
				answerNum2 = num2;
				answerGap = Math.abs(gap);
			}
			
			if(answerGap == 0) break;
			
			if(gap < 0) {
				leftIdx++;
			} else {
				rightIdx--;
			}
		}
		
		System.out.println(answerNum1 + " " + answerNum2);
		
		sc.close();
		
	}

}
