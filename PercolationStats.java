public class PercolationStats {
    
    private int T;
    private int N;
    private int[] measurements;
    //private double [] measurements;
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        measurements = new int[T];
        this.T = T;
        this.N = N;
        Percolation p = null;
        
        int sitesOpen;
        for (int i = 0; i < T; i++ ) {
            p = new Percolation(N);
            sitesOpen = 0;
            while(!p.percolates()) {
                p.open(StdRandom.uniform(N)+1, StdRandom.uniform(N)+1);
                sitesOpen++;
            }
            //System.out.println("Storing measurement of " + sitesOpen);
            measurements[i] = sitesOpen;
        }                      
    }
    
    // sample mean of percolation threshold
    public double mean() {      
        return StdStats.mean(measurements)/(N*N);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(measurements);
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96*stddev()/Math.sqrt(T);
    }
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() { 
        return mean() + 1.96*stddev()/Math.sqrt(T);
    }
    // test client, described below
    public static void main(String[] args) {
        //PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        PercolationStats ps = new PercolationStats(200,100);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}