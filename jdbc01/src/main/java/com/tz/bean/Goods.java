package com.tz.bean;

/**
 * 对应商品的实体类Goods
 * @author 南天
 * @date 2017年4月14日
 * @version 1.0
 */

public class Goods implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String goodsName;

	private int num;

	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
