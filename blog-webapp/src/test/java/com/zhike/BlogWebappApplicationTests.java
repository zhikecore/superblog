package com.zhike;

import com.alibaba.fastjson.JSON;
import com.zhike.model.B;
import com.zhike.model.Banana;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogWebappApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void  encryptTest()
	{
      /*BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
      //加密所需的salt(盐)
      textEncryptor.setPassword("jeffeyhu");
      //要加密的数据（数据库的用户名或密码）
      String username = textEncryptor.encrypt("root");
      String password = textEncryptor.encrypt("bhwcu2a9");
      System.out.println("username:"+username);
      System.out.println("password:"+password);*/

		//加密工具
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		//加密配置
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");

		//生成秘钥的公钥
		config.setPassword("xiaostudy");

		//应用配置
		encryptor.setConfig(config);

		//明文密码
		String username="root";
		String password = "bhwcu2a9";

		//加密
		String enPassword = encryptor.encrypt(password);

		System.out.println(password + "password加密后: " + enPassword);

		String enUsername=encryptor.encrypt(username);
		System.out.print(username + "username加密后: " + enUsername);

		//解密 过程
		//String pText = encryptor.decrypt(ciphertext);
		//System.out.println(ciphertext + "解密后: " + pText);

	}

	@Test
	void  ClassTest()
	{
		Banana banana=new Banana();
	}

	@Test
	void  SerializeTest()
	{
//		Item myItem = new Item(1, "theItem", new User(2, "theUser"));
//		String serialized = new ObjectMapper().writeValueAsString(myItem);

		B b=new B();
		b.setName("zhangsan");
		b.setAge(20);
		b.setScore(100);

		//String serialized = new ObjectMapper().writeValueAsString(b);
		String serialized=JSON.toJSONString(b);
		System.out.println(serialized);
	}
}
