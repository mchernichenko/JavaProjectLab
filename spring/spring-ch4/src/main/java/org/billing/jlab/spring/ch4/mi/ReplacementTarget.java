/**
 * Created on Sep 23, 2011
 */
package org.billing.jlab.spring.ch4.mi;

/**
 * @author Clarence
 * 
 */
public class ReplacementTarget {

	public String formatMessage(String msg) {
		return "<h1>" + msg + "</h1>";
	}

	public String formatMessage(Object msg) {
		return "<h1>" + msg + "</h1>";
	}

}