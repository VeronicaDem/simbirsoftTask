package ru.simbirsoft.urltask;

import java.util.List;

public class Util {
    public static String concat(List<Character> chars, Character delimeter) {
        if (chars.size() == 0) {
            return "";
        }
        StringBuilder s = new StringBuilder(chars.size());
        for (int i = 0; i < chars.size(); i++) {
            if(i != 0) {
                s.append(delimeter);
            }
            char c = chars.get(i);
            if(isPatternSymbol(c)) {
                s.append('\\');
            }
            s.append(c);
        }
        return s.toString();
    }
    public static Boolean isPatternSymbol(char symbol) {
        switch(symbol) {
            case '?':
            case '[':
            case ']':
            case '(':
            case ')':
            case '|':
            case '}':
            case '{':
            case '.':
                return true;
            default:
                return false;
        }
    }
}
