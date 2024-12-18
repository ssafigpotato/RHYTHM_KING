package boj_2866_문자열잘라내기;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int R = sc.nextInt();
		int C = sc.nextInt();
		String[][] table = new String [R][C];
		
		for(int r=0; r<R; r++) {
			String[] str = sc.next().split("");
			
			for(int c=0; c<C; c++) {
				table[r][c] = str[c];
			}
		}
		
		int count = 0;
		int idx = R-1;
		
		while(true) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			boolean isEnd = true;
			
			for(int c=0; c<C; c++) {
				// 문자열 만들기
				String str = "";
				
				for(int r=idx; r<R; r++) {
					str += table[r][c];
				}
				
				// map에 저장하면서 중복확인
				map.put(str, map.getOrDefault(str, 0)+1);
				
				if(map.get(str) > 1) {
					isEnd = false;
					break;
				}
			}
			
			// 중복이 없으면 종료
			if(isEnd) {
				count = idx;
				break;
			}
			
			// 가장 처음에 주어지는 테이블에는 열을 읽어서 문자열을 만들 때, 동일한 문자열이 존재하지 않는 입력만 주어진다
			if(idx <= 1) {
				break;
			}
			
			// 중복이 있으면 한 칸 위로 올라가서 계속 탐색
			idx--;
		}
		
		System.out.println(count);
		
		sc.close();
		
	}

}

