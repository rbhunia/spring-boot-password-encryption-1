package org.example.springbootpasswordencryption;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

public class PasswordEncryptorUtil {

    private static final String ALGORITHM = "PBEWITHHMACSHA512ANDAES_256";
    //private static final String ENCRYPTION_PASSWORD = System.getenv("JASYPT_ENCRYPTOR_PASSWORD"); // Fetch from environment variables
    private static final String ENCRYPTION_PASSWORD = "secretKey"; // Fetch from environment variables

    private static PooledPBEStringEncryptor encryptor;

    // Static block to configure the encryptor
    static {
        if (ENCRYPTION_PASSWORD == null || ENCRYPTION_PASSWORD.isEmpty()) {
            throw new IllegalStateException("Encryption password is not set. Please set the JASYPT_ENCRYPTOR_PASSWORD environment variable.");
        }

        encryptor = new PooledPBEStringEncryptor();
        encryptor.setAlgorithm(ALGORITHM);
        encryptor.setPassword(ENCRYPTION_PASSWORD); // Use the password for encryption/decryption
        encryptor.setPoolSize(4); // For better performance in multi-threaded applications
        encryptor.setIvGenerator(new RandomIvGenerator()); // Use a random initialization vector (IV) for better security
    }

    // Method to encrypt a plain text password
    public static String encrypt(String plainText) {
        if (plainText == null || plainText.isEmpty()) {
            throw new IllegalArgumentException("Input text to be encrypted is null or empty.");
        }
        return encryptor.encrypt(plainText);
    }

    // Method to decrypt an encrypted password
    public static String decrypt(String encryptedText) {
        if (encryptedText == null || encryptedText.isEmpty()) {
            throw new IllegalArgumentException("Encrypted text to be decrypted is null or empty.");
        }
        return encryptor.decrypt(encryptedText);
    }
}
