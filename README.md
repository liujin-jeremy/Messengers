# 通信框架

底层封装Handler实现通信框架

```
implementation 'tech.liujin:messengers:1.0.0'
```

## Messengers使用

1. 实现接收消息接口

```
private class TestReceiver implements OnMessageReceiveListener {
      @Override
      public void onReceive ( int what, Object extra ) {
            Log.e( TAG, "onReceive : " + what + " " + extra + " " + Thread.currentThread()
                                                                          .getName() );
      }
}
```

2. 发送消息

> 注意:消息标识的选择,如果是奇数那么发送到主线程处理,如果是偶数,发送到Messengers线程处理

```
// 空白消息,接受者会收到 1
Messengers.send( 1, mTestReceiver );

// 空白消息,不同的是在 Messengers 线程收到不在主线程
Messengers.send( 2, mTestReceiver );
```

```
// 延时消息,2s之后,接受者收到 3
Messengers.send( 3, 2000, mTestReceiver );

// 延时消息,Messengers线程处理
Messengers.send( 4, 2000, mTestReceiver );
```

```
// 携带额外消息,2s之后,接收者收到 7 和 额外信息 "Hello"
Messengers.send( 7, 2000, "Hello", mTestReceiver );

// 携带额外消息,不同的是在Messengers线程处理
Messengers.send( 8, 2000, "Hello", mTestReceiver );
```



## EventBoard使用

事件总线

```
// 注册
EventBoard.register( String.class, new OnNewEventListener<String>() {
      @Override
      public void onNewEvent ( String s ) {
            Toast.makeText( MainActivity.this, s, Toast.LENGTH_SHORT ).show();
            Log.i( TAG, "onNewEvent: " + s + " " + this );
      }
} );
```

```
// 发送
EventBoard.sendEvent( "Hello" );
EventBoard.sendEvent( "World" );
```

注册粘性事件

```
EventBoard.registerSticky( String.class, new OnNewEventListener<String>() {
      @Override
      public void onNewEvent ( String s ) {
            Toast.makeText( MainActivity.this, s, Toast.LENGTH_SHORT ).show();
            Log.i( TAG, "onNewEvent: " + s + " " + this );
      }
} );
```

```
// 发行粘性事件
EventBoard.sendStickyEvent( "sticky 1" );
```