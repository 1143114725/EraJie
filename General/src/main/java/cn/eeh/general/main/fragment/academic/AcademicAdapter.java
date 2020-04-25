package cn.eeh.general.main.fragment.academic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.eeh.general.R;

/**
 * @author EraJieZhang
 * @data 2020/2/27
 */
public class AcademicAdapter extends RecyclerView.Adapter {
	
	
	Context context;
	List<Integer> list;

	public AcademicAdapter(Context context, List<Integer> list) {
		this.context = context;
		this.list = list;
	}
	
	private OnItemClickListener mOnItemClickListener;
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.mOnItemClickListener = onItemClickListener;
	}
	
	public interface OnItemClickListener {
		void onItemClick(int position);
	}
	
	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpage_academic, parent, false);
		final MyViewHolder mViewHolder = new MyViewHolder(v);
		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if ( mOnItemClickListener != null ) {
					mOnItemClickListener.onItemClick((Integer) v.getTag());
					if ( (Integer) v.getTag() == 0 ) {
						mViewHolder.mTv.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
					}
				}
			}
		});
		return mViewHolder;
	}
	
	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
		((MyViewHolder) holder).mLay.setBackgroundColor(list.get(position));
		((MyViewHolder) holder).mTv.setText("当前 position = " + (position + 1));
		((MyViewHolder) holder).itemView.setTag(position);
	}
	
	@Override
	public int getItemCount() {
		return list != null ? list.size() : 0;
	}
	
	class MyViewHolder extends RecyclerView.ViewHolder {
		LinearLayout mLay;
		TextView mTv;
		
		public MyViewHolder(View itemView) {
			super(itemView);
			mLay = (LinearLayout) itemView.findViewById(R.id.item_view_pager_lay);
			mTv = (TextView) itemView.findViewById(R.id.item_view_pager_tv);
		}
	}
}