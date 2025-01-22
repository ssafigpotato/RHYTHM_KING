import java.io.*;
import java.util.*;

/** 
 * [알고리즘 전개 방식]
 * 1. 모든 화학 원소 기호를 HashSet에 저장 (빠른 검색을 위해)
 * 2. 입력받은 문자열에 대해 동적 프로그래밍(DP) 적용
 *    - DP[i] = i번째 위치까지의 문자열이 원소 기호들의 조합으로 가능한지 여부
 * 3. 각 위치에서 두 가지 경우를 확인
 *    - 현재 위치에서 한 글자를 원소 기호로 사용할 수 있는지
 *    - 현재 위치에서 두 글자를 원소 기호로 사용할 수 있는지
 * 4. 문자열 끝까지 확인 후 최종 결과 반환
 * 
 * [시간 복잡도]
 * - O(N), N은 입력 문자열의 길이
 * - 각 위치에서 최대 2번의 부분 문자열 확인
 */

public class Main {
    static Set<String> elements;
    static boolean[] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 원소 기호 초기화
        initializeElements();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < T; t++) {
            String word = br.readLine().toLowerCase();
            System.out.println(canMakeWord(word) ? "YES" : "NO");
        }
    }
    
    static void initializeElements() {
        elements = new HashSet<>();
        String[] elementList = {
            "h", "he", "li", "be", "b", "c", "n", "o", "f", "ne", "na", "mg",
            "al", "si", "p", "s", "cl", "ar", "k", "ca", "sc", "ti", "v", "cr",
            "mn", "fe", "co", "ni", "cu", "zn", "ga", "ge", "as", "se", "br", "kr",
            "rb", "sr", "y", "zr", "nb", "mo", "tc", "ru", "rh", "pd", "ag", "cd",
            "in", "sn", "sb", "te", "i", "xe", "cs", "ba", "la", "ce", "pr", "nd",
            "pm", "sm", "eu", "gd", "tb", "dy", "ho", "er", "tm", "yb", "lu", "hf",
            "ta", "w", "re", "os", "ir", "pt", "au", "hg", "tl", "pb", "bi", "po",
            "at", "rn", "fr", "ra", "rf", "db", "sg", "bh", "hs", "mt", "ds", "rg",
            "cn", "fl", "lv", "ac", "th", "pa", "u", "np", "pu", "am", "cm", "bk",
            "cf", "es", "fm", "md", "no", "lr"
        };
        elements.addAll(Arrays.asList(elementList));
    }
    
    static boolean canMakeWord(String word) {
        int n = word.length();
        dp = new boolean[n + 1];
        dp[0] = true;
        
        for(int i = 1; i <= n; i++) {
            /**
            word = "international"
            
            i = 1일 때
            dp[0] = true (빈 문자열)
            word.substring(0, 1) = "i"    
            "i"가 elements에 있으므로 dp[1] = true
            
            word = "international"
            i = 9일 때
            dp[7] = true (여기까지 "inter" 가능)
            word.substring(7, 9) = "na"
            "na"가 elements에 있으므로 dp[9] = true
            */
            
            // 한 글자 원소 확인
            if(i >= 1 && dp[i-1] && elements.contains(word.substring(i-1, i))) {
                dp[i] = true;
            }
            // 두 글자 원소 확인
            if(i >= 2 && dp[i-2] && elements.contains(word.substring(i-2, i))) {
                dp[i] = true;
            }
        }
        
        return dp[n];
    }
}