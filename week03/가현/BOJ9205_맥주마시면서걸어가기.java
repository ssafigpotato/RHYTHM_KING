package beer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t =1; t<=T; t++) {
			int N = sc.nextInt();
			
			/* 
			 * 모든 좌표를 하나의 배열에 입력받는다
			 * 각 좌표가 다른 좌표와 1000(20개의 맥주로 갈 수 있는 거리) 이내인지 확인해야 하기 때문이다
			 * 행: 편의점(N개) + 도착지+ 출발지 = N+2개, 열: x좌표, y좌표
			 */
			int[][] loc = new int[N+2][2]; 
			
			// 좌표들을 입력받는다
			for(int i=0; i<N+2; i++) {
				loc[i][0] = sc.nextInt();
				loc[i][1] = sc.nextInt();
			}
			
			// 좌표들을 모두 beerWalking 메서드로 넘겨줘 가능 여부를 확인
			// boolean 타입으로 반환받는다
			if(beerWalking(loc)) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
			
			
		}
		
	}

	private static boolean beerWalking(int[][] loc) {
		
		int L = loc.length; // 모든 좌표의 개수
		boolean[] visited = new boolean[L]; //편의점 방문여부를 저장
		
		Queue<Integer> q = new LinkedList<>();  // 큐 생성
		q.offer(0); // 시작 지점을 큐에 추가 / 숫자 n은 n번째 입력받은 좌표
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			/*
			 * 거리 계산
			 * 송도는 직사각형 모양으로 생긴 도시이고, 
			 * 두 좌표 사이의 거리는 x 좌표의 차이와 y 좌표의 차이의 합인 맨해튼 거리
			 */
			
			// 현재 위치에서 페스티벌까지 거리가 1000m 이하라면 성공 -> true 반환
			if(Math.abs(loc[curr][0] - loc[L-1][0]) + 
					Math.abs(loc[curr][1] - loc[L-1][1]) <= 1000)
				return true;
			
			// 현재 위치에서 갈 수 있는 모든 편의점을 큐에 추가
			for(int i =1; i<L; i++) {
				// 방문하지 않았고 && 맨해튼 거리가 1000이하라면
				if(!visited[i] && Math.abs(loc[curr][0] - loc[i][0]) 
						+ Math.abs(loc[curr][1] - loc[i][1]) <= 1000) {
					// 큐에 i번째 좌표를 넣고, 방문 처리를 해준다
					q.offer(i);
					visited[i] = true;
				}	
			}	
		}	
		
		// 기본값은 false를 반환, 조건에 맞는 경우만 true를 반환
		return false;
	}

}
