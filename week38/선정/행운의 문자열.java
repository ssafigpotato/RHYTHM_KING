

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static String str;
    static HashMap<Character, Integer> cnt = new HashMap<>();
    static boolean []visited;
    static int ans;
    static void backtracking(int len, int depth, char prev, String result){
        if(depth == len){
            // System.out.println(result);
            ans++;
            return;
        }
        for(char c : cnt.keySet()){
            if(cnt.get(c) > 0 && c != prev){ // 남아있는 문자 개수가 있고, 이전 문자와 같지 않을 떄만 백트래킹
                cnt.put(c, cnt.get(c)-1); // 해당 문자를 사용했다는 표시로 개수 감소
                backtracking(len, depth+1, c, result+c);
                cnt.put(c, cnt.get(c)+1); // 원상 복구
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str = sc.next();
        visited = new boolean[str.length()];

        // 예제 1번처럼 같은 문자가 여러개인경우를 대비해서
        // 각 문자열의 개수를 저장해줌
        for(char c : str.toCharArray()){
            cnt.put(c, cnt.getOrDefault(c, 0)+1);
        }

        backtracking(str.length(), 0, '\0', "");
        System.out.println(ans);
    }
}
