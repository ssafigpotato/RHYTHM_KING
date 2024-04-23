package week04;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 문제 풀이 방법) 
 * 802 / 1,2,3,4...?  ==  743 / 1,2,3,4..?
 * == 457 / 1,2,3,4...?  == 539 / 1,2,3,4...? == ans 
 * 반대로 생각하면) 
 * 802/ ans + 743 / ans + 457 / ans + 539/ans  >= K일 때
 * ans를 구하기! 
 * */
public class BOJ1654_랜선자르기 {
	static long K; // 이미 가지고 있는 랜선의 개수
	static long N; // 필요한 랜선개수
	static long []length; // 가지고 있는 랜선의 길이 배열
	static long ans;
	static long BinarySearch(long[]arr, long N) {
	
		long min = 1; // 나눌 수 있는 가장 작은 숫자
		long max = arr[arr.length -1] ; // 배열의 가장 큰 숫자
		
		ans = 0;
		while(min <= max) {
			long mid = (min + max)/2;
			long sum = 0;
			for(long i = 0; i <K; i++) {
				sum += length[(int) i]/mid;
//				System.out.println(length[i]+"/"+j+"일 때 sum: "+sum);
			}
			if(sum >= N) { // sum이 N보다 크거나 같으면
				if(ans < mid ) { // 그 중에서 최대 크기를 ans로 갱신
					ans = mid;
				}
				// 더 큰 값은 없는지 알아보기
				min = mid+1;
			}else {
				// 더 작은 값에서 알아보기
				max = mid-1;
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// 1. 입력받기 
		K = sc.nextInt();
		N = sc.nextInt();
		length = new long[(int) K];
		
		for(int i = 0 ; i <K; i++) {
			length[i] = sc.nextInt();
		}// 입력 끝
		
		// 2. 오름차순으로 정렬하기 
		Arrays.sort(length); 
		
		// 3. 이분탐색 
		BinarySearch(length, N);
		
		// 4. 정답 출력
		System.out.println(ans);
		
		
	}
}
