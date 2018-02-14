package net.gormelof.hackathontask;

import net.gormelof.hackathontask.response.searchrecords.Record;

import java.util.List;


public class Page {
    private int pageNo;
    private net.gormelof.hackathontask.response.searchrecords.Record record;

    public Page(int pageNo, net.gormelof.hackathontask.response.searchrecords.Record record) {
        this.pageNo = pageNo;
        this.record = record;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public net.gormelof.hackathontask.response.searchrecords.Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
