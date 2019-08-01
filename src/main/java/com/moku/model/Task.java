package com.moku.model;

/**
 * 映射f_f_task_setting
 */

import java.util.Date;

public class Task {

    private 	String		id	;
    private 	String		app_id	;
    private 	String		name	;
    private 	String		descr	;
    private 	String		costs	;
    private 	String		prizes	;
    private 	int		consume	;
    private 	int		gain	;
    private 	String		start_time	;
    private 	String		end_time	;
    private 	String		display_time	;
    private 	String		start_date	;
    private 	String		end_date	;
    private 	String		create_date	;
    private 	String		update_date	;
    private 	int		repeated	;
    private 	int		times	;
    private 	String		limit_of_show	;
    private 	int		expires	;
    private 	String		type	;
    private 	String		category	;
    private 	String		icon	;
    private 	String		setting_id	;
    private 	String		configs	;
    private 	String		extensions	;
    private 	String		apps	;
    private 	int		is_delete	;

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

    public int getConsume() {
        return consume;
    }

    public void setConsume(int consume) {
        this.consume = consume;
    }

    public int getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDisplay_time() {
        return display_time;
    }

    public void setDisplay_time(String display_time) {
        this.display_time = display_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public int getRepeated() {
        return repeated;
    }

    public void setRepeated(int repeated) {
        this.repeated = repeated;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getLimit_of_show() {
        return limit_of_show;
    }

    public void setLimit_of_show(String limit_of_show) {
        this.limit_of_show = limit_of_show;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(String setting_id) {
        this.setting_id = setting_id;
    }

    public String getConfigs() {
        return configs;
    }

    public void setConfigs(String configs) {
        this.configs = configs;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public String getApps() {
        return apps;
    }

    public void setApps(String apps) {
        this.apps = apps;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", app_id='" + app_id + '\'' +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", costs='" + costs + '\'' +
                ", prizes='" + prizes + '\'' +
                ", consume=" + consume +
                ", gain=" + gain +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", display_time='" + display_time + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", create_date='" + create_date + '\'' +
                ", update_date='" + update_date + '\'' +
                ", repeated=" + repeated +
                ", times=" + times +
                ", limit_of_show='" + limit_of_show + '\'' +
                ", expires=" + expires +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", icon='" + icon + '\'' +
                ", setting_id='" + setting_id + '\'' +
                ", configs='" + configs + '\'' +
                ", extensions='" + extensions + '\'' +
                ", apps='" + apps + '\'' +
                ", is_delete=" + is_delete +
                '}';
    }
}