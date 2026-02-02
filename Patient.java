package com.learnJDBC;
import java.sql.*;
import java.util.Scanner;

public class Patient {
	private Connection connection;
	private Scanner scanner;
	public Patient(Connection connection ,Scanner scanner)
	{
		this.connection = connection;
		this.scanner = scanner;
	}
	public void addPatient()
	{
		System.out.println("Enter Patient's Name");
		String name = scanner.next();
		System.out.println("Enter the Patient's Age");
		int age = scanner.nextInt();
		System.out.println("Enter Patient's Gender");
		String gender =  scanner.next();
		
	
try {
	String Query = "Insert into patients(name,age,gender) values (?,?,?)";
	PreparedStatement preparedStatement = connection.prepareStatement(Query);
	preparedStatement.setString(1,name);
	preparedStatement.setInt(2,age);
	preparedStatement.setString(3,gender);
 int affectedRows = preparedStatement.executeUpdate();
 if(affectedRows>0)
 {
	 System.out.println("Patient Added Successfully");
 }
 else {
	 System.out.println("Failed to add Patient!!!");
 }
	 }catch(SQLException e)
{
		 e.printStackTrace();
}
	}
	public void viewPatients()
	{
		String query = "select*from patients";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			ResultSet resultset =prepareStatement.executeQuery();
			System.out.println("Patient: ");
			System.out.println("+-------------+-----------------+--------+------------+");
			System.out.println("|Patients ID  | Name            | Age    | Gender     |");
			System.out.println("+-------------+-----------------+--------+------------+");
			while(resultset.next())
			{
				int id = resultset.getInt("id");
				String name =resultset.getString("name");
				int age = resultset.getInt("age");
				String gender = resultset.getString("gender");
				System.out.printf("|%-13s|%-17s|%-8s|%-12s|\n",id,name,age,gender);
				System.out.println("+-------------+-----------------+--------+------------+");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}	
	public boolean getPatientByID(int id)
	{
		String query = "select * from patients where  ID = ?";
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1,id);
			ResultSet resultset = preparedstatement.executeQuery();
			if(resultset.next())
			{
				return true;
			}
			else {
				return false;
			}
			
		}catch(SQLException e )
		{e.printStackTrace();
		}
		return false;
	}
}
