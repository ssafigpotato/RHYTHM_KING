package boj_15661_링크와스타트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] score;
	
	static int[] team1;
	static int[] team2;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());	// 총 인원수
		score = new int [n+1][n+1];		// 능력치 점수 배열
		
		for(int r=1; r<=n; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int c=1; c<=n; c++) {
				score[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		team1 = new int [n];
		team2 = new int [n];
		answer = Integer.MAX_VALUE;
		
		team1[0] = 1;	// 1번 사람은 첫 번째 팀으로 고정
		combi(2, 1, 0);
		
		System.out.println(answer);
		
	}
	
	/**
	 * 
	 * 팀 배정을 위한 메소드
	 * @param idx 팀을 배정해야 하는 사람의 번호
	 * @param team1Num	첫번째 팀의 인원수
	 * @param team2Num  두번째 팀의 인원수
	 */
	public static void combi(int idx, int team1Num, int team2Num) {
		if(idx > n) {
			// 두 팀 모두 한 명 이상의 팀원이 있을 경우 능력치 계산
			if(team1Num != 0 && team2Num != 0) {
				sum();
			}
			
			return;
		}
		
		// 첫번째 팀으로 배정
		team1[team1Num] = idx;
		combi(idx+1, team1Num+1, team2Num);
		team1[team1Num] = 0;
		
		// 두번째 팀으로 배정
		team2[team2Num] = idx;
		combi(idx+1, team1Num, team2Num+1);
		team2[team2Num] = 0;
	}
	
	/**
	 * 각 팀의 능력치를 계산하기 위한 메소드
	 */
	public static void sum() {
		int team1Sum = 0;		// 첫번째 팀의 능력치 총합
		int team2Sum = 0;		// 두번째 팀의 능력치 총합
		
		// 첫번째 팀 능력치 계산
		for(int p1 : team1) {
			if(p1 == 0) break;
			for(int p2 : team1) {
				if(p2 == 0) break;
				team1Sum += score[p1][p2];
			}
		}
		
		// 두번째 팀 능력치 계산
		for(int p1 : team2) {
			if(p1 == 0) break;
			for(int p2 : team2) {
				if(p2 == 0) break;
				team2Sum += score[p1][p2];
			}
		}
		
		int gap = Math.abs(team1Sum - team2Sum);
		answer = (gap < answer) ? gap : answer;
	}
}
