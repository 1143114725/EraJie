package com.erajiezhang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erajie.base.BaseActivity;
import com.erajie.global.ARouterPath;
import com.erajiezhang.R;
import com.erajiezhang.view.GobangView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author EraJieZhang
 * @data 2020/2/10
 * 五子棋
 */
@Route(path = ARouterPath.GobangActivity,group = ARouterPath.GROUP_MAIN)
public class GobangActivity extends BaseActivity {

    @BindView(R.id.gobangview)
    GobangView mGobangview;
    @BindView(R.id.btn_statr)
    Button mBtnStatr;
    @BindView(R.id.btn_gobank_admitdefeat)
    Button btnGobankAdmitdefeat;
    @BindView(R.id.btn_gobank_undo)
    Button btnGobankUndo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gobank_layout);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_statr, R.id.btn_gobank_undo, R.id.btn_gobank_admitdefeat})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_statr:
            	//再来一局
				mGobangview.start();
                break;
            case R.id.btn_gobank_undo:
            	//悔棋
				mGobangview.regretgame();
                break;
            case R.id.btn_gobank_admitdefeat:
            	//认输
				mGobangview.admitdefeat();
                break;
			default:
				break;
        }
    }


}
