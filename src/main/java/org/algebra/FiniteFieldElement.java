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

    public FiniteFieldElement multiply(FiniteFieldElement el) {
        int newA = (this.a * el.a + this.b * el.b * coefB) % p;
        int newB = (this.a * el.b + this.b * el.a + this.b * el.b * coefA) % p;
        return new FiniteFieldElement(p, newA, newB, coefA, coefB);
    }

    /**
     * Calculates the power of an element using repeated multiplication.
     *
     * @param exponent The exponent to raise the element to.
     * @return The result of (this^exponent) in GF(p^2).
     */
    public FiniteFieldElement pow(int exponent) {
        FiniteFieldElement result = new FiniteFieldElement(p, 1, 0, coefA, coefB); // 1 + 0x neutral element
        for (int i = 0; i < exponent; i++) {
            result = result.multiply(this);
        }
        return result;
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
        if (a == 0) return (b == 1) ? "x" : b + "x";
        if (b == 0) return String.valueOf(a);
        return (b == 1) ? a + " + x" : a + " + " + b + "x";
    }
}
