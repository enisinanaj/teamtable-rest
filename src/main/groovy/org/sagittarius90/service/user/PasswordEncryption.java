package org.sagittarius90.service.user;

import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryption {
    public enum Format {
        SHA256("sha2") {
            @Override
            public String convert(String value) {
                return DigestUtils.sha256Hex(value);
            }
        },
        SHA1("sha1") {
            @Override
            public String convert(String value) {
                return DigestUtils.sha1Hex(value);
            }
        },
        MD5("md5") {
            @Override
            public String convert(String value) {
                return DigestUtils.md5Hex(value);
            }
        };;

        String alias;

        Format(String alias) {
            this.alias = alias;
        }

        public static Format getFromAlias(String alias) {
            for(Format el: Format.values()) {
                if (el.getAlias().equals(alias)) {
                    return el;
                }
            }

            throw new RuntimeException("Requested format is not supported.");
        }

        public String getAlias() {
            return alias;
        }

        public String convert(String value) {
            return null;
        }
    }
}
