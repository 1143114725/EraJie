package com.erajie.responsebean;

/**
 * 登陆请求返回的实体
 * @date 2018/9/29
 * @author EraJi
 */
public class ToolLogin {


    /**
     * age : 23
     * height : 180
     * name : EraJieZhang
     */

    private String age;
    private String height;
    private String name;

    @Override
    public String toString() {

        return "ToolLogin{" + "age='" + age + '\'' + ", height='" + height + '\'' + ", name='" + name + '\'' + '}';
    }

    public String getAge() {

        return age;
    }

    public void setAge(String age) {

        this.age = age;
    }

    public String getHeight() {

        return height;
    }

    public void setHeight(String height) {

        this.height = height;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
