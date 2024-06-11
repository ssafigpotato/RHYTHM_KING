package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14891_톱니바퀴 {
	
	static class RotationInfo {
		int num, direction ;
		
		public RotationInfo(int num, int direction) {
			this.num = num ;
			this.direction = direction ;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		int[][] wheel = new int [4][8] ;	// 각 톱니바퀴 상태를 저장하는 배열
		int[] topIdx = new int [4] ;		// 각 톱니바퀴의 12시 방향의 인덱스를 저장해두는 배열
		int[] changeTopIdx = new int [4] ;	// 회전시켜서 12시 방향의 인덱스가 바뀌었을 때 저장하는 배열
		
		// 톱니바퀴 별 상태 입력 (N극(0), S극(1))
		for(int r=0; r<4; r++) {
			String[] s = sc.next().split(""); 
			for(int c=0; c<8; c++) {
				wheel[r][c] = Integer.parseInt(s[c]) ;
			}
		}
		
		Queue<RotationInfo> queue = new LinkedList<RotationInfo>() ;	// 회전하는 톱니바퀴는 큐에 넣기
		
		// 입력받은 회전 횟수만큼 회전 진행
		int time = sc.nextInt();
		for(int t=0; t<time; t++) {
			
			boolean[] visited = new boolean [4] ;		// 방문배열
			
			// 회전시킬 톱니바퀴의 번호와 방향을 입력받아서 큐에 대입
			queue.add(new RotationInfo(sc.nextInt()-1, sc.nextInt())) ;
			
			// 더 이상 회전시킬 톱니바퀴가 없을 때까지 반복
			while(!queue.isEmpty()) {
				RotationInfo rotationInfo = queue.poll() ;
				int num = rotationInfo.num ;				// 회전시킬 톱니바퀴의 번호
				int direction = rotationInfo.direction ;	// 회전시킬 방향
				visited[num] = true ;						// 방문체크
				
				// 회전
				if(direction == 1) {
					changeTopIdx[num] = ((changeTopIdx[num] + 8) - 1) % 8 ;
				} else if (direction == -1) {
					changeTopIdx[num] = (changeTopIdx[num] + 1) % 8 ;
				} 
				
				// num와 맞닿아 있는 왼쪽 톱니바퀴가 회전되는지 판별
				int left = num - 1 ;
				if(left >= 0 && !visited[left]) {						// num의 왼쪽 톱니바퀴의 번호
					int leftTouchNum = (topIdx[left] + 2) % 8 ;			// num 톱니바퀴와 맞닿아 있는 톱니 번호
					int leftType = wheel[left][leftTouchNum] ;			// 상태 (N극(0), S극(1))
					
					int numTouchNum = ((topIdx[num] + 8) - 2) % 8 ; 	// 왼쪽과 닿는 num의 번호
					int numType = wheel[num][numTouchNum] ;				// 상태 (N극(0), S극(1))
					
					// num의 톱니바퀴와 맞닿은 톱니의 극이 다르다면 direction가 반대방향으로 회전
					if(leftType != numType) {
						int changeDirection = (direction == 1) ? -1 : 1 ; 
						queue.add(new RotationInfo(left, changeDirection)) ;
					}
				}
				
				// num와 맞닿아 있는 오른쪽 톱니바퀴가 회전되는지 판별
				int right = num + 1 ;
				if(right <= 3 && !visited[right]) {
					int rightTouchNum = ((topIdx[right] + 8) - 2) % 8 ;
					int rightType = wheel[right][rightTouchNum] ;
					
					int numTouchNum = (topIdx[num] + 2) % 8 ;
					int numType = wheel[num][numTouchNum] ;
					
					if(rightType != numType) {
						int changeDirection = (direction == 1) ? -1 : 1 ; 
						queue.add(new RotationInfo(right, changeDirection)) ;
					}
				}
			}
			
			// 모든 회전이 끝나면 원본에 반영
			for(int i=0; i<4; i++) {
				topIdx[i] = changeTopIdx[i] ;
			}
			
		}
		
		// 입력받은 모든 회전이 종료된 후 네 톱니바퀴의 점수의 합을 계산
		int sum = 0 ;
		for(int i=0; i<4; i++) {
			// 12시 방향이 N극이면 0점
			if(wheel[i][topIdx[i]] == 0) continue ;
			
			// S극이면 1번은 +1, 2번은 +2, 3번은 +4, 4번은 +8
			if(i == 0) {
				sum += 1 ;
			} else if (i == 1) {
				sum += 2 ;
			} else if (i == 2) {
				sum += 4 ;
			} else if (i == 3) {
				sum += 8 ;
			}
		}
		
		System.out.println(sum);
		
		sc.close();
		
	}

}
