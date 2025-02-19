class Solution {
    // sol) 
    // 1) 한 번만 입력 되었을 경우
    // 주변이 벽이거나
    // 주변이 이미 빠져나가서 '0'인 경우 

    // 2) 두번 입력 되었을 경우 
    // 해당하는 알파벳 다 빼기 
    static int n,m; 
    static char[][] st;
    static int[]dr = {-1, 0, 1, 0};
    static int[]dc = {0, 1, 0, -1};
    static void calculation(char target, boolean flag){ // 일치하는 조건에 따라 빼고 '0'으로 갱신 
        // 복사 해 두고 
        char[][]clone = new char[n+2][m+2];
        for(int i = 0; i < n+2; i++){
            for(int j = 0; j <m+2; j++){
                clone[i][j] = st[i][j]; 
            }
        }
        
        
        for(int i = 0; i < n+2; i++){
            for(int j = 0; j < m+2; j++){
                if(!flag){ // requests가 한 글자일 때 
                    // 예외) 근데 차례대로 꺼내는 동안 새롭게 접근가능해진 것이면 쳐주면안됨
                    // 예2의 경우 꺼내는 중간에 접근가능해진 컨테이너는 꺼내면 안됨 
                     if(clone[i][j] == target && isAccesible(target, i, j, clone)){ // 접근 가능한 경우에만 갱신 
                        // 실 배열은 갱신 
                        st[i][j] = '0';
                     }
                }else { // requests가 두 글자일 때 
                    if(st[i][j] == target){
                        st[i][j] = '0';
                    }
                }
               
            }
        }
    }
    static boolean isAccesible(char target, int r, int c, char[][] clone){
        boolean accesible = false; 
        for(int d = 0; d <4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d]; 
            
            if(nr< 0 || nr >= n+2 || nc <0 || nc >= m+2) continue; 
            
            if(clone[nr][nc] == '0' || clone[nr][nc] == 0){ // 상하좌우가 빈 쿠션 쪽이거나 빼서 '0'된 곳이면 
                accesible = true; 
            }
        }
        return accesible; 
    }
    static void print(){
        for(int i = 0; i <n+1; i++){
            for(int j = 0; j <m+1; j++){
                System.out.print(st[i][j]+" ");
            }System.out.println();
        }
    }
    public int solution(String[] storage, String[] requests) {
        
        // 1. 상하좌우에 쿠션 깔고 storage배열을 넣기 
        n = storage.length;
        m = storage[0].length();
       
        st = new char[n+2][m+2]; 
        
        for(int i = 1; i <n+1; i++){
            String str = storage[i-1]; 
            // System.out.println(str);
            for(int j= 1; j<m+1; j++){
                st[i][j] = str.charAt(j-1);
            }
        }
        
        // for(int i = 0; i <n+1; i++){
        //     for(int j = 0; j <m+1; j++){
        //         System.out.print(st[i][j]+" ");
        //     }System.out.println();
        // }
        
        // 2. requests에 따라 요청사항 수행
        for(int i = 0; i < requests.length; i++){
            boolean flag = false; // 글자 수 체크 
            
            if(requests[i].length() == 1){
                // 주변값이 null이거나 '0'이면 빼고 '0'으로 갱신
                flag = false; 
                calculation(requests[i].charAt(0), flag); 
                print();
                System.out.println("=====================");
            }else {
                // 일치하면 모두 빼고 '0'으로 갱신
                flag = true; 
                calculation(requests[i].charAt(0), flag); 
                print();
                System.out.println("=====================");
            }
        }
        
    
        
        int answer = 0;
        
        return answer;
    }
}