package daily_0705;

import java.util.Scanner;

public class BOJ1992_쿼드트리 {
	static int N;
	static int [][]map;
	static StringBuilder sb;
	static void recursion(int size, int r, int c) {
		// 재귀를 돌리다가 한변의 길이가 1이되면 return
		if(size == 1) {
			// size가 1일 때 map[r][c]를 안 넣어줄 경우
			// ((110())(0010)1(0001)) 이렇게 나옴 
			sb.append(map[r][c]);
			return; 
		}
		
		int sum = 0;
		// i = 0; i <size 이렇게 설정하면 1만 나옴
		// 왼쪽 위(1사분면)인 경우뿐만 아니라 2,3,4사분면인 경우도 검사해야하니까
		// i = r, i <r+size 이런식으로 설정 
		for(int i = r; i < r+size; i++) {
			for(int j = c; j < c+size; j++) {
				sum += map[i][j];
			}
		}
		
		// 구역의 합이 0이면 0으로 압축(다 0이라는 뜻이므로)
		if(sum == 0) {
			sb.append(0);
		// 구역의 합이 size*size면 1로 압축(다 1이라는 뜻이므로)
		}else if(sum == size*size) {
			sb.append(1);
		}else {
			// 0, 1이 섞였을 경우 
			// 1. 앞 뒤에 (, )를 붙이고
			// 2. 네 등분으로 나누고 재귀
			sb.append("(");
			int newSize = size/2;
			recursion(newSize, r, c); // 왼쪽 위
			recursion(newSize, r, c+newSize); // 오른쪽 위
			recursion(newSize, r+newSize, c); // 왼쪽 아래
			recursion(newSize, r+newSize, c+newSize); // 오른쪽 아래
			sb.append(")");
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		map = new int[N][N];
		// 공백 없이 주어지므로 줄로 받기
		for(int i = 0; i <N; i++) {
			String str = sc.next();
			for(int j = 0; j <N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}// 입력 끝

		
		recursion(N,0,0);
		
		System.out.println(sb.toString());
		
		sc.close();
		
		
		
	}//main
}
