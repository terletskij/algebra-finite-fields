package org.algebra;

public class FiniteFieldGFp2 {
    private final int p;
    private final FiniteFieldGFp a;
    private final FiniteFieldGFp b;

    public FiniteFieldGFp2(int p, int a, int b) {
        this.p = p;
        this.a = new FiniteFieldGFp(p, a);
        this.b = new FiniteFieldGFp(p, b);
    }

    public FiniteFieldGFp2 add(FiniteFieldGFp2 otherField) {
        return new FiniteFieldGFp2(p, this.a.add(otherField.a).getValue(), this.b.add(otherField.b).getValue());
    }

    public FiniteFieldGFp2 subtract(FiniteFieldGFp2 otherField) {
        return new FiniteFieldGFp2(p, this.a.subtract(otherField.a).getValue(), this.b.subtract(otherField.b).getValue());
    }

//    (a + bx) * (c + dx)
    public FiniteFieldGFp2 multiply(FiniteFieldGFp2 otherField) {
        FiniteFieldGFp newA = this.a.multiply(otherField.b).subtract(this.b.multiply(otherField.b));
        FiniteFieldGFp newB = this.a.multiply(otherField.b).add(this.b.multiply(otherField.a));
        return new FiniteFieldGFp2(p, newA.getValue(), newB.getValue());
    }

    @Override
    public String toString() {
        return "(" + a + " + " + b + "x) mod " + p;
    }
}

