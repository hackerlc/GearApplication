package gear.yc.com.gearlibrary.utils;

/**
 * GearApplication
 * Created by YichenZ on 2017/2/7 09:41.
 */

public final class Preconditions {
    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}