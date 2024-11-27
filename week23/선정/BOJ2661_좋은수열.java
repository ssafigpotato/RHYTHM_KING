package algo_1127;

import java.util.Scanner; 

public class BOJ2661_좋은수열 {
	// 1 ~ 3 중 가장 작은 수부터 뽑고,
	// 뽑은 후 수열이 좋은 수열일 때에만 탐색을 계속 진행
	static int N; // 좋은 수열의 길이를 저장할 변수
	static int start = 1; 
	static int end = 3;
	
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in); 
	    N = sc.nextInt(); 
	    backtracking(""); // 초기 수열은 빈 문자열로 시작
	}
	
	public static void backtracking(String str) {
	    // 현재 수열의 길이가 N에 도달하면 정답으로 출력 후 종료
	    if (str.length() == N) {
	        System.out.println(str); // 좋은 수열 출력
	        System.exit(0); // 프로그램 종료
	    }
	
	    // 1부터 3까지의 숫자를 차례로 추가해 새로운 수열 생성
	    for (int i = start; i <= end; i++) {
	        if (check(str + i)) {
	            backtracking(str + i);
	        }
	    }
	}
	
	public static boolean check(String str) {
	    // 수열의 길이를 기준으로 반복문 실행
	    // i는 비교할 부분 수열의 길이
	    for (int i = 1; i <= str.length() / 2; i++) {
	        // 앞쪽 부분 수열 추출
	        String front = str.substring(str.length() - i * 2, str.length() - i);
	        // 뒤쪽 부분 수열 추출
	        String back = str.substring(str.length() - i, str.length());
	        // 두 부분 수열이 동일하면 나쁜 수열로 간주
	        if (front.equals(back)) {
	            return false;
	        }
	    }
	    return true; 
	}
}