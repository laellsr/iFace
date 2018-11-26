package iFace;

import java.util.Scanner;

public class InputBox
{
	String to_username, from_username, message;
	Scanner input = new Scanner(System.in);
	
	public void getMessage()
	{
		System.out.printf("Digite sua mensagem:\n=> ");
		message = input.nextLine();
	}
}
