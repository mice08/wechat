package com.mk.wechatservice.biz.servcie.impl;

import com.mk.wechatservice.biz.utils.DateUtils;
import com.mk.wechatservice.biz.utils.HttpUtils;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HotelDetailCrawlServiceImpl {
	private final String hotelDetailUrl = "http://pad.qunar.com/api/hotel/hoteldetail?city=%s&cityUrl=%s&checkInDate=%s&checkOutDate=%s&keywords=&location=&seq=%s&clickNum=0&isLM=0&type=0&extra=%s";

	private final Logger logger = Logger.getLogger(HotelDetailCrawlServiceImpl.class);

	public void crawl(List<String> hotelIds, String city, String cityUrl) throws Exception {
		Date day = new Date();
		String strCurDay = DateUtils.getStringFromDate(day, DateUtils.FORMATSHORTDATETIME);
		String strNextDay = DateUtils.getStringFromDate(DateUtils.addDays(day, 1), DateUtils.FORMATSHORTDATETIME);

		String invokeUrl = String.format(hotelDetailUrl, "city", cityUrl);
		
		String jsonString = HttpUtils.doPost(hotelDetailUrl, new HashMap<String, String>());

		logger.debug(jsonString);
	}
}
