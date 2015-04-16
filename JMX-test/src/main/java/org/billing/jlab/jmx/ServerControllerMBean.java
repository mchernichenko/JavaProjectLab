package org.billing.jlab.jmx;

/**
 * Created by Mikhail.Chernichenko on 16.04.2015.
 */
public interface ServerControllerMBean
{
    public int getCorePoolSize();
    public void setCorePoolSize(int corePoolSize);
    public int getMaxPoolSize();
    public void setMaxPoolSize(int maxPoolSize);
    public int getRejectedCount();
    public int getActiveThreads();
    public int getPassiveThreads();
    public int getTotalThreads();
    public void flushRejected();
}
