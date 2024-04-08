package boj_13335;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();   //트럭 수
		int W = sc.nextInt();   //다리 길이
		int L = sc.nextInt();   //최대 하중
		int time = 0;           //경과 시간
		int currWeight =0;      //현재 무게
		
		Queue<Integer> truck = new LinkedList<>();   //트럭의 배열
		Queue<Integer> bridge = new LinkedList<>();  //다리의 배열(1칸=트럭1대)
		
		// 트럭 무게를 입력받는다
		for(int i=0; i<N; i++) {
			truck.offer(sc.nextInt());
		}
		
		
		// 시작에는 0으로 weight의 큐를 채워준다.
		// 0(의 의미) = 빈자리
		for(int i =0; i<W; i++) {
			bridge.add(0);
		} 
		
		// 큐가 꽉 찬 경우 - 데이터 추가 시
		// add는 예외 발생, offer는 false를 리턴
		
		// 다리가 텅 빌때까지
		while(!bridge.isEmpty()) {
			
			time++; // 1턴 당 시간 1초 증가
			
			//현재 무게는 bridge 큐의 맨 앞을 뺀 값
			//(방금 빠져나간 트럭의 무게를 뺸다)
			currWeight -= bridge.poll();  
			
			// 트럭 배열이 다 출발할때까지
			if(!truck.isEmpty()) {
				//이번에 빠져나가는 트럭의 무게 + 현재 다리 위에 있는 트럭들의 무게 <= 최대하중
				if(truck.peek() + currWeight <= L) {
					// 다른 트럭을 출발시킨다
					int truck2 = truck.poll();
					// 다리에 트럭을 추가
					bridge.offer(truck2);
					// 현재 무게 업데이트
					currWeight += truck2;
				}
				// 트럭이 출발하지 못한다면 다음 칸에 0을 추가시켜준다(빈 자리)
				else
					bridge.offer(0);
			}
			
		}
	
	
	
		System.out.println(time);
	
	}
	
	
}
