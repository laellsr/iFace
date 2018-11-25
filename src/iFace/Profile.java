package iFace;

import java.util.ArrayList;
import java.util.Scanner;

public class Profile
{
	
	String name, age, gender, state, city, country, phrase, more1, more2;
	ArrayList<Friends> friends = new ArrayList<Friends>();
	ArrayList<InputBox> input_box = new ArrayList<InputBox>();
	ArrayList<Invite> invites = new ArrayList<Invite>();
	
	Scanner input = new Scanner(System.in);

	public void setProfile()
	{
		setName();
		setAge();
		setGender();
		setState();
		setCity();
		setCountry();
		setPhrase();
		setMore1();
		setMore2();
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry()
	{
		System.out.printf("\nDigite o país em que nasceu:\n=> ");
		country = input.nextLine();
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity()
	{
		System.out.printf("\nDigite a cidade em que nasceu:\n=> ");
		city = input.nextLine();
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState()
	{
		System.out.printf("\nDigite o estado em que nasceu:\n=> ");
		state = input.nextLine();
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
