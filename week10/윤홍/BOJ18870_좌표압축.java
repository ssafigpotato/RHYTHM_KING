package BOJ_18870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 진짜 별의별 방법을 다 써봤는데 시간초과만 7번은 낸거 같다. 그래서 내가 모르는 라이브러리를 써야하는거 같아 질문게시판을 검색한 결과 
 * HashSet이라는 듣도 보도 못한 걸 써야한다는걸 깨닫고 시도한 결과 겨우 통과.
 * 아직 시간 복잡도를 계산하는 법을 정확히 모로는 부분이 크다는걸 깨닫고 다시 좌절중.
 * 일단 HashSet의 경우 이 블로그를 참고 https://velog.io/@acacia__u/hashSet.
 * 요약을 하자면 증복을 허용하지 않지만 정렬은 되지 않는 빠른 Set 인터페이스의 구현 클래스라는 것.
 * 여기까지는 그러려니 하고 이제 써먹어야지 했는데 생각보다 또 정렬방식이 복잡...
 * 다시 List화 시켜서 Collections.sort해서 통과하긴 했는데 이거랑.. 그냥 list.contain해서 중복제거하는거랑 무슨 차이인지 아직도 정확히 모르겠다.
 * 어쨋든 통과하긴함 버퍼랑 스트링 빌더 다 써서...
 */



public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());

		Integer[] arr = new Integer[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Set<Integer> set = new HashSet<Integer>(Arrays.asList(arr));
		
		set.stream().sorted();
		
		List<Integer> list = new ArrayList<>(set);
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		
		/*
		 * 오랜만에 등장하는 이분 탐색도 중요한 포인트였다. 블로그 탐색 결과 좌표압축 관련 문제는 이분 탐색이 필수인 느
		 */
		
		for(int i = 0; i < N; i++) {
			sb.append(Collections.binarySearch(list, arr[i]) + " ");
		}
		
		System.out.println(sb);
		
		
		
	}
}
