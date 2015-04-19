package org.billing.jlab.jmx;

import java.util.Date;
import java.util.Queue;

/**
 *
 */
public class QueueSampler implements QueueSamplerMXBean {

    private Queue<String> queue;

    public QueueSampler(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void clearQueue() {
        synchronized (queue) {
            queue.clear();
        }
    }

    @Override
    public QueueSample getQueueSample() {
        synchronized (queue) {
            return new QueueSample(new Date(), queue.size(), queue.peek());
        }
    }
}
