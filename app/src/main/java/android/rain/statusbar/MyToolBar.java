package android.rain.statusbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MyToolBar {
    private Toolbar toolbar;
    private AppCompatActivity activity;
    public MyToolBar(Toolbar toolbar, AppCompatActivity activity) {
        this.toolbar = toolbar;
        this.activity = activity;
    }

    /**
     * 设置返回图标
     */
    public void setBack(){
        activity.setSupportActionBar(toolbar);//将其设置为Activity的ActionBar
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);//getSupportActionBar()返回ActionBar的实例，进而调用setDisplayHomeAsUpEnabled(true)来显示返回按钮。
    }
    //以下代码为返回图标的返回动作
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            onBackPressed(); // 处理返回按钮点击事件
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
