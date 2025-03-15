package org.algebra;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int p = 5;

        System.out.println("Field GF(" + p + "^2):");
        FiniteFieldGFp2 a = new FiniteFieldGFp2(p, 2, 3);
        FiniteFieldGFp2 b = new FiniteFieldGFp2(p, 4, 1);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a + b = " + a.add(b));
        System.out.println("a * b = " + a.multiply(b));

    }
}
