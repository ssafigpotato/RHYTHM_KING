package boj_2178;

import java.util.*;
import java.io.*;

public class BOJ1697_숨바꼭질 {
    
    static int N, K;  //수빈이와 동생의 위치
    static int[] seconds;
    static Queue<Integer> queue;
    static boolean[] visited;
    
    static int MAX = 100000;
    static int MIN = 0;
    
    public static void main (String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        seconds = new int[100001];
        visited = new boolean[100001];
        
        String[] input = br.readLine().split(" ");
        
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        
        System.out.println(bfs(N));
        
        br.close();
    }
    
    static int bfs (int s_place) {
        
        queue = new LinkedList<>();
        
        seconds[s_place] = 0;
        visited[s_place] = true;
        queue.add(s_place);
        
        while (!queue.isEmpty()) {
            
        	int soobin_is_here = queue.poll();
            
        	if (soobin_is_here == K) break;
            
            int time_plus = seconds[soobin_is_here] + 1;
            
            //걸어서 X+1로 이동
            if (soobin_is_here+1 <= MAX && !visited[soobin_is_here+1]) {
            	visited[soobin_is_here+1] = true;
            	seconds[soobin_is_here+1] = time_plus;
            	queue.add(soobin_is_here+1);
            }
           //걸어서 X-1로 이동
            if (soobin_is_here-1 >= MIN && !visited[soobin_is_here-1]) {
            	visited[soobin_is_here-1] = true;
            	seconds[soobin_is_here-1] = time_plus;
                queue.add(soobin_is_here-1);
            }
            //순간이동
            if (soobin_is_here*2 <= MAX && !visited[soobin_is_here*2]) {
            	visited[soobin_is_here*2] = true;
            	seconds[soobin_is_here*2] = time_plus;
                queue.add(soobin_is_here*2);
            }
        }
        
        return seconds[K];
    }
}
