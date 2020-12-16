package com.verwaltungsplatform.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	private String email;

	@Column (name = "passwort")
	private String password;
	
    @ManyToOne(targetEntity = Role_RegisterCode_Mapper.class)
    @JoinColumn(name="rolle")
    private Role_RegisterCode_Mapper role;
	

	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	//default constructor 
	public User() {
		
	}
	
	
	
	public User(String firstName, String lastName, String eMail, String passwort, Role_RegisterCode_Mapper role) {
		/* Erstellt beim Registrieren einen neuen Nutzer
		 * Rolle wird durch den Registrierungscode bestimmt. Z.B. Code 123 -> neuer User = Parent
		 */
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = eMail;
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
		return email;
	}
	
	
	public Role_RegisterCode_Mapper getRoleRegisterCodeMapper() {
		return role;
	}

	public void setRoleRegisterCodeMapper(Role_RegisterCode_Mapper role) {
		this.role = role;
	}
	
	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void forgotPassword(String eMail) {
		// schickt einen zufallsgenrierten code an die E-Mail des Nutzers
	}
	
	public void changePassword(int passwortcode, String neuesPasswort) {
		// Ändert das Passwort mithilfe des Codes
	}
	
	public void login(String eMail, String passwort) {
		// Meldet den Nutzer im System an
	}
	
	public void logout() {
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
                Objects.equals(email, nutzer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Nutzer{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
