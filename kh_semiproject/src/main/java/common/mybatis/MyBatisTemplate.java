package common.mybatis;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisTemplate {
	public static SqlSession getSession(boolean a) {
		SqlSession session=null;
		try {
			SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
			session = factory.openSession(a);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	public static SqlSession getSession() {
		SqlSession session=null;
		try {
			SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
			session = factory.openSession(true);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
}



