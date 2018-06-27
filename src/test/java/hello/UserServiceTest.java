package hello;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import hello.CommonDao.DaoException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void insertUserTest() {
		User user = new User();
		String name = "Alice";
		user.setName(name);
		String uuid = null;
		try {
			uuid = userService.insertUser(user);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(uuid);
		assertFalse(uuid.equals(""));
	}

	@Test
	public void getUserByUuidTest() {
		// Insert a user.
		User user = new User();
		String name = "Alice";
		user.setName(name);
		String uuid = null;
		try {
			uuid = userService.insertUser(user);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(uuid);
		assertFalse(uuid.equals(""));

		// Fetch the user.
		User fetchedUser = null;
		try {
			fetchedUser = userService.getUserByUuid(uuid);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(fetchedUser);
		assertEquals(uuid, fetchedUser.getUuid());
		assertEquals(name, fetchedUser.getName());
	}

	@Test
	public void updateUserTest() {
		// Insert a user.
		User user = new User();
		String name = "Alice";
		user.setName(name);
		String uuid = null;
		try {
			uuid = userService.insertUser(user);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(uuid);
		assertFalse(uuid.equals(""));

		// Fetch the user.
		User fetchedUser = null;
		try {
			fetchedUser = userService.getUserByUuid(uuid);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(fetchedUser);
		assertEquals(uuid, fetchedUser.getUuid());
		assertEquals(name, fetchedUser.getName());

		// Update the user.
		String updatedName = "Bob";
		fetchedUser.setName(updatedName);
		try {
			userService.updateUser(fetchedUser);
		} catch (DaoException e) {
			e.printStackTrace();
		}

		// Fetch the updated user.
		User updatedUser = null;
		try {
			updatedUser = userService.getUserByUuid(uuid);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(updatedUser);
		assertEquals(uuid, updatedUser.getUuid());
		assertEquals(updatedName, updatedUser.getName());
	}

	@Test
	public void deleteUserTest() {
		// Insert a user.
		User user = new User();
		String name = "Alice";
		user.setName(name);
		String uuid = null;
		try {
			uuid = userService.insertUser(user);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(uuid);
		assertFalse(uuid.equals(""));

		// Fetch the user.
		User fetchedUser = null;
		try {
			fetchedUser = userService.getUserByUuid(uuid);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(fetchedUser);
		assertEquals(uuid, fetchedUser.getUuid());
		assertEquals(name, fetchedUser.getName());

		// Delete the user.
		try {
			userService.deleteUser(uuid);
		} catch (DaoException e) {
			e.printStackTrace();
		}

		// Fetch the deleted user.
		User deletedUser = null;
		try {
			deletedUser = userService.getUserByUuid(uuid);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNull(deletedUser);
	}

	@Test
	public void getAllUsersTest() {
		// Insert multiple user.
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		String name1 = "Alice";
		String name2 = "Bob";
		String name3 = "Claire";
		user1.setName(name1);
		user2.setName(name2);
		user3.setName(name3);
		String uuid1 = null;
		String uuid2 = null;
		String uuid3 = null;
		try {
			uuid1 = userService.insertUser(user1);
			uuid2 = userService.insertUser(user2);
			uuid3 = userService.insertUser(user3);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(uuid1);
		assertFalse(uuid1.equals(""));
		assertNotNull(uuid2);
		assertFalse(uuid2.equals(""));
		assertNotNull(uuid3);
		assertFalse(uuid3.equals(""));

		// Fetch the users.
		List<User> fetchedUsers = null;
		try {
			fetchedUsers = userService.getAllUsers();
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		assertNotNull(fetchedUsers);
		assertEquals(3, fetchedUsers.size());
		boolean found1 = false;
		boolean found2 = false;
		boolean found3 = false;
		for (User fetchedUser : fetchedUsers) {
			if (fetchedUser.getUuid().equals(uuid1)) {
				found1 = true;
				assertEquals(name1, fetchedUser.getName());
			} else if (fetchedUser.getUuid().equals(uuid2)) {
				found2 = true;
				assertEquals(name2, fetchedUser.getName());
			} else if (fetchedUser.getUuid().equals(uuid3)) {
				found3 = true;
				assertEquals(name3, fetchedUser.getName());
			}
		}
		assertTrue(found1);
		assertTrue(found2);
		assertTrue(found3);
	}

}
