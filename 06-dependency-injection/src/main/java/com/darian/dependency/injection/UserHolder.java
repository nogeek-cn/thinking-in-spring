package com.darian.dependency.injection;

import com.darian.domain.User;

/***
 * {@link User} 的 Holder 类
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/16  23:25
 */
public class UserHolder {
    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
