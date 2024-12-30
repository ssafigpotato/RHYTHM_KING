

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {
	static class Status implements Comparable<Status>{
		int id; // 사람 id
		int mileage; // 마일리지 
		
		Status(int id, int mileage){
			this.id = id; 
			this.mileage = mileage;
		}
		
		@Override
		public int compareTo(Status o) {
			// 마일리지 내림차순 정렬 
			return Integer.compare(o.mileage, this.mileage);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 주어진 과목 수
		int M = sc.nextInt(); // 마일리지
		
		// 각 과목별 최소로 필요한 마일리지 저장 
		List<Integer> list = new ArrayList<>();
		// 1. 각 과목별로 PQ를 선언
		for(int i = 1; i <=N; i++) { // 각 과목별
			PriorityQueue<Status> pq = new PriorityQueue<>();
			
			int P = sc.nextInt(); // 신청한 사람 수
			int L = sc.nextInt(); // 수강인원
			// 1-1. 기존에 신청한 사람들의 마일리지를 PQ에 넣어 내림차순 정렬
			for(int j = 1; j <= P; j++) { // 사람별 마일리지
				int m = sc.nextInt();
				pq.add(new Status(j, m));
			}
			
			// 1-2. 필요한 최소 마일리지 연산 
			// 신청한 사람 수가 수강인원보다 많으면 
			if(P >=L) {
				for(int l = 0; l <L-1; l++) {
					pq.poll();
				}
				// 성준이는 
				// 수강인원에 안에 드는 사람 중 가장 작은 마일리지 가진 사람만큼
				// 마일리지 제출해야함 
				int mileage = pq.poll().mileage; 
				list.add(mileage);
			}else { // 신청한 사람 수가 수강인원보다 적으면 그냥 1만 내면 됨  
				list.add(1);
			}
			
		}// for 과목
		
		// 2. 과목별 필요 마일리지를 오름차순으로 정렬
		Collections.sort(list);
//		System.out.println(list);
		
		// 3. 최대로 들을 수 있는 과목 개수 세기 
		int sum = 0; // 마일리지 합 
		int cnt = 0; // 과목 개수 
		
		// 최대로 들을 수 있는 과목 개수 세기 
		for(int i=0; i <list.size(); i++) {
			sum += list.get(i);
//			System.out.println(i+" "+sum+" "+cnt);
			if(sum > M) {
				break;
			}
			cnt++;
		}
		
		// 4. cnt 출력 
		System.out.println(cnt);
		sc.close();
		
	}//main
}
