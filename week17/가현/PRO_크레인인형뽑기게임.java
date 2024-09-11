import java.util.Stack; 
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;  // 터트려져 사라진 인형 개수
        Stack<Integer> basket = new Stack<>();  // 바구니 역할을 하는 스택
        
        // moves 배열을 순회하면서 인형을 집어올림
        for (int move : moves) {
            int column = move - 1;  // moves 배열 값은 1-based index이므로 0-based로 변환
            
            // 해당 열에서 가장 위에 있는 인형을 찾아서 집어올림
            for (int i = 0; i < board.length; i++) {
                if (board[i][column] != 0) {
                    int doll = board[i][column];  // 집어올린 인형
                    board[i][column] = 0;  // 인형을 집었으니 해당 칸을 0으로 만듦
                    
                    // 바구니의 마지막 인형과 같은지 확인
                    if (!basket.isEmpty() && basket.peek() == doll) {
                        basket.pop();  // 같은 인형이면 터트림 (바구니에서 제거)
                        answer += 2;  // 두 개의 인형이 사라졌으므로 2를 더함
                    } else {
                        basket.push(doll);  // 다른 인형이면 바구니에 넣음
                    }
                    break;  // 인형을 집었으면 더 이상 해당 열에서 탐색할 필요 없음
                }
            }
        }
        
        return answer;  // 터트려져 사라진 인형 개수 반환
    }
}