package com.darian.beanscope.customer.util;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import reactor.util.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/***
 * ThreadLocal 级别的作用域
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/24  2:05
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-local";

    private NamedThreadLocal<Map<String, Object>> namedThreadLocal = new NamedThreadLocal<Map<String, Object>>("namedThreadLocal") {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @NonNull
    private Map<String, Object> getContext() {
        return namedThreadLocal.get();
    }

    // 拿到
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = getContext();
        Object obj = context.get(name);
        if (obj == null) {
            obj =  objectFactory.getObject();
            context.put(name, obj);
        }
        return obj;
    }

    // 删除
    public Object remove(String name) {
        Map<String, Object> context = getContext();
        return context.remove(name);
    }

    // 销毁式的回调
    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    // 查询相应的接口
    @Override
    public Object resolveContextualObject(String key) {
        return getContext().get(key);
    }

    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }
}
