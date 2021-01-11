package com.lay.mybatis.entity;

import java.util.List;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/21 17:07
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/21 lei.yue 1.0 create file
 */
public class MerchantEntity {

    private String id;

    private String merchantCode;

    private String merchantName;

    private List<MerchantConfigEntity> merchantConfigEntityList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public List<MerchantConfigEntity> getMerchantConfigEntityList() {
        return merchantConfigEntityList;
    }

    public void setMerchantConfigEntityList(List<MerchantConfigEntity> merchantConfigEntityList) {
        this.merchantConfigEntityList = merchantConfigEntityList;
    }
}
