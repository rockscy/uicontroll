package com.rock.sysuicontroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.rock.uicontroll.SystemUiControll;
import com.rock.uicontroll.listener.KeyboardListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListener();
        initMoveView();
        initSystemUiControll();
    }

    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * 任意地方监听软键盘高度
     */
    private void initListener() {
        SystemUiControll.getInstence().setKeyBoardListener(new KeyboardListener() {
            @Override
            public void onKeyBoardAnimStart() {
                Log.i(TAG, "key baord anim start");
            }

            @Override
            public void onKeyBoardHeightChange(int height) {
                Log.i(TAG, "key baord height: " + height);
            }

            @Override
            public void onKeyBoardAnimEnd() {
                Log.i(TAG, "key baord anim end");
            }
        });
    }


    /**
     * 设置任意view跟随软键盘移动
     */
    private EditText editText;


    private void initMoveView() {
        editText = findViewById(R.id.edt);
        View container = findViewById(R.id.btn_container);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(new MyAdapter());
        //分别设置
        SystemUiControll.getInstence().setAutoMoveView(recyclerView,container,editText);
        //对根布局进行设置
//        View rootView = getWindow().getDecorView().getRootView();
//        SystemUiControll.getInstence().setAutoMoveView(rootView);
    }


    /**
     * 对软键盘进行控制进行控制
     */
    private void initSystemUiControll() {
        findViewById(R.id.btn_keyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemUiControll.getInstence().showOrHideKeyBoard(editText);
            }
        });

    }
}