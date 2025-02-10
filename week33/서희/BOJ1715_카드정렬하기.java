package boj_1715_카드정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 숫자 카드 묶음 수
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		for(int i=0; i<n; i++) {
			pq.offer(Long.parseLong(br.readLine()));
		}
		
		long answer = 0;
		
		while(pq.size() > 1) {
			long num = pq.poll() + pq.poll();
			answer += num;
			pq.add(num);
		}
		
		System.out.println(answer);
	}
}
