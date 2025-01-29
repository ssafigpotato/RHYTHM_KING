import java.util.*;

class Solution {
    // sol) 
    // 1. 포인트 위치를 매핑
    // 2. 포인트 위치를 기반으로 로봇이 이동하는 경로에 따라 bfs
    //    bfs를 하는 동안 각 초마다 id번 로봇이 어디에 위치해있는지 기록
    // 3. 초당 로봇의 위치기록을 토대로 
    //    같은 시간에 같은 위치에 여러대의 로봇이 존재하는 경우 충돌 위험 +1
    static class Location {
        int r, c, id;
        Location(int r, int c, int id){
            this.r = r;
            this.c = c;
            this.id = id;
        }
        // 확인을 위해 출력 
        @Override
        public String toString() {
            return "[id=" + id + ", r=" + r + ", c=" + c + "]";
        }
    }

    static HashMap<Integer, Location> pointMap = new HashMap<>(); // 포인트 위치 매핑
    static HashMap<Integer, List<Location>> locMap = new HashMap<>(); // 초당 로봇의 위치 기록 
    static int time; // 전역 time 사용 <-- 그래야 예제 3번처럼 여러 포인트 들리는 경우 time을 연속적으로 쓸 수 있음! 

    static void bfs(Location start, Location end, int id) {
        Queue<Location> que = new LinkedList<>();
        que.offer(start);

        while (!que.isEmpty()) {
            Location curr = que.poll();
            
            // 현재 초에서 이미 같은 위치에 같은 로봇이 기록되었는지 확인
            // 예제 3번처럼 여러 포인트 거치는 경우 '도착지점이자 새로운 시작지점'인 포인트에서 위치가 중복으로 기록되는것 방지 
            if (locMap.containsKey(time)) {
                boolean flag = false;
                for (Location loc : locMap.get(time)) { // 해당 시간대에 이미 기록된 같은 위치의 같은 로봇이 있으면 break;
                    if (loc.r == curr.r && loc.c == curr.c && loc.id == id) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    locMap.get(time).add(new Location(curr.r, curr.c, id));
                }
            } else {
                // 시간당 로봇의 위치 기록 
                locMap.put(time, new ArrayList<>());
                locMap.get(time).add(new Location(curr.r, curr.c, id));
            }
            

            // 목적지 도달 시 종료 
            // <-- 수정) 이게 여기있으면 도달 후에도 또 map에 넣게 됨!!
            // 예제 3번처럼 여러 포인트를 거치는 경우 중간 지점에서 위치및 아이디가 중복으로 저장됨! 
            // <-- 수정) 를 위해 위에 적었더니(도달하면 바로 끝낼 수 있도록) 
            // 마지막 도달 위치에서 충돌나는 경우는 cnt해주지않는 경우 발생... (예제 2)
            if (curr.r == end.r && curr.c == end.c) return;

            int nr = curr.r;
            int nc = curr.c;
            
            // r의 위치가 같은지 아닌지가 기준! <-- r좌표를 우선적으로 이동해야하므로!
            if (curr.r != end.r) {
                nr += (curr.r < end.r) ? 1 : -1; // r 좌표 우선 이동
            } else {
                nc += (curr.c < end.c) ? 1 : -1; // c 좌표 이동
            }

            que.offer(new Location(nr, nc, id));
            time++; // 이동할 때마다 time 증가
        }
    }
    
    static void print(){  // 디버깅을 위해 출력 
        for(Integer key  : locMap.keySet()){
            System.out.println(key+"초 일 때: "+locMap.get(key));
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        // 1. 포인트 위치 매핑
        for (int i = 0; i < points.length; i++) {
            pointMap.put(i + 1, new Location(points[i][0], points[i][1], i + 1));
        }

        // 2. 각 로봇의 경로마다 BFS 실행 (초당 위치 기록)
        for (int i = 0; i < routes.length; i++) {
            time = 0; // 각 로봇마다 time 초기화

            for (int j = 0; j < routes[i].length - 1; j++) {
                Location start = pointMap.get(routes[i][j]);
                Location end = pointMap.get(routes[i][j + 1]);

                bfs(start, end, i + 1);
                // print();
            }
            // print();
            // System.out.println("=========================");
        }

        // 3. 충돌 횟수 계산 (같은 시간에 같은 위치에 있는 경우만 체크)
        int answer = 0;
        for (List<Location> locs : locMap.values()) { // 같은 시간에 대해서 
            HashMap<String, Integer> cnt = new HashMap<>(); // 위치별 존재하는 로봇 대수 저장 

            for (Location loc : locs) {
                String key = loc.r + "_" + loc.c;
                cnt.put(key, cnt.getOrDefault(key, 0) + 1);
            }

            for (int count : cnt.values()) { // 같은 위치에 여러대가 존재하면 
                if (count > 1) {
                    // --> 같은시간,같은 위치에 로봇이 2대 이상 존재하면 충돌위험+1
                    answer += 1;
                }
            }
        }

        return answer;
    }
}
