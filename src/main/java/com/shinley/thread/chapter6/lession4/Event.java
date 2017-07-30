package com.shinley.thread.chapter6.lession4;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 我们已实现Event类。这个类只有一个属性（表示事件的激活日期），
 * 实现了Delayed接口，所以，你可以在DelayedQueue类中存储Event对象。
 */
public class Event implements Delayed {

    private Date startDate;

    public Event(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * getDelay()方法返回在实际日期和激活日期之间的纳秒数。
     * 这两个日期都是Date类的对象。
     */
    @Override
    public long getDelay(TimeUnit unit) {
        Date now = new Date();
        long diff = startDate.getTime() - now.getTime();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        }
        return 0;
    }
}
