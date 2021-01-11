package com.lay.bean;

import java.util.List;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/10/15 17:29
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/10/15 lei.yue 1.0 create file
 */
public class BHReportQueryResponseBean {
    private ReportHeaderBean reportHeader;//报告头

    private List<HomeInfoBean> homeInfo;//居住信息

    private String name;

    public ReportHeaderBean getReportHeader() {
        return reportHeader;
    }

    public void setReportHeader(ReportHeaderBean reportHeader) {
        this.reportHeader = reportHeader;
    }

    public List<HomeInfoBean> getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(List<HomeInfoBean> homeInfo) {
        this.homeInfo = homeInfo;
    }

    public static class ReportHeaderBean{
        private String reportId;//报告编号
        private String reportTime;//报告时间
        private int queryResult;//查询结果 1命中

        public String getReportId() {
            return reportId;
        }

        public void setReportId(String reportId) {
            this.reportId = reportId;
        }

        public String getReportTime() {
            return reportTime;
        }

        public void setReportTime(String reportTime) {
            this.reportTime = reportTime;
        }

        public int getQueryResult() {
            return queryResult;
        }

        public void setQueryResult(int queryResult) {
            this.queryResult = queryResult;
        }
    }

    public static class HomeInfoBean{
        private String homeAddress;
        private String date;//家庭地址的信息报送时间

        public String getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
