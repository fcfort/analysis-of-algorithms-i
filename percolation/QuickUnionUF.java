public class QuickUnionUF {
    
    private int[] id;
    private int[] sz;
    
    public QuickUnionUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) { 
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; } 
        else { id[j] = i; sz[i] += sz[j]; }
    }
    
    public String getArray() {
        StringBuilder b = new StringBuilder();
        for (int i : id) {
            b.append(i);
            b.append(" ");
        }
        
        return b.toString();   
    }

    public static void main(String ... args) {
        QuickUnionUF u = new QuickUnionUF(10);
        
        for(String p : args) {
            int i = Integer.parseInt(Character.toString(p.charAt(0)));
            int j = Integer.parseInt(Character.toString(p.charAt(2)));
            // System.out.println(p.charAt(0) + " " + p.charAt(2));
            u.union(i,j);
        }   
        
        System.out.println(u.getArray());
    }
}