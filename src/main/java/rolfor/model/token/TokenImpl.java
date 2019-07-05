package rolfor.model.token;


import rolfor.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;


@Entity
@Table(name = "tokens")
public class TokenImpl extends AbstractEntity implements MutableToken {
	private Integer   userId;
	private String    tokenString;
	private Timestamp expirationDate;
	private Boolean   remembered;
	
	
	@Id
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}
	
	@Column(name = "token_string")
	public String getTokenString() {
		return tokenString;
	}
	
	@Column(name = "expiration_date")
	public Timestamp getExpirationDate() {
		return expirationDate;
	}
	
	@Column(name = "remembered")
	public Boolean getRemembered() {
		return remembered;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
		this.id = userId;
	}
	
	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
	
	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public void setRemembered(Boolean remembered) {
		this.remembered = remembered;
	}
}
