package boj_16987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] duration, weight;
	static int max = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 계란의 개수
		
		duration = new int[N];
		weight = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			duration[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
			
		}
		
		//(깨진 계란의 수, 현재 계란의 인덱스)
		backtracking(0,0);
		
		System.out.println(max);
		
	}

	
	
	private static void backtracking(int cnt, int idx) {
		
		// 끝까지 가면 리턴
		// N-1이 아니라 N인 이유는 끝까지 확인해야 하기 때문
		// 마지막 계란에서 -> idx+1 = N이 된 후 여기서 탈출
		if(idx == N) {
			max = Math.max(cnt, max); // 깨진 계란 수 업데이트
			return;
		}
		
		// 손에 든 계란이 이미 깨졌다면 그 다음 계란으로
		// 새로 깨는 계란은 없기 때문에 cnt는 그대로 넘겨준다
		if(duration[idx]<=0 ) {
			backtracking(cnt, idx+1);
			return;
		}
			
		
		// 현재 손에 든 계란으로 다른 계란을 칠 수 있는지 (기본은 X)
		boolean canBreak = false;
		
		// 아니면 하나 뒤로 가
		for(int i = 0; i<N; i++) {
			
			// 깨야 할 계란과 손에 든 계란이 다르고 && 내려칠 계란의 내구도가 0보다 크면
			if(i != idx && duration[i] >0) {
				
				// 조건 만족 -> 깰 수 있다(true)
				canBreak = true;
			
				// 충돌 시킨다
				eggConflict(idx, i);
				
				
				// 새로운 count를 도입한 것은
				// cnt를 계속 쓰면 깨진 계란이 중복으로 처리됨
				int count = cnt;
				if(duration[idx] <= 0)
					count++;
				
				if(duration[i] <= 0)
					count++;	
				
				// 깨진 계란의 수를 반영하여 (0~2개) 다음 계란충돌로 넘어간다.
				backtracking(count, idx+1);
				
				//다음 backtracking을 위해 초기화(다른 경우의 수에 영향 x)
				eggReset(idx, i);
			
			}
			
		}
		
		// 만약 현재 계란으로 다른 계란을 칠 수 없었다면 개수 세지 않고 넘어가
		if(!canBreak) {
			backtracking(cnt, idx+1);
		}
		
	
	}
	
	
	// 충돌 -> 내구도 감소
	public static void eggConflict(int hand, int floor) {
		duration[hand] -= weight[floor];
		duration[floor] -= weight[hand];
	}
	
	// 원상복구 -> 깎인 내구도를 복구
	public static void eggReset(int hand, int floor) {
		duration[hand] += weight[floor];
		duration[floor] += weight[hand];
		
	}
}
