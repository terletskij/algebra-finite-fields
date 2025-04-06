package org.algebra;

import java.util.Objects;

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

    /**
     * Multiplies two elements (a + bx) and (c + dx) in GF(p^2),
     * using the irreducible polynomial <br> x^2 + coefA * x + coefB = 0.
     * <br> x^2 = -coefA*x - coefB
     * <br>
     * <br> Formula:
     * <br> (a + bx)(c + dx) = ac + (ad + bc)x + bdx^2
     * <br>
     * <br> Substitute x^2 = -coefAx - coefB
     * <br> (a + bx)(c + dx) = (ac - bdcoefB) + (ad + bc - bdcoefA)x
     * <br>
     * <br> So, the result is:
     * <br> newA = (ac - bdcoefB) % p
     * <br> newB = (ad + bc - bdcoefA) % p
     * @return product of elements
     */
    public FiniteFieldElement multiply(FiniteFieldElement el) {
        int newA = ((this.a * el.a) - (this.b * el.b * coefB)) % p;
        int newB = ((this.a * el.b) + (this.b * el.a) - (this.b * el.b * coefA)) % p;

        // make sure that newA, newB is not negative numbers
        newA = (newA % p + p) % p;
        newB = (newB % p + p) % p;
        return new FiniteFieldElement(p, newA, newB, coefA, coefB);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FiniteFieldElement that = (FiniteFieldElement) o;
        return p == that.p && a == that.a && b == that.b && coefA == that.coefA && coefB == that.coefB;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p, a, b, coefA, coefB);
    }

    @Override
    public String toString() {
        if (b == 0) return a == 0 ? "0" : String.valueOf(a);
        if (a == 0) return (b == 1) ? "x" : b + "x";
        return (b == 1) ? a + " + x" : a + " + " + b + "x";
    }
}
