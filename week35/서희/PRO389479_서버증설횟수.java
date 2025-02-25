import java.util.*;

class Solution {
    
    static Queue<Integer> server;
    static int serverNum;
    
    public int solution(int[] players, int m, int k) {
        server = new LinkedList<>();
        serverNum = 0;
        
        for(int time=0; time<players.length; time++) {
            endServer(time);
            
            int needServer = players[time] / m;
            int nowServer = server.size();
            
            if(needServer > nowServer) {
                addServer(needServer-nowServer, time, k);
            }
        }
        
        return serverNum;
    }
    
    /**
        운영 시간이 끝난 서버 종료
    */
    public void endServer(int time) {
        while(!server.isEmpty() && server.peek() <= time) {
            server.poll();
        }
    }
    
    /**
        서버 증설
    */
    public void addServer(int n, int now, int k) {
        for(int i=0; i<n; i++) {
            serverNum++;
            server.add(now+k);
        }
    }
}