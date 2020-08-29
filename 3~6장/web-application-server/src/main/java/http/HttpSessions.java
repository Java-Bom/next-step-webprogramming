package http;

import java.util.HashMap;
import java.util.Map;

public class HttpSessions {
    private static Map<String, HttpSession> httpSessions = new HashMap<>();

    public static void addSession(HttpSession httpSession) {
        httpSessions.put(httpSession.getId(), httpSession);
    }

    public static HttpSession getSession(String uuid) {
        if (isNotExist(uuid)) {
            HttpSession httpSession = new HttpSession(uuid);
            httpSessions.put(httpSession.getId(), httpSession);
        }
        return httpSessions.get(uuid);
    }

    private static boolean isNotExist(final String uuid) {
        return httpSessions.get(uuid) == null;
    }

    public static void removeSession(String uuid) {
        httpSessions.remove(uuid);
    }
}
