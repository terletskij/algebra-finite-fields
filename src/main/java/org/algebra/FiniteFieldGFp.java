package org.algebra;

public class FiniteFieldGFp {
    private final int p;
    private final int value;

    public FiniteFieldGFp(int p, int value) {
        this.p = p;
        this.value = (value % p + p) % p; // remainder will always be non-negative
    }

    public FiniteFieldGFp add(FiniteFieldGFp field) {
        return new FiniteFieldGFp(p, (this.value + field.value) % p);
    }

    public FiniteFieldGFp subtract(FiniteFieldGFp field) {
        return new FiniteFieldGFp(p, (this.value - field.value + p) % p);
    }

    public FiniteFieldGFp multiply(FiniteFieldGFp field) {
        return new FiniteFieldGFp(p, (this.value * field.value) % p);
    }

    public FiniteFieldGFp inverse() {
        int inv = modInverse(value, p);
        return new FiniteFieldGFp(p, inv);
    }

//  Euclid's algorithm
    private int modInverse(int a, int m) {
        int m0 = m, y = 0, x = 1;
        while (a > 1) {
            int q = a / m;
            int t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        return (x + m0) % m0;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value + " mod " + p;
    }
}
