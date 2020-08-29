package http;

import java.util.HashMap;
import java.util.Map;

public class HttpSessionContainer {
    private static final Map<String, HttpSession> SESSION_MAP = new HashMap<>();

    public static HttpSession getSession(String id) {
        HttpSession session = SESSION_MAP.get(id);
        if (session == null) {
            session = new HttpSession(id);
            SESSION_MAP.put(id, session);
            return session;
        }
        return session;
    }

    public static void remove(String id) {
        SESSION_MAP.remove(id);
    }
}
