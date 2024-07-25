package ru.dragonScale;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import static ru.dragonScale.GenerateText.generateText;
import static ru.dragonScale.MaxCount.maxCount_a;

public class Main {
    private static final int SIZE_QUEUE = 100;
    private static final int SIZE_TEXT = 100_000;
    protected static final int COUNT_STRING = 10_000;
    private static final ArrayBlockingQueue<String> queue1 = new ArrayBlockingQueue<>(SIZE_QUEUE);
    private static final ArrayBlockingQueue<String> queue2 = new ArrayBlockingQueue<>(SIZE_QUEUE);
    private static final ArrayBlockingQueue<String> queue3 = new ArrayBlockingQueue<>(SIZE_QUEUE);

    public static void main(String[] args) {
        Random random = new Random();

        new Thread(() -> {
            for (int i = 0; i < COUNT_STRING; i++) {
                try {
                    queue1.put(generateText("abc", SIZE_TEXT));
                    queue2.put(generateText("abc", SIZE_TEXT));
                    queue3.put(generateText("abc", SIZE_TEXT));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Thread maxCount_a = new Thread(() -> {
            int max = maxCount_a(queue1, 'a');
            System.out.println("Максимальное кол-во символов 'a'- " + max);
        });
        Thread maxCount_b = new Thread(() -> {
            int max = maxCount_a(queue2, 'b');
            System.out.println("Максимальное кол-во символов 'b'- " + max);
        });

        Thread maxCount_c = new Thread(() -> {
            int max = maxCount_a(queue3, 'c');
            System.out.println("Максимальное кол-во символов 'c'- " + max);
        });
        maxCount_a.start();
        maxCount_b.start();
        maxCount_c.start();

        try {
            maxCount_a.join();
            maxCount_b.join();
            maxCount_c.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
