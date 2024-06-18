package 서희;

import java.util.Scanner;

public class BOJ20055_컨베이어벨트위의로봇 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();		// 컨베이어 벨트의 길이
		int k = sc.nextInt();		// 내구도가 0인 칸의 개수가 k개 이상이라면 종료
		
		int[] durability = new int [2*n];	// 내구도 배열
		for(int i=0; i<2*n; i++) {
			durability[i] = sc.nextInt();
		}
		
		int endPoint = n-1;		// 내리는 위치에 있는 인덱스 번호
		int zero = 0;			// 내구도가 0인 칸의 개수
		int step = 0;			// 현재 단계
		boolean[] robot = new boolean [2*n];	// 해당 인덱스에 로봇이 있는지 저장하는 배열
		
		while(true) {
			// 0. 단계 +1
			step++;
			
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
			// -> 내리는 위치에 있는 인덱스 번호를 수정하면서 회전을 구현
			endPoint = (endPoint == 0) ? 2*n-1 : endPoint-1 ;
			
			// 회전하여 내리는 위치에 도달했을 경우 즉시 내림
			if(robot[endPoint]) {
				robot[endPoint] = false ;
			}
			
            
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동
			int curIdx = endPoint ;		// 현재 위치의 인덱스 번호
			for(int i=1; i<n; i++) {
				int nextIdx = (curIdx+1) % (2*n);	// 이동하는 경우 이동하는 칸의 인덱스 번호
				
				// 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1이상 남아있으면 로봇 이동 가능
				if(robot[curIdx] && !robot[nextIdx] && durability[nextIdx] >= 1) {
					// 로봇이동
					robot[curIdx] = false ;
					robot[nextIdx] = true ;
					
					// 이동한 칸의 내구도 1감소
					durability[nextIdx]-- ;
					if(durability[nextIdx] == 0) zero++ ;
				}
				
				// 이동 후 내리는 위치에 있는 로봇을 즉시 내림
				if(robot[endPoint]) {
					robot[endPoint] = false ;
				}
					
				// 다음 인덱스 번호 계산
				curIdx = (curIdx == 0) ? 2*n-1 : curIdx-1 ;
			}
			
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if(durability[curIdx] != 0) {
				robot[curIdx] = true ;
				durability[curIdx]-- ;
				if(durability[curIdx] == 0) zero++ ;
			}
			
			
			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다.
			if(zero >= k) break;
		}
		
		System.out.println(step);
		
		sc.close();
		
	}

}
