package android.rain.OnClickListener;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * 按下可以跳转Activity
 */
public class StartActivity implements View.OnClickListener {
    private Class<?> cls;
    private Context packageContext;

    public StartActivity(Context packageContext,Class<?> cls) {
        this.cls = cls;
        this.packageContext = packageContext;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(packageContext, cls);
        packageContext.startActivity(intent);
    }
}
