package CRYPTO;

import java.util.HashMap;

/**
 * Created by Rathinakumar on 2/15/2015.
 */
public class VigenereAnalysis {
    public static void main(String[] args) {
        String cipherText = "hdsfgvmkoowafweetcmfthskucaqbilgjofmaqlgspvatvxqbiryscpcfrmvswrvnqlszdmgaoqsakmlupsqforvtwvdfcjzvgsoaoqsacjkbrsevbelvbksarlscdcaarmnvrysywxqgvellcyluwwveoafgclazowafojdlhssfiksepsoywxafowlbfcsocylngqsyzxgjbmlvgrggokgfgmhlmejabsjvgmlnrvqzcrggcrghgeupcyfgtydycjkhqluhgxgzovqswpdvbwsffsenbxapasgazmyuhgsfhmftayjxmwznrsofrsoaopgauaaarmftqsmahvqecev";
        int N = cipherText.length();

        int keyLength = determineKeyLength(cipherText);

        System.out.println(" Most Probable key lenght = " + keyLength);


        //frequency of english letters
        HashMap<Character, Double> englishFreq = new HashMap<Character, Double>();
        englishFreq.put('a', 0.08167);
        englishFreq.put('b', 0.01492);
        englishFreq.put('c', 0.02782);
        englishFreq.put('d', 0.04253);
        englishFreq.put('e', 0.12702);
        englishFreq.put('f', 0.02228);
        englishFreq.put('g', 0.02015);
        englishFreq.put('h', 0.06094);
        englishFreq.put('i', 0.06966);
        englishFreq.put('j', 0.00153);
        englishFreq.put('k', 0.00772);
        englishFreq.put('l', 0.04025);
        englishFreq.put('m', 0.02406);
        englishFreq.put('n', 0.06749);
        englishFreq.put('o', 0.07507);
        englishFreq.put('p', 0.01929);
        englishFreq.put('q', 0.00095);
        englishFreq.put('r', 0.05987);
        englishFreq.put('s', 0.06327);
        englishFreq.put('t', 0.09056);
        englishFreq.put('u', 0.02758);
        englishFreq.put('v', 0.00978);
        englishFreq.put('w', 0.02360);
        englishFreq.put('x', 0.00150);
        englishFreq.put('y', 0.01974);
        englishFreq.put('z', 0.00074);
        englishFreq.put('e', 0.12700);
        englishFreq.put('t', 0.09056);
        String key = "";
        //determines the char at each index
        for (int i = 0; i < keyLength; i++) {
            int totalChar = 0;
            HashMap<Character, Integer> countVector = new HashMap<Character, Integer>();
            int j = i;

            while (j < N) {
                totalChar++;
                char c = cipherText.charAt(j);
                if (!countVector.containsKey(c))
                    countVector.put(c, 0);
                countVector.put(c, countVector.get(c) + 1);
                j += keyLength;
            }


            //calculate cipher text frequncy vector from countVector (by dividing the count by total char)
            HashMap<Character, Double> cipherFreq = new HashMap<Character, Double>();
            for (char c : countVector.keySet()) {
                double d = (double) countVector.get(c) / totalChar;
                cipherFreq.put(c, d);
            }

            key = key + getProbableShiftChar(cipherFreq, englishFreq);
        }
        System.out.println(" KEY = " + key);

        System.out.println(" Decrypted Message : \n\t " + decrypt(cipherText, key));

    }

    public static int determineKeyLength(String cipherText) {
        int keyLength = 0, maxCount = 0;
        int N = cipherText.length();
        /**
         * based on char frequency determine the key length
         */
        for (int len = 2; len <= 6; len++) {
            int count = 0;
            for (int i = 0; i < N - len; i++) {
                if (cipherText.charAt(i) == cipherText.charAt(i + len))
                    count++;
            }
            if (maxCount < count) {
                maxCount = count;
                keyLength = len;
            }
        }
        return keyLength;
    }

    public static char getProbableShiftChar(HashMap<Character, Double> cipherFreq, HashMap<Character, Double> englishFreq) {
        double maxProduct = 0.0;
        char probableShiftChar = 'a';
        for (int sh = 1; sh < 26; sh++) {
            double product = 0.0;
            for (char c : cipherFreq.keySet()) {
                int shiftedChar = ((c - 'a') + sh) % 26 + 'a';
                //System.out.println(c+ " Shifted char = "+(char)shiftedChar);
                product += (cipherFreq.get(c) * (englishFreq.get((char) shiftedChar)));
            }
            if (product > maxProduct) {
                maxProduct = product;
                probableShiftChar = (char) ('a' + sh);
            }
        }
        return probableShiftChar;
    }


    public static String decrypt(String cipherText, String key) {
        int N = cipherText.length();
        String encryptedText = "";
        for (int i = 0; i < N; i++) {
            int shiftIndex = key.charAt(i % key.length()) - 'a';
            int shiftedChar = ((cipherText.charAt(i) - 'a') + shiftIndex) % 26 + 'a';
            char encryptedChar = (char) (shiftedChar);
            encryptedText += encryptedChar;
        }
        return encryptedText;
    }
}
