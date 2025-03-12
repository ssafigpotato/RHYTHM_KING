package boj_9081_단어맞추기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
	
	static String word;
	static String[] words;

	static boolean[] used;
	static String[] newWords;
	static HashSet<String> wordList;
	
	static boolean isEnd;
	static String answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=0; t<TC; t++) {
			word = br.readLine();
			answer = "";
			
			split();
			search();
			
			// 주어진 단어가 마지막 단어인 경우 그냥 주어진 단어를 출력
			System.out.println(answer == "" ? word : answer);
		}
	}
	
	/**
	 * 입력받은 단어를 알파벳 단위로 분리해서 사전순 정렬
	 */
	public static void split() {
		words = word.split("");
		Arrays.sort(words);
	}
	
	/**
	 * 주어진 단어 다음에 나오는 단어 찾기
	 */
	public static void search() {
		used = new boolean[word.length()];
		newWords = new String [word.length()];
		wordList = new HashSet<String>();
		isEnd = false;
		
		combi(0);
	}
	
	/**
	 * 주어진 알파벳으로 만들 수 있는 단어 만들기
	 */
	public static void combi(int idx) {
		// 이미 답을 구한 경우 종료
		if(!answer.equals("")) {
			return;
		}
		
		// 단어가 완성되었을 경우 다음 단계 진행
		if(idx >= words.length) {
			isCheck();
			return;
		}
		
		// 단어 만들기
		for(int i=0; i<words.length; i++) {
			if(used[i]) continue;
			
			newWords[idx] = words[i];
			used[i] = true;
			combi(idx+1);
			used[i] = false;
		}
	}
	
	/**
	 * 단어가 완성되었을 때 주어진 단어 다음에 나오는 단어인지 확인
	 */
	public static void isCheck() {
		String newWord = toString(newWords);
		
		// 기존에 만들어졌던 단어인 경우 pass
		if(wordList.contains(newWord)) {
			return;
		}
		
		// 주어진 단어 다음 단어인 경우 answer에 단어 넣기
		if(isEnd) {
			answer = newWord;
			return;
		}
		
		// 만들어진 단어가 주어진 단어와 같은 경우 이 다음 단어가 정답임을 체크해두기
		if(newWord.equals(word)) {
			isEnd = true;
		}

		// 만들어진 단어를 wordList에 넣기
		wordList.add(newWord);
	}
	
	/**
	 * 배열을 합쳐서 문자열로 변환
	 */
	public static String toString(String[] arr) {
		StringBuilder sb = new StringBuilder();
		
        for (String s : arr) {
            sb.append(s);
        }
        
        return sb.toString();
	}
}
