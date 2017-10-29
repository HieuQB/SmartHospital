package com.uit.tahitu.hci.smarthospital.utils;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static boolean isEmailValid(String email) {
        if (TextUtils.isEmpty(email)) return false;

        String expression = "(\\<|^)[a-zA-Z0-9]+[\\.]{0,1}[a-zA-Z0-9]+@(?:[a-zA-Z0-9]+\\.)+(\\w{2,})(\\>|$)";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    public static boolean isPasswordValid(String pass) {
        if (pass != null) {
            pass = pass.trim();
        }
        if (TextUtils.isEmpty(pass)) return false;

        String expression = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*()?&])[A-Za-z\\d$@$!%*?&()]{8,}";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pass);

        return matcher.matches();
    }

    public static boolean isIdValid(String id) {
        if (TextUtils.isEmpty(id)) return false;

        String expression = "[A-z0-9_]{1,}";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(id);

        return matcher.matches();
    }

    public static boolean isNameValid(String name) {
        if (TextUtils.isEmpty(name)) return false;

        String expression = "[A-z0-9_]{1,}";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }
}
