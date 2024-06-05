package algo_0605;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;


public class BOJ5430_AC {
	static int T; // TC 수
	static String p; // 명령 함수
	static int n; // 배열 크기
	static String str; // 입력되는 배열(문자열 형태)
	static Deque<String> deque;
	static String []arr;
	/**
	 * 주의할점
	 * 1. str.charAt을 쓰면 42의 경우 4,2로 각각 들어가버리는 문제 발생!
	 * 2. deque.size로 for문 돌리면, deque.poll에 따라 사이즈가 변하므로 while사용하기!
	 * */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int t = 1; t<=T; t++) {
			// 1. 입력받기
			p = sc.next();
			n = sc.nextInt();
			str = sc.next();
			// 입력 끝
			
			// 2. 입력받은 str을 deque에 삽입하기
			arr = new String[n];
			// str에서 [와 ]을 제거하고, ","을 기준으로 split해서 arr에 삽입
			arr = str.substring(1, str.length()-1).split(",");

			deque = new LinkedList<String>();
			for(int i = 0; i<arr.length; i++) {
				if(!arr[i].equals("")) { // str이 []으로 입력되면 안됨 
					deque.add(arr[i]);
				}
			}
			
			// 3. 명령 함수 수행하기
			boolean flag = false; // 뒤집기flag
			boolean isError = false; // 에러flag
			for(int i =0; i < p.length(); i++) {
				if(p.charAt(i)== 'R') { 
					flag = !flag;
				}else {
					// deque가 비었으면 error처리
					if(deque.isEmpty()) {
						isError = true;
						break;
					}
					
					if(flag) { // true이면 뒤집었으니 마지막 수를 제거 
						deque.removeLast();
					}else { // false면 첫번째 수를 제거
						deque.removeFirst();
					}
				}
			}

			// 4. 출력하기
			StringBuilder sb = new StringBuilder();
			if(isError == true) {
				sb.append("error");
			}else {
				sb.append("[");
				if(flag) {
					while (!deque.isEmpty()) {
						sb.append(deque.pollLast());
                        if (!deque.isEmpty()) {
                            sb.append(",");
                        }
                    }
				}else {
					while (!deque.isEmpty()) {
						sb.append(deque.pollFirst());
                        if (!deque.isEmpty()) {
                            sb.append(",");
                        }
                    }
				}
				sb.append("]");
			}
			System.out.println(sb.toString());
		}//tc
		
		
	}// main
}
