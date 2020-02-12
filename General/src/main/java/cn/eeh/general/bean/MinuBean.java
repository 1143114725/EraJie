package cn.eeh.general.bean;

import com.erajie.base.BaseViewModle;

import androidx.databinding.Bindable;

/**
 * 首页的数据
 * @author EraJieZhang
 * @data 2020/2/19
 */
public class MinuBean extends BaseViewModle {
	public String academicCircles;
	public String publicClass;
	public String found;
	public String my;
	
	public int academicCirclesIcon;
	public int publicClassIcon;
	public int foundIcon;
	public int myIcon;
	
	@Bindable
	public String getAcademicCircles() {
		return academicCircles;
	}
	
	public void setAcademicCircles(String academicCircles) {
		this.academicCircles = academicCircles;
		notifyPropertyChanged(cn.eeh.general.BR.academicCircles);
	}
	
	@Bindable
	public int getAcademicCirclesIcon() {
		return academicCirclesIcon;
	}
	
	public void setAcademicCirclesIcon(int academicCirclesIcon) {
		this.academicCirclesIcon = academicCirclesIcon;
		notifyPropertyChanged(cn.eeh.general.BR.academicCirclesIcon);
	}
	@Bindable
	public String getPublicClass() {
		return publicClass;
	}
	@Bindable
	public String getFound() {
		return found;
	}
	@Bindable
	public String getMy() {
		return my;
	}
	@Bindable
	public int getPublicClassIcon() {
		return publicClassIcon;
	}
	@Bindable
	public int getFoundIcon() {
		return foundIcon;
	}
	@Bindable
	public int getMyIcon() {
		return myIcon;
	}
	
	public void setPublicClass(String publicClass) {
		this.publicClass = publicClass;
		notifyPropertyChanged(cn.eeh.general.BR.publicClass);
	}
	
	public void setFound(String found) {
		this.found = found;
		notifyPropertyChanged(cn.eeh.general.BR.found);
	}
	
	public void setMy(String my) {
		this.my = my;
		notifyPropertyChanged(cn.eeh.general.BR.my);
	}
	
	public void setPublicClassIcon(int publicClassIcon) {
		this.publicClassIcon = publicClassIcon;
		notifyPropertyChanged(cn.eeh.general.BR.publicClassIcon);
	}
	
	public void setFoundIcon(int foundIcon) {
		this.foundIcon = foundIcon;
		notifyPropertyChanged(cn.eeh.general.BR.foundIcon);
	}
	
	public void setMyIcon(int myIcon) {
		this.myIcon = myIcon;
		notifyPropertyChanged(cn.eeh.general.BR.myIcon);
		
	}
}
