package sql;

public class UserQuery {

	public static final String ADD_USER = "INSERT INTO user(userName, firstName, lastName, email, password) values (?,?,?,?,?)";
	
	public static final String ADD_USERROLE = "INSERT INTO userrole(userName, roleName) values (?, ?)";

	public static final String FIND_BY_USERNAME = "SELECT * FROM user where userName = ?";
	
	public static final String FIND_ROLES_BY_USERNAME = "SELECT roleName FROM userrole where userName=?";
	
    public static final String FIND_BY_EMAIL = "SELECT * FROM user where email = ?";
	
    public static final String FIND_BY_NAME = "SELECT * FROM user where firstName = ? and lastName = ?";

    public static final String FIND_ALL = "SELECT * FROM user";

    public static final String FIND_USERNAME_BY_EMAIL = "SELECT userName FROM user WHERE email=?";
}


