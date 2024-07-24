package 서희;

import java.util.Scanner;

public class BOJ17179_케이크자르기 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();		// 자르는 횟수가 담긴 목록의 길이
		int m = sc.nextInt();		// 자를 수 있는 지점의 개수
		int l = sc.nextInt();		// 롤 케이크의 길이
		int[] mArr = new int [m+1];	// 자를 수 있는 지점을 담은 배열
		
		for(int i=0; i<m; i++) {
			mArr[i] = sc.nextInt();
		}
		mArr[m] = l;				// 마지막 지점도 배열에 넣기
		
		
		// 각 목록에 있는 횟수대로 롤 케이크를 잘랐을 때 가장 작은 조각의 길이의 최댓값
		for(int i=0; i<n; i++) {
			int cut = sc.nextInt();		// 자르는 횟수
			int answer = 0;				// 가장 작은 조각의 길이의 최댓값
			
			int left = 0;
			int right = mArr[m-1];
			
			while(left <= right) {
				// 가장 작은 조각 길이
				int mid = (left + right) / 2;
				
				// 자른 횟수 계산
				int cnt = 0;
				int prev = 0;
				for(int idx=0; idx<=m; idx++) {
					if(mArr[idx]-prev >= mid) {
						cnt++;
						prev = mArr[idx];
					}
				}
				
				// 해당 길이의 최솟값이 되도록 케이크를 잘랐을 때 num 횟수를 넘어간다면 mid 값을 크게 만들어 자르는 횟수를 줄인다.
				if(cnt > cut) {
					left = mid + 1;
					answer = Math.max(answer, mid);
				} else {
					// num 횟수를 넘어가지 못하면 mid 값이 크다는 뜻이므로 작게 만들어주어 자르는 횟수를 늘린다.
					right = mid - 1;
				}
			}
			
			System.out.println(right);
			
		}
		
		sc.close();
		
	}

}
