package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1697_숨바꼭질 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		int N = sc.nextInt();		// 수빈이 시작 위치
		int K = sc.nextInt();		// 동생 위치
		
		int[] arr = new int [100001] ;		// 수빈이와 동생이 있는 라인
		
		Queue<Integer> queue = new LinkedList<Integer>() ;

		arr[N] = 1 ;		// 수빈이 시작 위치 표시
		queue.add(N) ;		// 수빈이 시작 위치 큐에 넣기
		
		int time = 0 ;
		
		while(!queue.isEmpty()) {
			int idx = queue.poll() ;
			if(idx == K) {
				time = arr[idx]-1 ;		// 시작이 1초로 시작했기 때문에 -1 해줘야 한다.
				break ;
			}
			
			// 수빈이가 -1로 이동하는 경우
			if((idx-1) >= 0 && arr[idx-1] == 0) {
				queue.add(idx-1) ;
				arr[idx-1] = arr[idx] + 1;
			}
			
			// 수빈이가 +1로 이동하는 경우
			if((idx+1) < arr.length && arr[idx+1] == 0) {
				queue.add(idx+1) ;
				arr[idx+1] = arr[idx] + 1;
			}
			
			// 수빈이가 순간이동(2*X)하는 경우
			if((2*idx) < arr.length && arr[2*idx] == 0) {
				queue.add(2*idx) ;
				arr[2*idx] = arr[idx] +1 ;
			}
		}
		
		System.out.println(time);
		
		sc.close();
		
	}

}
