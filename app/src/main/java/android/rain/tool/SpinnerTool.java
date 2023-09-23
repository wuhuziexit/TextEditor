package android.rain.tool;

import android.widget.Spinner;

/**
 * Spinner相关工具
 * getSpinnerResults==获取Spinner的结果，并以字符串的形式返回
 */
public class SpinnerTool {
    private Spinner spinner;

    public SpinnerTool(Spinner spinner) {
        this.spinner = spinner;
    }

    private String getSpinnerResults() {
        int position = spinner.getSelectedItemPosition();
        return spinner.getItemAtPosition(position).toString();
    }
}
