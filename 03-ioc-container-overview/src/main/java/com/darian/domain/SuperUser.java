package com.darian.domain;

import com.darian.annotation.Super;

/***
 * 超级用户
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/10  0:14
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
