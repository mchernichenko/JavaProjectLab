package org.billing.jlab.jmx;

import javax.management.DescriptorKey;
import java.lang.annotation.*;

/**
 *
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
    @DescriptorKey("author")
    String value();
}
