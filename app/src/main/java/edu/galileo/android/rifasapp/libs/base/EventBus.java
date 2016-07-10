package edu.galileo.android.rifasapp.libs.base;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
