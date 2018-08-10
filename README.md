# 通信框架

底层封装Handler实现通信框架



## 使用

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

2. 发行消息

```
// 空白消息,接受者会收到 1
Messengers.send( 1, mTestReceiver );
```

```
// 延时消息,2s之后,接受者收到 3
Messengers.send( 3, 2000, mTestReceiver );
```

```
// 携带额外消息,2s之后,接收者收到 7 和 额外信息 "Hello"
Messengers.send( 7, 2000, "Hello", mTestReceiver );
```