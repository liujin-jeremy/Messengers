package tech.threekilogram.messengers.event;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 事件公告栏,使用观察者关注某一个类型的事件,
 * 当事件发生后,调用{@link #sendEvent(Object)}或者{@link #sendStickyEvent(Object)},会回调观察者
 *
 * @author: Liujin
 * @version: V1.0
 * @date: 2018-09-05
 * @time: 11:20
 */
public class EventBoard {

      /**
       * 管理所有监听者
       */
      private static ArrayMap<Class, Set<OnNewEventListener>> sListeners;
      /**
       * 静态事件
       */
      private static ArrayMap<Class, List>                    sStickyEvents;

      /**
       * 注册一个事件监听,当发生新事件时{@link #sendEvent(Object)}会回调该监听,
       * 在不需要的时候使用{@link #unregister(Class, OnNewEventListener)}解除注册
       *
       * @param eventType 事件类型
       * @param listener 监听者
       */
      public static <V> void register ( Class<V> eventType, OnNewEventListener<V> listener ) {

            if( listener == null ) {
                  return;
            }

            if( sListeners == null ) {
                  sListeners = new ArrayMap<>();
            }

            Set<OnNewEventListener> onNewEventListeners = sListeners.get( eventType );
            if( onNewEventListeners == null ) {
                  onNewEventListeners = new ArraySet<>();
                  sListeners.put( eventType, onNewEventListeners );
            }

            onNewEventListeners.add( listener );
      }

      /**
       * 注册一个粘性事件监听,注册时会收到之前发生的粘性事件,当发生粘性事件时{@link #sendStickyEvent(Object)}会回调该监听,
       * 在不需要的时候使用{@link #unregister(Class, OnNewEventListener)}解除注册
       *
       * @param eventType 事件类型
       * @param listener 监听者
       */
      @SuppressWarnings("unchecked")
      public static <V> void registerSticky ( Class<V> eventType, OnNewEventListener<V> listener ) {

            register( eventType, listener );

            if( sStickyEvents == null ) {
                  sStickyEvents = new ArrayMap<>();
                  return;
            }

            List events = sStickyEvents.get( eventType );
            if( events == null ) {
                  return;
            }

            for( Object event : events ) {
                  listener.onNewEvent( (V) event );
            }
      }

      /**
       * 解除一个监听的注册
       *
       * @param eventType 事件类型
       * @param listener 监听者
       */
      public static <V> void unregister ( Class<V> eventType, OnNewEventListener<V> listener ) {

            if( sListeners == null || listener == null ) {
                  return;
            }

            Set<OnNewEventListener> onNewEventListeners = sListeners.get( eventType );
            if( onNewEventListeners != null ) {
                  onNewEventListeners.remove( listener );
            }
      }

      /**
       * 解除一个事件的所有监听者
       *
       * @param eventType 事件类型
       */
      public static <V> void clearListener ( Class<V> eventType ) {

            if( sListeners == null ) {
                  return;
            }
            sListeners.remove( eventType );
      }

      /**
       * 解除所有事件的所有监听者
       */
      public static <V> void clearAllListener ( ) {

            if( sListeners == null ) {
                  return;
            }
            sListeners.clear();
      }

      /**
       * 发布一个新事件,会回调所有注册在该事件上的监听者
       *
       * @param event 新事件
       */
      @SuppressWarnings("unchecked")
      public static <V> void sendEvent ( V event ) {

            if( sListeners == null ) {
                  return;
            }

            Set<OnNewEventListener> onNewEventListeners = sListeners.get( event.getClass() );

            if( onNewEventListeners == null ) {
                  return;
            }

            for( OnNewEventListener onNewEventListener : onNewEventListeners ) {
                  onNewEventListener.onNewEvent( event );
            }
      }

      /**
       * 发布一个新粘性事件,会回调所有注册在该事件上的监听者
       *
       * @param event 新事件
       */
      @SuppressWarnings("unchecked")
      public static <V> void sendStickyEvent ( V event ) {

            if( sStickyEvents == null ) {
                  sStickyEvents = new ArrayMap<>();
            }

            List events = sStickyEvents.get( event.getClass() );
            if( events == null ) {
                  events = new ArrayList();
                  sStickyEvents.put( event.getClass(), events );
            }
            events.add( event );

            sendEvent( event );
      }

      /**
       * 获取一个类型的所有粘性事件,
       *
       * @param eventType 事件类型
       *
       * @return 所有粘性事件 or null
       */
      public static List getStickyEvents ( Class<?> eventType ) {

            return sStickyEvents == null ? null : sStickyEvents.get( eventType );
      }

      /**
       * 新事件发生的回调
       *
       * @param <V> 事件类型
       */
      public interface OnNewEventListener<V> {

            /**
             * 当发生新的事件时回调
             *
             * @param v 事件
             */
            void onNewEvent ( V v );
      }
}
