package ibatis.config;

import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactroy {
	
	private static SqlMapClient smc;
	
	static {
		try {
			
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("ibatis/config/SqlMapConfig.xml");
			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSMClient() {
		return smc;
	}
	
}
