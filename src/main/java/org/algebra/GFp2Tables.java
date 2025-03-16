package org.algebra;

public class GFp2Tables {
    private final int p;
    private final int[][] elements;
    private final int alpha;

    public GFp2Tables(int p) {
        this.p = p;
        this.alpha = (p - 1) % p;
        this.elements = generateElements();
    }

    /**
     *  Generates all (a,b) pairs, where a,b is in {0, 1, ..., p-1}
     * @return array of p^2 elements
     */
    private int[][] generateElements() {
        int size = p * p;
        int[][] arr = new int[size][2];
        int index = 0;

        for (int a = 0; a < p; a++) {
            for (int b = 0; b < p; b++) {
                arr[index][0] = a;
                arr[index][1] = b;
                index++;
            }
        }
        return arr;
    }

    /**
     * GF(p^2): (a,b) + (c,d) = ((a+c) mod p, (b+d) mod p).
     */
    private int[] add(int[] x, int[] y) {
        int a = (x[0] + y[0]) % p;
        int b = (x[1] + y[1]) % p;
        return new int[]{a, b};
    }

    /**
     *  GF(p^2), x^2 = alpha;
     *  (a + b*x)(c + d*x) = (a*c + b*d*alpha) * (a*d + b*c)*x
     */
    private int[] multiply(int[] x, int[] y) {
        int a = x[0], b = x[1];
        int c = y[0], d = y[1];

        int real = (a * c + b * d * alpha) % p;
        int imag = (a * d + b * c) % p;

        return new int[]{(real + p) % p, (imag + p) % p};
    }

    /**
     *  (a,b) -> "a + bx"
     */
    private String elementToString(int[] e) {
        return e[0] + " x " + e[1] + "x";
    }

    /**
     * Print all field elements with index
     */
    public void printElements() {
        System.out.println("All elements GF(" + p + "^2):");
        for (int i = 0; i < elements.length; i++) {
            System.out.printf("Index %d: %s\n", i, elementToString(elements[i]));
        }
    }

    public void printAdditionTable() {
        System.out.println("\nAddition Table GF(" + p + "^2):");
        System.out.print("          ");
        for (int col = 0; col < elements.length; col++) {
            System.out.printf("%15s", elementToString(elements[col]));
        }
        System.out.println();

        for (int row = 0; row < elements.length; row++) {
            System.out.printf("%-10s", elementToString(elements[row]));
            for (int col = 0; col < elements.length; col++) {
                int[] sum = add(elements[row], elements[col]);
                System.out.printf("%15s", elementToString(sum));
            }
            System.out.println();
        }
    }

    public void printMultiplicationTable() {
        System.out.println("\nMultiplication Table GF(" + p + "^2):");
        System.out.print("          ");

        for (int col = 0; col < elements.length; col++) {
            System.out.printf("%15s", elementToString(elements[col]));
        }

        for (int row = 0; row < elements.length; row++) {
            System.out.printf("%-10s", elementToString(elements[row]));
            for (int col = 0; col < elements.length; col++) {
                int[] product = multiply(elements[row], elements[col]);
                System.out.printf("%15s", elementToString(product));
            }
            System.out.println();
        }
    }
}
