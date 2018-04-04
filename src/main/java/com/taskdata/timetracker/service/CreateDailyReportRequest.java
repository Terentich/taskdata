package com.taskdata.timetracker.service;

import javax.xml.ws.ResponseWrapper;
import java.util.Date;

public class CreateDailyReportRequest {
    private Date reportDate;

    public CreateDailyReportRequest() {
    }

    public CreateDailyReportRequest(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}
