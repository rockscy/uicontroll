package com.rock.sysuicontroll;

import android.app.Application;

import com.rock.uicontroll.SystemUiControll;

/**
 * Created by timeDoMain.
 * User: scy
 * Date: 3/2/21  2:34 PM
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemUiControll.getInstence().register(this);
    }
}
