package iFace;

import java.util.Scanner;

public class SingUp
{
	String username, name, password;
	Scanner input = new Scanner(System.in);
	public void newUser(String username)
	{
		this.username = username;
		System.out.printf("\nDigite sua senha:\n=> ");
		this.password = input.nextLine();
		System.out.printf("\nDigite seu nome de perfil\n=> ");
		this.name = input.nextLine();
		System.out.printf("\nVocê foi cadastrado no iFace.\nFaça seu login!\n\n");
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getName()
	{
		return name;
	}
}
