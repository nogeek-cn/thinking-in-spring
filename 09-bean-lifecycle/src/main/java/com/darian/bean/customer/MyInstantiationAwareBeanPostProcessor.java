package com.darian.bean.customer;

import com.darian.domain.SuperUser;
import com.darian.domain.User;
import com.darian.domain.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.util.ObjectUtils;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2020/3/29  3:38
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superUser", beanName) || SuperUser.class.equals(beanClass)) {
            // 把配置完成的 superUser Bean 覆盖掉
            return new SuperUser();
        }
        return null; // 保持 Spring IOC 容器的实例化操作
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) || User.class.equals(bean.getClass())) {
            // "user" 对象不允许属性赋值（【填入】配置元信息 -> 属性值） 属性值全部都会被忽略掉。
            User user = User.class.cast(bean);
            user.setId(2L);
            user.setName(user.getName() + "postProcessAfterInstantiation");
            return false;
        }
        return true;
    }

    // user 是跳过 Bean 属性赋值（填入）
    // superUser 也是完成跳过 Bean 的实例化（Bean 的属性赋值（填入））
    // userHolder
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) || UserHolder.class.equals(bean.getClass())) {
            // "userHolder" 对象不允许属性赋值（【填入】配置元信息 -> 属性值） 属性值全部都会被忽略掉。
            // 假设 <Property name="number" value = "1"/> 配置的话，那么在 PropertyValues 就包含一个 PropertyValue(number=1)
            MutablePropertyValues propertyValues = new MutablePropertyValues();
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = MutablePropertyValues.class.cast(pvs);
            }
            // 等价于： <Property name="number" value = "1"/>
            propertyValues.addPropertyValue("number", "1");

            // 如果存在 “description” 的对象的话
            if (propertyValues.contains("description")) {
                // PropertyValue propertyValue = propertyValues.getPropertyValue("description");
//                    propertyValues.removePropertyValue("description"); // 删除不删除都行
                propertyValues.addPropertyValue("description",
                        ((TypedStringValue) propertyValues.getPropertyValue("description").getValue()).getValue()
                                + " -->>  #postProcessProperties v2");
            }

            return propertyValues;
        }
        return null;
    }

    //
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) || UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = UserHolder.class.cast(bean);
            userHolder.setDescription(userHolder.getDescription() + " -->> #postProcessBeforeInitialization v3");
        }
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) || UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = UserHolder.class.cast(bean);
            userHolder.setDescription(userHolder.getDescription() + " -->> #postProcessAfterInitialization v7");
        }
        return bean;
    }
}
