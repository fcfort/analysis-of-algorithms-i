public class Percolation {
    private boolean [][] field;
    public WeightedQuickUnionUF qu;
    private int N;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        qu = new WeightedQuickUnionUF(N*N + 2); // for false top and bottom 
        this.N = N;

        field = new boolean[N][N]; 
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) { 
                field[i][j] = false;
            }
        }
    }
    
    public String toString() {
        String s = new String();
        for (int i = 0; i < N; i++ ) {
            for ( int j = 0; j < N; j++) { 
                s += field[i][j] ? "1" : "0";
            }
            s += "\n";
        }
        return s;
    }
    
    private boolean inGrid (int i, int j) { 
        return 
            i < N && i >= 0 && 
            j < N && j >= 0
        ;
    }
    
    // given i and j returns an index to it's position in the quickfind data structure
    private int getQfIndex(int i, int j) {
        return i*N + 1 + j;
    }
    
    private void unionIfOpen(int originI, int originJ, int adjacentI, int adjacentJ) {
        if ( inGrid(adjacentI,adjacentJ) && field[adjacentI][adjacentJ] ) { 
            //System.out.println(adjacentI + " and " + adjacentJ + " are open, unioning");
            field[adjacentI][adjacentJ] = true; 
            int quFirstIndex = getQfIndex(originI,originJ);
            int quSecondIndex = getQfIndex(adjacentI,adjacentJ);
            //System.out.println(adjacentI + " and " + adjacentJ + " are open, unioning qu pos 1 = " + 
//quFirstIndex + ", pos 2 = " + quSecondIndex);
            qu.union(quFirstIndex, quSecondIndex);;
        }
    }
    
    private void unionToTop(int i, int j) {
        //System.out.println("Unionning to top with indexes i = " + i + " and j = " + j);
        qu.union(0,getQfIndex(i,j));
    }
    
    private void unionToBottom(int i, int j) {
        //System.out.println("Unionning to bottom with indexes i = " + i + " and j = " + j);
        qu.union(N*N + 1,getQfIndex(i,j));
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        field[i-1][j-1] = true;
        //System.out.println("Opening up i = " + (i-1) + " and j = " + (j-1));
        // if we've opened up a cell at the top or bottom
        if ( i == 1 ) { unionToTop(0,j-1); }
        if ( i == N ) { unionToBottom(N-1,j-1); }
        
        // up
        unionIfOpen(i-1,j-1,i-2,j-1);
        // down
        unionIfOpen(i-1,j-1,i,j-1);
        // left
        unionIfOpen(i-1,j-1,i-1,j-2);
        // right
        unionIfOpen(i-1,j-1,i-1,j);
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) { 
        return field[i-1][j-1];
    }    
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) { 
        return qu.connected(getQfIndex(i-1,j-1),0);
    }   
    
    // does the system percolate?
    public boolean percolates() {
        return qu.connected(0,N*N+1);    
    }
    
    public static void main ( String ... args ) {
        Percolation p = new Percolation(3);
        
        
        System.out.println(p);
        p.open(1,1);
         System.out.println(p);
        p.open(2,1);
         System.out.println(p);
        System.out.println("Percolates? " + p.percolates());
        p.open(3,1);
        System.out.println(p);
        System.out.println("Percolates? " + p.percolates());
    }
}