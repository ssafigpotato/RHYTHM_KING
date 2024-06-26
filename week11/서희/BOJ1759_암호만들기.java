package 서희;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1759_암호만들기 {
	
	static int L ;					// 암호의 문자 길이
	static int C ;					// 암호로 사용했을 법한 문자의 개수
	static char[] inputChar ;		// 암호로 사용했을 법한 문자
	static char[] codeArr ;			// 가능성 있는 암호 저장
	
	static char[] vowel = {'a', 'e', 'i', 'o', 'u'} ;	// 모음
	
	static StringBuilder sb ;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		sb = new StringBuilder() ;
		
		L = sc.nextInt();
		C = sc.nextInt();		
		
		inputChar = new char [C] ;
		codeArr = new char [L] ;
		
		for(int i=0; i<C; i++) {
			inputChar[i] = sc.next().charAt(0) ;
		}
		
		// 오름차순으로 정렬
		Arrays.sort(inputChar);
		
		// 조합 진행
		combi(0, 0) ;
		
		// 출력
		System.out.println(sb);
		
		sc.close();
		
	}

	/**
	 * 암호 조합 만드는 메소드
	 * @param idx
	 * @param cnt
	 */
	private static void combi(int idx, int cnt) {
		// L개의 문자들로 조합이 만들어졌으면 조건에 충족하는지 확인
		if (cnt >= L) {
			check() ;
			return ;
		}
		
		// idx가 입력된 문자의 크기를 벗어나면 종료
		if (idx >= C) return ;
		
		// 해당 idx를 포함하는 경우
		codeArr[cnt] = inputChar[idx] ;
		combi(idx+1, cnt+1) ;

		// 해당 idx를 포함하지 않는 경우 
		combi(idx+1, cnt) ;
	}

	/**
	 * 만들어진 암호가 최소 한 개의 모음과 최소 두 개의 자음으로 구성되어 있는지 확인하는 메소드
	 */
	private static void check() {
		int vowelCnt = 0 ;			// 모음의 개수
		int consonantCnt = 0 ;		// 자음의 개수
		
		// 만들어진 암호에서 모음의 수 세기
		for(int i=0; i<L; i++) {
			for(int j=0; j<5; j++) {
				if (codeArr[i] == vowel[j]) {
					vowelCnt++ ;
				}
			}
		}
		
		// 자음의 수 계산
		consonantCnt = L - vowelCnt ;
		
		// 조건이 충족되었으면 문자열 출력
		if(vowelCnt >= 1 && consonantCnt >= 2) {
			for(int i=0; i<L; i++) {
				sb.append(codeArr[i]) ;
			}
			sb.append("\n") ;
		}
	}

}
