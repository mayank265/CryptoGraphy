// Harsh Kasyap
// 1921CS01

import java.math.*;
import java.util.*;
//import java.util.concurrent.ThreadLocalRandom;

class RabinMillerAssign {

    static BigInteger pow(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) 
                result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }

    static BigInteger log2(BigInteger number) {
        BigInteger result = BigInteger.ZERO;
        BigInteger temp = new BigInteger("2");
        while (temp.compareTo(number) < 0) {
            temp = temp.multiply(new BigInteger("2"));
            result = result.add(BigInteger.ONE);
        }
        return result;
    }

    public static void main(String[] args) {
        BigInteger primeNumber, randomNumber, quotient, oddNumberM;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number to test its primality");
        primeNumber = scanner.nextBigInteger();
        scanner.close();

        if (primeNumber.mod(new BigInteger("2")).compareTo(BigInteger.ZERO) > 0) {
            // @TODO Should write a logic to auto-generate it
            // Find b
            randomNumber = new BigInteger("2");
            quotient = new BigInteger("0");
            oddNumberM = new BigInteger("0");

            BigInteger tempPrimeNumber = primeNumber.subtract(new BigInteger("1"));
            
            // Find q and m as (n-1 = (2^q)*m)
            // This is causing oddNumberM to be very large and causing overflow.
            for (BigInteger bi = BigInteger.valueOf(1); bi.compareTo(tempPrimeNumber) < 0; bi = bi.add(BigInteger.ONE)) {
                BigInteger power = RabinMillerAssign.pow(randomNumber, bi);
                BigInteger mod = tempPrimeNumber.mod(power);
                if (mod.compareTo(BigInteger.ZERO) == 0) {
                    if (tempPrimeNumber.divide(power).mod(new BigInteger("2")).compareTo(BigInteger.ZERO) > 0) {
                        quotient = bi;
                        oddNumberM = tempPrimeNumber.divide(power);
                        break;
                    }
                }
            }

            // Find q and m as (n-1 = (2^q)*m)
            /* Will try to have largest q possible, but this is also not doing wonders.
            for (BigInteger bi = RabinMillerAssign.log2(tempPrimeNumber); bi.compareTo(BigInteger.ZERO) > 0; bi = bi.divide(new BigInteger("2"))) {
                System.out.println(bi);
                BigInteger power = RabinMillerAssign.pow(randomNumber, bi);
                BigInteger mod = tempPrimeNumber.mod(power);
                if (mod.compareTo(BigInteger.ZERO) == 0) {
                    if (tempPrimeNumber.divide(power).mod(new BigInteger("2")).compareTo(BigInteger.ZERO) > 0) {
                        quotient = bi;
                        oddNumberM = tempPrimeNumber.divide(power);            
                        break;
                    }
                }
            }*/

            // Check Rabin Miller Case 1
            // System.out.println("First Criteria: b^m = 1(mod n)");
            boolean firstCriterionFlag = true;
            if (randomNumber.modPow(oddNumberM, primeNumber).compareTo(BigInteger.ONE) != 0) {
                firstCriterionFlag = false;
            }

            // I amn't sure about the importance of implementing second case, when first one is giving correct output
            // Check Rabin Miller Case 2
            // System.out.println("Second Criteria: There Exists i in [0, q-1] such that b^((2^i) m)= -1(mod n)");
            boolean secondCriterionFlag = true;
            for (BigInteger bi = BigInteger.valueOf(0); bi.compareTo(quotient) < 0; bi = bi.add(BigInteger.ONE)) {
                BigInteger innerMostBracket = RabinMillerAssign.pow(new BigInteger("2"), bi);
                BigInteger innerBracket = innerMostBracket.multiply(oddNumberM);
                if (randomNumber.modPow(innerBracket, primeNumber).compareTo(tempPrimeNumber) != 0){
                    secondCriterionFlag = false;
                    break;
                };
            }
            
            if (firstCriterionFlag || secondCriterionFlag) {
                System.out.println("This Number looks Prime");
            } else {
                System.out.println("This Number not looks Prime");
            }
        } else {
            System.out.println("How, An even number can be prime");
        }
    }
}