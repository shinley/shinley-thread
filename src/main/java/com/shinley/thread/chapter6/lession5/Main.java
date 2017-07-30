package com.shinley.thread.chapter6.lession5;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Java API 也提供了这个接口的实现类，这个类是ConcurrentSkipListMap，它实现了非阻塞列表且拥有ConcurrentNavigableMap的行为。
 * 在内部实现中，它使用Skip List来存储数据。Skip List是基于并行列表的数据结构，它允许我们获取类似二叉树的效率。
 * 使用它，你可以得到一个排序的数据结构，这比排序数列使用更短的访问时间来插入、搜索和删除元素。
 */
public class Main {
    public static void main(String[] args) {
        ConcurrentSkipListMap<String, Contact> map = new ConcurrentSkipListMap<>();
        Thread threads[] = new Thread[25];
        int counter = 0;
        for (char i = 'A'; i < 'Z'; i++) {
            Task task = new Task(map, String.valueOf(i));
            threads[counter] = new Thread(task);
            threads[counter].start();
            counter++;
        }

        // 使用join等待线程结束
        for (int i = 0; i < 25; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 取第一个数据
        System.out.printf("Main: Size of the map: %d\n", map.size());
        Map.Entry<String, Contact> element = map.firstEntry();
        Contact contact = element.getValue();
        System.out.printf("Main: First Entry: %s: %s\n", contact.getName(), contact.getPhone());

        // 取最后一个数据
        element = map.lastEntry();
        contact = element.getValue();
        System.out.printf("Main: Last Entry: %s: %s\n", contact.getName(), contact.getPhone());

        // subMap()方法返回map的部分元素的ConcurrentNavigableMap对象。在这个例子中，元素拥有A1996到B1002之间的key。
        // 在这种情况下，你可以使用pollFirst()方法来处理subMap()方法返回的这些元素。这个方法将返回并删除submap中的第一个Map.Entry对象。
        System.out.printf("Main: Submap from A1996 to B1002: \n");
        ConcurrentNavigableMap<String, Contact> submap = map.subMap("A1996", "B1002");
        do {
            element = submap.pollFirstEntry();
            if (element != null) {
                contact = element.getValue();
                System.out.printf("%s: %s\n", contact.getName(), contact.getPhone());
            }
        } while (element != null);
    }
}