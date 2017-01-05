package com.wwq.springJedisDemo;

import com.wwq.springJedisDemo.domain.User;
import com.wwq.springJedisDemo.utils.SerializingUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Unit test for simple App.
 */
public class JedisDemoTest {
    ApplicationContext context = null;
    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }




    /**
     * spring集成之后使用redis连接池
     */
//    @Test
    public void testJedis() {
        JedisPool jedisPool = (JedisPool) context.getBean("jedisPool");
        Jedis jedis = jedisPool.getResource();
        jedis.set("name2_name", "hahaha");
        String value = jedis.get("name2_name");
        System.out.println(value);
        jedisPool.returnResource(jedis);
    }


    /*
     *  序列化javabean并且存储在redis中
     */
    @Test
    public void testSerialize() {
        JedisPool jedisPool = (JedisPool) context.getBean("jedisPool");
        Jedis jedis = jedisPool.getResource();
        User user = new User("jack",12);
        String userKey = "user:id:"+user.getName();
        jedis.set(userKey.getBytes(), SerializingUtil.serialize(user));

        User user1 = (User) SerializingUtil.deSerialize(jedis.get(userKey.getBytes()));

        System.out.println(user1.getAge());
        System.out.println(user1.getName());

    }


}
