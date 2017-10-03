package duelist.spirifoxy.com.github.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Objects;

public final class Utils {

    private static final int iterations = 10000;
    private static final int saltLength = 32;
    private static final int keyLength = 256;

    private static int lastRoomNumber = 1;

    public static boolean checkPassword(String password, String stored) throws Exception{
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            return false;
//            throw new IllegalStateException(
//                    "Формат записи: 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLength);
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("Пустой пароль не допускается");
        }
        SecretKey key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(
                new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength)
        );
        return Base64.encodeBase64String(key.getEncoded());
    }

    public static int getNextRoomNumber() {
        return lastRoomNumber++;
    }
}
