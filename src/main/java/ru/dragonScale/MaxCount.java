package ru.dragonScale;

import java.util.concurrent.ArrayBlockingQueue;

import static ru.dragonScale.Main.COUNT_STRING;


public class MaxCount {

    public static int maxCount_a(ArrayBlockingQueue<String> queue, char ch) {
        int count = 0;
        int max = 0;
        String text;
        try {
            for (int i = 0; i < COUNT_STRING; i++) {
                text = queue.take();
                for (char c : text.toCharArray()) {
                    if ((c == ch)) {
                        count++;
                    }
                }
                if (count > max) {
                    max = count;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return max;
    }

}
