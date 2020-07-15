package com.pumsac.TestDAO;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.pumsac.vo.LoginLogVO;

@ExtendWith(MockitoExtension.class)
@Transactional
@ContextConfiguration(locations = { "classpath:com/pumsac/context/dataSource-context.xml","classpath:com/pumsac/context/servlet-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginLogDAOTest {
	@Mock
//	LoginLogDao loginLogDao;
	@Autowired
	private MockMvc  mockMvc;

	LoginLogVO loginLogVO = new LoginLogVO();

	/*
	 * @BeforeEach public void setLoginLogDao() throws Exception {
	 * 
	 * loginLogVO.setAccessId("admin@admin.com"); // / loginLogVO.setNo(no);
	 * loginLogVO.setIpAddress("0.0.0.1"); }
	 */

	@Test
	@DisplayName("로그인 로그 시퀀스 값  테스트 ")
	@Rollback(true)
	public void test() throws Exception {
//		int no = loginLogDao.selectLogLogSeq();
//		assertEquals(no, 2);
	
	}
	@Test 
	@DisplayName("로그인로그 insert 테스트")
	@Rollback(true)
	public void test1() throws Exception{
		
	}

}
