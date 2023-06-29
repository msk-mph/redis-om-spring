package com.redis.om.spring.client;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Connection;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.UnifiedJedis;

import java.util.Set;

public class JedisSentineled extends UnifiedJedis {

  public JedisSentineled(String masterName, final JedisClientConfig masterClientConfig,
                         Set<HostAndPort> sentinels, final JedisClientConfig sentinelClientConfig) {
    this(new SentineledConnectionProvider(masterName, masterClientConfig, sentinels, sentinelClientConfig));
  }

  public JedisSentineled(String masterName, final JedisClientConfig masterClientConfig,
      final GenericObjectPoolConfig<Connection> poolConfig,
      Set<HostAndPort> sentinels, final JedisClientConfig sentinelClientConfig) {
    this(new SentineledConnectionProvider(masterName, masterClientConfig, poolConfig, sentinels, sentinelClientConfig));
  }

  public JedisSentineled(SentineledConnectionProvider sentineledConnectionProvider) {
    super(sentineledConnectionProvider);
  }

  public HostAndPort getCurrentMaster() {
    return ((SentineledConnectionProvider) provider).getCurrentMaster();
  }
}