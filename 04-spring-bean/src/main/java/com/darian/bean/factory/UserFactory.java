package com.darian.bean.factory;

import com.darian.domain.User;


/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/14  20:38
 */
public interface UserFactory {
    default User createUser() {
        User user = User.createUser();
        user.setId(user.getId() + 1);
        user.setName(user.getName() + "--UserFactory#UserFactory");
        return user;
    }
}
