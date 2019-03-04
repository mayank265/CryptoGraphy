import java.math.BigInteger;

class KeyScheduling {
  String  key = "FFFFFFFFFFFFFFFF";
  String keyAfterPC1 = "";

  public BigInteger getDecimal() {
    return new BigInteger(key ,16);
  }

  public String getBinary() {
    return new BigInteger(key ,16).toString(2);
  }

  // Remove last two columns of the key
  public String keyPermutation1() {
    String key = getBinary();
    String keyAfterPC1 = "";
    for (int tviKey = 0 ; tviKey < key.length(); tviKey++) {
      if (((tviKey+1) % 8 != 0) && ((tviKey+2) % 8 != 0)) {
        keyAfterPC1 = keyAfterPC1.concat(Character.toString(key.charAt(tviKey)));
      }
    }
    this.keyAfterPC1 = keyAfterPC1;
    return this.keyAfterPC1;
  }
}

class DESModified {

}

public class DES {
  public static void main(String []args) {
    KeyScheduling keyScheduling = new KeyScheduling();
    System.out.println(keyScheduling.getBinary());
    System.out.println(keyScheduling.keyPermutation1());
  }
}