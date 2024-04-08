package 서희;

import java.util.Scanner;

public class BOJ13335_트럭 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		int n = sc.nextInt();		// 다리를 건너는 트럭의 수
		int w = sc.nextInt();		// 다리의 길이
		int L = sc.nextInt();		// 다리의 최대하중

		int[] truck = new int [n] ;			// 트럭의 무게를 저장한 배열
		int[] location = new int [n] ;		// 트럭의 현재 위치
		boolean[] done = new boolean [n] ;	// 트럭의 도착 여부

		// 트럭 무게 입력
		for(int i=0; i<n; i++) {
			truck[i] = sc.nextInt();
		}
		
		int curTruckNum = 0 ;	// 현재 다리를 건너는 트럭의 수
		int curKg = 0 ;			// 현재 다리 위의 트럭의 무게
		int time = 0 ;			// 다리를 건너는데 걸린 시간
		
		// 마지막 트럭의 다리를 건널 때까지 반복문 진행
		while(true) {
			time++ ;
			
			// 기존에 다리 위에 있던 트럭 한 칸씩 이동
			for(int i=0; i<n; i++) {
				if(location[i] > 0 && location[i] <= w) {
					location[i]++ ;
				}
			}
			
			// 이번에 도착한 트럭이 있는지 체크
			for(int i=0; i<n; i++) {
				if(!done[i] && location[i] > w) {
					curKg -= truck[i] ;
					curTruckNum-- ;
					done[i] = true ;
				}
			}
			
			// 마지막 트럭이 도착했으면 반복문종료
			if(done[n-1]) break ;
			
			// 다리의 단위 길이가 남고 무게가 가능하다면 새로운 트럭 1개 출발
			for(int i=0; i<n; i++) {
				if(location[i] == 0) {
					int tmpTruckNum = curTruckNum + 1 ;
					int tmpKg = curKg + truck[i] ;
					
					// 트럭의 순서대로 출발해야 하므로
					// 다리의 단위 길이가 부족하거나 무게가 초과된다면 바로 종료
					if(tmpTruckNum > w || tmpKg > L) break ;
					
					location[i]++ ;
					curTruckNum++ ;
					curKg = tmpKg ;
					
					break ;
				}
			}
			
		}
		
		System.out.println(time);
		
		sc.close();
		
	}

}
