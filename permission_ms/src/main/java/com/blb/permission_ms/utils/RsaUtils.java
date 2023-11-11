package com.blb.permission_ms.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA工具类
 */
public class RsaUtils {

    public static final String RSA_SECRET = "blbweb@#$%"; //秘钥
    public static final String RSA_PATH = System.getProperty("user.dir")+"/rsa/";//秘钥保存位置
    public static final String RSA_PUB_KEY_PATH = RSA_PATH + "pubKey.rsa";//公钥路径
    public static final String RSA_PRI_KEY_PATH = RSA_PATH + "priKey.rsa";//私钥路径

    public static PublicKey publicKey; //公钥
    public static PrivateKey privateKey; //私钥

    /**
     * 类加载后，生成公钥和私钥文件
     */
    static {
        try {
            File rsa = new File(RSA_PATH);
            if (!rsa.exists()) {
                rsa.mkdirs();
            }
            File pubKey = new File(RSA_PUB_KEY_PATH);
            File priKey = new File(RSA_PRI_KEY_PATH);
            //判断公钥和私钥如果不存在就创建
            if (!priKey.exists() || !pubKey.exists()) {
                //创建公钥和私钥文件
                RsaUtils.generateKey(RSA_PUB_KEY_PATH, RSA_PRI_KEY_PATH, RSA_SECRET);
            }
            //读取公钥和私钥内容
            publicKey = RsaUtils.getPublicKey(RSA_PUB_KEY_PATH);
            privateKey = RsaUtils.getPrivateKey(RSA_PRI_KEY_PATH);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 从文件中读取公钥
     *
     * @param filename 公钥保存路径，相对于classpath
     * @return 公钥对象
     * @throws Exception
     */
    public static PublicKey getPublicKey(String filename) throws Exception {
        byte[] bytes = readFile(filename);
        return getPublicKey(bytes);
    }

    /**
     * 从文件中读取密钥
     *
     * @param filename 私钥保存路径，相对于classpath
     * @return 私钥对象
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String filename) throws Exception {
        byte[] bytes = readFile(filename);
        return getPrivateKey(bytes);
    }

    /**
     * 获取公钥
     *
     * @param bytes 公钥的字节形式
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(byte[] bytes) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    /**
     * 获取密钥
     *
     * @param bytes 私钥的字节形式
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(byte[] bytes) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    /**
     * 根据密文，生存rsa公钥和私钥,并写入指定文件
     *
     * @param publicKeyFilename  公钥文件路径
     * @param privateKeyFilename 私钥文件路径
     * @param secret             生成密钥的密文
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static void generateKey(String publicKeyFilename, String privateKeyFilename, String secret) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 获取公钥并写出
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        writeFile(publicKeyFilename, publicKeyBytes);
        // 获取私钥并写出
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        writeFile(privateKeyFilename, privateKeyBytes);
    }

    private static byte[] readFile(String fileName) throws Exception {
        return Files.readAllBytes(new File(fileName).toPath());
    }

    private static void writeFile(String destPath, byte[] bytes) throws IOException {
        File dest = new File(destPath);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        Files.write(dest.toPath(), bytes);
    }
}