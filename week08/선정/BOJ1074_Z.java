package algo_0605;

import java.util.Scanner;

public class BOJ1074_Z {
	static int N;
	static int r; // 행
	static int c; // 열
	static int cnt; // r,c 위치
	// 전체를 사분면으로 나누고, 입력받은 r,c가 몇번째 사분면에 위치하는지 찾는 메소드
	static void recursion(int size, int r, int c) {
		// 재귀를 반복하다가 size가 1이 되면 return 
		if(size == 1) return;
		
		/**
		 *  1. r,c가 1사분면에 있을 경우
		 *  해당 사분면 안에서 위치를 찾기 위해 한 변의 길이를 size/2로 바꿔주고 다시 재귀 돌리기
		 * */
		if(r < size/2 && c < size/2) {
			recursion(size/2, r, c);
		}
		/**
		 * 2. r,c가 2사분면에 있을 경우
		 * 1사분면은 모두 방문했으므로 cnt에 하나의 사분면 크기인 (size*size)/4를 더해준다.
		 * 해당 사분면 안에서 위치를 찾기 위해 
		 * 한 변의 길이를 size/2로 바꿔주고, 
		 * r,c의 상대위치(한 변의 길이가 size/2인 사분면이라고 가정했을 때의 위치)로 바꿔준 다음 다시 재귀 돌리기
		 * */
		else if ( r < size/2 && c >= size/2) {
			cnt += size*size/4;
			recursion(size/2, r, c-size/2);
		}
		/***
		 * 3. r,c가 3사분면에 있을 경우
		 * 1,2 사분면 모두 방문했으므로 cnt += (size*size/4)*2
		 * size/2, r,c의 상대위치로 재귀 돌리기
		 */
		else if (r >= size/2 && c < size/2) {
			cnt += (size*size/4)*2;
			recursion(size/2, r-size/2, c);
		}
		/***
		 * 4. r,c,가 4사분면에 있을 경우
		 * 1,2,3사분면 모두 방문했으므로 cnt += (size*size/4)*3
		 * size/2, r,c,의 상대위치로 재귀 돌리기
		 */
		else {
			cnt += (size*size/4)*3;
			recursion(size/2, r-size/2, c-size/2);
		}
	}//recursion
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		int size = (int) Math.pow(2, N); // 한 변의 길이

		
		recursion(size,r,c);
		System.out.println(cnt);
		
		
		
	}// main
}
