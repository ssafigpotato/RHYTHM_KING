package boj_1717_집합의표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] unf;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());	// 0부터 n까지의 숫자가 존재
		int m = Integer.parseInt(st.nextToken());	// 입력으로 주어지는 연산의 개수
		
		// unf 배열 초기화
		unf = new int [n+1];
		for(int i=0; i<=n; i++) {
			unf[i] = i;
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int type = Integer.parseInt(st.nextToken());

			// 0이 입력되면 합집합 연산
			if(type == 0) {
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} else {
				// 1이 입력되면 두 원소가 같은 집합에 포함되어 있는지 확인
				System.out.println(check(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
		}
	}
	
	/**
	 * 합집합 연산 메소드
	 */
	public static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		
		if(fa != fb) {
			unf[fa] = fb;
		}
	}
	
	/**
	 * 숫자 n이 포함된 집합 번호를 리턴하는 메소드
	 */
	public static int find(int n) {
		if(n == unf[n]) {
			return n;
		}
		
		return unf[n] = find(unf[n]);
	}
	
	/**
	 * a와 b 원소가 같은 집합에 포함되어 있는지를 출력하는 메소드
	 * @return "YES" or "NO"
	 */
	public static String check(int a, int b) {
		if(find(a) == find(b)) {
			return "YES";
		}
		
		return "NO";
	}
}
