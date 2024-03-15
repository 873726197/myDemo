package com.own.web;


import java.util.regex.*;

/**
 * @author liuchang
 * @date 2024-03-05 14:37
 */

public class HostValidator {
    public static boolean validateIP(String input) {
        // IPv4 address regex pattern
        String ipv4Regex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        // IPv6 address regex pattern
        String ipv6Regex = "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$";
        // Hostname regex pattern
        String hostnameRegex = "^(?=.{1,255}$)([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])" +
                "(\\.[a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])*(\\.[a-zA-Z]{2,})$";

        Pattern ipv4Pattern = Pattern.compile(ipv4Regex);
        Pattern ipv6Pattern = Pattern.compile(ipv6Regex);
        Pattern hostnamePattern = Pattern.compile(hostnameRegex);

        Matcher ipv4Matcher = ipv4Pattern.matcher(input);
        Matcher ipv6Matcher = ipv6Pattern.matcher(input);
        Matcher hostnameMatcher = hostnamePattern.matcher(input);

        return ipv4Matcher.matches() || ipv6Matcher.matches() || hostnameMatcher.matches();
    }

    public static void main(String[] args) {
        String host1 = "example.com";
        String host2 = "subdomain.example.com";
        String host3 = "991.0.0.0"; // IP address

        System.out.println(host1 + " is valid: " + validateIP(host1));
        System.out.println(host2 + " is valid: " + validateIP(host2));
        System.out.println(host3 + " is valid: " + validateIP(host3));
    }
}
