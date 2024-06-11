// package 백준  17615 볼 모으기;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int ball = sc.nextInt(); // 모든 공의 개수
		sc.nextLine();
	
		char[] arr = new char[ball]; // 공의 배열
		
		int red = 0; //빨간 공
		int blue = 0; // 파란 공
		
		String line = sc.nextLine(); // 공 입력받기
		for(int i=0; i<ball; i++) {
			arr[i] = line.charAt(i);
			if(arr[i]=='R') red++; 
			else if(arr[i]=='B') blue++;
		}
		
		// 그럴듯하게 해보려다 실패
//		int moveRedLeft = 0;
//		int moveRedRight = 0;
//		int moveBlueLeft = 0;
//		int moveBlueRight = 0;
		
		// 여기부터 노가다
		int count=0;
		int ans = ball; // 공 개수만큼 초기화
		
		// R을 다 왼쪽으로
		for(int i =0; i<ball; i++) {
			// 우선, 왼쪽에서부터 연속되는 r의 개수를 세고(=count)
			if(arr[i] == 'R') 
				count++;
			// 연속성이 끊기는 순간 개수 세기(=반복문)를 중단
			else 
				break;	
		}
		//red-count: 왼쪽부터 연속된 R을 제외한 나머지 R의 수 = 최소 이동 횟수
		ans = Math.min(ans, red-count);
		
		// R을 다 오른쪽으로
		count =0; //count 또 쓸 거라 초기화
		for (int i = ball-1; i>=0; i--) {
			if(arr[i]=='R') {
				count++;
			}
			else
				break;
		}
		ans = Math.min(ans, red-count);
		
		
		// B를 다 왼쪽으로
		count =0;
		for(int i =0; i<ball; i++) {
			if(arr[i] == 'B') 
				count++;
			else 
				break;	
		}
		ans = Math.min(ans, blue-count);
		
		// B를 다 오른쪽으로
		count =0;
		for (int i = ball-1; i>=0; i--) {
			if(arr[i]=='B') {
				count++;
			}
			else
				break;
		}
		ans = Math.min(ans, blue-count);
		
		System.out.println(ans);
		
	}

}
