package com.darian.bean.factory;

import com.darian.domain.User;
import org.springframework.beans.factory.FactoryBean;

/***
 * {@link User} Bean {@link FactoryBean}的实现
 * 默认是单例
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/14  20:58
 */
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        User user = User.createUser();
        user.setName(user.getName() + "--UserFactoryBean#getObject");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
