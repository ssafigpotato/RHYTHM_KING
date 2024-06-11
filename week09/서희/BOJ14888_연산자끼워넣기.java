package 서희 ;

import java.util.Scanner;

public class BOJ14888_연산자끼워넣기 {
	
	static int n ;				// n의 값
	static int[] numArr ;		// N개로 이루어진 수열
	static int[] operatorNum ;	// 각 연산자 개수를 저장하는 배열
	static int[] operator ;		// 수식에 사용할 연산자 순서대로 저장

	static int max ;			// 식 결과의 최댓값
	static int min ;			// 식 결과의 최솟값
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		// 초기화
		n = sc.nextInt();
		numArr = new int [n] ;
		operatorNum = new int [4] ;
		operator = new int [n-1] ;
		max = Integer.MIN_VALUE ;
		min = Integer.MAX_VALUE ;
		
		// 수열 입력
		for(int i=0; i<n; i++) {
			numArr[i] = sc.nextInt();
		}
		
		// 연산자 개수 입력
		for(int i=0; i<4; i++) {
			operatorNum[i] = sc.nextInt();
		}
		
		// 연산자 순서 결정 후 식을 계산하며, 최댓값과 최솟값 갱신
		perm(0) ;
		
		// 출력
		System.out.println(max);
		System.out.println(min);
		
		sc.close();
		
	}

	/**
	 * 연산자 순서를 결정하는 메소드
	 */
	private static void perm(int idx) {
		// 모든 연산자 순서가 결정되면 식의 결과 계산
		if(idx >= n-1) {
			cal() ;
			return ;
		}
		
		for(int i=0; i<4; i++) {
			// 해당 연산자가 존재하지 않을 경우 pass 
			if(operatorNum[i] == 0) continue ;
			
			// 해당 연산자가 존재할 경우 선택
			operator[idx] = i+1 ;		// -> operator 배열에 아무값도 대입되지 않은 문제가 발생할 경우를 쉽게 파악하기 위해 초기값(0)이 포함되지 않도록 설정
			operatorNum[i]-- ;
			perm(idx+1) ;
			
			// 사용하지 않은 척 복구
			operatorNum[i]++ ;
		}
	}

	/**
	 * 결정된 연산자 순서에 따라 식의 결과 계산
	 */
	private static void cal() {
		int sum = numArr[0] ;
		
		for(int i=1; i<n; i++) {
			int operatorType = operator[i-1] ;		// 덧셈(1), 뺄셈(2), 곱셈(3), 나눗셈(4)
			int num = numArr[i] ;					// 계산할 숫자
			
			if(operatorType == 1) {
				sum += num ;
			} else if (operatorType == 2) {
				sum -= num ;
			} else if (operatorType == 3) {
				sum *= num ;
			} else if (operatorType == 4) {
				sum /= num ;
			} 
		}
		
		// 최댓값 또는 최솟값 갱신
		max = Math.max(sum, max) ;
		min = Math.min(sum, min) ;
	}

}
