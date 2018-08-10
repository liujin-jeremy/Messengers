package tech.threekilogram.messengerslib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import tech.threekilogram.messengers.Messengers;
import tech.threekilogram.messengers.OnMessageReceiveListener;

/**
 * @author liujin
 */
public class MainActivity extends AppCompatActivity {

      private static final String TAG = MainActivity.class.getSimpleName();

      private TestReceiver mTestReceiver;

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_main );

            mTestReceiver = new TestReceiver();
      }

      public void empty ( View view ) {

            Messengers.send( 1, mTestReceiver );
      }

      public void delay ( View view ) {

            Messengers.send( 3, 2000, mTestReceiver );
      }

      public void take ( View view ) {

            Messengers.send( 7, 2000, "Hello", mTestReceiver );
      }

      public void delete ( View view ) {

            Messengers.remove( 3, mTestReceiver );
      }

      public void emptyMessenger ( View view ) {

            Messengers.send( 2, mTestReceiver );
      }

      public void delayMessenger ( View view ) {

            Messengers.send( 4, 2000, mTestReceiver );
      }

      public void takeMessenger ( View view ) {

            Messengers.send( 8, 2000, "Hello", mTestReceiver );
      }

      public void deleteMessenger ( View view ) {

            Messengers.remove( 4, mTestReceiver );
      }

      /**
       * 测试消息接收
       */
      private class TestReceiver implements OnMessageReceiveListener {

            @Override
            public void onReceive ( int what, Object extra ) {

                  Log.e( TAG, "onReceive : "
                      + what + " "
                      + extra + " "
                      + Thread.currentThread().getName()
                  );
            }
      }
}
