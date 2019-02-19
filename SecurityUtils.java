/*
 * Class       : SecurityUtils
 * Description : Encrypt/decrypt/hash methods
 * Created by  : vv0z <i.beshkar@gmail.com> 2018/09/07
 */

package com.yanagroup.gamer.iBHelper;

import android.annotation.SuppressLint;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class SecurityUtils {

    private String source;

    private SecurityUtils(String source) {
        this.source = source;
    }

    public static SecurityUtils getSource(String source) {
        return new SecurityUtils(source);
    }

    /**
     * Hash source string to MD5 algorithm
     */
    public String _toMD5() {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(source.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                StringBuilder h = new StringBuilder(Integer.toHexString(0xFF & aMessageDigest));
                while (h.length() < 2)
                    h.insert(0, "0");
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Hash source string to SHA-1 algorithm
     */
    public String _toSHA1() {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(source.getBytes("UTF-8"));
            byte[] bytes = messageDigest.digest();
            StringBuilder buffer = new StringBuilder();
            for (byte b : bytes) {
                buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return buffer.toString();
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return null;
        }

    }

    /**
     * Hash source string to SHA-256 algorithm
     */
    public String _toSHA256() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(source.getBytes());
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                String hex = Integer.toHexString(0xFF & aByte);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Encryption method
     */
    @SuppressLint("GetInstance")
    public String Encrypt() {

        String cryptoPass = "<select any words>";

        try {
            DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] clearText = source.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // Encrypted source to encryptedValue
            return Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);

        } catch (InvalidKeyException | UnsupportedEncodingException | InvalidKeySpecException |
                BadPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException |
                NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return source;
    }

    /**
     * Decryption method
     */
    @SuppressLint("GetInstance")
    public String Decrypt() {

        String cryptoPass = "<select any words>";

        try {
            DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] encryptedPwdBytes = Base64.decode(source, Base64.DEFAULT);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValueBytes = (cipher.doFinal(encryptedPwdBytes));

            // Decrypted source to decryptedValue
            return new String(decryptedValueBytes);

        } catch (InvalidKeyException | UnsupportedEncodingException | InvalidKeySpecException |
                BadPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException |
                NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return source;
    }

}
