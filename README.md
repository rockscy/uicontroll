# uicontroll
android keybaord软键盘解决方案，监听软键盘打开关闭并实时获取高度，任意view跟随软键盘进行平移


## 效果
<img width="300"  src="https://github.com/rockscy/uicontroll/blob/main/snapshot.gif"/>

## 使用 
> android studio
   ```groovy
   implementation 'com.rock.uicontroll:uicontroll:1.0.1'
   ```

  
  
## Api详解
- 注册（appliation中调用onCreate中调用）

    ```java
    SystemUiControll.getInstence().register(this);
    ```
- 设置任意view跟随软键盘进行平移（可传入多个view）

    ```java
     SystemUiControll.getInstence().setAutoMoveView(view);
    ```
    
- 打开关闭软键盘
- 
    ```java
     SystemUiControll.getInstence().showOrHideKeyBoard(editText);
    ```
    
- 自行监听软键盘高度变化
- 
    ```java
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
    ```


## 联系我 ##
- QQ 617913246（问题交流）
