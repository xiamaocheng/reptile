package com.moku.model;

/**
 *   解析360 实体bean
 *
 */
public class Info {
	private String id;
	private String name;
	private String imgPath;
	private String downPath;
	private String tips;
	private String downNums;
	private String stars;
	private String searchNums;// 多余字段，统计搜索到的软件的数量

	public String getSearchNums() {
		return searchNums;
	}

	public void setSearchNums(String searchNums) {
		this.searchNums = searchNums;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getDownPath() {
		return downPath;
	}

	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getDownNums() {
		return downNums;
	}

	public void setDownNums(String downNums) {
		this.downNums = downNums;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	@Override
	public String toString() {
		return "Info [id=" + id + ", name=" + name + ", imgPath=" + imgPath
				+ ", downPath=" + downPath + ", tips=" + tips + ", downNums="
				+ downNums + ", stars=" + stars + ", searchNums=" + searchNums
				+ "]";
	}


}
