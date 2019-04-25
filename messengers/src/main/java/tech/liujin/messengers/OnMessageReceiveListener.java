package tech.liujin.messengers;

/**
 * @author wuxio 2018-05-01:20:37
 */
public interface OnMessageReceiveListener {

      /**
       * receive a message,尽量不执行复杂操作
       *
       * @param what signal
       * @param extra extra info
       */
      void onReceive ( int what, Object extra );
}
