public class PercolationTest {
    public void isOpenTest() {
        Percolation p = new Percolation(3);
        
        assertTrue(p.isFull(1,1));
    }
}