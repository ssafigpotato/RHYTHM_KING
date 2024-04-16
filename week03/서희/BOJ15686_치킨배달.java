package 서희;

import java.util.Scanner;

public class BOJ15686_치킨배달 {
	
	static int n ;
	static int m ;
	static int[][] city ;
	static int[][] chicken ;
	static boolean[] select ;
	static int chickenIdx ;
	static int answer ; 
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		n = sc.nextInt();				// n * n 크기의 도시
		m = sc.nextInt();				// 폐업시키지 않을 치킨집의 수
		city = new int [n+1][n+1] ;		// 도시 정보 (0: 빈칸, 1: 집, 2: 치킨집)
		chicken = new int[13][13] ;		// 현재 치킨집이 있는 위치 정보
		chickenIdx = 0	;				// 치킨집의 위치 정보를 저장하기 위한 idx 변수
		answer = Integer.MAX_VALUE ;	// 도시의 치킨 거리 최솟값
		
		// 도시 정보 입력
		for(int r=1; r<=n; r++) {
			for(int c=1; c<=n; c++) {
				int n = sc.nextInt();
				city[r][c] = n;
				
				// 치킨집 위치가 입력되면 chicken 배열에 저장
				if(n == 2) {
					chicken[chickenIdx][0] = r ;
					chicken[chickenIdx][1] = c ;
					chickenIdx++ ;
				}
			}
		}
		
		select = new boolean[13] ;	// 해당 idx의 치킨집을 선택할지 정보
		
		// m개의 치킨집 선택
		combi(0, 0) ;
		
		System.out.println(answer);
		
		sc.close();
		
	}

	/**
	 * m개의 치킨집을 선택
	 * @param idx
	 * @param selNum
	 */
	private static void combi(int idx, int selNum) {
		// m개의 치킨집을 선택했으면 치킨 거리 계산
		if(selNum == m) {
			chickenStree() ;
			return ;
		}
		
		// idx가 배열의 크기를 벗어났으면 종료
		if(idx >= chickenIdx) return ;
		
		// 해당 idx를 선택했을 경우
		select[idx] = true ;
		combi(idx+1, selNum+1) ;
		
		// 해당 idx를 선택하지 않은 경우
		select[idx] = false ;
		combi(idx+1, selNum) ;
	}

	/**
	 * 도시의 치킨 거리 계산해서 최솟값 갱신
	 */
	private static void chickenStree() {
		// 해당 조합에서의 치킨 거리의 합
		int minChickenSum = 0 ;
		
		for(int r=1; r<=n; r++) {
			for(int c=1; c<=n; c++) {
				// 지도의 모든 요소를 순회하다가 집이 있다면
				if(city[r][c] == 1) {
					// 선택된 치킨집에 대해서 치킨거리 최솟값을 구하기
					int minChicken = Integer.MAX_VALUE;
					
					for(int i=0; i<chickenIdx; i++) {
						// 선택되지 않은 치킨집이면 pass
						if(!select[i]) continue ;	
						
						// 해당 집과 모든 치킨집과의 거리를 계산해서 가장 작은 치킨 거리를 계산
						int v = Math.abs(r-chicken[i][0]) + Math.abs(c-chicken[i][1]) ;
						minChicken = Math.min(minChicken, v) ;
					}
					
					minChickenSum += minChicken ;
				}
			}
		}
		
		// 해당 조합에서 answer보다 치킨 거리의 합이 작으면 갱신
		answer = Math.min(answer, minChickenSum) ;
	}

}
