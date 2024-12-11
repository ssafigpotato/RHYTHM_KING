package boj_1253_좋다;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int [n];
		
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);	// 오름차순 정렬
		
		int answer = 0;

		// arr[i]가 다른 수 두 개의 합으로 나타낼 수 있는지 확인
		for(int i=0; i<n; i++) {
			int left = 0;
			int right = n-1;

			while(left < right) {
				// 자기 자신이 포함되는 경우 제외
				if (i == left) {
					left++;
					continue;
				} else if (i == right) {
					right--;
					continue;
				}
				
				int sum = arr[left] + arr[right];
				
				if (sum == arr[i]) {
					answer++;
					break;
				} else if (sum < arr[i]) {
					left++;
				} else {
					right--;
				}

			}
		}
		
		System.out.println(answer);

		sc.close();
		
	}

}
