package algo_0610;

import java.util.Arrays;
import java.util.Scanner;
//예시)
// P1 = IOI			I:2, O:1
// P2 = IOIOI		I:3, O:2
// P3 = IOIOIOI		I:4, O:3...
public class BOJ5525_IOIOI {
	static int N;  // N+1게의 I와 N개의 O로 이루어진 문자열 Pn
	static int M; // S의 길이
	static String S; // I와 O로 이루어진 문자열
	static int P; // 패턴의 길이
	static int cnt = 0; // 패턴의 개수

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		S = sc.next(); // 입력 끝
		P = 2*N+1;
		
		// IOI 패턴 찾기
        int i = 0;
        while (i <= M - P) {
            if (S.charAt(i) == 'I') {
            	// IOI의 개수 변수
                int count = 0;
                while (i + 1 < M && S.charAt(i + 1) == 'O' && i + 2 < M && S.charAt(i + 2) == 'I') {
                    count++;
                    // IOI의 개수가 N가 같아지면 cnt++,
                    // 겹치는 부분을 고려해서 count-- (예)N=2이고, S가 IOIOIOI인 경우 생각
                    if (count == N) {
                        cnt++;
                        count--;  // 겹치는 패턴을 고려하여 count 줄임
                    }
                    i += 2;
                }
            }
            i++;
        }
		System.out.println(cnt);
		
	}// main
}
