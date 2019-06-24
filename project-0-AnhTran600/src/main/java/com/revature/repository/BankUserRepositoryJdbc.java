package com.revature.repository;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.service.BankConnectionUtil;
import com.revature.model.*;

public class BankUserRepositoryJdbc implements BankUserRepository {

	private static final Logger LOGGER = Logger.getLogger(BankUserRepositoryJdbc.class);
	@Override
	public boolean createBankUser(BankUser user) {
		LOGGER.trace("Entering createBankUser method with parameter: " + user);
		try(Connection connection = BankConnectionUtil.getConnection()) {		// try with resource
			int parameterIndex = 0;
			String sql = "INSERT INTO BANK_USER VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(++parameterIndex, user.getId());
			statement.setString(++parameterIndex, user.getUsername());
			statement.setString(++parameterIndex, user.getPassword());
			statement.setDouble(++parameterIndex, user.getBalance());
			
			if (statement.executeUpdate() > 0) {
				System.out.println("Registration Success!");
				return true;
			}
			
		}
		catch (SQLException e) {
			LOGGER.error("Could not create User.",e);
		}
		return false;
	}
	@Override
	public BankUser findBankUsername(String username) {
		LOGGER.trace("Entering find Username method with parameter: ");
		try(Connection connection = BankConnectionUtil.getConnection()) {
			int parameterIndex = 0;
			String sql = "SELECT * FROM BANK_USER WHERE U_USER_NAME = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(++parameterIndex, username);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return new BankUser (
						result.getLong("U_ID"),
						result.getString("U_USER_NAME"),
						result.getString("U_PASSWORD"),
						result.getDouble("U_BALANCE")
						);
			}
		}
		catch (SQLException e) {
			LOGGER.error("Could not find Username.", e);
		}
		return new BankUser(0,"Default","Default",0.00);
	}
	@Override
	public List<BankUser> getAll() {
		LOGGER.trace("Entering finding all user ");
		try(Connection connection = BankConnectionUtil.getConnection()) {		// try with resource has auto close connection
			String sql = "SELECT * FROM BANK_USER ORDER BY U_ID";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			List<BankUser> userList = new ArrayList<>();
			
			while(result.next()) {
				
				userList.add(new BankUser (
						result.getLong("U_ID"),
						result.getString("U_USER_NAME"),
						result.getString("U_PASSWORD"),
						result.getDouble("U_BALANCE")
						));
			}
			return userList;
		}
		catch (SQLException e) {
			LOGGER.error("Could not get all user.", e);
		}
		return null;
	}
	@Override
	public void withdraw(BankUser user) {
		// TODO Auto-generated method stub
		LOGGER.trace("Entering withdraw method with parameter: " + user);
		try(Connection connection = BankConnectionUtil.getConnection()) {		// try with resource
			int parameterIndex = 0;
			String sql = "UPDATE BANK_USER SET U_BALANCE = ? WHERE U_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDouble(++parameterIndex, user.getBalance());
			statement.setLong(++parameterIndex, user.getId());
			
			if (statement.executeUpdate() > 0) {
				System.out.println("Withdraw Success!");
			}
		}
		catch (SQLException e) {
			LOGGER.error("Could not withdraw.",e);
		}
	}
	@Override
	public void deposit(BankUser user) {
		// TODO Auto-generated method stub
		LOGGER.trace("Entering deposit method with parameter: " + user);
		try(Connection connection = BankConnectionUtil.getConnection()) {		// try with resource
			int parameterIndex = 0;
			String sql = "UPDATE BANK_USER SET U_BALANCE = ? WHERE U_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDouble(++parameterIndex, user.getBalance());
			statement.setLong(++parameterIndex, user.getId());
			
			if (statement.executeUpdate() > 0) {
				System.out.println("Deposit Success!");
			}
		}
		catch (SQLException e) {
			LOGGER.error("Could not deposit.",e);
		}
	}
	public long findMaxId() {
		LOGGER.trace("Entering findMaxId method without parameter: ");
		try(Connection connection = BankConnectionUtil.getConnection()) {
			String sql = "SELECT MAX(U_ID) FROM BANK_USER";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			long maxId = 1;
			while (result.next()) {
				maxId = result.getLong("MAX(U_ID)");
			}
			return maxId;
		}
		catch (SQLException e) {
			LOGGER.error("Could not find Id.", e);
		}
		return 0;
	}
}
