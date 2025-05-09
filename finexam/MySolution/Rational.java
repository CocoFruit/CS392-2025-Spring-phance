public class Rational {
    private final int num;
    private final int den;

    public Rational(int n, int d) {
        int g = gcd(Math.abs(n), Math.abs(d));
        this.num = d < 0 ? -n / g : n / g;
        this.den = Math.abs(d / g);
    }

    public Rational add(Rational other) {
        return new Rational(this.num * other.den + other.num * this.den, this.den * other.den);
    }

    public Rational subtract(Rational other) {
        return new Rational(this.num * other.den - other.num * this.den, this.den * other.den);
    }

    public Rational multiply(Rational other) {
        return new Rational(this.num * other.num, this.den * other.den);
    }

    public Rational divide(Rational other) {
        if (other.num == 0) throw new ArithmeticException("Divide by zero");
        return new Rational(this.num * other.den, this.den * other.num);
    }

    public boolean equals24() {
        return this.num == 24 && this.den == 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rational)) return false;
        Rational r = (Rational) o;
        return this.num == r.num && this.den == r.den;
    }

    @Override
    public int hashCode() {
        return 31 * num + den;
    }

    @Override
    public String toString() {
        return num + (den == 1 ? "" : "/" + den);
    }
}
