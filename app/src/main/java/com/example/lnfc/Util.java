package com.example.lnfc;

import java.util.HashMap;

public class Util {
    public static String encode(String num) {
        String encodedBinaryPattern = "1010";

        char[] numArr = num.toCharArray();
        HashMap<String, String> encodeMap = new HashMap<>();

        encodeMap.put("0", "0010");
        encodeMap.put("1", "0011");
        encodeMap.put("2", "0100");
        encodeMap.put("3", "0101");
        encodeMap.put("4", "0110");
        encodeMap.put("5", "1001");
        encodeMap.put("6", "1010");
        encodeMap.put("7", "1011");
        encodeMap.put("8", "1100");
        encodeMap.put("9", "1101");

        for (char digit : numArr) {
            encodedBinaryPattern += encodeMap.get(String.valueOf(digit));
        }

        encodedBinaryPattern += "01111111111110";

        return encodedBinaryPattern;
    }
}
