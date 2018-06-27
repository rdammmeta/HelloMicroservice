package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.CommonDao.DaoException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> get(@RequestParam(value = "uuid") String uuid) {
		try {
			return new ResponseEntity<>(userService.getUserByUuid(uuid), HttpStatus.OK);
		} catch (DaoException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> post(User user) {
		try {
			return new ResponseEntity<>(userService.insertUser(user), HttpStatus.OK);
		} catch (DaoException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<?> put(User user) {
		try {
			if (user.getUuid() != null && !user.getUuid().equals("")
					&& userService.getUserByUuid(user.getUuid()) != null) {
				userService.updateUser(user);
				return new ResponseEntity<>(user.getUuid(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(userService.insertUser(user), HttpStatus.OK);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(String uuid) {
		try {
			userService.deleteUser(uuid);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (DaoException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
