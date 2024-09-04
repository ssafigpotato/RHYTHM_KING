class Solution {
    public int[] solution(String[][] places) {
    	
    	// 정답 배열 생성
        int[] answer = new int[5];
        
        // 전체 대기실에 대해 조회
        for (int i = 0; i < 5; i++) {
            answer[i] = checkWaitingRoom(places[i]);
        }
        
        return answer;
    }
    
    // 사람을 찾아서 거리두기 확인하기
    public int checkWaitingRoom(String[] room) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (room[r].charAt(c) == 'P') {
                	// 거리두기 조건을 만족하는지 확인
                    if (!checkDistance(room, r, c)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
    
    // 거리두기 
    public boolean checkDistance(String[] room, int r, int c) {
    	
    	int[] dr = {-1,0,1,0};
    	int[] dc = {0,1,0,-1};
    	
    	for(int i =0; i<4; i++) {
    		int nr = r + dr[i];
    		int nc = c + dc[i];    	
        
            if (isInRange(nr, nc)) { // 범위 확인
            	
            	// 거리가 1인 경우 탐색 -> 사람이 있다면 실패!
                if (room[nr].charAt(nc) == 'P') { 
                    return false;
                    
                    // 빈 테이블이 있다면 -> 한 칸 더 가서 사람이 있는지 확인
                } else if (room[nr].charAt(nc) == 'O') {
                    int nnr = nr + dr[i];
                    int nnc = nc + dc[i];
                    
                    // 범위 확인 && 사람있냐? 
                    if (isInRange(nnr, nnc) && room[nnr].charAt(nnc) == 'P') {
                        return false;
                    }
                }
            }
        }
        
    	int[] dgr = {-1,-1,1,1};
    	int[] dgc = {-1,1,1,-1}; 
    	
    	for(int i =0; i<4; i++) {
    		int nr = r + dgr[i];
    		int nc = c + dgc[i];
            
    		// 대각선에 사람이 있고
            if (isInRange(nr, nc) && room[nr].charAt(nc) == 'P') {
            	// 그 사이에 파티션이 없다면 -> 실패!
                if (room[r].charAt(nc) != 'X' || room[nr].charAt(c) != 'X') {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // 범위를 벗어나지는 않았는지
    public boolean isInRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}





// 정답률 90%의 코드 
//class Solution {
//    public int[] solution(String[][] places) {
//
//        // 정답 배열 생성
//        int[] answer = new int[5];
//
//        // 전체 대기실에 대해 조회
//        for(int i =0; i<5; i++){
//            answer[i] = checkWaitingRoom(places[i]);
//        }
//
//        return answer;
//    }
//
//    public int checkWaitingRoom(String[] roomNum){
//        int size = 5;
//
//        // 사람을 발견한다면
//        for(int r = 0 ; r<5; r++){
//            for(int c =0; c<5 ; c++){
//                if (roomNum[r].charAt(c) == 'P'){
//                    // 거리두기 조건을 만족하는지 확인한다.
//                    if(!checkDistance(roomNum, r, c)){
//                        return 0;
//                    }
//                }
//            }
//        }
//        return 1;
//    }
//
//    public boolean checkDistance(String[] roomNum, int r, int c){
//
//        int[] dr = {-1,0,1,0};
//        int[] dc = {0,1,0,-1};
//        int[] dgr = {-1,-1,1,1};
//        int[] dgc = {-1,1,1,-1};
//
//        // 바로 옆에 있는 경우
//        for(int i =0; i<4; i++){
//            int nr = r + dr[i];
//            int nc = c + dc[i];
//
//            if(checkArea(nr,nc,5) && roomNum[nr].charAt(nc) == 'P')
//                return false;
//        }
//
//        // 파티션 없는 채로 맨해튼 거리가 2
//        for(int i =0; i<4; i++){
//            int nr = r + 2dr[i];
//            int nc = c + 2dc[i];
//
//            if(checkArea(nr,nc,5) && roomNum[nr].charAt(nc)=='P'){
//                int midR = r + dr[i];
//                int midC = c + dc[i];
//                if(roomNum[midR].charAt(midC) != 'X'){
//                    return false;
//                }
//            }
//        }
//
//        // 대각선인데 파티션이 없음
//        for(int i=0; i<4; i++){
//            int nr = r + dgr[i];
//            int nc = c + dgc[i];
//
//            if(checkArea(nr, nc, 5) && roomNum[nr].charAt(nc)=='P'){
//                if(roomNum[r].charAt(nc)!='X' && roomNum[nr].charAt(c)!='X')
//                    return false;
//            }
//        }
//
//        return true;
//    }
//
//    // 영역 check~!
//    public boolean checkArea(int r, int c, int len){
//        return r>=0 && r<len && c>=0 && c<len;
//    }
//}