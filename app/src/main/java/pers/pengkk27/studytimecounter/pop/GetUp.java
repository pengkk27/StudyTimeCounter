package pers.pengkk27.studytimecounter.pop;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import pers.pengkk27.studytimecounter.R;

public class GetUp extends Dialog {

    Activity context;

    private Button btn_save_pop;
    public EditText getup_time_pop;

    private View.OnClickListener mClickListener;

    public GetUp(Activity context) {
        super(context);
        this.context = context;
    }

    public GetUp(Activity context, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.intput_getup);

        getup_time_pop = findViewById(R.id.getup_time_pop);

        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = this.getWindow();

        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);

        // 根据id在布局中找到控件对象
        btn_save_pop = findViewById(R.id.getup_btn_save_pop);

        // 为按钮绑定点击事件监听器
        btn_save_pop.setOnClickListener(mClickListener);

        this.setCancelable(true);
    }
}
