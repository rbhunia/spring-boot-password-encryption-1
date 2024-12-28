package org.example.springbootpasswordencryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import static org.example.springbootpasswordencryption.PasswordEncryptorUtil.decrypt;
import static org.example.springbootpasswordencryption.PasswordEncryptorUtil.encrypt;

public class JasyptUtil {

    // Test the Utility Methods
    public static void main(String[] args) {
        String plainText = "mySecretPassword";

        // Encrypt the plain text
        String encryptedText = encrypt(plainText);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the encrypted text
        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        // Validate the decrypted text match
        if (plainText.equals(decryptedText)) {
            System.out.println("Encryption/Decryption works correctly!");
        } else {
            System.out.println("Something went wrong!");
        }
    }
}