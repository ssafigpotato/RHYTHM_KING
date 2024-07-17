package 서희;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1374_강의실 {
	
	static class Lecture implements Comparable<Lecture>{
		int num, start, end;	// 강의실 번호, 강의 시작시간, 강의 종료시간
		
		Lecture(int num, int start, int end) {
			this.num = num;
			this.start = start;
			this.end = end;
		}

		// 강의 시작시간을 기준으로 정렬, 만약 시작시간이 같다면 종료시간을 기준으로 정렬
		@Override
		public int compareTo(Lecture o) {
			if(this.start == o.start) {
				return this.end - o.end;
			}
			
			return this.start - o.start;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();				// 강의개수
		Lecture[] list = new Lecture[n];	// 강의실 리스트
		
		for(int i=0; i<n; i++) {
			int num = sc.nextInt();
			int start = sc.nextInt();
			int end = sc.nextInt();
			
			list[i] = new Lecture(num, start, end);
		}
		
		Arrays.sort(list);		// 강의 시작시간을 기준으로 정렬, 만약 시작시간이 같다면 종료시간을 기준으로 정렬
		
		// 우선순위 큐 -> 종료시간이 빠른 강의를 우선적으로 처리
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for(int i=0; i<n; i++) {
			// 큐가 비어있으면 현재 강의의 종료 시간을 큐에 추가
			if(pq.isEmpty()) {
				pq.add(list[i].end);
			} else {
				int endTime = pq.peek();		// 큐에 저장되어 있는 가장 빠른 종료시간
				
				// 다음 강의가 시작되는 시간보다 이전 강의가 종료되는 시간이 더 빠르거나 같다면 큐에서 제거
				if(list[i].start >= endTime) {
					pq.poll();
				}
				
				// 현재 강의의 종료시간을 큐에 추가
				pq.add(list[i].end);
			}
		}
		
		System.out.println(pq.size());
		
		sc.close();
		
	}

}
