package base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


import com.zkrkj.peoplehospital.MyApplication;
import com.zkrkj.peoplehospital.R;

/**
 * Created by lenovo on 2016/3/16.
 */
public abstract class BaseActivity extends ActionBarActivity {
    public static String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //透明状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        super.onCreate(savedInstanceState);
        //添加Activity到堆栈
        MyApplication.getInstance().addActivity(this);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }


        initView();
        initAction();

        TAG = this.getClass().getName();
    }

    /**
     * @return
     */
    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initAction();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().finishActivity(this);
    }
}
