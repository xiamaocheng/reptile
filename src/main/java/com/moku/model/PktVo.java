package com.moku.model;

/**
 * 分组报文实体bean.
 */
public class PktVo {

    private String  market;
    private String softwareName;
    private String name;

    public PktVo(String market, String softwareName, String name) {
        this.market = market;
        this.softwareName = softwareName;
        this.name = name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PktVo{" +
                "market='" + market + '\'' +
                ", softwareName='" + softwareName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
