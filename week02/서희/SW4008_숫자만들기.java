package 서희;

import java.io.File;
import java.util.Scanner;

public class SW4008_숫자만들기 {
	
	static int N ;					    // 숫자의 개수
	static int[] operatorNum ;	// 각 연산자의 개수
	static int[] numArr ;			  // 수식에 사용되는 숫자
	static int[] operator ;			// 연산자 저장
	
	static int max ;				// 최댓값
	static int min ;				// 최솟값
	
	public static void main(String[] args) throws Exception {
		
		// File file = new File("src/swea_4008_숫자만들기/input.txt") ;
		// Scanner sc = new Scanner(file) ;
		Scanner sc = new Scanner(System.in) ;
		
		int T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			N = sc.nextInt();	
			operatorNum = new int [4] ;
			numArr = new int [N] ;
			operator = new int [N-1] ;
			
			max = Integer.MIN_VALUE ;
			min = Integer.MAX_VALUE ;
			
			// 각 연산자의 개수 입력
			for(int i=0; i<4; i++) {
				operatorNum[i] = sc.nextInt();
			}
			
			// 수식에 사용되는 숫자 입력
			for(int i=0; i<N; i++) {
				numArr[i] = sc.nextInt();
			}
			
			// 연산자 조합
			combi(0, 0) ;
			
			// 최댓값과 최솟값 차이 계산
			int answer = max - min ;
			
			// 출력
			System.out.printf("#%d %d\n", test_case, answer);
			
		}
		
		sc.close() ;
		
	}


	/**
	 * 연산자 선택하기 위한 메소드
	 * @param idx
	 * @param selCnt
	 */
	private static void combi(int idx, int selCnt) {
		// 모든 위치에 연산자가 선택되었으면 값 계산
		if (selCnt == N-1) {
			calcu() ;
			return ;
		}
		
		// 연산자 선택
		for(int i=0; i<4; i++) {
			// 남은 연산자 수가 0이면 pass
			if(operatorNum[i] == 0) continue ;

			// 해당 인덱스의 연산자가 남아있으면 사용하고, 다음 자리 연산자 선택
			operator[selCnt] = i ;
			operatorNum[i]-- ;
			combi(idx+1, selCnt+1) ;
			
			// 사용하지 않았던 것처럼 다시 돌려놓기
			operatorNum[i]++ ;			
		}
	}


	/**
	 * 결정된 연산자에 따라 결과값을 계산하는 메소드
	 */
	private static void calcu() {
		int total = numArr[0] ;			// 초기값은 numArr의 첫 번째 요소 값
		
		// 저장된 연산자 배열의 크기만큼 반복문 돌면서 값 계산
		for(int i=0; i<N-1; i++) {
			int opIdx = operator[i] ;
			int num = numArr[i+1] ;
			
			if(opIdx == 0) {
				total += num ;
			} else if (opIdx == 1) {
				total -= num ;
			} else if (opIdx == 2) {
				total *= num ;
			} else if (opIdx == 3) {
				total /= num ;
			}
		}
		
		// 최댓값과 최솟값 갱신
		max = (total > max) ? total : max ;
		min = (total < min) ? total : min ;
	}
	
}
