package com.mk.common.dto;

import java.math.BigDecimal;

public class PromotionDto {
	public  Long id;
	public  String name;
	public  String  select;  //ѡ��
	public  String  check;  //����ʹ��
	public  BigDecimal  subprice;
	public  BigDecimal  offlinesubprice;
	public  String  type;
	public  String  isticket ;
	public  String  uselimit;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public BigDecimal getSubprice() {
		return subprice;
	}
	public void setSubprice(BigDecimal subprice) {
		this.subprice = subprice;
	}
	public BigDecimal getOfflinesubprice() {
		return offlinesubprice;
	}
	public void setOfflinesubprice(BigDecimal offlinesubprice) {
		this.offlinesubprice = offlinesubprice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsticket() {
		return isticket;
	}
	public void setIsticket(String isticket) {
		this.isticket = isticket;
	}
	public String getUselimit() {
		return uselimit;
	}
	public void setUselimit(String uselimit) {
		this.uselimit = uselimit;
	}
	
	
}
