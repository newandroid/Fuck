package css.com.fuck.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import css.com.fuck.R;

/**
 * Created by css on 2018/3/8.
 */

public class DialogSnapshot {
    /**
     * 自定义弹窗
     */
    public  void showInfoDialog(Context context,String text) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        Window win = alertDialog.getWindow();
        win.getDecorView().setPadding(20, 25, 20, 30);
        win.getDecorView().setBackgroundResource(R.drawable.shape_solid_white_r15);
        WindowManager.LayoutParams lp = win.getAttributes();
        // 设置弹出框的宽高
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        // 设置弹出框的位置
        win.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
        win.setAttributes(lp);

        // 设置布局
        win.setContentView(R.layout.activity_login);
        TextView tv_text = (TextView) win.findViewById(R.id.email);
        TextView tv_title = (TextView) win.findViewById(R.id.email);
        tv_text.setText(text);
        tv_title.setText("");
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setCancelable(true);
    }
}
