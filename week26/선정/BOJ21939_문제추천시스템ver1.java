package algo_1216;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;
/**
 * Sol) TreeSet + HashMap 사용
 * TreeSet만 사용할 경우 removeIf로 인해 시간 초과가 발생할 수 있음.
 * removeIf는 전체 TreeSet을 탐색해야 하므로 시간 복잡도가 최악의 경우 O(N)
 * 이를 방지하기 위해 HashMap을 사용하여 문제 번호로 난이도를 O(1) 시간에 조회.
 * TreeSet의 remove는 O(log N) 시간에 동작하여 전체 시간 복잡도를 최적화.
 */
public class BOJ21939_문제추천시스템ver1 {
	static int N; //문제 리스트에 있는 문제 개수
	static class Problem implements Comparable<Problem>{
		int id;
		int lev;
		
		Problem(int id, int lev){
			this.id = id;
			this.lev = lev;
		}
		
		@Override
		public int compareTo(Problem o) {
			if(this.lev == o.lev) {
				return Integer.compare(this.id, o.id);
			}
			return Integer.compare(this.lev, o.lev);
		}
		/**
		 * TreeSet에서 solved 명령으로 객체를 삭제할 때, 객체의 동등성을 비교하여 삭제 대상을 찾음
		 * TreeSet은 내부적으로 객체를 compareTo로 정렬하지만, 
		 * 객체를 정확히 삭제하려면 equals와 hashCode가 필요
		 * 
		 * solved 명령어에서 problems.remove(new Problem(P, L))를 호출할 때, 
		 * TreeSet은 입력 객체와 기존 객체가 같은지 판단하기 위해 equals와 hashCode를 호출
		 * 따라서 이 코드에서 equals와 hashCode를 반드시 구현해야 함.
		 * 그렇지 않으면 TreeSet에서 정확히 원하는 객체를 삭제할 수 없거나 예기치 않은 동작이 발생
		 * */
		@Override
		public boolean equals(Object obj) {
			/**
			 * 객체 참조 비교:
			 * 현재 객체(this)와 비교 대상 객체(obj)가 같은 참조를 가리킴: true
			 * 두 객체가 같은 메모리 주소를 참조하면, 이는 곧 두 객체가 완전히 동일함을 의미
			 * */
			if(this == obj) return true;  // 동일한 참조면 true
			/**
			 * 비교 대상 객체(obj)가 Problem 클래스의 인스턴스인지 확인
			 * 만약 obj가 Problem 클래스의 객체가 아니라면 비교 자체가 의미 없으므로 false
			 * 잘못된 타입의 객체와 비교 방지: ClassCastException과 같은 런타임 오류 피함
			 * */
			if(!(obj instanceof Problem)) return false; // 타입이 다르면 false
			Problem other = (Problem) obj;
			return this.id == other.id && this.lev == other.lev; // 문제 번호와 난이도가 같으면 true
		}
		/**
		 * 객체를 해시 기반 컬렉션(HashMap, HashSet)에 넣을 때 빠르게 검색하기 위해 사용
		 * TreeSet에서 직접 사용하지는 않지만, remove 같은 메서드를 호출할 때 
		 * 해시 기반 동작과 호환을 유지하기 위해 구현해야 함
		 * */
		@Override
		public int hashCode() {
			// 문제 번호와 난이도를 바탕으로 고유한 해시코드 생성
			// equals에서 동일하다고 판단한 객체는 반드시 동일한 해시코드 필요 
			return Objects.hash(id, lev);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 1. TreeSet, HashMap 선언
		TreeSet<Problem> problems = new TreeSet<>();
		HashMap<Integer, Integer> problemMap = new HashMap<>();
		
		// 2. TreeSet, HashMap에 문제 번호와 난이도 입력
		N = sc.nextInt();
		for(int i = 0; i <N; i++) {
			int P = sc.nextInt();
			int L = sc.nextInt();
			problems.add(new Problem(P,L));
			problemMap.put(P, L);
		}
		
		// 3. 명령어 연산 
		int M = sc.nextInt();
		for(int i = 0; i <M; i++) {
			String command = sc.next();
			if(command.equals("recommend")) {
				int x = sc.nextInt();
				if(x == 1) {
					System.out.println(problems.last().id);
				}else {
					System.out.println(problems.first().id);
				}
			}else if (command.equals("add")) {
				int P = sc.nextInt();
				int L = sc.nextInt();
				problems.add(new Problem(P,L));
				problemMap.put(P, L);
			}else if (command.equals("solved")) {
				int P = sc.nextInt();
				int L = problemMap.get(P);
				/**
				 * remove 메소드는 P뿐만 아니라 난이도 L도 알아야함 (O(log N))
				 * 만약 treeSet만 사용한다면 removeIf를 사용해야함 
				 * problems.removeIf(problem -> problem.id == P);
				 * */
				problems.remove(new Problem(P,L));
				problemMap.remove(P);
			}
		}
		
		sc.close();
	}//main
}
