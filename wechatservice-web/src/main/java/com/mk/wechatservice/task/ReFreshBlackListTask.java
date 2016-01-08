package com.mk.wechatservice.task;

import com.mk.wechatservice.api.enums.BlackUserEnum;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kirinli on 15/9/9.
 */
@Service
public class ReFreshBlackListTask {
    public static Logger log = org.slf4j.LoggerFactory.getLogger(ReFreshBlackListTask.class);


    public static ConcurrentHashMap<Integer, BlackUserEnum> midBlackMap = new ConcurrentHashMap<Integer, BlackUserEnum>();
    public static ConcurrentHashMap<String, BlackUserEnum> phoneBlackMap = new ConcurrentHashMap<String, BlackUserEnum>();
    public static ConcurrentHashMap<String, BlackUserEnum> cardIdBlackMap = new ConcurrentHashMap<String, BlackUserEnum>();
    public static ConcurrentHashMap<String, Integer> passSwitchMap = new ConcurrentHashMap<String, Integer>();


    public void run() {
        reFlashBlackListData();
    }

    public void reFlashBlackListData() {
        phoneBlackMap.clear();
        midBlackMap.clear();
        cardIdBlackMap.clear();
        passSwitchMap.clear();



        initCache();
    }


    public void initCache() {

        log.info("\n++++++++++++++++++++++ pass switch refresh {} ++++++++++++++++++++++\n" );

    }


}
