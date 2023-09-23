package android.rain.textView;

import android.widget.TextView;

public class textString {
    private TextView textView;
    private String s;
    private int time;
    private int nn;
    private int length;

    public textString(TextView textView, String s, int time) {
        this.textView = textView;
        this.s = s;
        this.time = time;
    }
    /**
     * 通过线程使文字一个个的出现
     * @param n 间隔时间，单位毫秒
     */
    public void startTv(final int n) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String stv = s.substring(0, n);//截取要填充的字符串
                            textView.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(stv);
                                }
                            });
                            Thread.sleep(time);//休息片刻
                            nn = n + 1;//n+1；多截取一个
                            if (nn <= s.length()) {//如果还有汉字，那么继续开启线程，相当于递归的感觉
                                startTv(nn);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }

        ).start();
    }
}
