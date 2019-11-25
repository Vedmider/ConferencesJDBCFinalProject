package com.study.web.config;

import java.util.*;

import static com.study.web.constant.ContentConstants.*;
import static com.study.web.constant.PathConstants.SLASH_ADMINISTRATION;
import static com.study.web.constant.PathConstants.SLASH_SPEAKERS;

public class SecurityConfig {

    private static Map<String, List<String>> securityPages = new HashMap<>();

    static {
        securityPages.put(ADMIN, Arrays.asList(SLASH_ADMINISTRATION, SLASH_SPEAKERS));
        securityPages.put(USER, Arrays.asList(SLASH_SPEAKERS));
        securityPages.put(SPEAKER, Arrays.asList(SLASH_SPEAKERS, SLASH_ADMINISTRATION));
        securityPages.put(MODERATOR, Arrays.asList(SLASH_SPEAKERS, SLASH_ADMINISTRATION));
    }

    public static boolean isSecurePage(String page) {
        return securityPages.values().stream()
                .anyMatch(list -> list.stream()
                        .anyMatch(pageValue -> pageValue.equals(page)));
    }

    public static boolean hasPermission(String page, String roleTitle) {
        return securityPages.getOrDefault(roleTitle, Collections.EMPTY_LIST)
                .stream()
                .anyMatch(securePage -> securePage.equals(page));
    }

}
