package com.bankingApplication;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import oracle.jdbc.driver.OracleDriver;

public class Model {
	String url = "jdbc:oracle:thin:@//localhost:1521/XE";
	String un = "system";
	String pass = "system";
	Connection con = null;
	ResultSet res = null;
	PreparedStatement pstmt = null;
	String name;
	int accountNumber;
	int balance;
	String customerId;
	String password;
	String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	Model() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection(url, un, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean login() {
		String q = "select * from bank_details where customerid=? and password=?";
		try {
			pstmt = con.prepareStatement(q);
			pstmt.setString(1, customerId);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			if (res.next() == true) {
				accountNumber = res.getInt(2);
				return true;
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean checkBalance() {
		String q1 = "select * from bank_details where accountNumber=?";
		try {
			pstmt = con.prepareStatement(q1);
			pstmt.setInt(1, accountNumber);
			res = pstmt.executeQuery();
			if (res.next()) {
				balance = res.getInt("balance");
				return true;
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean change(String newPassword) {
		// TODO Auto-generated method stub
		String query = "update bank_details set password=? where accountnumber=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, accountNumber);
			int val = pstmt.executeUpdate();
			if (val > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean transfer(int amount, int toAccountNumber) throws SQLException {
		// TODO Auto-generated method stub
		con.setAutoCommit(false);
		String q1 = "update bank_details set balance=balance-? where accountnumber=?";
		try {

			pstmt = con.prepareStatement(q1);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, accountNumber);
			int val1 = pstmt.executeUpdate();
			if (val1 > 0) {
				String q2 = "update bank_details set balance=balance+? where accountnumber=?";
				pstmt = con.prepareStatement(q2);
				pstmt.setInt(1, amount);
				pstmt.setInt(2, toAccountNumber);
				int val2 = pstmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
				if (val2 > 0 && val1 > 0) {
					String q3 = "insert into statement values(?,?,?,?,?)";
					pstmt = con.prepareStatement(q3);
					pstmt.setInt(1, accountNumber);
					pstmt.setInt(2, toAccountNumber);
					pstmt.setInt(3, amount);
					Date d = new Date();
					String res = " ";
					String time = "";
					String mon = " " + (1 + d.getMonth());
					String year = " " + (Calendar.YEAR + 2022);
					res = res + d.getDate() + "/" + mon + "/" + year;
					time = time + d.getHours() + ":" + d.getMinutes();
					pstmt.setString(5, time);
					pstmt.executeUpdate();

					return true;
				}

				else
					return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}

		return false;

	}

	public ArrayList getStatement() {
		// TODO Auto-generated method stub
		String q = "select * from statement where fromaccountnumber=?";
		try {
			pstmt = con.prepareStatement(q);
			pstmt.setInt(1, accountNumber);
			res = pstmt.executeQuery();
			ArrayList al = new ArrayList();
			while (res.next()) {
				al.add(res.getInt(1));
				al.add(res.getInt(2));
				al.add(res.getInt(3));
				al.add(res.getString(4));
				al.add(res.getString(5));
				al.add(" ");
			}
			return al;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
