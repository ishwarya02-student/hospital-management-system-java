package com.learnJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	private Connection connection;
	public Doctor(Connection connection)
	{
		this.connection = connection;
	}
	public void viewDoctor() {
		String query = "select*from doctors";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			ResultSet resultset =prepareStatement.executeQuery();
			System.out.println("Doctors: ");
			System.out.println("+-------------+----------------+--------------------+");
			System.out.println("|ID           | Name           |Specialization      |");
			System.out.println("+-------------+----------------+--------------------+");
			while(resultset.next())
			{
				int id = resultset.getInt("id");
				String name =resultset.getString("name");
				String Specialization = resultset.getString("Specialization");
				System.out.printf("|%-13s|%-16s|%-20s|\n",id,name,Specialization);
				System.out.println("+-------------+----------------+--------------------+");
			}
	}catch(SQLException e)
		{
		e.printStackTrace();
		}
	}
	public boolean getDoctorByID(int id)
	{
		String query = "select * from Doctors where ID = ?";
		
		try {
			java.sql.PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1,id);
			ResultSet resultset = preparedstatement.executeQuery();
			if(resultset.next())
			{
				return true;
			}
			else {
				return false;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			}
		return false;

}

}
