package com.sitech.paas.test1;



import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
//	private static final String KEY_MD5 = "MD5";
//	private static MessageDigest md5Digest;
//
//	static {
//		try {
//			md5Digest = MessageDigest.getInstance(KEY_MD5);
//		} catch (NoSuchAlgorithmException e) {
//			//
//		}
//	}
//	/**
//	 *
//	 * 加密
//	 *
//	 * @param content
//	 *            �?要加密的内容
//	 *
//	 * @param keyWord
//	 *            加密密钥
//	 *
//	 * @return byte[] 加密后的字节数组
//	 */
//
//	public static byte[] encrypt256(String content, String keyWord) {
//	 String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
//		try {
//			//创建AES的key生产者
//			//KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			//利用用户密码作为随机数初始化
//		//	kgen.init(256,new SecureRandom(keyWord.getBytes()));
//			//根据用户密码生成一个密钥
//			//SecretKey  secretkey = kgen.generateKey();
//			//返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
//			//byte[] enCodeFormat = secretkey.getEncoded();
//			//转换为AES专用密钥
//		//	SecretKeySpec key = new SecretKeySpec(enCodeFormat,"AES");
//		//    Security.addProvider(new org.bouncycastle.crypto.generators);
//			//创建密码器
//			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//			byte[] byteContent = content.getBytes();
//			//初始化为加密模式的密码器
//			 cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyWord.getBytes(), "AES"));
//			//cipher.init(Cipher.ENCRYPT_MODE, key);
//			byte[] result = cipher.doFinal(byteContent);
//			return result;
//
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	public static byte[] encrypt(String content, String keyWord) {
//		try {
//			KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
//			secureRandom.setSeed(keyWord.getBytes());
//			kgen.init(128, secureRandom);
//			SecretKey secretKey = kgen.generateKey();
//			byte[] enCodeFormat = secretKey.getEncoded();
//			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
//			Cipher cipher = Cipher.getInstance("AES");// 创建密码�?
//			byte[] byteContent = content.getBytes("utf-8");
//			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始�?
//			byte[] result = cipher.doFinal(byteContent);
//			return result; // 加密
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 *
//	 * @param content
//	 *            �?要加密的内容
//	 *
//	 * @param password
//	 *            加密密钥
//	 *
//	 * @return String 加密后的字符�?
//	 */
//	public static String encryptToHex(String content, String password) {
//		return parseByte2HexStr(encrypt(content, password));
//	}
//
//	/**
//	 *
//	 * @param content
//	 *            �?要加密的内容
//	 *
//	 * @param password
//	 *            加密密钥
//	 *
//	 * @return String 加密后的字符�?
//	 * @throws Exception
//	 */
//	public static String encryptToBase64(String content, String password) throws Exception {
//		return Base64Utils.encode(AESUtils.encrypt(content, password));
//	}
//
//	/**
//	 * 解密
//	 *
//	 * @param content
//	 *            待解密内�?
//	 *
//	 * @param keyWord
//	 *            解密密钥
//	 *
//	 * @return byte[]
//	 */
//	public static byte[] decrypt(byte[] content, String keyWord) {
//		try {
//			KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
//			secureRandom.setSeed(keyWord.getBytes());
//			kgen.init(128, secureRandom);
//			SecretKey secretKey = kgen.generateKey();
//			byte[] enCodeFormat = secretKey.getEncoded();
//			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
//			Cipher cipher = Cipher.getInstance("AES");// 创建密码�?
//			cipher.init(Cipher.DECRYPT_MODE, key);// 初始�?
//			byte[] result = cipher.doFinal(content);
//			return result; // 加密
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 *
//	 * @param content
//	 *            待解密内�?(字符�?)
//	 *
//	 * @param keyWord
//	 *            解密密钥
//	 *
//	 * @return byte[]
//	 */
//	public static byte[] decryptHex(String content, String keyWord) {
//		return decrypt(parseHexStr2Byte(content), keyWord);
//	}
//
//	/**
//	 *
//	 * @param content
//	 *            待解密内�?(BASE64字符�?)
//	 *
//	 * @param keyWord
//	 *            解密密钥
//	 *
//	 * @return byte[]
//	 * @throws Exception
//	 */
//	public static byte[] decrypt(String content, String keyWord) throws Exception {
//		return decrypt(Base64Utils.decode(content), keyWord);
//	}
//
//	/**
//	 * 将二进制转换16进制的大写字符串
//	 *
//	 * @param buf
//	 *
//	 * @return String
//	 */
//	public static String parseByte2HexStr(byte buf[]) {
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < buf.length; i++) {
//			String hex = Integer.toHexString(buf[i] & 0xFF);
//			if (hex.length() == 1) {
//				hex = '0' + hex;
//			}
//			sb.append(hex.toUpperCase());
//		}
//		return sb.toString();
//	}
//
//	/**
//	 * �?16进制转换为二进制
//	 *
//	 *
//	 * @return byte[]
//	 */
//	public static byte[] parseHexStr2Byte(String hexString) {
//		if (hexString.length() < 1)
//			return null;
//		byte[] byteArray = new byte[hexString.length()/2];
//    	byte tmpByte;
//	    for(int i=0;i<hexString.length();i=i+2)
//	    {
//	    	tmpByte = new Integer(Integer.parseInt(hexString.substring(i,i+2),16)).byteValue();
//	    	byteArray[i/2] = tmpByte;
//	    }
//    	return byteArray;
//	}
//

	/*private final static String HEX = "0123456789ABCDEF";
	private static final int keyLenght = 16;
	private static final String defaultV = "0";

	*//**
	 * 加密
	 *
	 * @param key
	 *            密钥
	 * @param src
	 *            加密文本
	 * @return
	 * @throws Exception
	 *//*
	public static String encrypt(String key, String src) throws Exception {
		// /src = Base64.encodeToString(src.getBytes(), Base64.DEFAULT);
		byte[] rawKey = toMakekey(key, keyLenght, defaultV).getBytes();// key.getBytes();
		byte[] result = encrypt(rawKey, src.getBytes("utf-8"));
		// result = Base64.encode(result, Base64.DEFAULT);
		return toHex(result);
	}

	*//**
	 * 加密
	 *
	 * @param key
	 *            密钥
	 * @param src
	 *            加密文本
	 * @return
	 * @throws Exception
	 *//*
	public static String encrypt2Java(String key, String src) throws Exception {
		// /src = Base64.encodeToString(src.getBytes(), Base64.DEFAULT);
		byte[] rawKey = toMakekey(key, keyLenght, defaultV).getBytes();// key.getBytes();
		byte[] result = encrypt2Java(rawKey, src.getBytes("utf-8"));
		// result = Base64.encode(result, Base64.DEFAULT);
		return toHex(result);
	}

	*//**
	 * 解密
	 *
	 * @param key
	 *            密钥
	 * @param encrypted
	 *            待揭秘文本
	 * @return
	 * @throws Exception
	 *//*
	public static String decrypt(String key, String encrypted) throws Exception {
		byte[] rawKey = toMakekey(key, keyLenght, defaultV).getBytes();// key.getBytes();
		byte[] enc = toByte(encrypted);
		// enc = Base64.decode(enc, Base64.DEFAULT);
		byte[] result = decrypt(rawKey, enc);
		// /result = Base64.decode(result, Base64.DEFAULT);
		return new String(result, "utf-8");
	}

	*//**
	 * 密钥key ,默认补的数字，补全16位数，以保证安全补全至少16位长度,android和ios对接通过
	 * @param str
	 * @param strLength
	 * @param val
	 * @return
	 *//*
	private static String toMakekey(String str, int strLength, String val) {

		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(str).append(val);
				str = buffer.toString();
				strLen = str.length();
			}
		}
		return str;
	}

	*//**
	 * 真正的加密过程
	 * 1.通过密钥得到一个密钥专用的对象SecretKeySpec
	 * 2.Cipher 加密算法，加密模式和填充方式三部分或指定加密算 (可以只用写算法然后用默认的其他方式)Cipher.getInstance("AES");
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 *//*
	private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
		byte[] encrypted = cipher.doFinal(src);
		return encrypted;
	}

	*//**
	 * 真正的加密过程
	 *
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 *//*
	private static byte[] encrypt2Java(byte[] key, byte[] src) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
		byte[] encrypted = cipher.doFinal(src);
		return encrypted;
	}

	*//**
	 * 真正的解密过程
	 *
	 * @param key
	 * @param encrypted
	 * @return
	 * @throws Exception
	 *//*
	private static byte[] decrypt(byte[] key, byte[] encrypted) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	public static String toHex(String txt) {
		return toHex(txt.getBytes());
	}

	public static String fromHex(String hex) {
		return new String(toByte(hex));
	}


	*//**
	 * 把16进制转化为字节数组
	 * @param hexString
	 * @return
	 *//*
	public static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		return result;
	}


	*//**
	 * 二进制转字符,转成了16进制
	 * 0123456789abcdefg
	 * @param buf
	 * @return
	 *//*
	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}

	*//**
	 * 初始化 AES Cipher
	 * @param sKey
	 * @param cipherMode
	 * @return
	 *//*
	public static Cipher initAESCipher(String sKey, int cipherMode) {
		// 创建Key gen
		// KeyGenerator keyGenerator = null;
		Cipher cipher = null;
		try {
			*//*
			 * keyGenerator = KeyGenerator.getInstance("AES");
			 * keyGenerator.init(128, new SecureRandom(sKey.getBytes()));
			 * SecretKey secretKey = keyGenerator.generateKey(); byte[]
			 * codeFormat = secretKey.getEncoded(); SecretKeySpec key = new
			 * SecretKeySpec(codeFormat, "AES"); cipher =
			 * Cipher.getInstance("AES"); //初始化 cipher.init(cipherMode, key);
			 *//*
			byte[] rawKey = toMakekey(sKey, keyLenght, defaultV).getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
			cipher = Cipher.getInstance("AES");
			cipher.init(cipherMode, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();  // To change body of catch statement use File |
			// Settings | File Templates.
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();  // To change body of catch statement use File |
			// Settings | File Templates.
		} catch (InvalidKeyException e) {
			e.printStackTrace();  // To change body of catch statement use File |
			// Settings | File Templates.
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cipher;
	}

	*//**
	 * 对文件进行AES加密
	 * @param sourceFile
	 * @param sKey
	 * @return
	 *//*
	public static File encryptFile(File sourceFile, String toFile, String dir, String sKey) {
		// 新建临时加密文件
		File encrypfile = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(sourceFile);
			encrypfile = new File(dir + toFile);
			outputStream = new FileOutputStream(encrypfile);
			Cipher cipher = initAESCipher(sKey, Cipher.ENCRYPT_MODE);
			// 以加密流写入文件
			CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
			byte[] cache = new byte[1024];
			int nRead = 0;
			while ((nRead = cipherInputStream.read(cache)) != -1) {
				outputStream.write(cache, 0, nRead);
				outputStream.flush();
			}
			cipherInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();  // To change body of catch statement use File |
			// Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace();  // To change body of catch statement use File |
			// Settings | File Templates.
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();  // To change body of catch statement use
				// File | Settings | File Templates.
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();  // To change body of catch statement use
				// File | Settings | File Templates.
			}
		}
		return encrypfile;
	}

	*//**
	 * AES方式解密文件
	 * @param sourceFile
	 * @return
	 *//*
	public static File decryptFile(File sourceFile, String toFile, String dir, String sKey) {
		File decryptFile = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			decryptFile = new File(dir + toFile);
			Cipher cipher = initAESCipher(sKey, Cipher.DECRYPT_MODE);
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(decryptFile);
			CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = inputStream.read(buffer)) >= 0) {
				cipherOutputStream.write(buffer, 0, r);
			}
			cipherOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();  // To change body of catch statement use File |
			// Settings | File Templates.
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();  // To change body of catch statement use
				// File | Settings | File Templates.
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();  // To change body of catch statement use
				// File | Settings | File Templates.
			}
		}
		return decryptFile;
	}*/


	/**
	 * 加密
	 * @param encryptString  待加密内容
	 * @param password 解密密钥
	 * @return
	 */
	public static String encrypt(String encryptString,String passWord){
		String result =null;
		try {
			// 生成Key
			KeyGenerator keyGenerator;
			keyGenerator = KeyGenerator.getInstance("AES");
			// 使用这种初始化方法可以特定种子来生成密钥，这样加密后的密文是唯一固定的。
			//keyGenerator.init(128, new SecureRandom(passWord.getBytes()));
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
			secureRandom.setSeed(passWord.getBytes());
			keyGenerator.init(128,secureRandom);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			// Key转换
			Key key = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encodeResult = cipher.doFinal(encryptString.getBytes());
			result = parseByte2HexStr(encodeResult); ;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 解密
	 * @param decryptString  待解密内容
	 * @return
	 */
	public static String decrypt(String decryptString,String passWord){
		String result = null;
		try {
			byte[] decryptBytes = parseHexStr2Byte(decryptString);
			// 生成Key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			// 使用这种初始化方法可以特定种子来生成密钥，这样加密后的密文是唯一固定的。
			//keyGenerator.init(128, new SecureRandom(passWord.getBytes()));
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
			secureRandom.setSeed(passWord.getBytes());
			keyGenerator.init(128,secureRandom);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			// Key转换
			Key key = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decodeResult = cipher.doFinal(decryptBytes);
			result = new String(decodeResult);
		}catch(NumberFormatException e){
			e.printStackTrace();
			System.out.println("AES解密参数非法！");
			return "";
		}
		catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**将二进制转换成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
