package untitled2;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by epic on 24.4.2014.
 */
public class SessionCounterListener implements HttpSessionListener, HttpSessionActivationListener {

    private static int totalActiveSessions;

    public static int getTotalActiveSession(){
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        totalActiveSessions++;
        System.out.println("sessionCreated - add one session into counter " + totalActiveSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        totalActiveSessions--;
        System.out.println("sessionDestroyed - deduct one session from counter " + totalActiveSessions);
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("sessionPassivated");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("sessionActivated");
    }
}
