package org.billing.jlab.camel.ch1;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Копирование файлов с использованием Apache Camel
 */

public class FileCopierWithCamel {

    public static void main(String args[]) throws Exception {
        // create CamelContext
        CamelContext context = new DefaultCamelContext();

        /* add our route to the CamelContext
          noop=true - говорит Camel оставить исходный файл как есть, иначе будет перемещение файла
         */
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:camel-ch1/data/inbox?noop=true").to("file:camel-ch1/data/outbox");
            }
        });

        // start the route and let it do its work
        context.start();
        Thread.sleep(10000); //10сек

        // stop the CamelContext
        context.stop();
    }
}
