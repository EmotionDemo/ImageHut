package com.lifh.imagehut.imagehut.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lifh.imagehut.imagehut.R;
import com.lifh.imagehut.imagehut.fragments.HotFragment;
import com.lifh.imagehut.imagehut.fragments.MyFragment;
import com.lifh.imagehut.imagehut.fragments.PlaceFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.fragment_content)
    RelativeLayout fragmentContent;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.ly_btn_me)
    LinearLayout lyBtnMe;
    @BindView(R.id.main_menu)
    LinearLayout mainMenu;
    @BindView(R.id.blue_menu)
    RelativeLayout blueMenu;
    @BindView(R.id.rl_menu)
    RelativeLayout rlMenu;
    @BindView(R.id.iv_hot)
    ImageView ivHot;
    @BindView(R.id.ly_btn_hot)
    LinearLayout lyBtnHot;
    @BindView(R.id.iv_place)
    ImageView ivPlace;
    @BindView(R.id.ly_btn_place)
    LinearLayout lyBtnPlace;
    private HotFragment hotFragment = new HotFragment();
    private MyFragment myFragment = new MyFragment();
    private PlaceFragment placeFragment = new PlaceFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    private Fragment mfrom = hotFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBottomClicklistener();
    }

    //注册底部监听器
    private void setBottomClicklistener() {
        lyBtnHot.setOnClickListener(this);
        lyBtnPlace.setOnClickListener(this);
        lyBtnMe.setOnClickListener(this);
    }


    /**
     * 控制从activity
     */
    public void switchContent(Fragment from, Fragment to) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if (!to.isAdded()) { // 先判断是否被add过
            fragmentTransaction.hide(from).add(R.id.fragment_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            // Log.i("status", "===没有添加过===");
        } else {
            fragmentTransaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            // Log.i("status", "===添加过===");
        }
    }

    /**
     * 修改主页按钮样式
     */

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changeHotMenu() {
        ivHot.setBackground(getResources().getDrawable(R.drawable.ic_tab_home_pressed));
        // iv_sheet.setBackground(getResources().getDrawable(R.drawable.ic_tab_sheet_unpress));
        ivPlace.setBackground(getResources().getDrawable(R.drawable.ic_tab_news_unpress));
        ivMe.setBackground(getResources().getDrawable(R.drawable.ic_tab_me_unpress));
    }


    /**
     * 修改新闻公告按钮样式
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changePlaceMenu() {
        ivHot.setBackground(getResources().getDrawable(R.drawable.ic_tab_home_unpress));
        //iv_sheet.setBackground(getResources().getDrawable(R.drawable.ic_tab_sheet_unpress));
        ivPlace.setBackground(getResources().getDrawable(R.drawable.ic_tab_news_pressed));
        ivMe.setBackground(getResources().getDrawable(R.drawable.ic_tab_me_unpress));
    }

    /**
     * 修改我的按钮样式
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changeMeMenu() {
        ivHot.setBackground(getResources().getDrawable(R.drawable.ic_tab_home_unpress));
        //  iv_sheet.setBackground(getResources().getDrawable(R.drawable.ic_tab_sheet_unpress));
        ivPlace.setBackground(getResources().getDrawable(R.drawable.ic_tab_news_unpress));
        ivMe.setBackground(getResources().getDrawable(R.drawable.ic_tab_me_pressed));
    }

    /**
     * 底部菜单栏点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_btn_hot:
                switchContent(mfrom, hotFragment);//先执行fragment切换操作
                changeHotMenu();//再执行更换底部菜单视图操作
                mfrom = hotFragment;
                break;
            case R.id.ly_btn_place:
                switchContent(mfrom, placeFragment);
                changePlaceMenu();
                mfrom = placeFragment;
                break;
            case R.id.ly_btn_me:
                switchContent(mfrom, myFragment);
                changeMeMenu();
                mfrom = myFragment;
                break;
            default:
                break;
        }
    }
}
