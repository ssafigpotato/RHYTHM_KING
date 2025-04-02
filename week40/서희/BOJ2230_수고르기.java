package boj_2230_수고르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());	// 수열의 크기
		int m = Integer.parseInt(st.nextToken());	// 두 수의 차이가 m 이상
		
		int[] arr = new int [n];	// 수열		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 오름차순 정렬
		Arrays.sort(arr);
		
		System.out.println(minDiff(arr, m));
	}
	
	/**
	 * 두 수의 차이가 min 이상이면서 가장 작은 차이 계산
	 */
	private static int minDiff(int[] arr, int min) {
		int minDiff = Integer.MAX_VALUE;
		int left = 0;
		int right = 1;
		
		while(left <= right) {
			if(left >= arr.length || right >= arr.length) {
				break;
			}
			
			int diff = arr[right] - arr[left];
			
			if(diff < min) {
				right++;
			} else {
				minDiff = Math.min(minDiff, diff);
				left++;	
			}
		}
		
		return minDiff;
	}
}