package 서희;

import java.util.Scanner;

public class BOJ1107_리모컨 {
	
	static int endNum ;			// 이동하려고 하는 채널
	static int[] trouble ;		// 고장난 번호를 체크하기 위한 배열
	static int[] numArr ;		// 조합을 통해 만들어진 수
	static int answer ;			// 채널 N으로 이동하기 위해서 눌러야 하는 버튼 최소 수	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		endNum = sc.nextInt();		// 이동하려고 하는 채널
		trouble = new int [10] ;	// 고장난 번호 체크하기 위한 배열

		// 고장난 번호 입력
		int trobleNum = sc.nextInt();		// 고장난 버튼의 개수
		for(int i=0; i<trobleNum; i++) {
			int n = sc.nextInt();			// 고장난 번호
			trouble[n] = 1 ;
		}
		
		answer = Math.abs(endNum - 100) ;		// 시작위치에서부터 목표지점까지 +, - 버튼으로만 이동가능한 횟수가 초기값
		
		for(int len=1; len<=(endNum+"").length()+1; len++) {
			numArr = new int [len] ;	// num 초기화
			combi(0, len) ;				// 해당 len 크기의 숫자 조합
		}
		
		System.out.println(answer);
		
		sc.close();
		
	}

	// 누를 수 있는 번호 조합 만들기
	private static void combi(int sel, int len) {
		// 모든 경우가 다 선택되면 버튼을 누른 횟수 계산
		if(sel >= len) {
			mathClick(len) ;
			return ;
		}
		
		for(int i=0; i<10; i++) {
			// 고장난 번호는 pass
			if(trouble[i] == 1) continue ;
			
			// 해당 인덱스 번호를 선택한 경우
			numArr[sel] = i ;
			combi(sel+1, len) ;
		}
		
	}

	// 총 버튼을 누른 횟수 계산 (매개변수 = 숫자 번호를 누른 횟수)
	private static void mathClick(int clickNum) {
		// 배열을 숫자 문자열로 바꿔주기
		String num = "" ;
		for(int i=0; i<numArr.length; i++) {
			num += numArr[i]+"" ;
		}
		
		// endNum와 차이 구하기
		int gap = Math.abs(endNum - Integer.parseInt(num)) ;
		int totalClick = gap + clickNum ;
		answer = (totalClick < answer) ? totalClick : answer ;
	}

}
