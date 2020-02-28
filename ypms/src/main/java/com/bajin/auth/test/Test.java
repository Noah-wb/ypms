package com.bajin.auth.test;

import com.bajin.auth.entity.UserInfo;
import com.bajin.auth.utils.JwtUtils;
import com.bajin.auth.utils.RsaUtils;

public class Test {

	public static void main(String[] args) throws Exception {
		/**
		 * 	生成公钥和私钥
		 */
		RsaUtils.generateKey("F:\\rsa\\rsa.pub", "F:\\rsa\\rsa.pri", "963789");

	}
	
	/**
	 * 	生成token
	 * @throws Exception
	 */
	//@org.junit.Test
	public void generateToken() throws Exception {
		String generateToken = JwtUtils.generateToken(new UserInfo(20L,"admin"), RsaUtils.getPrivateKey("F:\\\\rsa\\\\rsa.pri"), 30);
		System.out.println(generateToken);
	}
	
	//@org.junit.Test
	public void yanzhengToken() throws Exception {
		String  token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiYWRtaW4iLCJleHAiOjE1Njg2Mjg2OTd9.PwFlvvBS5Bz0FIu6-AGHiW-k-nt9mTgcoQaz5jfM3HdrOimrXyXpfYtexSdfQ8sxGGPQs-YRpNR5UzUh0Rft0_QAIKoo9gyZhafLpoTg8nXqs8c30Qd5LGQFjSpktfN0biauo8KWQena0R9HccqiK2k_j-FlOVrnhbMHutHF4ew";
		UserInfo userInfo = JwtUtils.getUserInfo(RsaUtils.getPublicKey("F:\\rsa\\rsa.pub"), token);
		System.out.println(userInfo);
	}

}
