package menus;

import java.io.BufferedReader;
import java.io.IOException;

import functions.CheckSessionDetails;

public class MenuCheckSessionDetails {
	public static void beginSessionDetailsCheck(BufferedReader reader){
		while(true){
			System.out.println(
					"Input the session ID.\n" +
							"q. Quit"
					);
			String input;
			try {
				input = reader.readLine();
				if (input.equals("q")){
					break;
				} else{
					CheckSessionDetails.checkSessionDetails(reader, input);
					break;
				}

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
