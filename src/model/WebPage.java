//中文UTF-8 NO BOM
package model;

import status.WebPageStatus;

import java.util.TimerTask;

public class WebPage extends TimerTask {
    private Integer id;
    private String urlAddress;
    private String status;
    private String urlName;
    private Integer monitoringPeriod;
    private Integer responseTime;
    private Integer responseCode;
    private String responseSubstring;
    private String responseCharset;    
    private Integer connectTimeout;
    private Integer readTimeout;

    public WebPage(Integer id, String urlAddress, String status, String urlName, Integer monitoringPeriod, Integer responseTime,
                   Integer responseCode, String responseSubstring,String responseCharset, Integer connectTimeout, Integer readTimeout) {
        this.id = id;
        this.urlAddress = urlAddress;
        this.status = status;
        this.urlName = urlName;
        this.monitoringPeriod = monitoringPeriod;
        this.responseTime = responseTime;
        this.responseCode = responseCode;
        this.responseSubstring = responseSubstring;
        this.responseCharset = responseCharset;        
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    // run when the timer is triggered
    @Override
    public void run() {
        this.setStatus();
        System.out.println(this.getStatus() + " : " + this.getUrlName());
    }

    public Integer getId() {
        return id;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        this.status = new WebPageStatus(this).determineStatus();
    }

    public String getUrlName() {
        return urlName;
    }

    public Integer getMonitoringPeriod() {
        return monitoringPeriod;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseSubstring() {
        return responseSubstring;
    }
    public String getResponseCharset() {
        return responseCharset;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

}
