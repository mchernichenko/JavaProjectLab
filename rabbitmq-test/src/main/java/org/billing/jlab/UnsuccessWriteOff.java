package org.billing.jlab;

/**
 * Created by Mikhail.Chernichenko on 10.02.2016.
 */
public class UnsuccessWriteOff {
    private long subscriptionId;
    private long subscriberId;
    private String msisdn;
    private long objectTypeId;
    private long objectId;
    private String objectName;
    private long balance;
    private long chargeAmount;

    public UnsuccessWriteOff() {

    }

    public UnsuccessWriteOff(long subscriptionId, long subscriberId, String msisdn, long objectTypeId, long objectId, String objectName, long balance, long chargeAmount) {

        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.msisdn = msisdn;
        this.objectTypeId = objectTypeId;
        this.objectId = objectId;
        this.objectName = objectName;
        this.balance = balance;
        this.chargeAmount = chargeAmount;
    }

    public long getSubscriptionId() {
        return subscriptionId;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public long getObjectTypeId() {
        return objectTypeId;
    }

    public long getObjectId() {
        return objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public long getBalance() {
        return balance;
    }

    public long getChargeAmount() {
        return chargeAmount;
    }

    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public void setObjectTypeId(long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setChargeAmount(long chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    @Override
    public String toString() {
        return "UnsuccessWriteOff{" +
                "subscriptionId=" + subscriptionId +
                ", subscriberId=" + subscriberId +
                ", msisdn='" + msisdn + '\'' +
                ", objectTypeId=" + objectTypeId +
                ", objectId=" + objectId +
                ", objectName='" + objectName + '\'' +
                ", balance=" + balance +
                ", chargeAmount=" + chargeAmount +
                '}';
    }
}

