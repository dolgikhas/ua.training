package ua.testing.model.entity;

public enum LoginData {
	 Taras1( "Taras", "taras1" ),
	 Petro1( "Petro", "petro1" ),
	 Mikola( "Mikola", "mikola1" );
	
	private String firstName;
	private String login;
	
	LoginData( final String firstName, final String login ) {
		this.firstName = firstName;
		this.login = login;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLogin() {
		return login;
	}

}
