public class Percolation {
    private boolean falseTop;
    private boolean falseBottom;
    private boolean [][] field;
    private QuickUnionUF qu;
    private int N;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        qu = new QuickUnionUF(N*N + 1);
        this.N = N;
        falseTop = true;
        falseBottom = true;

        field = new boolean[N][N];
        for ( boolean[] row : field ) {
            for ( boolean site : row ) {
                site = false;
            }
        }
    }
    
    private boolean inGrid (int i, int j) { 
        return 
            i <= N && i > 0 && 
            j <= N && j > 0
        ;
    }
    
    // given i and j returns an index to it's position in the quickfind data structure
    private int getQfIndex(int i, int j) {
        return (i-1)*N + j;
    }
    
    private unionIfOpen(int originI, int originJ, int adjacentI, int adjacentJ) {
        if ( inGrid(adjacentI,adjacentJ) && ! field[adjacentI][adjacentJ] ) { 
            field[adjacentI][adjacentJ] = true; 
            qu.union(getQfIndex(originI,originJ),getQfIndex(adjacentI,adjacentJ));
        }
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        field[i-1][j-1] = true;
        
        // up
        unionIfOpen(i-1,j-1,i-1,j-2);
        // down
        unionIfOpen(i-1,j-1,i-1,j);
        // left
        unionIfOpen(i-1,j-1,i-2,j-1);
        // right
        unionIfOpen(i-1,j-1,i,j-1);
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) { 
        return field[i-1][j-1];
    }    
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) { 
        return ! isOpen(i, j);
    }   
    
    // does the system percolate?
    public boolean percolates() {
        qu.connected(0,N+1);    
    }
    
    public static void main ( String ... args ) {
        
    }
}