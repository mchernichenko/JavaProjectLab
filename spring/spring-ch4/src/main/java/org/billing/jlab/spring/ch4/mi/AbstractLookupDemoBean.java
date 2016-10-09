/**
 * Created on Sep 23, 2011
 */
package org.billing.jlab.spring.ch4.mi;

/**
 * @author Clarence
 * 
 */
public abstract class AbstractLookupDemoBean implements DemoBean {

	public abstract MyHelper getMyHelper();

	public void someOperation() {
		getMyHelper().doSomethingHelpful();
	}
}
