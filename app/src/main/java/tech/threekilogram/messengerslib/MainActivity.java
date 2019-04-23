package tech.threekilogram.messengerslib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import tech.threekilogram.messengers.EventBoard;
import tech.threekilogram.messengers.EventBoard.OnNewEventListener;
import tech.threekilogram.messengers.Messengers;
import tech.threekilogram.messengers.OnMessageReceiveListener;

/**
 * @author liujin
 */
public class MainActivity extends AppCompatActivity implements OnClickListener {

      private static final String TAG = MainActivity.class.getSimpleName();

      private TestReceiver mTestReceiver;
      private Button       mEvent0;
      private Button       mEvent1;
      private Button       mButton;
      private Button       mButton2;
      private Button       mButton3;
      private Button       mButton4;
      private TextView     mTextView;
      private Button       mButton5;
      private Button       mButton6;
      private Button       mButton7;
      private Button       mButton8;
      private TextView     mTextView2;
      private Button       mButton12;
      private Button       mButton13;
      private Button       mButton14;

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_main );
            initView();

            mTestReceiver = new TestReceiver();
      }

      private void initView ( ) {

            mEvent0 = (Button) findViewById( R.id.event0 );
            mEvent0.setOnClickListener( this );
            mEvent1 = (Button) findViewById( R.id.event1 );
            mEvent1.setOnClickListener( this );

            EventBoard.register( String.class, new OnNewEventListener<String>() {

                  @Override
                  public void onNewEvent ( String s ) {

                        Toast.makeText( MainActivity.this, s, Toast.LENGTH_SHORT ).show();
                        Log.i( TAG, "onNewEvent: " + s + " " + this );
                  }
            } );

            EventBoard.registerSticky( String.class, new OnNewEventListener<String>() {

                  @Override
                  public void onNewEvent ( String s ) {

                        Toast.makeText( MainActivity.this, s, Toast.LENGTH_SHORT ).show();
                        Log.i( TAG, "onNewEvent: " + s + " " + this );
                  }
            } );
            mButton = (Button) findViewById( R.id.button );
            mButton.setOnClickListener( this );
            mButton2 = (Button) findViewById( R.id.button2 );
            mButton2.setOnClickListener( this );
            mButton3 = (Button) findViewById( R.id.button3 );
            mButton3.setOnClickListener( this );
            mButton4 = (Button) findViewById( R.id.button4 );
            mButton4.setOnClickListener( this );
            mTextView = (TextView) findViewById( R.id.textView );
            mButton5 = (Button) findViewById( R.id.button5 );
            mButton5.setOnClickListener( this );
            mButton6 = (Button) findViewById( R.id.button6 );
            mButton6.setOnClickListener( this );
            mButton7 = (Button) findViewById( R.id.button7 );
            mButton7.setOnClickListener( this );
            mButton8 = (Button) findViewById( R.id.button8 );
            mButton8.setOnClickListener( this );
            mTextView2 = (TextView) findViewById( R.id.textView2 );
            mButton12 = (Button) findViewById( R.id.button12 );
            mButton12.setOnClickListener( this );
            mButton13 = (Button) findViewById( R.id.button13 );
            mButton13.setOnClickListener( this );
            mButton14 = (Button) findViewById( R.id.button14 );
            mButton14.setOnClickListener( this );
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

      @Override
      public void onClick ( View v ) {

            switch( v.getId() ) {
                  case R.id.event0:
                        EventBoard.sendEvent( "Hello" );
                        break;
                  case R.id.event1:
                        EventBoard.sendEvent( "World" );
                        break;
                  case R.id.button12:
                        EventBoard.sendStickyEvent( "sticky 0" );
                        break;
                  case R.id.button13:
                        EventBoard.sendStickyEvent( "sticky 1" );
                        break;
                  case R.id.button14:
                        EventBoard.registerSticky( String.class, new OnNewEventListener<String>() {

                              @Override
                              public void onNewEvent ( String s ) {

                                    Toast.makeText( MainActivity.this, s, Toast.LENGTH_SHORT ).show();
                                    Log.i( TAG, "onNewEvent: " + s + " " + this );
                              }
                        } );
                        break;
                  default:
                        break;
            }
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
