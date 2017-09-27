package duelist.spirifoxy.com.github.utils;

import java.util.Objects;

public final class Utils {

    public static boolean checkPassword(String password1, String password2) {
        //TODO rewrite
        return Objects.equals(password1, password2);
    }
}
