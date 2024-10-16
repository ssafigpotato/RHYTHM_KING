import java.util.*; 
class Solution {
    public int solution(int m, int n, String[] board) {
        
        char[][] pan = new char[m][n];

        // 판 입력받기
        for(int r=0; r<m; r++){
            String line = board[r];
            for(int c=0; c<n; c++){
                pan[r][c] = line.charAt(c);
            }
        }
        
        int answer = 0; // 팡팡 block 수
        
        while(true) {
            // 붙어있는 곳 찾기
        
            // HashSet을 사용하여 중복 제거를 방지, 좌표를 저장
            // int[] 배열로 좌표값을 저장하려고 했는데 -> 중복을 처리하지 못할 수 있음
            // '1,2' 와 같은 String의 형태로 저장
            HashSet<String> set = new HashSet<>();

            for(int r=0; r<m-1; r++){
                for(int c=0; c<n-1; c++){
                    if(pan[r][c] != ' ' && pan[r][c] == pan[r+1][c] && pan[r][c] == pan[r][c+1] && pan[r][c] == pan[r+1][c+1]){
                        set.add(r+","+c); //','로 했다가 char였음
                        set.add((r+1)+","+c);
                        set.add(r+","+(c+1));
                        set.add((r+1)+","+(c+1));
                    }
                }
            }
            
            // 비었다==터뜨릴게 없다 => 끝!
            if(set.isEmpty()) break;

            // 블록 없애기
            for(String position : set){
                String parts[] = position.split(",");
                int r = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                
                if(pan[r][c] != ' '){ // 이거 해줘야 중복 세는 것을 방지할 수 있음
                    pan[r][c] = ' ';
                    answer++;
                }
            }
            
            // 아래로 내리기
            for(int c=0; c<n; c++){
                for(int r=m-1; r>0; r--){
                    if(pan[r][c] == ' ') {
                        for(int a=r-1; a>=0; a--){
                            if(pan[a][c] != ' '){
                                pan[r][c] = pan[a][c];
                                pan[a][c] = ' ';
                                break; // break를 해줘야 하는 이유: 하나 내리면 그 윗줄로 올라가서 다시 탐색해야 함
                            }
                        }
                    }
                }
            }
            
        }
        return answer;
    }
}