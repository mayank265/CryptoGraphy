import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

class KeyScheduling {
  // Remove last two columns of the key and outputs 48 bit key
  public String keyPermutation1(String hexKey) {
    String key = new BigInteger(hexKey ,16).toString(2);
    String keyAfterPC1 = "";
    for (int tviKey = 0 ; tviKey < key.length(); tviKey++) {
      if (((tviKey+1) % 8 != 0) && ((tviKey+2) % 8 != 0)) {
        keyAfterPC1 = keyAfterPC1.concat(Character.toString(key.charAt(tviKey)));
      }
    }

    return keyAfterPC1;
  }

  // Remove last two columns of the key and outputs 32 bit key
  public String keyPermutation2(String key) {
    String keyAfterPC2 = "";
    for (int tviKey = 0 ; tviKey < key.length(); tviKey++) {
      if (((tviKey+1) % 6 != 0) && ((tviKey+2) % 6 != 0)) {
        keyAfterPC2 = keyAfterPC2.concat(Character.toString(key.charAt(tviKey)));
      }
    }

    return keyAfterPC2;
  }

  // Left shift by spliting into 24 bits;
  public String leftShift(String key, int round) {
    String leftKey = key.substring(0, 24);
    String rightKey = key.substring(24);
    round = round + 1;
    if (round == 1 || round == 2 || round == 9 || round == 16) {
      leftKey = leftKey.substring(1).concat("0");
      rightKey = rightKey.substring(1).concat("0");
    } else {
      leftKey = leftKey.substring(2).concat("00");
      rightKey = rightKey.substring(2).concat("00");
    }

    return leftKey.concat(rightKey);
  }

  // Returns Key after left shift a 48 bit key
  public String getKey(String key, int round) {
    if (round == 0) {
      key = leftShift(keyPermutation1(key), round);
    } else {
      key = leftShift(key, round);
    }
    return key;
  }
}

class DESModified {
  int[] IP={
    58,50,42,34,26,18,10,2,
    60,52,44,36,28,20,12,4,
    62,54,46,38,30,22,14,6,
    64,56,48,40,32,24,16,8,
    57,49,41,33,25,17,9,1,
    59,51,43,35,27,19,11,3,
    61,53,45,37,29,21,13,5,
    63,55,47,39,31,23,15,7};
  int[] IPinverse={
    40,8,48,16,56,24,64,32,
    39,7,47,15,55,23,63,31,
    38,6,46,14,54,22,62,30,
    37,5,45,13,53,21,61,29,
    36,4,44,12,52,20,60,28,
    35,3,43,11,51,19,59,27,
    34,2,42,10,50,18,58,26,
    33,1,41,9,49,17,57,25
  };

  int[] PermutationBox={
    17,28,12,29,21,20,7,16,
    1,15,23,26,5,18,31,10,
    25,4,11,22,6,30,13,19,
    2,8,24,14,32,27,3,9
  };

  HashMap<String, String> sBox = new HashMap<String, String>();

  public DESModified() {
    // Fill SBox Table
    sBox.put("0000", "1110");
    sBox.put("0001", "0100");
    sBox.put("0010", "1101");
    sBox.put("0011", "0001");
    sBox.put("0100", "0010");
    sBox.put("0101", "1111");
    sBox.put("0110", "1011");
    sBox.put("0111", "1000");
    sBox.put("1000", "0011");
    sBox.put("1001", "1010");
    sBox.put("1010", "0110");
    sBox.put("1011", "1100");
    sBox.put("1100", "0101");
    sBox.put("1101", "1001");
    sBox.put("1110", "0000");
    sBox.put("1111", "0111");
  }

  public String initialPermutaion(String plainText) {
    String textAfterInitialPC = "";
    for (int tvindex : IP) {
      textAfterInitialPC = textAfterInitialPC.concat(Character.toString(plainText.charAt(tvindex-1)));
    }
    return textAfterInitialPC;
  }

  public String finalPermutaion(String plainText) {
    String textAfterFinalPC = "";
    for (int tvindex : IPinverse) {
      textAfterFinalPC = textAfterFinalPC.concat(Character.toString(plainText.charAt(tvindex-1)));
    }
    return textAfterFinalPC;
  }

  public String XOR(String rightPlainText, String key) {
    String textAfterXOR = "";
    for (int tviKey = 0 ; tviKey < key.length(); tviKey++) {
      if (rightPlainText.charAt(tviKey) != key.charAt(tviKey)) {
        textAfterXOR = textAfterXOR.concat("1");
      } else {
        textAfterXOR = textAfterXOR.concat("0");
      }
    }
    return textAfterXOR;
  }

  // Option 0 for encrypt andd Option 1 for decrypt;
  public String DESEncryption(String plainText, String key, int option) {
    // Initial Permutation
    plainText = initialPermutaion(plainText);

    KeyScheduling keyScheduling = new KeyScheduling();

    // Get all 16 keys
    ArrayList<String> keyArr = new ArrayList<String>();
    for (int index = 0; index < 16; index++) {
      key = keyScheduling.getKey(key, index);
      keyArr.add(key);
    }

    String round12Cipher = "";
    for (int index = 0; index < 16; index++) {
      if (option == 0) {
        key = keyArr.get(index);
      } else {
        key = keyArr.get(15 - index);
      }
    
      String leftText = plainText.substring(0, 32);
      String rightText = plainText.substring(32);
      // F Box Operations
      
      // XOR With KEY
      String rightTextXor = XOR(rightText, keyScheduling.keyPermutation2(key));
      
      // SBOX Operation
      String rightTextSBox = "";
      for (int tviRightText = 0 ; tviRightText < rightTextXor.length(); tviRightText = tviRightText+4) {
        rightTextSBox = rightTextSBox.concat(sBox.get(rightTextXor.substring(tviRightText, tviRightText + 4)));
      }

      // Permutation Operation
      String rightTextAfterPerm = "";
      for (int tvindex : PermutationBox) {
        rightTextAfterPerm = rightTextAfterPerm.concat(Character.toString(rightTextSBox.charAt(tvindex-1)));
      }

      // XOR Operation after F Box Result with Left Text
      String fboxTextXorWithLeftText = XOR(rightTextAfterPerm, leftText);

      // Final PlainText After an round is
      plainText = rightText.concat(fboxTextXorWithLeftText);

      if (option == 0 && (index == 11 || index == 15)) {
        System.out.println("\nIn Round " + (index + 1) + "      :  " + plainText);
        if (index == 11) {
          round12Cipher = plainText;
        } else {
          int count = 0;
          for (int tvindex = 0 ; tvindex < plainText.length(); tvindex++) {
            if (plainText.charAt(tvindex) != round12Cipher.charAt(tvindex)) {
              count++;
            }
          }
          System.out.println("\nAvalanche Effect :  " + count + " differences between 12th and 16th round.");
        }
      }
    }

    // Remaining Half Round, interchange the left and right chunks;
    String leftText = plainText.substring(0, 32);
    String rightText = plainText.substring(32);
    plainText = rightText.concat(leftText);

    // Final Permutation
    String cipherText = finalPermutaion(plainText);
    return cipherText;
  }
  
}

public class DES {
  public static void main(String []args) {
    // Change Plaintext here(16 Hex numbers)
    String plainText = "F0F0F0F0FFFFFFFF";
    // Change Key here(16 Hex numbers)
    String key = "FFFFFFFFFFFFFFFF";

    if (plainText.length() != 16 || key.length() != 16) {
      System.out.println("Looks Like Either of plainText or Key isn't of 16 Hex numbers");
      System.exit(0);
    }

    DESModified desModified = new DESModified();
    System.out.println("\nPlainText        :  "+ plainText);
    System.out.println("\nPlainText(Bin)   :  "+ new BigInteger(plainText ,16).toString(2));
    String cipherText = desModified.DESEncryption(new BigInteger(plainText ,16).toString(2), key, 0);
    System.out.println("\nCipherText       :  "+ new BigInteger(cipherText ,2).toString(16).toUpperCase());
    System.out.println("\nCipherText(Bin)  :  "+ cipherText);
    String decryptedText = desModified.DESEncryption(cipherText, key, 1);
    System.out.println("\nDecryptText      :  "+ new BigInteger(decryptedText ,2).toString(16).toUpperCase());
    System.out.println("\nDecryptText(Bin) :  "+ decryptedText + "\n");
  }
}