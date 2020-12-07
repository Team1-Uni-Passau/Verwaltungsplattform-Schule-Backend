package com.verwaltungsplatform.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name="nutzer")
public class User {
	
	/*
	 * @param id			eindeutige ID des Nutzers
	 * @param firstName		Vorname des Nutzers
	 * @param lastName		Nachname des Nutzers
	 * @param eMail			E-Mail Addresse des Nutzers
	 * @param passwort		Passwort des Nutzers
	 */
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "vorname")
	private String firstName;
	
	@Column (name = "name")
	private String lastName;
	
	@Column (name = "e_mail")
	private String eMail;

	@Column (name = "passwort")
	private String password;
	
	@Column (name = "rolle")
	private String role;
	
	@Column (name = "registrierungscode")
	private String registerCode;

	
	public User(String firstName, String lastName, String eMail, String passwort, String role) {
		/* Erstellt beim Registrieren einen neuen Nutzer
		 * Rolle wird durch den Registrierungscode bestimmt. Z.B. Code 123 -> neuer User = Parent
		 */
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.password = passwort;
		this.role = role;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return eMail;
	}
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	public void forgotPasswort(String eMail) {
		// schickt einen zufallsgenrierten code an die E-Mail des Nutzers
	}
	
	public void changePasswort(int passwortcode, String neuesPasswort) {
		// Ändert das Passwort mithilfe des Codes
	}
	
	public void anmelden(String eMail, String passwort) {
		// Meldet den Nutzer im System an
	}
	
	public void abmelden() {
		// Meldet den Nutzer vom System ab
	}
	
//	public Notification[] getNotifications() {
		// Gibt einem Nutzer alle ihn betreffenden Ankündigungen aus
//	}

	
	
	

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User nutzer = (User) o;
        return Objects.equals(id, nutzer.id) &&
                Objects.equals(firstName, nutzer.firstName) &&
                Objects.equals(lastName, nutzer.lastName) &&
                Objects.equals(eMail, nutzer.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, eMail);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Nutzer{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(eMail).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
