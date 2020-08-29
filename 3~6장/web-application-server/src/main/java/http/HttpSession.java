package http;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpSession {
    private final String uuid;
    private Map<String, Object> attributes = new HashMap<>();

    public HttpSession() {
        this.uuid = UUID.randomUUID().toString();
    }

    public HttpSession(final String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return this.uuid;
    }

    public void setAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    public void removeAttribute(String name) {
        this.attributes.remove(name);
    }

    public void invalidate() {
        HttpSessions.removeSession(this.uuid);
    }
}
