class Solution {
    // 사용할 모음들을 배열로 저장
    public final char[] vowels = {'A', 'E', 'I', 'O', 'U'};
    public int count = 0;  // 현재까지 만든 단어의 수를 카운트
    public int result = 0; // 찾고자 하는 단어의 순서를 저장
    public boolean found = false; // 단어를 찾았는지 여부를 저장
    
    public int solution(String word) {
        // 백트래킹 시작 (빈 문자열부터 시작)
        backtrack("", word);
        return result;
    }
    
    /**
     * 백트래킹을 통해 모든 가능한 단어를 생성하는 메소드
     * @param current 현재까지 만든 단어
     * @param target 찾고자 하는 목표 단어
     */
    public void backtrack(String current, String target) {
        // 이미 단어를 찾았다면 더 이상 진행하지 않음
        if (found) return;
        
        // 현재 만든 단어가 존재하면 카운트 증가
        // 실행 순서:
        // count = 1: "A" 
        // count = 2: "AA"
        // count = 3: "AAA"
        // count = 4: "AAAA"
        // count = 5: "AAAAA"
        // count = 6: "AAAAE"
        // count = 7: "AAAAI"
        // count = 8: "AAAAO"
        // count = 9: "AAAAU"
        // count = 10: "AAAE" (목표 단어)
        if (!current.isEmpty()) {
            count++;
            
            // 현재 단어가 목표 단어와 일치하면
            if (current.equals(target)) {
                result = count;
                found = true;
                return;
            }
        }
        
        // 현재 단어의 길이가 5이면 더 이상 문자를 추가할 수 없음
        if (current.length() == 5) {
            return;
        }
        
        // 모든 모음에 대해 반복
        // * 재귀 호출의 실행 순서 (트리 구조로 표현):
        //    "" (빈 문자열)
        //    |
        //    A -> AA -> AAA -> AAAA -> AAAAA
        //    |         |      |       AAAAE
        //    |         |      |       AAAAI
        //    |         |      |       AAAAO
        //    |         |      |       AAAAU
        //    |         |      AAAE (목표 단어를 찾음!)
        //    |         |      AAAI
        //    |         |      AAAO
        //    |         |      AAAU
        //    |         AAE
        //    |         AAI
        //    |         AAO
        //    |         AAU
        //    E -> EA -> ...
        //    I
        //    O
        //    U
        for (char vowel : vowels) {
            // 현재 단어에 새로운 모음을 추가하여 재귀 호출
            backtrack(current + vowel, target);
        }
    }
}