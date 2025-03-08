public class Assign03_01 {

    public static int mystery(int x) {
        return (x < 100 ? 100 - x : mystery(mystery(x - 11)));
    }

    public static int check(int num) {
        int test = num;
        int lo = 0;
        int hi = test;
        int result = lo;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            try {
                mystery(mid);  // Start recursion with depth 0
                result = mid;
                lo = mid + 1;
            } catch (StackOverflowError e) {
                hi = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] argv) {
        // System.out.println(check(1000000));
        System.out.println(mystery(374997));  // Start recursion with depth 0
    }
}
