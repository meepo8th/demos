import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jedis manager
 */
public class JedisManager {

    public static final int TIMEOUT = 30000;
    private static final int MAX_ACTIVE = 1024;
    private static final int MAX_IDLE = 200;
    private static final boolean TEST_ON_BORROW = true;

    private JedisManager() {
        init();
    }

    private void init() {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(MAX_ACTIVE);
        jedisPoolConfig.setMaxIdle(MAX_IDLE);
        jedisPoolConfig.setMaxWaitMillis(TIMEOUT);
        jedisPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
        pools = new ConcurrentHashMap<>();
    }

    private String defaultHost = "111.230.176.200";
    private int defaultPort = 6379;
    private String defaultPwd = "#bailey88bikeman";
    private Map<String, JedisPool> pools;
    JedisPoolConfig jedisPoolConfig;

    /**
     * 获取jedis
     *
     * @param host
     * @param port
     * @return
     */
    private Jedis getJedis(String host, int port) {
        return getJedis(host, port, "");
    }

    /**
     * 获取jedis
     *
     * @param host
     * @param port
     * @param pwd
     * @return
     */
    private synchronized Jedis getJedis(String host, int port, String pwd) {
        String key = host + ":" + port;
        if (!pools.containsKey(key)) {
            if (null==pwd||"".equals(pwd)) {
                pools.put(key, new JedisPool(jedisPoolConfig, host, port, TIMEOUT));
            } else {
                pools.put(key, new JedisPool(jedisPoolConfig, host, port, TIMEOUT, pwd));
            }
        }
        Jedis jedis = pools.get(key).getResource();
        if(null!=pwd&&!"".equals(pwd)){
            jedis.auth(pwd);
        }
        return pools.get(key).getResource();
    }

    /**
     * 获取jedis
     *
     * @return
     */
    private Jedis getJedis() {
        return getJedis(defaultHost, defaultPort, defaultPwd);
    }

    /**
     * 内部类保持单例 用类加载器来保证并发单例
     */
    static class JedisHolder {
        private JedisHolder() {

        }

        private static JedisManager jedisManager;

        static {
            jedisManager = new JedisManager();
        }

        public static JedisManager getInstance() {
            return jedisManager;
        }
    }

    /**
     * 获取jedis
     *
     * @param host
     * @param port
     * @return
     */
    public static Jedis getResource(String host, int port) {
        return JedisHolder.getInstance().getJedis(host, port);
    }

    /**
     * 获取jedis
     *
     * @param host
     * @param port
     * @param pwd
     * @return
     */
    public static Jedis getResource(String host, int port, String pwd) {
        return JedisHolder.getInstance().getJedis(host, port, pwd);
    }


    /**
     * 获取jedis
     *
     * @return
     */
    public static Jedis getResource() {
        return JedisHolder.getInstance().getJedis();
    }

    public static void main(String[] args) {
        Jedis jedis =getResource();
        jedis.set("123","456");
        System.out.println(jedis.get("123"));
        jedis.close();
        System.exit(0);
    }

}
