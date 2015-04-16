package org.billing.jlab.jmx;

import javax.management.*;
import java.lang.management.*;

/**
 *
 */
public class Agent {
    public static void main(String[] args) throws Exception {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("com.example.mbeans:type=Hello");

        HelloJmx mbean = new HelloJmx();

        mbs.registerMBean(mbean, name);

        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
