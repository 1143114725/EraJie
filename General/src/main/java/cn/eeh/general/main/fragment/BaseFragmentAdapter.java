package cn.eeh.general.main.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author EraJieZhang
 * @data 2020/2/27
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter
{
	public BaseFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
		super(fm, behavior);
	}
	
	@NonNull
	@Override
	public Fragment getItem(int position) {
		return null;
	}
	
	@Override
	public int getCount() {
		return 0;
	}
}
