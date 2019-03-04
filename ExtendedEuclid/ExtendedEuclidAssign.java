// Harsh Kasyap
// 1921CS01

import java.math.*;
import java.util.*;

class Polynomial {
  private int[] coef;
  private int deg;

  // a * x^b
  public Polynomial(int a, int b) {
    coef = new int[b+1];
    //System.out.println(a);
    //if (a<0){
    //  a = a*-1;
    // }
    coef[b] = a;
    deg = degree();
  }

  // return coeff
  public int[] getCoeff() {
    return this.coef;
  }

  // return the degree of this polynomial (0 for the zero polynomial)
  public int degree() {
    int d = 0;
    for (int i = 0; i < coef.length; i++)
        if (coef[i] != 0) d = i;
    return d;
  }

  // return c = a + b
  public Polynomial plus(Polynomial b) {
    Polynomial a = this;
    Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
    for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
    for (int i = 0; i <= b.deg; i++) c.coef[i] += b.coef[i];
    c.deg = c.degree();
    return c;
  }

  // return (a - b)
  public Polynomial minus(Polynomial b) {
    Polynomial a = this;
    Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
    for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
    for (int i = 0; i <= b.deg; i++) c.coef[i] -= b.coef[i];
    c.deg = c.degree();
    return c;
  }

  // return (a * b)
  public Polynomial times(Polynomial b) {
    Polynomial a = this;
    Polynomial c = new Polynomial(0, a.deg + b.deg);
    for (int i = 0; i <= a.deg; i++)
        for (int j = 0; j <= b.deg; j++)
            c.coef[i+j] += (a.coef[i] * b.coef[j]);
    c.deg = c.degree();
    return c;
  }

  public Polynomial divides(Polynomial b) {
    Polynomial a = this;
    if ((b.deg == 0) && (b.coef[0] == 0))
        throw new RuntimeException("Divide by zero polynomial");

    if (a.deg < b.deg) return new Polynomial(0,0);

    int coefficient = a.coef[a.deg]/(b.coef[b.deg]);
    int exponent = a.deg - b.deg;
    Polynomial c = new Polynomial(coefficient, exponent);
    return c.plus( (a.minus(b.times(c)).divides(b)) );
  }

  // convert to string representation
  public String toString() {
    if (deg ==  0) return "" + coef[0];
    if (deg ==  1) return coef[1] + "x + " + coef[0];
    String s = coef[deg] + "x^" + deg;
    for (int i = deg-1; i >= 0; i--) {
        if      (coef[i] == 0) continue;
        else if (coef[i]  > 0) s = s + " + " + ( coef[i]);
        else if (coef[i]  < 0) s = s + " - " + (-coef[i]);
        if      (i == 1) s = s + "x";
        else if (i >  1) s = s + "x^" + i;
    }
    return s;
  }
}

class EuclidSet {
  private Polynomial Q,A1,A2,A3,B1,B2,B3;

  public Polynomial removeEvenPowers(Polynomial polynomial) {
    if (polynomial.toString().equals("0")) {
      return polynomial;
    }
    Polynomial corrPolynomial = null;
    int number[] = polynomial.getCoeff();
    for(int i = 0; i < number.length; i++) {
      if (number[i] % 2 != 0) {
        if (corrPolynomial == null) {
          corrPolynomial = new Polynomial(1, i);
        } else {
          corrPolynomial = corrPolynomial.plus(new Polynomial(1, i));
        }
      }
    }
    return corrPolynomial;
  }

  public void setQ(Polynomial q) {
    this.Q = q;
  }
  public void setA1(Polynomial a1) {
    this.A1 = a1;
  }
  public void setA2(Polynomial a2) {
    this.A2 = a2;
  }
  public void setA3(Polynomial a3) {
    this.A3 = a3;
  }
  public void setB1(Polynomial b1) {
    this.B1 = removeEvenPowers(b1);
  }
  public void setB2(Polynomial b2) {
    this.B2 = removeEvenPowers(b2);
  }
  public void setB3(Polynomial b3) {
    this.B3 = removeEvenPowers(b3);
  }
  public Polynomial getQ() {
    return this.Q;
  }
  public Polynomial getA1() {
    return this.A1;
  }
  public Polynomial getA2() {
    return this.A2;
  }
  public Polynomial getA3() {
    return this.A3;
  }
  public Polynomial getB1() {
    return this.B1;
  }
  public Polynomial getB2() {
    return this.B2;
  }
  public Polynomial getB3() {
    return this.B3;
  }
}

class ExtendedEuclidAssign {

  static int convertToInverse(int []number){
    int result = 0;
    for(int i = 0; i < number.length; i++) {
      if (number[i] < 0) {
        number[i] *= -1;
      }
      if (number[i] % 2 != 0) {
        result += number[i] * Math.pow(2, i);
      }
    }
    return result;
  }

  static String convertToFx(int number) {
    StringBuilder fX = new StringBuilder("");
    while(number > 0) {
      fX = fX.append(new StringBuilder(String.valueOf(number % 2)));
      number = number / 2;
    }
    if (fX.length() == 1) {
      fX.append("00");
    }
    if (fX.length() == 2) {
      fX.append("0");
    }
    return fX.toString();
  }

  public static void main(String[] args) {
    int inputNumber;

    Polynomial pX1   = new Polynomial(1, 3);
    Polynomial pX2   = new Polynomial(1, 1);
    Polynomial pX3   = new Polynomial(1, 0);
    Polynomial pX = pX1.plus(pX2).plus(pX3);
    System.out.println("Irreversible Polynomial in GF(2^3) is "+pX);

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a number in range of 1 to 7");
    inputNumber = scanner.nextInt();
    scanner.close();

    if (inputNumber < 1 || inputNumber > 7) {
      System.out.println("The number is out of range");
    } else {
      // Store in form of Polynomial Expression
      Polynomial fX = null;
      String fXStr = convertToFx(inputNumber);
      for (int i = 0; i < fXStr.length(); i++){
        char c = fXStr.charAt(i);
        if (fX == null) {
          fX = new Polynomial(Character.getNumericValue(c), i);
        } else {
          fX = fX.plus(new Polynomial(Character.getNumericValue(c), i));
        }
      }
      System.out.println("Input in form of Polynomial is "+fX);
      
      EuclidSet euclidSet[] = new EuclidSet[10];
      int euclidIndex = 0;
      euclidSet[euclidIndex] = new EuclidSet();
      euclidSet[euclidIndex].setA1(new Polynomial(1, 0));
      euclidSet[euclidIndex].setA2(new Polynomial(0, 0));
      euclidSet[euclidIndex].setA3(pX);
      euclidSet[euclidIndex].setB1(new Polynomial(0, 0));
      euclidSet[euclidIndex].setB2(new Polynomial(1, 0));
      euclidSet[euclidIndex].setB3(fX);

      do {
        euclidIndex++;
        euclidSet[euclidIndex] = new EuclidSet();
        
        euclidSet[euclidIndex].setA1(euclidSet[euclidIndex - 1].getB1());
        euclidSet[euclidIndex].setA2(euclidSet[euclidIndex - 1].getB2());
        euclidSet[euclidIndex].setA3(euclidSet[euclidIndex - 1].getB3());

        euclidSet[euclidIndex].setQ(euclidSet[euclidIndex - 1].getA3().divides(euclidSet[euclidIndex - 1].getB3()));
        
        euclidSet[euclidIndex].setB1(euclidSet[euclidIndex - 1].getA1().minus(euclidSet[euclidIndex].getQ().times(euclidSet[euclidIndex - 1].getB1())));
        euclidSet[euclidIndex].setB2(euclidSet[euclidIndex - 1].getA2().minus(euclidSet[euclidIndex].getQ().times(euclidSet[euclidIndex - 1].getB2())));
        euclidSet[euclidIndex].setB3(euclidSet[euclidIndex - 1].getA3().minus(euclidSet[euclidIndex].getQ().times(euclidSet[euclidIndex - 1].getB3())));
      } while(euclidSet[euclidIndex].getB3().degree() != 0);

      System.out.println(euclidSet[euclidIndex].getB2());
      System.out.println("Inverse of given number is " + convertToInverse(euclidSet[euclidIndex].getB2().getCoeff()));
    }
  }
}