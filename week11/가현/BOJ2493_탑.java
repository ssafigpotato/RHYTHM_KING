import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in); // Scanner 써서 Buffered로 바꾸니까 통과됨
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 탑의 수
		int[] tower = new int[N]; // 탑 높이 배열
		int[] receive = new int[N]; // (정답 출력을 위한) 신호 수신 탑 저장 배열
		
		// 탑 높이 입력받기
		String[] input = br.readLine().split(" ");
		for(int i =0; i<N; i++) {
			tower[i] = Integer.parseInt(input[i]);
		}
		
		// 스택 생성: 스택에 쌓는 것은 인덱스
		Stack<Integer> stack = new Stack<>(); 
		
		
		// 순서대로 순회
		for(int i=0; i<N; i++) {
			
			// 스택이 비어있지 않고 + 꺼내보니까 지금 탑보다 낮다면 => (peek 했던 탑은) 신호 수신을 못함
			while(!stack.isEmpty() && tower[stack.peek()] <= tower[i]) {
				// 스택에서 제거
				stack.pop();
			}
			
			// 스택이 비어있다면 => 수신할 수 있는 탑 없음
			if(stack.isEmpty())
				// 0 출력
				receive[i] = 0;
			// 수신할 탑 있다면 
			else
				// 인덱스에 +1 해준다
				receive[i] = stack.peek() +1;
			
			// (다음 탑이 신호를 보낼 수 있는지 확인하기 전에) 현재 탑의 높이를 스택에 추가
			stack.push(i);
			
		}
		
		// 정답 출력
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<N; i++) {
			sb.append(receive[i]).append(" ");
		}
		//trim()은 맨 마지막 공백 제거
		System.out.println(sb.toString().trim()); 
	
	}

}

/*
 *	과정:

	초기 상태:
	tower = [6, 9, 5, 7, 4]
	receive = [0, 0, 0, 0, 0]
	stack = []
	
	탑 1 (높이 6):
	i = 0
	스택이 비어 있으므로 receive[0] = 0
	stack.push(0) → stack = [0]
	
	탑 2 (높이 9):
	i = 1
	tower[1] (9)는 tower[stack.peek()] (6)보다 크므로 stack.pop() → stack = []
	스택이 비어 있으므로 receive[1] = 0
	stack.push(1) → stack = [1]
	
	탑 3 (높이 5):
	i = 2
	tower[2] (5)는 tower[stack.peek()] (9)보다 작으므로 receive[2] = stack.peek() + 1 → receive[2] = 1 + 1 = 2
	stack.push(2) → stack = [1, 2]
	
	탑 4 (높이 7):
	i = 3
	tower[3] (7)는 tower[stack.peek()] (5)보다 크므로 stack.pop() → stack = [1]
	tower[3] (7)는 tower[stack.peek()] (9)보다 작으므로 receive[3] = stack.peek() + 1 → receive[3] = 1 + 1 = 2
	stack.push(3) → stack = [1, 3]
	
	탑 5 (높이 4):
	i = 4
	tower[4] (4)는 tower[stack.peek()] (7)보다 작으므로 receive[4] = stack.peek() + 1 → receive[4] = 3 + 1 = 4
	stack.push(4) → stack = [1, 3, 4]
	최종적으로, receive 배열은 [0, 0, 2, 2, 4]가 됩니다. 이는 각 탑이 수신하는 신호의 출발 탑 인덱스를 나타내며, 인덱스는 1부터 시작합니다. 
 */
 

