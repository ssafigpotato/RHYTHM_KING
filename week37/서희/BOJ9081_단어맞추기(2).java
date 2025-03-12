package boj_9081_단어맞추기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
	
	final static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=0; t<TC; t++) {
			findNextWord(br.readLine());
		}
		
		System.out.println(sb.toString());
	}
	
	public static void findNextWord(String str) {
		int len = str.length();
		char[] words = str.toCharArray();
		
		int idx1 = -1;	// 처음으로 감소하는 원소 위치
		int idx2 = 0;	// 1에서 찾은 원소보다 처음으로 큰 원소 위치
		
		// 1. 맨 뒤에서부터 확인하며 처음으로 감소하는 부분 찾기
		for(int i=len-1; i>0; i--) {
			if(words[i-1] < words[i]) {
				idx1 = i-1;
				break;
			}
		}
		
		if(idx1 == -1) {
			sb.append(str).append("\n");
			return;
		}
		
		// 2. 다시 맨 뒤에서부터 확인하며 감소하는 부분보다 큰 부분 찾기
		for(int i=len-1; i>=0; i--) {
			if(words[idx1] < words[i]) {
				idx2 = i;
				break;
			}
		}
		
		// 3. idx1과 idx2에 있는 두 원소 swap 하기
		char temp = words[idx1];
		words[idx1] = words[idx2];
		words[idx2] = temp;
		
		// 4. idx1의 위치 뒷 부분 오름차순 정렬
		Arrays.sort(words, idx1+1, len);
		
		for(char c : words) {
			sb.append(c);
		}
		sb.append("\n");
	}
}
