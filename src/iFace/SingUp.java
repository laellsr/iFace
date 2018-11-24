package iFace;

import java.util.Scanner;

public class SingUp
{
	String username, password;
	String name, age = "-----", gender = "-----", phrase = "-----", more1 = "-----", more2 = "-----";
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
	
	public void setPassword()
	{
		System.out.printf("\nDigite sua nova senha:\n=> ");
		password = input.nextLine();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName()
	{
		System.out.printf("\nDigite seu novo nome de perfil:\n=> ");
		name = input.nextLine();
	}
	
	public String getAge()
	{
		return age;
	}
	
	public void setAge()
	{
		System.out.printf("\nDigite sua idade:\n=> ");
		age = input.nextLine();
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public void setGender()
	{
		System.out.printf("\nDigite seu gênero:\n=> ");
		gender = input.nextLine();
	}
	
	public String getPhrase()
	{
		return phrase;
	}
	
	public void setPhrase()
	{
		System.out.printf("\nDigite uma frase favorita:\n=> ");
		phrase = input.nextLine();
	}
	public String getMore1()
	{
		return more1;
	}
	
	public void setMore1()
	{
		System.out.printf("\nAdicione outra informação sobre você\n=> ");
		more1 = input.nextLine();
	}
	
	public String getMore2()
	{
		return more2;
	}
	
	public void setMore2()
	{
		System.out.printf("\nAdicione outra informação sobre você\n=> ");
		more2 = input.nextLine();
	}
}
