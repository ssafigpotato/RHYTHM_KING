package wordchanger;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	
	// 단어와 변환 횟수를 관리
	private static class Node {
		String word;
		int depth;
		
		Node(String word, int depth){
			this.word = word;
			this.depth = depth;
		}
	}
	
	public int solution(String begin, String target, String[] words) {
		
		// 목표 단어가 변환 가능한 단어 목록에 없는 경우 변환할 수 없음
		if(!Arrays.) {
			
		}
		
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[words.length];
		
		queue.add(new Node(begin, 0)); // 시작 단어와 변환 횟수를 큐에 추가
		
		while(!queue.isEmpty()) {
			
			Node curr = queue.poll(); // 현재 단어
			
			// 현재 단어가 목표 단어와 같다면 변환 횟수를 반환
			if(curr.word.equals(target)) {
				return curr.depth;
			}
			
			// 모든 단어를 순회하면서 변환 가능 검사
			for(int i =0; i<words.length; i++) {
				
				// 방문하지 않았고(=사용하지 않음) + 한 긓자만 다르다면
				if(!visited[i] && changeable(curr.word, words[i])) {
					visited[i] = true; // 해당 단어 words 리스트에서 사용 완료
					queue.add(new Node(words[i], curr.depth+1)); // 큐에 새로운 단어(=바뀐 단어)와 +1된 변환 횟수를 추가
				}
			}
		}
	}
	
	
	// 두 단어가 한 글자만 다른지 확인
	private boolean changeable(String curr, String next) {
		int count = 0;
		for(int i =0; i<curr.length(); i++) {
			// 두 단어에서 다른 글자의 수를 세기
			if(curr.charAt(i) != next.charAt(i)) {
				count++;
			}
		}
		return count==1; // 한 글자만 다르면 true 반환
	}

}
