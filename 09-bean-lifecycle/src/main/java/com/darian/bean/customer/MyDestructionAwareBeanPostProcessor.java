package com.darian.bean.customer;

import com.darian.domain.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/***
 * {@link DestructionAwareBeanPostProcessor}
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/29  15:02
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) || UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = UserHolder.class.cast(bean);
            System.err.println(" -->> #postProcessBeforeDestruction v9");
            userHolder.setDescription(userHolder.getDescription() + " -->> #postProcessBeforeDestruction v9");
        }
        return;
    }
}
