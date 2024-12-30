package boj_19598_최소회의실개수;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	/**
	 * 미팅 시작시간과 종료시간을 관리하기 위한 클래스
	 */
	static class Time {
		int start, end;
		
		Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	/**
	 * 정렬조건
	 * 	1. 시작시간이 빠른 순서대로 정렬
	 * 	2. 시작시간이 같다면 종료시간이 빠른 순서대로 정렬
	 */
	static class TimeCompartor implements Comparator<Time> {
		@Override
		public int compare(Time o1, Time o2) {
			if (o1.start == o2.start) {
				return o1.end - o2.end;
			} else {
				return o1.start - o2.start;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();	// 회의 개수
		int[] meetingRoom = new int [n];	// 각 회의실별 끝나는 시간 (회의실 개수는 최대 N개)
		int roomNum = 0;		// 회의실 개수
		
		PriorityQueue<Time> pq = new PriorityQueue<>(1, new TimeCompartor());
		
		for(int i=0; i<n; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			
			pq.add(new Time(start, end));
		}
		
		while(!pq.isEmpty()) {
			Time time = pq.poll();
			
			// 기존에 있는 회의실에 배정할 수 있는지 확인
			boolean isChange = false;
			for(int i=0; i<roomNum; i++) {
				if(time.start >= meetingRoom[i]) {
					meetingRoom[i] = time.end;
					isChange = true;
					break;
				}
			}
			
			// 기존 회의실에 배정할 수 없다면 회의실 개수 + 1
			if(!isChange) {
				meetingRoom[roomNum++] = time.end;
			}
		}
		
		System.out.println(roomNum);
		
		sc.close();
	}
}
