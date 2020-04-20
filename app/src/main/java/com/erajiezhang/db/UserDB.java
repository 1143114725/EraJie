package com.erajiezhang.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用户表
 * @author EraJieZhang
 * @data 2020/4/2
 */
@Entity
public class UserDB {
    @Id
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String passworld;
    /**
     * 手机
     */
    private String phong;
    /**
     * 是否可用 0 不可用 1可用
     */
    private String enable;
    /**
     * 最后登时间
     */
    private String lastlgointime;
    /**
     * 设备唯一标识
     */
    private String deviceId;
    /**
     * 权限
     */
    private int permissionID;
    /**
     * 邮箱
     */
    private String mailAddress;
    @Generated(hash = 482369259)
    public UserDB(Long id, String username, String passworld, String phong,
            String enable, String lastlgointime, String deviceId, int permissionID,
            String mailAddress) {
        this.id = id;
        this.username = username;
        this.passworld = passworld;
        this.phong = phong;
        this.enable = enable;
        this.lastlgointime = lastlgointime;
        this.deviceId = deviceId;
        this.permissionID = permissionID;
        this.mailAddress = mailAddress;
    }
    @Generated(hash = 1312299826)
    public UserDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassworld() {
        return this.passworld;
    }
    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }
    public String getPhong() {
        return this.phong;
    }
    public void setPhong(String phong) {
        this.phong = phong;
    }
    public String getEnable() {
        return this.enable;
    }
    public void setEnable(String enable) {
        this.enable = enable;
    }
    public String getLastlgointime() {
        return this.lastlgointime;
    }
    public void setLastlgointime(String lastlgointime) {
        this.lastlgointime = lastlgointime;
    }
    public String getDeviceId() {
        return this.deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public int getPermissionID() {
        return this.permissionID;
    }
    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }
    public String getMailAddress() {
        return this.mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    public String toString() {

        return "UserDB{" + "id=" + id + ", username='" + username + '\'' + ", passworld='" + passworld + '\'' + ", phong='" + phong + '\'' + ", enable='" + enable + '\'' + ", lastlgointime='" + lastlgointime + '\'' + ", deviceId='" + deviceId + '\'' + ", permissionID=" + permissionID + ", mailAddress='" + mailAddress + '\'' + '}';
    }
}
