package boj_12101_123더하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int k;
	static int num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());	// n을 1,2,3의 합으로 표현
		k = Integer.parseInt(st.nextToken());	// 위의 방식으로 표현했을 때 k번째로 오는 식
		
		num = 0;	// 몇 번째 식인지 번호
		perm(new int[n], 0, 0);
		
		// k번째 오는 식이 없는 경우 -1 출력
		if(num < k) {
			System.out.println(-1);
		}
	}
	
	/**
	 * 순열 메서드
	 * @param arr	현재까지 만들어진 식
	 * @param idx	새로운 숫자가 들어갈 위치
	 * @param sum	현재까지 만들어진 식의 합
	 */
	private static void perm(int[] arr, int idx, int sum) {
		if(idx >= n || sum >= n) {
			if(sum == n) {
				check(arr);
			}
			
			return;
		}
		
		for(int i=1; i<=3; i++) {
			arr[idx] = i;
			perm(arr, idx+1, sum+i);
			arr[idx] = 0;
		}
	}
	
	/**
	 * 현재 만들어진 식이 k번째 식인지 확인
	 * @param arr
	 */
	private static void check(int[] arr) {
		num += 1;
		
		if(num == k) {
			print(arr);
		}
	}
	
	/**
	 * 매개변수로 입력된 arr 배열을 'arr[0]+arr[1]+arr[2]'과 같은 형식으로 출력
	 * @param arr
	 */
	private static void print(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == 0) {
				break;
			}
			
			if(i != 0) {
				System.out.print("+");
			}
			
			System.out.print(arr[i]);
		}
	}
}