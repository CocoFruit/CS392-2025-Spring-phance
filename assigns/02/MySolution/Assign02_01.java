import edu.princeton.cs.algs4.*;

public class Assign02_01 {
    /*
      HX-2025-02-13: 10 points
      The 'int' type in Java is for integers of some fixed precision.
      More precisely, each integer of the type int is represented a sequence of bits
      of some fixed length. Please write a program that finds this length. Your program
      is expected return the correct answer instantly. Note that you can only use arithmetic
      operations here. In particular, no bit-wise operations are allowed.
     */
    public static void main(String[] argv) {
	int n = 1;
    int bit_length = 0;
    while(n != 0){ // 0 is the last number that can be represented by int as it will overflow
        bit_length = bit_length + 1;
        n = n *2;
    }
    StdOut.println(bit_length);
    }
}
