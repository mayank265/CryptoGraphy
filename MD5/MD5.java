import java.math.BigInteger;
import java.nio.file.*;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

class MD5 {
  public static String getMd5(byte bArr[]) 
  { 
    try { 
      MessageDigest md = MessageDigest.getInstance("MD5"); 
      byte[] messageDigest = md.digest(bArr); 
      BigInteger no = new BigInteger(1, messageDigest); 
      String hashtext = no.toString(16); 
      while (hashtext.length() < 32) { 
          hashtext = "0" + hashtext; 
      } 
      return hashtext; 
    }  
    catch (NoSuchAlgorithmException e) { 
      throw new RuntimeException(e); 
    } 
  }

  public static byte[] flipOneBit(byte[] bArr)  {
    String s1 = String.format("%8s", Integer.toBinaryString(bArr[0] & 0xFF)).replace(' ', '0');
    if (s1.charAt(7) == '0')
      s1 = s1.substring(0,7) + '1';
    else
      s1 = s1.substring(0,7) + '0';
    bArr[0] = Byte.parseByte(s1, 2);
    return bArr;
  }

  public static void main(String []args) throws Exception {
    String data = new String(Files.readAllBytes(Paths.get("input.txt")));
    byte[] bArr = data.getBytes();
    String orgHash = getMd5(bArr);
    System.out.println("MD5 Hash Of Original Text File:                    " + orgHash);
    flipOneBit(bArr);
    String flipHash = getMd5(bArr);
    int diffBytes = 0;
    for (int i = 0; i < orgHash.length(); i++) {
      if (orgHash.charAt(i) == orgHash.charAt(i)) {
        diffBytes ++;
      }
    }
    System.out.println("MD5 Hash after Flipping One bit of Same Text File: " + flipHash);
    System.out.println("No of Bytes Diffference in these two Hashes:       " + diffBytes);
  }
}