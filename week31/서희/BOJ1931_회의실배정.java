package boj_1931_회의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Meeting implements Comparable<Meeting> {
		int start, end;
		
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			if(this.end == o.end) {
				return this.start - o.start;
			}
			
			return this.end - o.end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 회의 개수
		Meeting[] meetings = new Meeting[n];		// 예정된 회의 목록
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings[i] = new Meeting(start, end);
		}
		
		// 회의가 끝나는 시간을 기준으로 정렬
		Arrays.sort(meetings);
		
		int answer = 0;		// 최대 사용할 수 있는 회의의 개수
		int time = 0;		// 현재 시간 
		
		for(int i=0; i<n; i++) {
			if(time > meetings[i].start) continue;
			
			answer++;
			time = meetings[i].end;
		}
		
		System.out.println(answer);
	}
}
