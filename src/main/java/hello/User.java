package hello;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable {

	private static final long serialVersionUID = 4417965101703511714L;
	@Id
	// TODO: See if Hibernate can generate UUID.
	@Column(name = "uuid", nullable = false)
	private String uuid;
	@Column(name = "name", nullable = false)
	private String name;
	
	public User() {
	}

	public User(String uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
