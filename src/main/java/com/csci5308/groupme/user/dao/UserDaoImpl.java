package com.csci5308.groupme.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.csci5308.datasource.DataSource;
import com.csci5308.groupme.user.model.User;

import sql.UserQuery;

@Repository
public class UserDaoImpl implements UserDao {

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	DataSource dataSource;

	private Connection connection;

	private PreparedStatement preparedStatement;

	private ResultSet resultSet;

	@Override
	public User findByUserName(String userName) {

		User user = new User();
		try {
			connection = dataSource.openConnection();
			String sql = "SELECT * FROM user where userName = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setUserName(resultSet.getString("userName"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
			}
			resultSet.close();

			String sql2 = "SELECT roleName FROM userrole where userName=?";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			List<String> roles = new ArrayList<String>();

			while (resultSet.next()) {
				roles.add(resultSet.getString("roleName"));
			}
			resultSet.close();
			user.setRoles(roles);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se) {
			}

			if (connection != null)
				try {
					dataSource.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = new User();
		try {
			connection = dataSource.openConnection();
			String sql = "SELECT * FROM user where email = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setUserName(resultSet.getString("userName"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
			}
			resultSet.close();

			String sql2 = "SELECT roleName FROM userrole where userName = ?";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, user.getUserName());
			resultSet = preparedStatement.executeQuery();
			List<String> roles = new ArrayList<String>();

			while (resultSet.next()) {
				roles.add(resultSet.getString("roleName"));
			}
			resultSet.close();
			user.setRoles(roles);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se) {
			}

			if (connection != null)
				try {
					dataSource.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return user;
	}

	@Override
	public List<User> findByName(String firstName, String lastName) {
		User user = new User();
		List<User> users = new ArrayList<User>();
		try {
			connection = dataSource.openConnection();
			String sql = "SELECT * FROM user where firstName = ? and lastName = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setUserName(resultSet.getString("userName"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
			resultSet.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se) {
			}

			if (connection != null)
				try {
					dataSource.closeConnection();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return users;
	}

	@Override
	public int save(User user) {
		int addedUserCount = 0;
		try {
			connection = dataSource.openConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(UserQuery.ADD_USER);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPassword());
			addedUserCount = preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(UserQuery.ADD_USERROLE);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, "ROLE_GUEST");
			addedUserCount = preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException se) {
			try {
				logger.debug(se.getMessage());
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se) {
			}

			if (connection != null)
				try {
					dataSource.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return addedUserCount;
	}

	
	@Override
	public boolean updateRole(User user, String oldRole, String newRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findAll() {
		User user = new User();
		List<User> users = new ArrayList<User>();
		try {
			connection = dataSource.openConnection();
			String sql = "SELECT * FROM user";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setUserName(resultSet.getString("userName"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
			resultSet.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se) {
			}

			if (connection != null)
				try {
					dataSource.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return users;
	}

	@Override
	public int addRole(String userName, String roleName) {
		int addedUserCount = 0;
		try {
			connection = dataSource.openConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(UserQuery.ADD_USERROLE);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, "ROLE_GUEST");
			addedUserCount = preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException se) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se) {
			}

			if (connection != null)
				try {
					dataSource.closeConnection();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return addedUserCount;
	}

}
