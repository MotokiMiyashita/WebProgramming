package Controller;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Util {

	public static String hashPass(String source) {
		try {
			//ハッシュ生成前にバイト配列に置き換える際のCharset
			Charset charset = StandardCharsets.UTF_8;
			//ハッシュアルゴリズム
			String algorithm = "MD5";
			//ハッシュ生成処理
			byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
			String result = DatatypeConverter.printHexBinary(bytes);
			return result;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * パラメータに空があった場合trueを返す
	 * @param params
	 * @return
	 */
	public static boolean isEmptyCheck(String[] params) {
		boolean result = false;
		for (String param : params) {
			if (param.isEmpty()) {
				result = true;
				break;
			}
		}
		return result;
	}


}
