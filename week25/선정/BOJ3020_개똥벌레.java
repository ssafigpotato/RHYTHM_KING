package algo_1210;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ3020_개똥벌레 {
	// 첫 번째 장애물은 항상 석순
	// 그 다음에는 종유석과 석순이 번갈아가면서 등장
	static int N; // 동굴의 길이. 짝수
	static int H; // 동굴의 높이 
	static int []top; // 종유석
	static int []bottom; // 석순
	
	// 이분탐색 
	// 배열에서 높이가 height 이상인 장애물의 개수를 반환
	public static int binarySearch(int left, int right, int height, int[]arr) {
		while(left < right) {
			int mid = (left + right)/2;
			
			if(arr[mid] >= height) {
				right = mid;
			}else {
				left = mid + 1;
			}
		}
		// 처음으로 높이가 height 이상인 장애물의 배열상 위치: right
		// 따라서 arr.length - right = 높이가 height 이상인 장애물 개수
		return arr.length - right;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		H = sc.nextInt();
		
		top = new int[N/2];
		bottom = new int[N/2];
		
		// 석순부터 길이 차례대로 입력
		for(int i = 0; i <N/2; i++) {
			bottom[i] = sc.nextInt();
			top[i] = sc.nextInt();
		}
		
		// 정렬하기 
		Arrays.sort(top);
		Arrays.sort(bottom);
		
		int min = N; // 최소 파괴수 
		int cnt = 0; // 해당 구간 개수 
		
		// 높이 1부터 높이 H까지 돌면서 
		// 파괴해야하는 장애물 최솟값과 해당 구간 수 구하기 
		for(int i = 1; i < H+1; i++) {
			// wallCnt: i 높이에서 파괴해야 하는 장애물의 총 개수
			// binarySearch(0,N/2,i,bottom):높이 i에서 석순과 충돌 개수
			// binarySearch(0,N/2,H−i+1,top): 높이 i에서 종유석과 충돌 개수
			int wallCnt = binarySearch(0, N/2, i, bottom) + binarySearch(0, N/2, H-i+1, top);
			
			// 1) 최소 파괴 개수를 가진 높이가 추가로 발견되었을 때
			if(min == wallCnt) { // 현재 파괴해야 하는 장애물 개수가 지금까지의 최소 파괴 개수와 같다면
				cnt++;
				continue;
			}
			// 2) 더 적은 파괴 개수를 가지는 높이가 발견되었을 때
			if(min > wallCnt) { // 현재 파괴해야 하는 장애물 개수가 지금까지의 최소 파괴 개수보다 작다면
				min = wallCnt;
				cnt = 1; // 새로운 최소 파괴 개수를 가지는 높이는 처음이므로 개수를 1로 초기화
			}
		}
		System.out.println(min + " " + cnt);
		
	}// main
}
