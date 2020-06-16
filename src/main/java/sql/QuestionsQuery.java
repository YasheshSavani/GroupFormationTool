package sql;

public class QuestionsQuery {
	public static final String SAVE_QUESTION = "INSERT INTO questions_manager(instructorUserName, questionTitle, questionType, question, dateCreated) VALUES(?,?,?,?,?)";
}
