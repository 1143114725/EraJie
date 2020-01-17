package com.erajiezhang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author EraJieZhang
 * @data 2020/1/14
 */
public class ReturnBean implements Parcelable {
	public String name;
	public String phone;
	public String age;
	
	public ReturnBean() {
	}
	
	public ReturnBean(String name, String phone, String age) {
		this.name = name;
		this.phone = phone;
		this.age = age;
	}
	
	protected ReturnBean(Parcel in) {
		name = in.readString();
		phone = in.readString();
		age = in.readString();
	}
	
	public static final Creator<ReturnBean> CREATOR = new Creator<ReturnBean>() {
		@Override
		public ReturnBean createFromParcel(Parcel in) {
			return new ReturnBean(in);
		}
		
		@Override
		public ReturnBean[] newArray(int size) {
			return new ReturnBean[size];
		}
	};
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "ReturnBean{" +
			"name='" + name + '\'' +
			", phone='" + phone + '\'' +
			", age='" + age + '\'' +
			'}';
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(phone);
		dest.writeString(age);
	}
}
