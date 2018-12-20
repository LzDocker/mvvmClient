package com.docker.constantmodule.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhangxindang on 2018/12/13.
 */

public class ToastTool {


    private static Toast toast;

    /**
     * 显示字符串
     *
     * @param context
     * @param content
     */
    public static void show(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 显示非字符串
     *
     * @param context
     * @param content
     */
    public static void show(Context context, int content) {
        if (toast == null) {
            toast = Toast.makeText(context, content + "", Toast.LENGTH_SHORT);
        } else {
            toast.setText(content + "");
        }
        toast.show();
    }


}
