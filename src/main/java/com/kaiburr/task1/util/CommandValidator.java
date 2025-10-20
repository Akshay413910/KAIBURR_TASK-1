package com.kaiburr.task1.util;

public final class CommandValidator {
    private CommandValidator() {}

    /**
     * Return true when the command is considered safe for execution.
     * - null/blank => false
     * - disallow newlines
     * - disallow dangerous shell metacharacters: ; & | ` $ > < \ 
     * - otherwise allow (simple commands with args)
     */
    public static boolean isSafe(String cmd) {
        if (cmd == null || cmd.isBlank()) return false;
        // no newlines
        if (cmd.matches(".*[\\r\\n].*")) return false;
        // block common dangerous metacharacters and command separators
        if (cmd.matches(".*[;&|`$><\\\\].*")) return false;
        // allow the rest (letters, numbers, spaces, basic punctuation)
        return true;
    }
}
