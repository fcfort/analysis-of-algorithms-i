public class Subset {	
     public static void main(String[] args) {
         if (args.length != 1) {
             return;
         }
         
         int N = Integer.parseInt(args[0]);
         
         RandomizedQueue<String> q = new RandomizedQueue<String>();
         
         // A B C D E F G H I
         String[] inputArray = StdIn.readAllStrings();
         // System.out.println("Got input string with " + inputArray.length + " els");
         
         for (String s : inputArray) {
        	 // System.out.println("Adding " + s);
        	 q.enqueue(s);	 
         }

         for (int i = 0; i < N; i++) {
             StdOut.println(q.dequeue());
         }
     }
}