package week03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1389_케빈베이컨의6단계법칙2 {
	static int N; // 유저 수
	static int M; // 관계 수
	static int[][]map; // 인접행렬
	/**
	 * 플로이드 와샬 알고리즘
	 * 1. 의미: 모든 정점에서 다른 모든 정점으로의 최단 경로를 구하는 알고리즘
	 * (vs 다익스트라 알고리즘 : 하나의 정점에서 출발했을 때 다른 모든 정점으로의 최단경로 구하는 알고리즘)
	 * 2. 핵심 아이디어: 거쳐가는 정점을 기준으로 최단거리를 구하기
	 * 3. 점화식 : Ck(u, v) = Math.min( (Ck-1(u, k)+Ck-1(k, v)), Ck-1(u,v))
	 * 	  C(u,v)는 0번 정점부터 k번 정점까지만을 경유점으로 썼을 때 u에서 v까지 가는 최단 경로의 길이
	 * 	  여기서는 map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
	 * 4. 주의점: 플로이드 와샬 알고리즘은 시간 복잡도가 매우 높기 때문에  
	 * 	  효율적인 코드 작성이 필요할 때(코테)는 V의 크기가 크다(500 이상)면 가급적 피하는 것이 좋다. 
	 * 참고 사이트: https://loosie.tistory.com/146?category=972195
	 * */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N+1][N+1]; //1번부터 시작하니까
		
		// 1. 초기값 설정
		for(int i = 1; i <=N; i++) {
			for(int j = 1; j <=N; j++) {
				if(i == j) {
					map[i][j] = 0;
				}else {
//					map[i][j] = Integer.MAX_VALUE; // 이러면 쓰레기 값이 나온다.
//					Integer.MAX_VALUE는 int 변수에 저장할 수 있는 최대값을 나타내며, 2^31−1 
//					Integer.MAX_VALUE에 값을 더하면 정수 오버플로우가 발생하여 음수로 감.
					map[i][j] = 987654321;
				}
			}
		}// 초기값 설정

		
		// 2. 관계 입력받고 인접행렬 만들기
		for(int i = 0; i <M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			map[x][y] = map[y][x] = 1;
		} 
		
//		for(int i = 1; i <=N; i++) {
//			for(int j = 1; j <=N; j++) {
//				System.out.print(map[i][j]+" ");
//			}System.out.println();
//		}// 확인
//		System.out.println("============");
		// 3. 플로이드 와샬 알고리즘
		// k : 거쳐가는 노드(기준)
		for(int k = 1; k <=N; k++) {
			// i : 출발 노드
			for(int i = 1; i <=N; i++) {
				// j : 도착 노드 
				for(int j = 1; j<=N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
//		for(int i = 1; i <=N; i++) {
//			for(int j = 1; j <=N; j++) {
//				System.out.print(map[i][j]+" ");
//			}System.out.println();
//		} // 확인
		

		
		// 4. 최소 노드 찾기 
		int ans = Integer.MAX_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i = 1; i <=N; i++) {
			int sum = 0;
			for(int j = 1; j <= N; j++) {
				sum += map[i][j];
			}
//			System.out.println(i+"일 때 sum=케빈베이컨 수는: "+sum);
			if(min >sum ) { // 그 중 가장 작은 사람 찾기
				ans = i;
				min = sum;
			}
		}
		
		System.out.println(ans);
		
	
	}// main
}
