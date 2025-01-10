package algo_0106;

import java.util.Scanner;

public class 부등호 {
	static int K;
	static String[]str;
	static int[]num = {0,1,2,3,4,5,6,7,8,9};
	static int[]out; // 출력값 저장배열
	static boolean[]visited;
	// int로 하면 numberFormat~~에러 뜸 
	static long max = 0;
	static long min = Long.MAX_VALUE; // <-- Integer.MAX_VALUE하면 테캐 몇개 틀림!! 
	// 중복 없이, 숫자 고르기 순서 중요!
	static void backtracking(int depth) {
		String ans = "";
		
		if(depth == K+1) {
			for(int o : out) {
				ans += o;
			}
			max = Math.max(max, Long.parseLong(ans));
			min = Math.min(min, Long.parseLong(ans));
			return;
		}
		
		for(int i = 0; i <10; i++) { // 숫자 0~9 중에서 고르기
			if(!visited[i]) { // 숫자가 사용된 적이 없고
				// str[depth-1]에 따라서 
				// '현재' depth에서 고르려는 숫자와 '이전' depth에서 선택해둔 숫자를 비교함
				// 이 비교가 유효하면 방문처리 하고 out에 집어넣고, 재귀~~~
				if(depth == 0 || isValid(str[depth-1], out[depth-1], i)) {
				// depth가 0일 때는 부등호 비교 안함.
				// 할 필요도 없을 뿐더러 depth-1에 접근할 수 없음
					
					visited[i] = true;
					out[depth] = num[i];
					backtracking(depth+1);
					visited[i] = false;
				}
			}
		}
	}
	static boolean isValid(String s, int prev, int curr) {
		if(s.equals("<")) {
			// 이전보다 현재가 크면 true, 아니면 false
			return prev < curr; 
		}else if(s.equals(">")){
			// 이전보다 현재가 작으면 true, 아니면 false
			return prev > curr;
		}
		return false; // 기본 반환값: false
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		visited = new boolean[10];
		str = new String[K]; // 부등호 저장 
		for(int i = 0; i <K; i++) {
			str[i] = sc.next();
		}
		out = new int[K+1]; // 값으로 나올수 잇는 숫자는 K+1개 뽑아야함
		backtracking(0);
		
		
//		System.out.println(max);
//		System.out.println(min);
		
		// max값 min값 출력 
		int max_len = Long.toString(max).length();
		String zero = "0";
		
		if(max_len < K+1) {
			System.out.println(zero.repeat(K+1-max_len)+max);
		}else if(max_len == K+1) {
			System.out.println(max);
		}
		
		int min_len = Long.toString(min).length();
		if(min_len < K+1) {
			System.out.println(zero.repeat(K+1-min_len)+min);
		}else if(min_len == K+1) {
			System.out.println(min);
		}
		
		
	}
}
