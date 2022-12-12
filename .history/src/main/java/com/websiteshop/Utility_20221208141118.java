package com.websiteshop;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static String getSizeURL(HttpServletRequest request) {
        String siteURL = request.getRequestURI().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
