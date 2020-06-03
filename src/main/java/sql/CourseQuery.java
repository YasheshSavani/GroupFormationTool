package sql;

public class CourseQuery {

    public static final String SELECT_ALL_COURSE = "SELECT * FROM course";

    public static final String SELECT_COURSES_BY_USERNAME_TA = "SELECT courseCode FROM courseadmin WHERE taUserName=?";

    public static final String SELECT_COURSES_BY_USERNAME_INSTRUCTOR = "SELECT courseCode FROM courseadmin WHERE instructorUserName=?";

    public static final String SELECT_COURSE_BY_COURSE_CODE = "SELECT * FROM course WHERE courseCode=?";
}
