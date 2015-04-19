package org.billing.jlab.jmx;

/**
 * MXBean является новым типом MBean, который обеспечивает простой способ кодировать MBean
 * Объявляется аналогично стандартному MBean
 */

@Author("Mr Bean")
@Version("1.0")
public interface QueueSamplerMXBean {
    @DisplayName("GETTER: QueueSample")
    public QueueSample getQueueSample();

    @DisplayName("OPERATION: clearQueue")
    public void clearQueue();
}
