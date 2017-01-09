package com.kiranacustomerapp.Models;

/**
 * Created by Siddhi on 1/9/2017.
 */
public class Bills {

    public String startDate,endDate,kiranaName;
    public Long bill_id,amount;
    public int status;

    public Bills(Long bill_id,String kiranaName,String startDate,String endDate,Long amount)
    {
        this.bill_id = bill_id;
        this.kiranaName = kiranaName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public Long getBill_id() {
        return bill_id;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getKiranaName() {
        return kiranaName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setBill_id(Long bill_id) {
        this.bill_id = bill_id;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setKiranaName(String kiranaName) {
        this.kiranaName = kiranaName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
