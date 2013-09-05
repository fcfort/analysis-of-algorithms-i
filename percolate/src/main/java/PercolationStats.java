public class PercolationStats {
    private int T;
    //private int[] measurements;
    private double [] percentMeasurements;
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        //measurements = new int[T];
        percentMeasurements = new double[T];
        this.T = T;
        //this.N = N;
        Percolation p = null;
        
        int sitesOpen;
        for (int t = 0; t < T; t++) {
            p = new Percolation(N);
            sitesOpen = 0;

            while (!p.percolates()) {
                int i = StdRandom.uniform(N)+1;
                int j = StdRandom.uniform(N)+1;
                if (!p.isOpen(i, j)) {
                    p.open(i, j);
                    sitesOpen++;
                }               
            }
            double percent = (double) sitesOpen/(N*N);
            // System.out.println("Storing measurement of 
            // " + sitesOpen + ", percent = " + percent );
            //measurements[t] = sitesOpen;
            percentMeasurements[t] = percent;
        }                      
    }
    
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percentMeasurements);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percentMeasurements);
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
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), 
                                                   Integer.parseInt(args[1]));
        // PercolationStats ps = new PercolationStats(200,100);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() 
                               + ", " + ps.confidenceHi());
    }
}