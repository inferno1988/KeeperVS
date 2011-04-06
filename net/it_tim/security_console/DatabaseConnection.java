package net.it_tim.security_console;

import java.io.IOException;
import java.sql.SQLException;

import net.it_tim.security_console.Settings.SettingsLoader;

public class DatabaseConnection {
	public DatabaseConnection() {
		SettingsLoader props = new SettingsLoader(".keeper/db_props");
		try {
		props.loadConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() throws SQLException {
		
	}
}
