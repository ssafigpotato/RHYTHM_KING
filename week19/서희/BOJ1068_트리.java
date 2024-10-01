package boj_1068_트리;

import java.util.Scanner;

public class Main {
	
	static int n;
	static int delete;
	static int[] parent;
	static int count;
	static boolean[] visited;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		parent = new int [n];
		
		int root = 0;
		for(int i=0; i<n; i++) {
			parent[i] = sc.nextInt();
			
			if(parent[i] == -1) {
				root = i;
			}
		}
		
		delete = sc.nextInt();
		deleteNode(delete);
		
		count = 0;
		visited = new boolean [n];
		countLeaf(root);
		
		System.out.println(count);
		
		sc.close();
		
	}
	
	public static void deleteNode(int d) {
		parent[d] = -2;		// 삭제된 노드 표시
		for(int i=0; i<n; i++) {
			if(parent[i] == d) {
				deleteNode(i);
			}
		}
	}
	
	public static void countLeaf(int node) {
		boolean isLeaf = true;
		visited[node] = true;
		
		if(parent[node] != -2) {
			for(int i=0; i<n; i++) {
				if(parent[i] == node && !visited[i]) {
					countLeaf(i);
					isLeaf = false;
				}
			}
			
			if(isLeaf) count++;
		}
	}

}
