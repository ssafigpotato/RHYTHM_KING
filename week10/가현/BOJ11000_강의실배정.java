package classroom;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); //수업의 개수
		
		int[][] classes = new int[N][2]; //class는 예약어라 안됨
		
		for(int t=0; t<N; t++) {
			classes[t][0] = sc.nextInt();
			classes[t][1] = sc.nextInt();
		}
		
		// 강의 시작 시간을 기준으로 오름차순 정렬
		Arrays.sort(classes, (a, b) -> a[0] - b[0]);
		/*
		 * (a, b) -> a[0] - b[0] 는 람다 표현식 (Comparator 인터페이스 구현을 단순화)
		 * 양수 반환(a[0] > b[0]) : a가 b보다 더 크다 : a와 b의 위치 변경
		 * 음수 변환(a[0] < b[0]) : b가 a보다 더 크다 : a와 b의 위치 그대로
		 * 0 반환: 그대로 유지
		 * 
		 * a[0], b[0] 는 모두 시작 시간
		 * 각 비교 시, classes의 2가지 수업을 비교
		 */
		
		
		
		// 우선순위 큐 생성: 종료 시간을 관리하기 위함
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		// 오름차순 정렬이 완료된 후, 첫 강의(의 종료시간)를 큐에 추가
		q.add(classes[0][1]);
		
		
		
		
		// 그 다음 강의부터 순회하며, 강의실 배정
		// 오름차순 정렬을 해놓았기 때문에 아래의 코드 사용 가능
		/*
		 * 강의 추가하기: 각 강의의 종료 시간을 우선순위 큐에 추가함
		 * 강의실 재사용: 현재 강의의 시작 시간이 큐의 최소 원소(종료 시간)보다 크거나 같으면
		 *  -> 강의실을 재사용할 수 있으므로, 큐에서 기존 종료시간을 제거한다.
		 *  + 예시) 첫 번째 강의(1-3시) 입력 -> 큐에 3 추가
		 *         정렬 후 2번째 강의인 (2,5) 는 2<3이므로 큐에 5 추가
		 *         정렬 후 3번쨰 강의인 (3,6) 는 3=3이므로 큐에서 3을 제거하고 5를 추가
		 */
		for(int i =1; i<N; i++) {
			
			// 현재 강의의 시작 시간이 (큐에 저장된) 가장 빠른 종료 시간보다 크거나 같으면 
			if(classes[i][0] >= q.peek()) {
				q.poll(); // 기존 강의실 재사용 -> 해당 강의실에 저장되어 있던 종료 시각 삭제
			}
			
			// 그렇지 않다면 (새로운 강의실을 사용해야 한다면)
			q.add(classes[i][1]);
		}
		
		
		
		// 현재 우선순위 큐에 저장되어 있는 수 = 각 강의실의 수업이 끝나는 시간
		// 따라서, 큐의 사이즈 == 강의실의 개수
		System.out.println(q.size()); //size 뒤 () 유의
	
		
	}
}
