package com.mk.common.dto;

import java.math.BigDecimal;

public class OrderRoomDto {
	public   Long  orderroomid;
	public   Long  hotelid;
	public   Long  roomtypeid;
	public   String roomtypename;
	public   Long  roomid;
	public   Long  roomno;
	public   String   ordermethod;
	public   String   ordertype;
	public   String  pricetype;
	public   String  begintime;
	public   String  endtime;
	public   int  orderday;
	public   String  createtime;
	public   String  promotion;
	public   BigDecimal   totalprice;
	public Long getOrderroomid() {
		return orderroomid;
	}
	public void setOrderroomid(Long orderroomid) {
		this.orderroomid = orderroomid;
	}
	public Long getHotelid() {
		return hotelid;
	}
	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	public Long getRoomtypeid() {
		return roomtypeid;
	}
	public void setRoomtypeid(Long roomtypeid) {
		this.roomtypeid = roomtypeid;
	}
	public String getRoomtypename() {
		return roomtypename;
	}
	public void setRoomtypename(String roomtypename) {
		this.roomtypename = roomtypename;
	}
	public Long getRoomid() {
		return roomid;
	}
	public void setRoomid(Long roomid) {
		this.roomid = roomid;
	}
	public Long getRoomno() {
		return roomno;
	}
	public void setRoomno(Long roomno) {
		this.roomno = roomno;
	}
	public String getOrdermethod() {
		return ordermethod;
	}
	public void setOrdermethod(String ordermethod) {
		this.ordermethod = ordermethod;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getPricetype() {
		return pricetype;
	}
	public void setPricetype(String pricetype) {
		this.pricetype = pricetype;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int getOrderday() {
		return orderday;
	}
	public void setOrderday(int orderday) {
		this.orderday = orderday;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	
}
