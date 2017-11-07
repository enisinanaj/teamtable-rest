package org.sagittarius90.service.user;

public class PasswordHelper {

    private String password;
    private String salt;

    private String persistentPassword;
    private String persistentSalt;

    public PasswordHelper(String salt) {
        this.salt = salt;
    }

    public PasswordHelper() {
    }

    public boolean isValid() {
        return false;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersistentPassword() {
        return persistentPassword;
    }

    public void setPersistentPassword(String persistentPassword) {
        this.persistentPassword = persistentPassword;
    }

    public String getPersistentSalt() {
        return persistentSalt;
    }

    public void setPersistentSalt(String persistentSalt) {
        this.persistentSalt = persistentSalt;
    }
}
