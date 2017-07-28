package com.shinley.thread.chapter5.lession2;

import java.util.Random;

/**
 * Created by shinley on 17-7-28.
 */
public class Document {
    private String words[] = {"the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main"};

    // 根据words数组的长度,随机生成一个索引位置,从words数组中取出,放入document二维数组中.
    // 如果从words数组中取出的字符串等于指定的字符串, counter自增记录数量
    public String[][] generateDocument(int numLines, int numWords, String word) {
        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random random = new Random();
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (document[i][j].equals(word)) {
                    counter++;
                }
            }
        }
        System.out.println("DocumentMock: The word appears " + counter + " times in the document");
        return document;
    }
}
