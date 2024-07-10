package 서희;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ13164_행복유치원 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] height = new int[n];
        
        for (int i = 0; i < n; i++) {
        	height[i] = sc.nextInt();
        }
        
        // 인접한 원생들 사이의 키 차이를 계산
        int[] diff = new int[n - 1];
        for (int i = 1; i < n; i++) {
            diff[i - 1] = height[i] - height[i - 1];
        }
        
        // 키 차이를 오름차순으로 정렬
        Arrays.sort(diff);
        
        // 가장 큰 (k-1)개의 차이를 제거한 나머지의 합이 최소 비용
        int minPrice = 0;
        for (int i = 0; i < n - k; i++) {
        	minPrice += diff[i];
        }
        
        System.out.println(minPrice);
        
        sc.close();
    }
}