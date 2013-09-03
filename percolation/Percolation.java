public class Percolation {
    private boolean falseTop;
    private boolean falseBottom;
    private boolean [][] field;
    private QuickUnionUF qu;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        qu = new QuickUnionUF(N*N + 1);
        
        falseTop = true;
        falseBottom = true;

        field = new boolean[N][N];
        for ( boolean[] row : field ) {
            for ( boolean site : row ) {
                site = false;
            }
        }
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        field[i - 1][j - 1] = true;
        
        // up
        if ( ! field[i-i][j-2] ) {
            
        }
        
        // down
        
        // left
        
        // right
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) { return false; }    
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) { return false; }   
    
    // does the system percolate?
    public boolean percolates() { return false; }
    
    public static void main ( String ... args ) {
        
    }
}