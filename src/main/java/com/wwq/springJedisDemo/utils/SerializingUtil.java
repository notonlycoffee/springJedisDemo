package com.wwq.springJedisDemo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * Created by Administrator on 2017-1-5.
 */
public class SerializingUtil {
    private static Log logger = LogFactory.getLog(SerializingUtil.class);

    /**
     * 对实体bean进行序列化
     * @param target
     * @return
     */
    public static byte[] serialize(Object target) {

        ByteArrayOutputStream byteOut = null;
        ObjectOutputStream objectOut = null;
        try {

            byteOut = new ByteArrayOutputStream();
            objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(target);
            objectOut.flush();

        } catch (Exception e) {
            logger.error(target.getClass().getName() + "serialize error",e);
            e.printStackTrace();
        } finally {
            if (null != objectOut) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return byteOut.toByteArray();
    }


    public static Object deSerialize(byte[] target) {

        ByteArrayInputStream byteIn = null;
        ObjectInputStream objectIn = null;
        Object object = null;
        try {

            byteIn = new ByteArrayInputStream(target);
            objectIn = new ObjectInputStream(byteIn);
            object = objectIn.readObject();

        } catch (Exception e) {
            logger.error("deSerialze error",e);
        } finally {
            if (null != objectIn) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return object;

    }


}
