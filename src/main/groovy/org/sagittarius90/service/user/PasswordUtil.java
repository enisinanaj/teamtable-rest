package org.sagittarius90.service.user;

import org.sagittarius90.database.entity.User;
import org.sagittarius90.service.user.PasswordEncryption.Format;

import java.util.StringTokenizer;

public class PasswordUtil {

    private static final String PASSWORD_ENCRYPTION_DELIMITER = "+";
    private static final Format passwordEncryptionFormat = Format.SHA256;
    private User user;

    private String persistentPassword;
    private String persistentSalt;

    public PasswordUtil(User user) {
        this.user = user;
        explodePassword();
    }

    public boolean isValid(String password) {
        System.out.println("Encrypted principal token: " + encodeForUserSalt(password));
        System.out.println("Persistent password: " + persistentPassword);

        return encodeForUserSalt(password).equals(persistentPassword);
    }

    private void explodePassword() {
        checkIfUserExists();
        StringTokenizer tokenizer = new StringTokenizer(user.getPassword(), PASSWORD_ENCRYPTION_DELIMITER, false);

        if (tokenizer.countTokens() == 2) {
            this.persistentPassword = tokenizer.nextToken();
            this.persistentSalt = tokenizer.nextToken();
        } else {
            this.persistentPassword = user.getPassword();
            this.persistentSalt = "";
        }
    }

    private void checkIfUserExists() {
        if (this.user == null) {
            throw new RuntimeException("No session base given for password util!");
        }
    }

    private String getPersistentSalt() {
        if (persistentSalt == null) {
            explodePassword();
        }

        return persistentSalt;
    }

    private String encodeForUserSalt(String value) {
        System.out.println("Persistent salt is: " + getPersistentSalt());

        if (getPersistentSalt() == null || "".equals(getPersistentSalt())) {
            return value;
        }

        return encodeString(value + getPersistentSalt());
    }

    public static String encodeString(String value) {
        return passwordEncryptionFormat.convert(value);
    }
}
