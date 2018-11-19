package com.builder.common.base.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocalMap 本地线程map
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-19 15:04:16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadLocalMap {
    private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new MapThreadLocal();

    private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>(8) {
                private static final long serialVersionUID = 1L;
                @Override
                public Object put(String key, Object value) {
                    return super.put(key, value);
                }
            };
        }
    }
    /**
     * 取得thread context Map的实例
     * @return map
     * */
    public static Map<String, Object> getContextMap() {
        return THREAD_CONTEXT.get();
    }
    /**
     * 添加key/value
     * @param key key
     * @param value value
     * */
    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }
    /**
     * 获取key对应的value
     * @param key key
     * @return value
     * */
    public static Object get(String key) {
        return getContextMap().get(key);
    }
    /**
     * 删除key/value
     * @param key key
     * @return 被删除的对象value
     * */
    public static Object remove(String key) {
        return getContextMap().remove(key);
    }
    /**
     * 清空thread local所有对象，便于重用
     * */
    public static void removeAll() {
        getContextMap().clear();
    }
}
