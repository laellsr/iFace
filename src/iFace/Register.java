package iFace;

import java.util.ArrayList;
import java.util.Scanner;

public class Register
{
	Profile new_profile = new Profile();
	String username, password;
	Scanner input = new Scanner(System.in);
	
	public void setRegister(ArrayList<Register> accounts, Register new_register)
	{
		setUsername();
		if(!accounts.isEmpty())
		{
			for(int i = 0; i < accounts.size(); i++)
			{
				if(username.intern() == (accounts.get(i).getUsername()).intern())
				{
					System.out.printf("\nEsse usuário já existe.\n\n");
					return;
				}
			}
		}
		setPassword();
		System.out.printf("\nVocê foi cadastrado no iFace!\n\n");
		accounts.add(new_register);
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername()
	{
		System.out.printf("\nDigite seu novo nome de usuário:\n=> ");
		username = input.nextLine();
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword()
	{
		System.out.printf("\nDigite sua nova senha:\n=> ");
		password = input.nextLine();
	}
}
