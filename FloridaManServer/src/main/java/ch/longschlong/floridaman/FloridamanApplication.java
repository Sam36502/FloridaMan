package ch.longschlong.floridaman;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class FloridamanApplication {

	public static final String VERSION = "1.0-SNAPSHOT";
	public static final String BANNER = "" +
			"oooooooooooo oooo                      o8o        .o8                 ooo        ooooo                       " + Ansi.ansi().cursorDownLine().cursorToColumn(6) +
			"`888'     `8 `888                      `\"'       \"888                 `88.       .888'                       " + Ansi.ansi().cursorDownLine().cursorToColumn(6) +
			" 888          888   .ooooo.  oooo d8b oooo   .oooo888   .oooo.         888b     d'888   .oooo.   ooo. .oo.   " + Ansi.ansi().cursorDownLine().cursorToColumn(6) +
			" 888oooo8     888  d88' `88b `888\"\"8P `888  d88' `888  `P  )88b        8 Y88. .P  888  `P  )88b  `888P\"Y88b  " + Ansi.ansi().cursorDownLine().cursorToColumn(6) +
			" 888    \"     888  888   888  888      888  888   888   .oP\"888        8  `888'   888   .oP\"888   888   888  " + Ansi.ansi().cursorDownLine().cursorToColumn(6) +
			" 888          888  888   888  888      888  888   888  d8(  888        8    Y     888  d8(  888   888   888  " + Ansi.ansi().cursorDownLine().cursorToColumn(6) +
			"o888o        o888o `Y8bod8P' d888b    o888o `Y8bod88P\" `Y888\"\"8o      o8o        o888o `Y888\"\"8o o888o o888o ";

	public static void main(String[] args) {
		AnsiConsole.systemInstall();
		SpringApplication.run(FloridamanApplication.class, args);

		System.out.print(Ansi.ansi().eraseScreen());
		System.out.print(Ansi.ansi().cursor(6, 6));
		System.out.print(Ansi.ansi().fgBrightCyan().bgDefault().a(BANNER));
		System.out.print(Ansi.ansi().cursorDown(2).cursorToColumn(6));
		System.out.print(Ansi.ansi().fgBrightRed().bgDefault().a("VERSION: " + VERSION));
		System.out.print(Ansi.ansi().cursorDownLine().cursorToColumn(6));
		System.out.print(Ansi.ansi().fgBrightRed().bgDefault().a("Made by Long-Schlong-Gang."));
	}

	@PreDestroy
	public void onExit() {
		AnsiConsole.systemUninstall();
	}
}
