package com.study.servlet.config;

import java.util.*;

import static com.study.servlet.constant.ContentConstants.ADMIN;
import static com.study.servlet.constant.ContentConstants.USER;

public class SecurityConfig {

    private static Map<String, List<String>> securityPages = new HashMap<>();

    static {
        securityPages.put(ADMIN, Arrays.asList("/admin.jsp", "/common.jsp"));
        securityPages.put(USER, Arrays.asList("/user.jsp", "/common.jsp"));
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
