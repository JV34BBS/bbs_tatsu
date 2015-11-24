package jp.ac.hal.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.util.codec.binary.StringUtils;

public class Utils {
	
	/**
	 * ログインチェック
	 * @param email
	 * @param passwd
	 * @return
	 */
	public static String loginValidator(String email, String passwd) {
		String err = null;
		
		if (email == null || passwd == null || email.isEmpty() || passwd.isEmpty()) {
			return err = "未入力項目があります";
		}
		
		if (email.length() > 50 || emailValidator(email) || passwd.length() > 30) {
			err = "入力形式が間違っています";
		}
		
		return err;
	}
	
	/**
	 * ユーザ登録チェック
	 * @param userName
	 * @param passwd
	 * @param email
	 * @return
	 */
	public static String registValidator(String userName, String passwd, String email) {
		String err = null;
		
		if (userName == null || email == null || passwd == null || userName.isEmpty() || email.isEmpty() || passwd.isEmpty()) {

			return err = "未入力項目があります";
		}
		
		if (userName.length() > 20 || email.length() > 50 || emailValidator(email) || passwd.length() > 30) {
			err = "入力形式が間違っています";
		}
		
		return err;
	}
	
	/**
	 * コメント入力チェック
	 * @param comment
	 * @return
	 */
	public static String commentValidator(String comment) {
		String err = null;
		comment = comment.trim();
		
		if (comment == null || comment.isEmpty()) {
			
			return err = "コメントが未入力です";
		}
		
		if (comment.length() > 120) {
			err = "コメントは120文字以内で入力してください";
		}
		
		return err;
	}
	
	/**
	 * emailの正規表現
	 * @param email
	 * @return boolean
	 */
	private static boolean emailValidator(String email) {
		boolean ret = false;
		
		String ptnStr ="[\\w\\.\\-]+@(?:[\\w\\-]+\\.)+[\\w\\-]+";
		Pattern ptn = Pattern.compile(ptnStr);
		Matcher mc = ptn.matcher(email);
		ret = (mc.matches()) ? false : true;
		
		return ret;
	}

}
