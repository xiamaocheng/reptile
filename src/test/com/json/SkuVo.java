package com.json;

public class SkuVo {

    private String skuId;
    private String productName;
    private String brandStoreSn;

    public SkuVo(String skuId, String productName, String brandStoreSn) {
        super();
        this.skuId = skuId;
        this.productName = productName;
        this.brandStoreSn = brandStoreSn;
    }

    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getBrandStoreSn() {
        return brandStoreSn;
    }
    public void setBrandStoreSn(String brandStoreSn) {
        this.brandStoreSn = brandStoreSn;
    }

    @Override
    public String toString() {
        return "SkuVo [skuId=" + skuId + ", productName=" + productName + ", brandStoreSn=" + brandStoreSn + "]";
    }
}
