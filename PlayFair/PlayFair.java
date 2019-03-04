// Harsh Kasyap
// 1921CS01

import java.util.*;

class PlayFairCrypto {

  String cryptoText;
  ArrayList<String> cryptoList;

  String textToAttack = "ZFLQMVOBSIDVSTMSMSAXYNAXXLQITBYZYWGVKIXLNGVDULKQ OPOBQVKVIMUAILYNPCVWCOSMGCXYNUXZXLOIULIKMTUDWOV ONUSCIXSLKSVWLFAXYAMSMSAXYNAXXLOIULIPCYZFFLCZTCYC CGYBQVMVBOVNYHMVOBSIDVSTLFYZIQOCHWCFIPUDWOVOGLHUHWCFILHMEXEKSCKTOBDMNUGMCTXWOCFXWXLPLSNUGIDAV NMZIPCYOINUIQXLQOSLXZXLWOVNYHMVOBSIDVSTLFYZIQZFFLF CWRKQIBCYKQIPLTMVOBSIKOKOKVOCPITBYUAEOKIQMSIEVKAL DVGXXLQOZFVKQVXLIFKIQVOVEISCIOEMLBIMTXVKXIKIZFOCDX LFYZIQUDNUDOGXOMCTOVEWOCFNXTCYSMGTQLKVAXYAEMBO NORGFLZFMIQLIOOCXNCTCBSLSAUHZDIQKILOVEIQTCKCIBNBPIO MCTVCTXOLBKCTMIPLMZVLXLIFDVRDOKCTVCTXOLBKNCWVZFV KCBXZZFOBVDCFXLWIEIXLNYGVOINUOKNIBUZYWYMLKSVWLFA XLVHUUNOYLSDUWGQLTZIMWGOIXZPLUHZBZBSTCYZHWZKWBZ YBZFVKOCIPFNGOUNOIULVQVKLKVNMUFUUAILKITZPIHCZKLBEA CFPLOTOLGXYBZFVKOCILOVEIFQVKWGYAOLBKCTMIPLKUCFMSL BGWMOXTSLKIXLPLOTYAVNLOVEVQVKGWNGWNFUGLBUSMSLX TQKOBXYWNOPKBTZIMUAIPCYSMIQQVYUCXZYCLHQSLAXLNZYB AMBFYOLGXVNSYWACYILCZXZXLDOEIKINULXIVFRLBDVCYFOCF ZFFLMSWVSZEKFCXSOBDMOLLYLPKIKILPNFKVFLEKMZWGOLBK WLCZCYLDWLBANUQVUYKIMCMITMVNNUSMOVEIWLNRCLLTVW KIQIUYYBZFHNIPXMFUSLOVEIWLAHVWIEVKNUGCIWFLPLUZWNG LKRLBKVVCTXKVKIKCVKWLHZULIKIPCYZFOPOBSCIXXLGLZFVKT XVWLITCKVNCVNLYVELBCGGVKXIVTEOPOBHCZQBTTKYWTXVW MLTDUYHYIWZFFLYWUNKIMILVZDBVCBSYLXQVMUFYITKIWLAZ DVIMICMTMSOCYWTXVWKAODUKLXQVKRBYVBSKLXQVMVIDIW OCHNZQVKOKLSFZQVTXMDWQLPBZWLCZZASEFGWOVOKLICIWV DCFFYYNMTOVEISCWYLXQVKUAZZDVQCFFYYNMTAECYIQKIGLK YATFLQVMHLBZFXIGWMOCYGIOCQOEIKWZFOCFXWXCLUCNARQ VKOVEIIEOBLDLSCMAXMEOTOLGXYWPIYTLSXWXZXLZFKEVLAX RGVKILYNTXKWOCDXVGUYFSFUSLNWAVSILFURLQVWFCMTYNI MZYMLGXFUMHOBVNHQWIZFVKSMGCXZZFOCMPWNFUXYCTMSV LGXRKWGDXVLYNEYIQBZXLYWIMTXVKZMWOIWWIZFVKRGFLYN IMGIOCXNWVGXRGMFWGLAVNOVEIKINUWYVWIKLXQVMVCYXI WZKWGLYZQVDLMZXZSLKIOCFYWGFXMCILNAZAFYYAICLOIQCB XZZFFLCOHQSLGLWHLXXLAILBUBXEBOLGZFVKWOIWICLXQVKU LBIYVBAXLOMDVNIFAXMILFCGBZWIZFVKGIWLAVVEIKOBWNFUE IMCCFVBTQVWIEVKALDVGXVDULIQWGOKFRNIAVGCLAVNIXOPL PFUSLOVEIFMVNFYYFOVGXWIZFVKRGWT";
  String knownPlainText = "thefriendshipsididntwanttodepartmyhometownihavebeenheresinceiwasbornididn twanttoleavemyschoolandmostimportantlyididntwanttoleavesamthatussamandka herineourfriendshipstartedingradeschoolfourgradeidroppedmypenciandwasgoing togetitandofcoursesamleanedtogetittooourfriendshipstartedthatdaywebecamebest friendswewereinseparablewedideverythingtogetherhetoldmehelovedmelikeasiste rsomethingstartedchangingiwaslookingatsamdifferentlyiknewwhatthisfeelingwa sbutitcrushedmeilovedsambecauseiwasjustlikeasistersoitoldhimhewasjustlikeabr otherbutthenihadtomovetoawholeanewcountrymyimportantjournalwithconfessio nletterupupupsamhurryupkatherinesgannaleavehereyourchanceimesseduprealba dtellingkatherineilovedheronlylikeasisterbadideanowitstimetotellyouiloveherno wnowhafoundititsbeentwoweekssincesamdiedheranstraightintoatruckcallingout mynamejusttogivemeanoteohyeahiamgladthatidroppedatpencilitletmemeetagreat personlikeyousamifyoucanhearmeimissyouandiloveyouwaitformedearkathguess whatiloveyouforeverandnomatterhowforwearejustrememberyouhavemesamthee ndmostofotherstoriesareaboutloveandhowpeopleenduphappymystoryisfarfromthatmynameisjoshuebutmothercallsmeyouthisismysidinmystorybigbrotherwakeup motheriscomingupherewiththestickgetupyoustupidfoolyesmomihadalwaysloved mymotherbutshehadalwaysblamedmeformyfatherdeathsonowiamdoingeverythin gtogainbackherloveevenifitmintkillingmyselfitgottothepointwhereiwastryingsoh ardthatiwouldstarvefordayswasitryinghardenoughmotherdidntthinksowhatwasid oingwrongsoiwalkeduptomysistersroommotherwhatwasidoingwrongwhydontyo ulovemeanymoremotheriamsorryforthefirsttimeinalongtimeiactuallysmiledbutth atnightiforgottocleanuponeoftheroomsmotherbealmeuntilicouldntistandupmothe rdoyoulovemenowhaveimadeupforeverythingihavedonewhycouldntyouoseethati lovedyouallalongmotherwhy";

  PlayFairCrypto (String cryptoText) {
    cryptoList = new ArrayList<String>();
    char[] alphabet = "abcdefghiklmnopqrstuvwxyz".toUpperCase().toCharArray();
    for(char c: alphabet) {
      cryptoList.add(Character.toString(c));
    }

    cryptoText = cryptoText.toUpperCase();
    ArrayList<String> tCryptoText = new ArrayList<String>();
    this.cryptoText = cryptoText;
    for (int i = cryptoText.length() -1 ; i > -1; i--){
      char c = cryptoText.charAt(i);
      String tC = Character.toString(c);

      int cIndex = tCryptoText.indexOf(tC);
      if (cIndex != -1) {
        System.out.println("Looks like you have used a character two times in your CRYPTO... Please try again!");
        System.exit(0);
      }
      if (tC.equals("I") || tC.equals("J")) {
        System.out.println("Not Supporting I and J in CRYPTO... Please try again!");
        System.exit(0);
      }
      tCryptoText.add(tC);

      try {
        int index = cryptoList.indexOf(tC);
        cryptoList.remove(index);
        cryptoList.add(0, tC);
      } catch (Exception e) {
        System.out.println("Looks like some issue with Crypto Text.. Please try again " + e.toString());
        System.exit(0);
      }
    }
    System.out.println(cryptoList);
  }

  void encryptText (String plainText) {
    // Prepare pairs of two
    plainText = plainText.toUpperCase();
    plainText = plainText.replaceAll("\\s+","");
    ArrayList<String> plainArr = new ArrayList<String>();
    for (int i = 0; i < plainText.length(); i++){
      String pC = Character.toString(plainText.charAt(i));
      if (pC.equals("J")) {
        pC = "I";
      }
      String nPC = null;
      if (i < plainText.length() - 1) {
        nPC = Character.toString(plainText.charAt(i+1));
        if (nPC.equals("J")) {
          nPC = "I";
        }
      }
      if (nPC != null && !nPC.equals(pC)){
        plainArr.add(pC.concat(nPC));
        i++;
      } else {
        plainArr.add(pC.concat("X"));
      }
    }
    
    // Find Encrypted Text
    ArrayList<String> cipherArr = new ArrayList<String>();
    for (String pT: plainArr) {
      int index1 = cryptoList.indexOf(Character.toString(pT.charAt(0)));
      int index2 = cryptoList.indexOf(Character.toString(pT.charAt(1)));

      int row1 = index1 / 5;
      int col1 = index1 % 5;

      int row2 = index2 / 5;
      int col2 = index2 % 5;
      
      if (row1 == row2) {
        int cIndex1 = (row1*5) + ((col1 + 1)%5);
        int cIndex2 = (row2*5) + ((col2 + 1)%5);
        cipherArr.add(cryptoList.get(cIndex1).concat(cryptoList.get(cIndex2)));
      } else if (col1 == col2) {
        int cIndex1 = (((row1+1)*5)%25) + (col1);
        int cIndex2 = (((row2+1)*5)%25) + (col2);
        cipherArr.add(cryptoList.get(cIndex1).concat(cryptoList.get(cIndex2)));
      } else {
        int cIndex1 = (row1*5) + (col2);
        int cIndex2 = (row2*5) + (col1);
        cipherArr.add(cryptoList.get(cIndex1).concat(cryptoList.get(cIndex2)));
      }
    }

    System.out.println("\n********************");
    System.out.println("Result after Encryption " + plainText + " Using PlayFair Cipher: ");
    System.out.println(cipherArr);
    for (String cT: cipherArr) {
      System.out.print(cT);
    }
    System.out.println("\n********************\n");
  }

  void attack() {
    LinkedHashMap<String, Double> commonDiagrams = new LinkedHashMap<String, Double>();
    commonDiagrams.put("ST", 1.22);
    commonDiagrams.put("TI", 1.28);
    commonDiagrams.put("OR", 1.28);
    commonDiagrams.put("TE", 1.30);
    commonDiagrams.put("ED", 1.32);
    commonDiagrams.put("ES", 1.36);
    commonDiagrams.put("AT", 1.51);
    commonDiagrams.put("EN", 1.53);
    commonDiagrams.put("AN", 1.81);
    commonDiagrams.put("ON", 1.83);
    commonDiagrams.put("RE", 1.90);
    commonDiagrams.put("ER", 2.13);
    commonDiagrams.put("IN", 2.30);
    commonDiagrams.put("HE", 3.05);
    commonDiagrams.put("TH", 3.21);

    ArrayList<String> commonDiagramsKeys = new ArrayList(commonDiagrams.keySet());

    // Prepare pairs of two
    String cipherText = textToAttack;
    cipherText = cipherText.toUpperCase();
    cipherText = cipherText.replaceAll("\\s+","");
    LinkedHashMap<String, Integer> cipherArr = new LinkedHashMap<String, Integer>();
    ArrayList<String> cipherList = new ArrayList<String>();
    for (int i = 0; i < cipherText.length(); i++){
      String cT = Character.toString(cipherText.charAt(i));
      if (cT.equals("J")) {
        cT = "I";
      }
      String nCT = null;
      if (i < cipherText.length() - 1) {
        nCT = Character.toString(cipherText.charAt(i+1));
        if (nCT.equals("J")) {
          nCT = "I";
        }
      }
      int count = 1;
      if (nCT != null && !nCT.equals(cT)){
        cT = cT.concat(nCT);
        if (cipherArr.containsKey(cT)) {
          count = cipherArr.get(cT);
          count = count + 1; 
        }
        cipherArr.put(cT, count);
        cipherList.add(cT);
        i++;
      } else {
        cT = cT.concat("X");
        if (cipherArr.containsKey(cT)) {
          count = cipherArr.get(cT);
          count = count + 1;
        }
        cipherArr.put(cT, count);
        cipherList.add(cT);
      }
    }

    int minValue = Collections.min(cipherArr.values());
    int maxValue = Collections.max(cipherArr.values());
    maxValue += 1;
    
    double rangeFactor = ((double)maxValue - (double)minValue) / (double)commonDiagrams.size();
    rangeFactor += 0.000000001;

    ArrayList<String> decryptedText = new ArrayList<String>();
    for (String key : cipherList) {
      int value = cipherArr.get(key);
      value = (int)((double)value / rangeFactor);
      decryptedText.add(commonDiagramsKeys.get(value));
    }

    System.out.println("\n********************");
    System.out.println("Result after Decryption Using Diagrams Frequency Analysis (Refer PlainText in the Code)");
    for (String pT: decryptedText) {
      System.out.print(pT);
    }
    System.out.println("\n********************\n");
  }

}

public class PlayFair {
  public static void main(String []args) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Enter the crypto Text");
    String cryptoText = scanner.nextLine();
    PlayFairCrypto playFairCrypto = new PlayFairCrypto(cryptoText);

    while (true) {
      try {
        System.out.println("1. Enter the plaintext to be encrypted \n2. Test an attack on PlayFair Cipher \n3. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            System.out.println("Enter the plainText");
            playFairCrypto.encryptText(scanner.nextLine());
            break;
          case 2:
            System.out.println("Test an attack on a given cipher text");
            playFairCrypto.attack();
            break;
          case 3: 
            scanner.close();
            System.exit(0);
        }
      } catch (Exception e) {
        System.out.println("Looks some issue... Please try again " + e.toString());
        scanner.next();
      }
    }
  }
}