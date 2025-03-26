import java.util.*;
class Solution {
    static List<Integer> list = new ArrayList<>();
    static int res = 0;
    static void combination(int at, int depth, int n, int[][]q, int[]ans){
        if(depth == 5){
            // System.out.println(list);
            if(check(q, ans)){ // 조건에 맞다면 ans++ 
                res++; 
            };
            return;
        }
        
        for(int i = at; i <= n; i++){ // 숫자 1~n 중에서
            list.add(i);
            combination(i+1, depth+1, n, q, ans);
            list.remove(list.size()-1); // 마지막에 추가된 것 지우기 
        }
    }
    static boolean check(int[][]q, int[]ans){ //q와 list의 원소가 일치하는 개수가 ans와 맞는지 판단
        for(int i = 0; i < q.length; i++){
            
            int correct = 0; // 일치하는 원소 개수 
            for(int j = 0; j < q[i].length; j++){
                for(int l : list){
                    if(l == q[i][j]) correct++;
                }
            }
            // System.out.println(correct+" "+ans[i]);
            if(ans[i] != correct) return false;
        }
        return true;
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        // sol) n개에서 5개를 뽑는 조합
        // 거기서 조건에 맞으면 cnt++
        combination(1,0,n,q,ans);
      
        return res;
    
    }
}