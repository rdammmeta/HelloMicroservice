package hello;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TransactionConfiguration(defaultRollback = true)
@Transactional
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void userPostAndGetTest() {
		try {
			// POST a user.
			String name = "Alice";
			MvcResult postResult = this.mockMvc.perform(post("/user").param("name", name)).andExpect(status().isOk())
					.andReturn();
			String postResponseBody = postResult.getResponse().getContentAsString();
			assertNotNull(postResponseBody);
			assertNotEquals("", postResponseBody);
			JSONObject parsedPostResponseBody = new JSONObject(postResponseBody);
			assertFalse(parsedPostResponseBody.isNull("uuid"));
			String uuid1 = parsedPostResponseBody.getString("uuid");
			assertNotNull(uuid1);
			assertNotEquals("", uuid1);
			assertFalse(parsedPostResponseBody.isNull("name"));
			assertNotNull(parsedPostResponseBody.getString("name"));
			assertEquals(name, parsedPostResponseBody.getString("name"));

			// GET the user.
			MvcResult getResult = this.mockMvc.perform(get("/user").param("uuid", uuid1)).andExpect(status().isOk())
					.andReturn();
			String getResponseBody = getResult.getResponse().getContentAsString();
			assertNotNull(getResponseBody);
			assertNotEquals("", getResponseBody);
			JSONObject parsedGetResponseBody = new JSONObject(getResponseBody);
			assertFalse(parsedGetResponseBody.isNull("uuid"));
			assertNotNull(parsedGetResponseBody.getString("uuid"));
			assertEquals(uuid1, parsedGetResponseBody.getString("uuid"));
			assertFalse(parsedGetResponseBody.isNull("name"));
			assertNotNull(parsedGetResponseBody.getString("name"));
			assertEquals(name, parsedGetResponseBody.getString("name"));

			// POST another user with the same information.
			postResult = this.mockMvc.perform(post("/user").param("name", name)).andExpect(status().isOk()).andReturn();
			postResponseBody = postResult.getResponse().getContentAsString();
			assertNotNull(postResponseBody);
			assertNotEquals("", postResponseBody);
			parsedPostResponseBody = new JSONObject(postResponseBody);
			assertFalse(parsedPostResponseBody.isNull("uuid"));
			String uuid2 = parsedPostResponseBody.getString("uuid");
			assertNotNull(uuid2);
			assertNotEquals("", uuid2);
			assertNotEquals(uuid1, uuid2);
			assertFalse(parsedPostResponseBody.isNull("name"));
			assertNotNull(parsedPostResponseBody.getString("name"));
			assertEquals(name, parsedPostResponseBody.getString("name"));

			// GET both users and confirm that they are distinct.
			getResult = this.mockMvc.perform(get("/user").param("uuid", uuid1)).andExpect(status().isOk()).andReturn();
			getResponseBody = getResult.getResponse().getContentAsString();
			assertNotNull(getResponseBody);
			assertNotEquals("", getResponseBody);
			parsedGetResponseBody = new JSONObject(getResponseBody);
			assertFalse(parsedGetResponseBody.isNull("uuid"));
			assertNotNull(parsedGetResponseBody.getString("uuid"));
			assertEquals(uuid1, parsedGetResponseBody.getString("uuid"));
			assertFalse(parsedGetResponseBody.isNull("name"));
			assertNotNull(parsedGetResponseBody.getString("name"));
			assertEquals(name, parsedGetResponseBody.getString("name"));
			getResult = this.mockMvc.perform(get("/user").param("uuid", uuid2)).andExpect(status().isOk()).andReturn();
			getResponseBody = getResult.getResponse().getContentAsString();
			assertNotNull(getResponseBody);
			assertNotEquals("", getResponseBody);
			parsedGetResponseBody = new JSONObject(getResponseBody);
			assertFalse(parsedGetResponseBody.isNull("uuid"));
			assertNotNull(parsedGetResponseBody.getString("uuid"));
			assertEquals(uuid2, parsedGetResponseBody.getString("uuid"));
			assertFalse(parsedGetResponseBody.isNull("name"));
			assertNotNull(parsedGetResponseBody.getString("name"));
			assertEquals(name, parsedGetResponseBody.getString("name"));
		} catch (UnsupportedEncodingException e) {
			fail(e.getMessage());
		} catch (JSONException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void userPutAndGetTest() {
		try {
			// PUT a user.
			String name1 = "Alice";
			MvcResult postResult = this.mockMvc.perform(put("/user").param("name", name1)).andExpect(status().isOk())
					.andReturn();
			String postResponseBody = postResult.getResponse().getContentAsString();
			assertNotNull(postResponseBody);
			assertNotEquals("", postResponseBody);
			JSONObject parsedPostResponseBody = new JSONObject(postResponseBody);
			assertFalse(parsedPostResponseBody.isNull("uuid"));
			String uuid = parsedPostResponseBody.getString("uuid");
			assertNotNull(uuid);
			assertNotEquals("", uuid);
			assertFalse(parsedPostResponseBody.isNull("name"));
			assertNotNull(parsedPostResponseBody.getString("name"));
			assertEquals(name1, parsedPostResponseBody.getString("name"));

			// GET the user.
			MvcResult getResult = this.mockMvc.perform(get("/user").param("uuid", uuid)).andExpect(status().isOk())
					.andReturn();
			String getResponseBody = getResult.getResponse().getContentAsString();
			assertNotNull(getResponseBody);
			assertNotEquals("", getResponseBody);
			JSONObject parsedGetResponseBody = new JSONObject(getResponseBody);
			assertFalse(parsedGetResponseBody.isNull("uuid"));
			assertNotNull(parsedGetResponseBody.getString("uuid"));
			assertEquals(uuid, parsedGetResponseBody.getString("uuid"));
			assertFalse(parsedGetResponseBody.isNull("name"));
			assertNotNull(parsedGetResponseBody.getString("name"));
			assertEquals(name1, parsedGetResponseBody.getString("name"));

			// PUT the same user.
			String name2 = "Bob";
			postResult = this.mockMvc.perform(put("/user").param("uuid", uuid).param("name", name2))
					.andExpect(status().isOk()).andReturn();
			postResponseBody = postResult.getResponse().getContentAsString();
			assertNotNull(postResponseBody);
			assertNotEquals("", postResponseBody);
			parsedPostResponseBody = new JSONObject(postResponseBody);
			assertFalse(parsedPostResponseBody.isNull("uuid"));
			assertNotNull(parsedPostResponseBody.getString("uuid"));
			assertEquals(uuid, parsedPostResponseBody.getString("uuid"));
			assertFalse(parsedPostResponseBody.isNull("name"));
			assertNotNull(parsedPostResponseBody.getString("name"));
			assertEquals(name2, parsedPostResponseBody.getString("name"));

			// GET the user to confirm that the UUID is unchanged but the name
			// is.
			getResult = this.mockMvc.perform(get("/user").param("uuid", uuid)).andExpect(status().isOk()).andReturn();
			getResponseBody = getResult.getResponse().getContentAsString();
			assertNotNull(getResponseBody);
			assertNotEquals("", getResponseBody);
			parsedGetResponseBody = new JSONObject(getResponseBody);
			assertFalse(parsedGetResponseBody.isNull("uuid"));
			assertNotNull(parsedGetResponseBody.getString("uuid"));
			assertEquals(uuid, parsedGetResponseBody.getString("uuid"));
			assertFalse(parsedGetResponseBody.isNull("name"));
			assertNotNull(parsedGetResponseBody.getString("name"));
			assertEquals(name2, parsedGetResponseBody.getString("name"));
		} catch (UnsupportedEncodingException e) {
			fail(e.getMessage());
		} catch (JSONException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void userPostAndDeleteAndGetTest() {
		try {
			// POST a user.
			String name = "Alice";
			MvcResult postResult = this.mockMvc.perform(post("/user").param("name", name)).andExpect(status().isOk())
					.andReturn();
			String postResponseBody = postResult.getResponse().getContentAsString();
			assertNotNull(postResponseBody);
			assertNotEquals("", postResponseBody);
			JSONObject parsedPostResponseBody = new JSONObject(postResponseBody);
			assertFalse(parsedPostResponseBody.isNull("uuid"));
			String uuid1 = parsedPostResponseBody.getString("uuid");
			assertNotNull(uuid1);
			assertNotEquals("", uuid1);
			assertFalse(parsedPostResponseBody.isNull("name"));
			assertNotNull(parsedPostResponseBody.getString("name"));
			assertEquals(name, parsedPostResponseBody.getString("name"));

			// GET the user.
			MvcResult getResult = this.mockMvc.perform(get("/user").param("uuid", uuid1)).andExpect(status().isOk())
					.andReturn();
			String getResponseBody = getResult.getResponse().getContentAsString();
			assertNotNull(getResponseBody);
			assertNotEquals("", getResponseBody);
			JSONObject parsedGetResponseBody = new JSONObject(getResponseBody);
			assertFalse(parsedGetResponseBody.isNull("uuid"));
			assertNotNull(parsedGetResponseBody.getString("uuid"));
			assertEquals(uuid1, parsedGetResponseBody.getString("uuid"));
			assertFalse(parsedGetResponseBody.isNull("name"));
			assertNotNull(parsedGetResponseBody.getString("name"));
			assertEquals(name, parsedGetResponseBody.getString("name"));

			// Delete the user.
			this.mockMvc.perform(delete("/user").param("uuid", uuid1)).andExpect(status().isOk());

			// GET the deleted user.
			this.mockMvc.perform(get("/user").param("uuid", uuid1)).andExpect(status().isNotFound());
		} catch (UnsupportedEncodingException e) {
			fail(e.getMessage());
		} catch (JSONException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
