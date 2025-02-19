class Solution {
    public char[][] warehouse;
    public int n, m;
    
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        warehouse = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            warehouse[i] = storage[i].toCharArray();
        }
        
        for (String request : requests) {
            char target = request.charAt(0);
            if (request.length() == 1) {
                forklift(target);
            }
            else {
                crane(target);
            }
        }
        return countContainers() ;
        
    }
    
    
    public void forklift(char target) {
        boolean removed;
        do {
            removed = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (warehouse[i][j] == target && isAccessible(i, j)) {
                        warehouse[i][j] = '.';
                        removed = true;
                    }
                }
            }
        } while (removed);
    }
    
     public void crane(char target) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (warehouse[i][j] == target) {
                    warehouse[i][j] = '.';
                }
            }
        }
    }
    
    
    public boolean isAccessible(int row, int col) {
      
        if (warehouse[row][col] == '.') return false;
  
        if (row == 0 || row == n-1 || col == 0 || col == m-1) return true;
        
     
        return warehouse[row-1][col] == '.' || 
               warehouse[row+1][col] == '.' || 
               warehouse[row][col-1] == '.' || 
               warehouse[row][col+1] == '.';
    }
    
    
    public int countContainers() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (warehouse[i][j] != '.') {
                    count++;
                }
            }
        }
        return count;
    }
    
    
}