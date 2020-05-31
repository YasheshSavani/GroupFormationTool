package sql;

public class UserQuery {

	public static final String ADD_USER = "INSERT INTO user(userName, firstName, lastName, email, password) values (?,?,?,?,?)";
	
	public static final String ADD_USERROLE = "INSERT INTO userrole(userName, roleName) values (?, ?)";

}
