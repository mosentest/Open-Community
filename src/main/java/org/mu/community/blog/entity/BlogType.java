package org.mu.community.blog.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muu on 2014/9/25.
 */
public enum BlogType {

    ORIGINAL("O", "Original"),
    TRANSLATION("T", "Translation"),
    RESOURCE("R", "Resource");

    private static final Map<String, BlogType> cache = new HashMap<>();

    static {
        for (BlogType type : values()) {
            cache.put(type.ident, type);
        }
    }

    private final String ident;

    private final String key;

    private BlogType(String ident, String key) {
        this.ident = ident;
        this.key = key;
    }

    public String getIdent() {
        return ident;
    }

    public String getKey() {
        return key;
    }

    public static BlogType getEnumType(String ident) {
        return cache.get(ident);
    }

}
