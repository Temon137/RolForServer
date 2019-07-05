package rolfor.model.token;


import java.sql.Timestamp;


public interface MutableToken extends Token {
	void setUserId(Integer userId);
	
	void setTokenString(String tokenString);
	
	void setExpirationDate(Timestamp expirationDate);
	
	void setRemembered(Boolean remembered);
}
