package boj_14426_접두사찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		String alphabet;
		HashMap<String, Node> children = new HashMap<>();
		
		Node() {
			
		}
		
		Node(String alphabet) {
			this.alphabet = alphabet;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());	// 집합 S에 포함되어 있는 문자열
		int m = Integer.parseInt(st.nextToken());	// 검사해야 하는 문자열
		
		Node root = new Node();		// 루트 노드
		
		// n개의 단어를 입력받아서 트라이 구조로 만들기
		for(int i=0; i<n; i++) {
			String[] strArr = br.readLine().split("");
			Node node = root;
			
			for(String alphabet : strArr) {
				// 새로운 정점인 경우 현재 노드의 자식 요소로 추가
				if(!node.children.containsKey(alphabet)) {
					node.children.put(alphabet, new Node(alphabet));
				}
				
				// 해당 알파벳(alphabet)의 정점으로 이동
				node = node.children.get(alphabet);
			}
		}

		int cnt = 0;	// 접두사가 맞는 문자열 수
		
		// m개의 문자열 각각이 접두사인지 확인
		for(int i=0; i<m; i++) {
			String[] strArr = br.readLine().split("");
			Node node = root;
			boolean isTrue = true;	// 해당 문자열이 접두사인지 확인
			
			for(String alphabet : strArr) {
				// 접두사에 해당하지 않으면 종료
				if(!node.children.containsKey(alphabet)) {
					isTrue = false;
					break;
				}
				
				node = node.children.get(alphabet);
			}
			
			if(isTrue) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
