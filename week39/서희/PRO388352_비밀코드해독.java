class Solution {
    
    static int n;
    static int[][] q;
    static int[] ans;
    
    static int[] code;
    static int result;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        code = new int [5];
        result = 0;
        
        combi(0);
        
        return result;
    }
    
    /**
        비밀 코드 만드는 메서드
    */
    private void combi(int idx) {
        if(idx >= 5) {
            check();
            return;
        }
        
        int start = (idx == 0) ? 1 : code[idx-1]+1; 
        for(int i=start; i<=n; i++) {
            code[idx] = i;
            combi(idx+1);
        }
    }
    
    /**
        만들어진 비밀코드가 시스템이 응답한 값과 일치하는지 체크해서 result 값 증가
    */
    private void check() {
        for(int i=0; i<q.length; i++) {
            if(count(q[i]) != ans[i]) {
                return;
            }
        }
        
        result++;
    }
    
    /**
        arr 배열에 비밀 코드에 포함된 정수가 몇 개 있는지 세기 
    */
    private int count(int[] arr) {
        int cnt = 0;
        int codeIdx = 0;   // 비밀 코드 idx
        int arrIdx = 0;    // arr 배열 idx
        
        while(codeIdx < 5 && arrIdx < 5) {
            if(arr[arrIdx] == code[codeIdx]) {
                cnt++;
                codeIdx++;
                arrIdx++;
            } else if (arr[arrIdx] > code[codeIdx]) {
                codeIdx++;
            } else {
                arrIdx++;
            }
        }
        
        return cnt;
    }
}