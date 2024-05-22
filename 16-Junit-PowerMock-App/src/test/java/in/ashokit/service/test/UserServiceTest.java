package in.ashokit.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import in.ashokit.dao.UserDao;
import in.ashokit.service.UserService;

public class UserServiceTest {

	@Test
	public void getNameTest() {

		// creating mock object
		UserDao userDaoMock = PowerMockito.mock(UserDao.class);

		// defining mock obj behaviour
		PowerMockito.when(userDaoMock.findNameById(101)).thenReturn("Raju");
		// injecting mock obj into service obj
		UserService service = new UserService(userDaoMock);

		String actualName = service.getName(101);

		String expectedName = "Raju";

		assertEquals(expectedName, actualName);
	}

	@Test
	public void loginTest() {
		UserDao userDaoMock = PowerMockito.mock(UserDao.class);
		PowerMockito.when(userDaoMock.findByEmailAndPwd("admin@gmail.com", "admin")).thenReturn(true);

		UserService service = new UserService(userDaoMock);
		String actualStatus = service.login("admin@gmail.com", "admin");

		String expectedStatus = "SUCCESS";
		assertEquals(expectedStatus, actualStatus);
	}

	@Test
	public void testDoProcess() {
		UserService mockService = PowerMockito.mock(UserService.class);
		try {
			PowerMockito.doNothing().when(mockService, "pushMsgToKafkaTopic", anyString());
			mockService.doProcess();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/*
	 * @Test public void testDoWorkWithPrivateMethodMock() throws Exception {
	 * UserService service = new UserService(); UserService spy =
	 * PowerMockito.spy(service); PowerMockito.doReturn("TEST MSG").when(spy,
	 * "formatMsg", "test msg"); String formattedMsg = service.doWork("test msg");
	 * assertEquals("TEST MSG", formattedMsg);
	 * 
	 * }
	 */

}
