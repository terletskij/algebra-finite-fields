package org.algebra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GFp2Tables {
    private final int p;
    private final List<FiniteFieldElement> elements;
    private final int coefA, coefB; // coefs of x^2 + ax + b

    public GFp2Tables(int p) {
        this.p = p;
        int[] coefficients = findIrreduciblePolynomial(p);
        this.coefA = coefficients[0];
        this.coefB = coefficients[1];
        this.elements = generateElements();
    }

    private List<FiniteFieldElement> generateElements() {
        List<FiniteFieldElement> elems = new ArrayList<>();

        for (int a = 0; a < p; a++) {
            for (int b = 0; b < p; b++) {
                elems.add(new FiniteFieldElement(p, a, b, coefA, coefB));
            }
        }
        return elems;
    }

    public void printAdditionTable() {
        System.out.println("\nAddition Table GF(" + p + "^2):");
        printTable(true);
    }

    public void printMultiplicationTable() {
        System.out.println("\nMultiplication Table GF(" + p + "^2) with x^2 + " + coefA + "x + " + coefB + ":");
        printTable(false);
    }

    public void printGenerators() {
        System.out.println("\nGenerator Elements of GF(" + p + "^2):");
        List<FiniteFieldElement> generators = findGenerators();

        for (FiniteFieldElement generator : generators) {
            System.out.println(generator);
        }
    }

    private void printTable(boolean isAddition) {
        System.out.print("        ");
        for (FiniteFieldElement header : elements) {
            System.out.printf("%10s", header);
        }
        System.out.println();

        for (FiniteFieldElement e1 : elements) {
            System.out.printf("%10s", e1);
            for (FiniteFieldElement e2 : elements) {
                FiniteFieldElement result = isAddition ? e1.add(e2) : e1.multiply(e2);
                System.out.printf("%10s", result);
            }
            System.out.println();
        }
    }

    private int[] findIrreduciblePolynomial(int p) {
        for (int a = 0; a < p; a++) {
            for (int b = 1; b < p; b++) {
                boolean hasRoot = false;
                for (int x = 0; x < p; x++) {
                    if ((x * x + a * x + b) % p == 0) {
                        hasRoot = true;
                        break;
                    }
                }
                if (!hasRoot) {
                    return new int[]{a, b};
                }
            }
        }
        throw new RuntimeException("No irreducible polynomial found for GF(" + p + "^2)");
    }

    private List<FiniteFieldElement> findGenerators() {
        List<FiniteFieldElement> generators = new ArrayList<>();
        int groupOrder = p * p - 1;

        Set<Integer> primeFactors = factorize(groupOrder);
        FiniteFieldElement one = new FiniteFieldElement(p, 1, 0, coefA, coefB);

        for (FiniteFieldElement e : elements) {
            if (e.equals(0)) continue;
            boolean isPrimitive = true;

            for (int factor: primeFactors) {
                int exponent = groupOrder / factor;
                FiniteFieldElement power = e.pow(exponent);
                if (power.equals(one)) {
                    isPrimitive = false;
                    break;
                }
            }
            if (isPrimitive) {
                generators.add(e);
            }
        }

        return generators;
    }

    /**
     * @param n number to factorize
     * @return unique prime divisors
     */
    private Set<Integer> factorize(int n) {
        Set<Integer> factors = new HashSet<>();

        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors;
    }
}
