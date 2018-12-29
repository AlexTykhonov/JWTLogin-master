package com.example.semen.jwtlogin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class Pet implements Serializable {

//    @Id
//    private Long ids;

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    private final static long serialVersionUID = 2292327506375878095L;
    @Generated(hash = 1916802310)
    public Pet(Integer id, String name, Integer age, Integer userId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.userId = userId;
    }
    @Generated(hash = 1478286243)
    public Pet() {
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return this.age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}