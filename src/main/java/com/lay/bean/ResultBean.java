package com.lay.bean;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/7/31 17:03
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/7/31 lei.yue 1.0 create file
 */
public class ResultBean {

    private String msg;

    private String ok;

    private Integer status;

    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
