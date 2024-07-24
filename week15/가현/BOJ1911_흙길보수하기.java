import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt(); // 물웅덩이의 개수
        int L = sc.nextInt(); // 널빤수의 길이
        
        int[][] puddles = new int[N][2];
        
        for (int i = 0; i < N; i++) {
            puddles[i][0] = sc.nextInt(); // 물웅덩이 시작
            puddles[i][1] = sc.nextInt(); // 물웅덩이 끝
        }
        
        Arrays.sort(puddles, (a, b) -> a[0] - b[0]); // 물웅덩이를 처음부터 정렬 정렬 
        
        int count = 0; // 사용된 널빤지 수
        int currentEnd = 0; // 현재 널빤지가 끝나는 위치
        
        for (int i = 0; i < N; i++) { // 물웅덩이 순환
            int start = puddles[i][0]; // 이번 물웅덩이의 시작점
            int end = puddles[i][1]; // 이번 물웅덩이의 끝나는 지점
            
            if (currentEnd < start) { // 저번 널빤지 뒤에 새로운 물웅덩이가 시작한다면 ->> 물웅덩이 시작지점부터 깔아준다.
                int needed = (int)Math.ceil((double)(end - start) / L); // 필요한 널빤지 개수 = 물웅덩이 길이 / 널빤지 길이 (올림)
                count += needed; // 널빤지 개수 더해준다.
                currentEnd = start + needed * L; // 널빤지의 끝지점 = 새로운 물웅덩이에서 널빤지 깔아준 곳까지
            } 
            else if (currentEnd < end) { // (새로운 물웅덩이 시작지점은 넘겼지만) 깔려있는 널빤지가 END 포인트까지 커버하지는 못한다면
                start = currentEnd; // 시작점: 물웅덩이의 시작점 -> 널빤지가 끝나는 곳으로 ; 업데이트
                int needed = (int)Math.ceil((double)(end - start) / L); // 위와 동일
                count += needed; 
                currentEnd = start + needed * L; 
            }
        }
        
        System.out.println(count);
    }
}