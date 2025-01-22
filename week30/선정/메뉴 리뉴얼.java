import java.util.*;
class Solution {
    // Solution) 
    // 1. orders에 잇는 전체 요리 종류를 TreeSet에 넣어서 중복 제거 && 오름차순 정렬
    // 2. 그 요리들로 각 course 개수에 맞는 요리 조합을 만들고
    // 3. 각 조합이 orders에 몇개 포함되어있는지 체크
    // 4. 가장 많이 포함된 조합을 result로 반환 
   
    static TreeSet<Character> set = new TreeSet<>(); // 트리셋에 넣어서 중복 제거하고, 자동 오름차순 정렬
    static List<Character> list; // 검색을 위해 TreeSet을 넣는 list 
    static char[]out;
    static int max;
    static HashMap<Integer, List<String>> resMap = new HashMap<>(); // <코스길이(target), 그 때 해당하는 코스요리들 list>
    
    // 전체 요리 종류에서 course 개수에 맞는 요리조합 만들어서
    // 최대로 많이 주문된 조합 구하기 
    public static void combination(int depth, int target, int at, String[]orders){
        if(depth == target){ // 원하는 코스길이에 도달하면 
            // 1) 그 조합이 orders에 몇 번 포함되는지 확인 
            int cnt = check(orders);
            String combi = new String(out); // char배열을 생성자를 이용해 String으로 변환 
            
            // 2) 그 중 가장 큰 값을 구하고 그 때의 조합을 resMap에 저장        
            if(cnt>= 2){ // 최소 2번 이상 주문했을 때로 한정!!!!!!!! 
                if(max < cnt){
                    max = cnt; 
                    // max일 때의 out배열을 resMap에 담으면 됨!!! 
                    resMap.put(target, new ArrayList<>() ); // 새로운 맥스를 발견하면 List를 초기화해주고
                    resMap.get(target).add(combi); // 해당 요리조합을 넣어줌
                }else if(max == cnt){ // 또 같은 cnt를 가지는 요리조합이 나왔을 때는 추가 
                    resMap.get(target).add(combi);
                }
             }
            
            return;
        }
        
        // 중복 x, 순서 상관 없으므로 조합! 
        for(int i= at; i < list.size(); i++){
            out[depth] = list.get(i); 
            combination(depth+1, target, i+1, orders);
        }
        
    }
    
    // orders 배열의 각 원소가 out배열의 원소를 모두 가지고 있을 때 그 수가 몇개인지  반환하는 메소드 
    // 예) AC 요리의 경우 ABCFG 주문 조합에서 A와 C 모두 포함됨
    // 이런식으로 AC, ACDE, ACDEH 주문에서도 A와 C 모두 포함되므로 총 4번 주문됨. 
    // 주의) 처음에는 HashSet으로 orders 원소를 변환하고 체크해서 테캐 두 개에서 시간초과남....   
    public static int check(String[] orders){ 
        int cnt = 0; 
			       
        for(String order : orders){ // 각 주문에 대하여 
            boolean isContain = true; // out배열(만들어진 요리조합)을 모두 포함하는지 여부를 저장하는 flag
            
            for(char o : out){
                if(!order.contains(Character.toString(o))){ // 각 주문이 out배열의 원소들을 모두 포함하는가? 
                    isContain = false; // 아니라면 flag를 false로 바꾸고 빠져나가기 
                    break;
                }
            }
            if(isContain){ // 모두 포함했을 때만 cnt++;
                cnt++; 
            }
        }
        return cnt;
        
    }
    public String[] solution(String[] orders, int[] course) {
        // 1. 전체 orders에 포함된 요리종류를 중복 제거 && 오름차순 정렬하여 TreeSet에 넣기 
        // TreeSet은 오름차순이 기본 정렬 
        for(int i = 0; i < orders.length; i++){
            String str = orders[i];
            for(int j = 0; j <str.length(); j++){
                set.add(str.charAt(j));
            }
        }
        
        // 2. TreeSet은 인덱스 기반으로 특정 원소를 가져오는 기능이 없으므로 list로 변환 
        // TreeSet은 Collection 인터페이스를 구현하므로 
        // 생성자를 통해 다른 Collection으로 쉽게 변환할 수 있음 
        list = new ArrayList<>(set); 
       
        // 3. 각 코스길이를 타겟으로 하는 요리 조합 구하기 
        for(int i = 0; i < course.length; i++){
            out = new char[course[i]]; // 요리조합 배열은 각 코스길이로 초기화 
            max = 0; // 맥스값도 0으로 초기화 
            combination(0, course[i], 0, orders);
            // System.out.println(course[i]+"개 일 때===============");
        }
        
        
        // 4. resMap에 저장된 결과를 리스트에 저장하고 
        List<String> res = new ArrayList<>();
        for(List<String> v : resMap.values()){
           //  System.out.print(v+" ");
            res.addAll(v);
        }
       
        // 5. 사전 오름차순 정렬 
        Collections.sort(res);
        // System.out.println(res);
        
        // 6. 리스트를 배열로 바꾸기 
        // (new 생성자 이용. 자바 11부터는 크기를 명확하게 지정해주지 않아도 toArray메서드가 자동으로 적절한 크기로 변환해줌 )
        String[] answer = res.toArray(new String[0]);
        return answer;
    }
}