import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		
        // scanner: 시간 초과
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]); // 수열의 길이
		int K = Integer.parseInt(input[1]); // 같은 정수는 K개 이하
		
		int[] arr = new int[N]; // 수열을 저장하는 배열
		String[] num = br.readLine().split(" ");
		
		for(int i =0; i<N; i++) { // 수열 입력
			arr[i] = Integer.parseInt(num[i]);
		}
		
		System.out.println(longestSubarray(arr, K));
		
	}

	private static int longestSubarray(int[] arr, int K) {
		
		int maxLen = 0; // 최장 연속 부분 수열의 길이
		int start = 0; // 연속 수열의 시작 인덱스
		int end = 0; // 연속 수열의 끝 인덱스
		
		HashMap<Integer, Integer> map = new HashMap<>(); // <수열에 있는 숫자, 이 숫자가 나온 횟수 >
		
        // for문은 수열의 뒷부분을 늘리는 기능
		for(end=0; end<arr.length; end++) { //수열을 처음부터 하나씩 늘려나가면서 확인한다.
			
            // getOrDefault(a,b): a 값을 가져오고, 없으면 b값을 가져옴
            // 이번에 확인하는 숫자, 지금까지 나온 횟수에 + 1
            map.put(arr[end], map.getOrDefault(arr[end], 0)+1);
			
            // 슬라이딩 도어 (수열을 스르르ㅡㅡㄱ 옆으로 옮김)
            // while문은 수열의 앞부분을 줄이는 기능
			while (map.get(arr[end]) > K) { // 이번에 확인한 숫자가 K번 초과해서 나왔다면
				map.put(arr[start], map.get(arr[start]) -1); //(옆으로 옮기니까) 시작점의 숫자가 나왔던 횟수를 -1 해준다.
				start++; // 시작점 한 칸 뒤로
			}
			
			maxLen = Math.max(maxLen, end-start+1); // 수열 길이 업데이트
		}
		
		
		return maxLen;
	}

}
