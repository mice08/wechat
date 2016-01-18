/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.cache.RedisAccessTokenCache;

public class WeixinConfig extends JFinalConfig {

	public void configConstant(Constants me) {
		PropKit.use("weixin_config.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
	}
	
	public void configRoute(Routes me) {
		me.add("/wx", WeixinMsgController.class);
		me.add("/wx/api", WeixinApiController.class);
		me.add("/wx/pay", WeixinPayController.class);
	}
	
	public void configPlugin(Plugins me) {
//		//定时任务
//		QuartzPlugin quzrtz = new QuartzPlugin();
//		quzrtz.setJobs("quartzJob.properties");
//		me.add(quzrtz);

		// C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		// me.add(c3p0Plugin);
		
		// EhCachePlugin ecp = new EhCachePlugin();
		// me.add(ecp);

        Prop result = new Prop("redis.properties", "UTF-8");
        String nodes = result.get("spring.redis.sentinel.nodes");
        String[] addresses = nodes.split(",");
        String[] addressAndPort = addresses[0].split(":");
        RedisPlugin redisPlugin = new RedisPlugin("weixin", addressAndPort[0], Integer.valueOf(addressAndPort[1]));
        redisPlugin.start();
        ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
//        me.add(redisPlugin);
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		
	}
	
	public void afterJFinalStart() {
		// 1.5 之后支持redis存储access_token、js_ticket，需要先启动RedisPlugin
//		ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
		// 1.6新增的2种初始化
//		ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache(Redis.use("weixin")));
//		ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache("weixin"));
	}

	public static void main(String[] args) {
		JFinal.start("webapp", 80, "/", 5);
	}
}
