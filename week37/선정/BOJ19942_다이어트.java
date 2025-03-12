// 왜 8%에서 런타임에러가뜰까?(indexoutofbounds...)
package algo_0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19942_다이어트 {
    static int N;
    static int mp,mf,ms,mv;
    static List<List<Integer>> nutrients = new ArrayList<>(); // 영양성분 저장
    static List<Integer> pick = new ArrayList<>(); // 음식 N개에서 K개를 뽑는 조합
    static int min = Integer.MAX_VALUE;  // 최소 비용
    static List<List<Integer>> results = new ArrayList<>(); // 정답 후보 리스트 저장
    static void combination(int depth, int at, int K){
        if(depth == K){ // K개를 다 뽑았으면 return
            int cSum = check();
            if(cSum != -1){ // 최소 영양성분을 만족하는 조합에 대해서
                if(min > cSum){ // 새로운 최소비용이 나오면
                    min = cSum;
                    results.clear(); // 기존 정답 후보들 싹 비우고
                    results.add(new ArrayList<>(pick));
                    for(List<Integer> l : results){
                        System.out.println(l);
                    }
                } else if (min == cSum){ // 같은 비용인 pick이 나왔다면
                    results.add(new ArrayList<>(pick)); // 추가
                }
            }
            return;
        }

        for(int i = at; i <=N; i++){ // 1번째 ~ N번째 음식 중에 고르기
            pick.add(i);
            combination(depth+1, i+1, K);
            pick.remove(pick.size()-1);
        }
    }

    static int check(){ // 최소 영양성분을 만족할때만 cSum반환
        int pSum =0, fSum =0, sSum =0, vSum =0, cSum =0;
        if(pick.isEmpty()) return -1;

        for(int i = 0; i < pick.size(); i++){
            List<Integer> curr = nutrients.get(pick.get(i));
            pSum += curr.get(0);
            fSum += curr.get(1);
            sSum += curr.get(2);
            vSum += curr.get(3);
            cSum += curr.get(4);
        }
        if(pSum >= mp && fSum >= mf && sSum >= ms && vSum >= mv) return cSum;
        else return -1;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        // System.out.println(N+" "+mp+" "+mf+" "+ms+" "+mv);

        // 0번째 인덱스에 0,0,0,0,0 추가
        nutrients.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)));


        for(int i = 1; i <=N; i++){ // 1번째부터 N번째 음식 영양성분 삽입
            nutrients.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for(int j= 0; j<5; j++){ // 단백질, 지방, 탄수화물, 비타민, 가격 정보 삽입
                nutrients.get(i).add(Integer.parseInt(st.nextToken()));
            }
            // System.out.println(nutrients.get(i));
        }
        // System.out.println("size는???: " +nutrients.size());

        for(int i = 0; i <= N; i++){ // 0개~ N개까지 뽑아서 조건들 만족하는지 조합
            combination(0,1,i);
        }

        if (min == Integer.MAX_VALUE || results.isEmpty()) {
            System.out.println(-1);
            return;
        }

        System.out.println(min);

        Collections.sort(results, (a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return Integer.compare(a.get(i), b.get(i));
                }
            }
            return 0;
        });

        if (results.isEmpty()) { // 방어 코드 추가 (results가 비어있을 경우)
            System.out.println(-1);
            return;
        }

        List<Integer> best = results.get(0);
        for (int b : best) {
            System.out.print(b + " ");
        }

//        if(min == Integer.MAX_VALUE || results.isEmpty()){ // 만족하는 답 없음
//            System.out.println(-1);
//        }else {
//            System.out.println(min);
//
//            // 사전 순 정렬!!!
//            Collections.sort(results, (a,b) -> { // results안의 List들을 각각 비교
//                for (int i =0; i <a.size(); i++){ //
//                    if(!a.get(i).equals(b.get(i))){ // 리스트안의 원소가 서로 다르다면 (같다면 건너뛰고 그 다음 원소를 비교)
//                        return Integer.compare(a.get(i),b.get(i)); // 오름차순 정렬
//                    }
//                }
//                return 0; // 두 리스트가 완전히 같다면 순서 유지
//            });
//
//            // 사전순으로 가장 앞에 있는 조합을 출력
//            List<Integer> best = results.get(0);
//            for(int b : best){
//                System.out.print(b+" ");
//            }
//        }


    }
}
