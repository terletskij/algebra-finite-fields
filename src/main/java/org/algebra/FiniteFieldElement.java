package org.algebra;

public class FiniteFieldElement {
    private final int p;
    private final int a, b; // element represented as a + bx
    private final int coefA, coefB; // Coeffs of x^2 + a*x + b

    public FiniteFieldElement(int p, int a, int b, int coefA, int coefB) {
        this.p = p;
        this.a = (a % p + p) % p;
        this.b = (b % p + p) % p;
        this.coefA = coefA;
        this.coefB = coefB;
    }

    public FiniteFieldElement add(FiniteFieldElement el) {
        return new FiniteFieldElement(p, (this.a + el.a) % p, (this.b + el.b) % p, coefA, coefB);
    }

    public FiniteFieldElement multiply(FiniteFieldElement el) {
        int newA = (this.a * el.a + this.b * el.b * coefB) % p;
        int newB = (this.a * el.b + this.b * el.a + this.b * el.b * coefA) % p;
        return new FiniteFieldElement(p, newA, newB, coefA, coefB);
    }

    @Override
    public String toString() {
        if (a == 0) return (b == 1) ? "x" : b + "x";
        if (b == 0) return String.valueOf(a);
        return (b == 1) ? a + " + x" : a + " + " + b + "x";
    }
}
