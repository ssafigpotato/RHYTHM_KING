package boj_6236_용돈관리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());	// n일
		int m = Integer.parseInt(st.nextToken());	// m번
		
		int[] money = new int [n];
		int min = 0;
		int max = 0;
		
		for(int i=0; i<n; i++) {
			money[i] = Integer.parseInt(br.readLine());
			min = Math.max(min, money[i]);
			max += money[i];
		}
		
		while(min <= max) {
			int k = (min + max) / 2;	// min과 max의 중간값으로 인출금액(k) 후보 설정
			int sum = 0;
			int count = 1;
			
			for(int i=0; i<n; i++) {
				sum += money[i];
				
				// money[i]를 더했을 때 k를 초과한다면 돈을 다시 인출
				if(sum > k) {
					sum = money[i];
					count++;
				}
			}
			
			// m번보다 많이 인출해야 한다면 k가 적다는 것을 의미
			if(count > m) {
				min = k+1;
			} else {
				// m번 이하로 인출할 수 있다면 k가 크다는 것을 의미
				max = k-1;
			}
		}
		
		System.out.println(min);
	}
}
