package org.sagittarius90.service.utente;

import org.sagittarius90.database.entity.Utente;
import org.sagittarius90.service.user.PasswordEncryption.Format;

import java.util.StringTokenizer;

public class PasswordUtil {

    private static final String PASSWORD_ENCRYPTION_DELIMITER = "+";
    private static final Format passwordEncryptionFormat = Format.SHA256;
    private Utente utente;

    private String persistentPassword;
    private String persistentSalt;

    public PasswordUtil(Utente utente) {
        this.utente = utente;
        explodePassword();
    }

    public boolean isValid(String password) {
        return encodeForUserSalt(password).equals(persistentPassword);
    }

    private void explodePassword() {
        checkIfUtenteExists();
        StringTokenizer tokenizer = new StringTokenizer(utente.getPassword(), PASSWORD_ENCRYPTION_DELIMITER, false);

        if (tokenizer.countTokens() == 2) {
            this.persistentPassword = tokenizer.nextToken();
            this.persistentSalt = tokenizer.nextToken();
        } else {
            this.persistentPassword = user.getPassword();
            this.persistentSalt = "";
        }
    }

    private void checkIfUtenteExists() {
        if (this.utente == null) {
            throw new RuntimeException("No session base given for password util!");
        }
    }

    private String getPersistentSalt() {
        if (persistentSalt == null) {
            explodePassword();
        }

        return persistentSalt;
    }

    public String encodeForUserSalt(String value) {
        System.out.println("Persistent salt is: " + getPersistentSalt());

        if (getPersistentSalt() == null || "".equals(getPersistentSalt())) {
            return value;
        }

        return encodeString(value + getPersistentSalt());
    }

    public void setPersistentSalt(String persistentSalt) {
        this.persistentSalt = persistentSalt;
    }

    public static String encodeString(String value) {
        return passwordEncryptionFormat.convert(value);
    }

    public void updatePasswordForUser(String newPassword) {
        String newPasswordEncoded = encodeForUserSalt(newPassword);
        user.setPassword(newPasswordEncoded + PASSWORD_ENCRYPTION_DELIMITER + persistentSalt);
    }
}
