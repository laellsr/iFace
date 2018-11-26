package iFace;

import java.util.ArrayList;
import java.util.Scanner;

public class Community
{
	String name, theme, admin;
	ArrayList<InputBox> messages = new ArrayList<InputBox>();
	ArrayList<Friends> members = new ArrayList<Friends>();
	ArrayList<Invite> invites = new ArrayList<Invite>();
	Scanner input = new Scanner(System.in);
	
	public void setCommunity(String username)
	{
		System.out.printf("Digite um nome para sua nova comunidade:\n=> ");
		name = input.nextLine();
		System.out.printf("Digite o tema da sua nova comunidade:\n=> ");
		theme = input.nextLine();
	}
}
