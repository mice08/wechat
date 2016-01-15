package tool.jedis;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;

import java.util.Set;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfiguration {

	@Value("${spring.redis.sentinel.master}")
	private String sentinelMasterName = null;

	@Value("${spring.redis.sentinel.nodes}")
	private String hostAndPorts = null;

	@Value("${jedis.pool.maxidl}")
	private Integer maxIdle = null;

	@Value("${jedis.pool.minidl}")
	private Integer minIdl = null;

	@Value("${jedis.pool.maxtotal}")
	private Integer maxTotal = null;

	public RedisConfiguration(){

		Prop result = new Prop("redis.properties", "UTF-8");
		this.sentinelMasterName = result.get("spring.redis.sentinel.master");
		this.hostAndPorts = result.get("spring.redis.sentinel.nodes");
		this.maxIdle = result.getInt("jedis.pool.maxidl");
		this.minIdl = result.getInt("jedis.pool.minidl");
		this.maxTotal = result.getInt("jedis.pool.maxtotal");


	}

	@Bean
	public MkJedisConnectionFactory connectionFactory() {
		Set<String> sentinelHostAndPorts = StringUtils.commaDelimitedListToSet(this.getHostAndPorts());
		RedisSentinelConfiguration sc = new RedisSentinelConfiguration(this.getSentinelMasterName(), sentinelHostAndPorts);
		JedisPoolConfig pc = this.createPoolConfig();
		MkJedisConnectionFactory factory = new MkJedisConnectionFactory(sc, pc);

		factory.setShardInfo(new JedisShardInfo("localhost", Protocol.DEFAULT_PORT));
		return factory;
	}

	private JedisPoolConfig createPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(this.getMaxIdle());
		poolConfig.setMinIdle(this.getMinIdl());
		poolConfig.setMaxTotal(this.getMaxTotal());


		return poolConfig;
	}

	private String getSentinelMasterName() {
		return this.sentinelMasterName;
	}

	private String getHostAndPorts() {
		return this.hostAndPorts;
	}

	public Integer getMaxIdle() {
		return this.maxIdle;
	}

	public Integer getMinIdl() {
		return this.minIdl;
	}

	public Integer getMaxTotal() {
		return this.maxTotal;
	}

	public static void main(String [] args){
		RedisConfiguration r = new RedisConfiguration();
		MkJedisConnectionFactory factory =  r.connectionFactory();
	}
}
