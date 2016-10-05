package com.itis.androidlab.contentprovider.utils;

import java.util.Random;

/**
 * @author Valiev Timur.
 */

public class AndroidUtils {
    public static String generateRandomWord() {
        String randomString;
        Random random = new Random();
        char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        randomString = new String(word);
        return randomString;
    }
}
