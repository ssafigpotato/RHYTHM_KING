package boj_2529_부등호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int k;
	static String[] arr;
	static boolean[] visited;
	static List<String> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());	// 부등호 문자의 개수
		arr = br.readLine().split(" ");			// 부등호 기호 순서
		
		visited = new boolean [10];		// 사용한 숫자 체크
		list = new ArrayList<>();		// 부등호 조건을 충족하는 문자열 저장
		
		dfs("", 0);
		
		System.out.println(list.get(list.size() - 1));	// 최댓값
		System.out.println(list.get(0));	// 최솟값
	}
	
	/**
	 * 부등호 조건을 충족하는 문자열 만들기
	 * @param num	현재까지 만들어진 숫자 (문자열)
	 * @param idx	판단해야 하는 인덱스 번호
	 */
	static void dfs(String num, int idx) {
		// 모든 숫자를 지정했으면 종료
		if(idx == k+1) {
			list.add(num);		// 리스트에 저장
			return;
		}
		
		for(int n=0; n<10; n++) {
			// 사용한 숫자인지 체크
			if(visited[n]) {
				continue;
			}
			
			if(idx == 0 || check(Character.getNumericValue(num.charAt(idx - 1)), n, arr[idx-1])) {
				visited[n] = true;
				dfs(num+n, idx+1);
				visited[n] = false;
			}
		}
	}
	
	static boolean check(int leftNum, int rightNum, String c) {
		// 부등호가 성립하지 않을 경우 false
		if(c.equals(">") && leftNum < rightNum) {
			return false;
		} else if (c.equals("<") && leftNum > rightNum) {
			return false;
		}
		
		return true;
	}
}
