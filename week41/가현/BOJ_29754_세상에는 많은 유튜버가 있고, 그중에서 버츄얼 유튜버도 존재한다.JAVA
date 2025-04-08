import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 총 방송 수
		int M = sc.nextInt();  // 마지막 방송 본 날
		int totalWeeks = (M - 1) / 7 + 1; // 총 몇 주 봤을 까
	    
	    
	    /* 예제 입력 예시(tsuna)
	    tsuna 1 00:00 05:00
	    tsuna 2 03:00 20:00
	    tsuna 3 10:00 23:00
	    tsuna 5 07:00 20:00
	    tsuna 6 14:00 23:00
	    tsuna 7 16:00 22:00
	     */

	    
	    /*  저장 구조 예시
	       record = {
			  "tsuna" = {
			    0 = [6, 4980]  // 0번째 주차에 방송 6회, 총 4980분
			  }
			}
	     */

	    Map<String, Map<Integer, int[]>> record = new HashMap<>();
	    
	    for(int i =0; i<N; i++) {
	    	String name = sc.next(); // 버튜버 이름
	    	int day = sc.nextInt(); // 방송 날
	    	String start = sc.next(); // 시작 시간
	    	String end = sc.next(); // 종료 시간
	    	
	    	// 방송 지속 시간 (분)
	    	int startMin = parseTimeToMinutes(start);
	    	int endMin = parseTimeToMinutes(end);
	    	int duration = endMin - startMin;
	    	
	    	// 0주차부터 시작
	    	int week = (day-1)/7;
	    	
	    	// 첫 방송 -> 뉴 맵
	    	record.putIfAbsent(name, new HashMap<>());
	    	// 이름 찾아와서 방송 기록 저장
	    	Map<Integer, int[]> weekData = record.get(name);
	    	
	    	// 0번에 주차, 1번에 방송시간 
	    	weekData.putIfAbsent(week, new int[2]);
	    	weekData.get(week)[0]++;
	    	weekData.get(week)[1] += duration;
	    }
	    
	    //진짜 버튜버 저장할 리스트
	    List<String> real = new ArrayList<>();
	    
	    // 이름을 기준으로 모든 주차 방송했나요?
	    for(String name : record.keySet()) {
	    	Map<Integer, int[]> weeks = record.get(name);
	    	boolean isReal = true;
	    	
	    	// 모든 주차를 검사
            for(int week = 0; week < totalWeeks; week++) {
                // 해당 주차 데이터가 없거나, 조건 미달이면 진짜가 아님
                if(!weeks.containsKey(week) || 
                   weeks.get(week)[0] < 5 || 
                   weeks.get(week)[1] < 3600) {
                    isReal = false;
                    break;
                }
            }

            if(isReal) {
                real.add(name);
            }
	    }
	    
	    // 진짜 버튜버 없으면 -1
	    // 있으면 오름차순 정렬, 출력
	    if(real.isEmpty())
	    	System.out.println("-1");
	    else {
	    	Collections.sort(real);
	    	for(String name : real)
	    		System.out.println(name);
	    }
	    
	}

	
	// 시간 형식 -> 분 단위 변환
	private static int parseTimeToMinutes(String time) {
		
		// : 로 나누어서 String 배열에 시간/분 따로 저장
		String[] parts = time.split(":");
		
		return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
	}

}
