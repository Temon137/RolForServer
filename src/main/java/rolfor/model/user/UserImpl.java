package rolfor.model.user;


import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "users")
public class UserImpl implements User {
	private Integer id;
	private String  description;
	private String  avatarImageName;
	private Integer role;
	private String  password;
	private String  login;
	private String  name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	
	@Column(name = "avatar_image_name")
	public String getAvatarImageName() {
		return avatarImageName;
	}
	
	@Column(name = "role")
	public Integer getRole() {
		return role;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	
	@Column(name = "login")
	public String getLogin() {
		return login;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setAvatarImageName(String avatarImageName) {
		this.avatarImageName = avatarImageName;
	}
	
	public void setRole(Integer role) {
		this.role = role;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@SuppressWarnings("RedundantIfStatement")
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		UserImpl user = (UserImpl) o;
		
		if (!id.equals(user.id)) return false;
		if (!role.equals(user.role)) return false;
		if (!Objects.equals(description, user.description)) return false;
		if (!Objects.equals(avatarImageName, user.avatarImageName)) {
			return false;
		}
		if (!Objects.equals(password, user.password)) return false;
		if (!Objects.equals(login, user.login)) return false;
		if (!Objects.equals(name, user.name)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (avatarImageName != null ? avatarImageName.hashCode() : 0);
		result = 31 * result + role;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
