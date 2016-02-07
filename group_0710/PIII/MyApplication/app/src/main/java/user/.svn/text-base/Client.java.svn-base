package user;

import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import util.*;

/**
 * A client class.
 * 
 * @author Li Ju
 */

public class Client extends User {

	/**
	 * The last name of the client.
	 */
	private String lastName;

	/**
	 * The first name of the client.
	 */
	private String firstName;

	/**
	 * The email address of the client.
	 */
	private String email;

	/**
	 * The billing address of the client.
	 */
	private String address;

	/**
	 * The credit card number of the client.
	 */
	private String creditCardNumber;

	/**
	 * The expiration date of the client's credit card.
	 */
	private String expiryDate;

	/**
	 * A list representation of all the information of one client.
	 */
	private List<String> clientInfo;

	/**
	 * Constructor of object client add client information into a given csv
	 * file.
	 * 
	 * @param lastName
	 *            the last name of this client
	 * @param firstName
	 *            the first name of this client
	 * @param email
	 *            the email address of this client
	 * @param address
	 *            the billing address of this client
	 * @param creditCardNumber
	 *            the credit card number of this client
	 * @param expiryDate
	 *            credit card expiration date of this client
	 * 
	 * @throws NullPointerException
	 *             If any argument is null.
	 */
	public Client(String lastName, String firstName, String email,
			String address, String creditCardNumber, String expiryDate) {
		Assertions.checkNotNull(lastName);
		Assertions.checkNotNull(firstName);
		Assertions.checkNotNull(email);
		Assertions.checkNotNull(address);
		Assertions.checkNotNull(creditCardNumber);
		Assertions.checkNotNull(expiryDate);
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.address = address;
		this.creditCardNumber = creditCardNumber;
		this.expiryDate = expiryDate;
		clientInfo = new ArrayList<String>();
		clientInfo.add(lastName);
		clientInfo.add(firstName);
		clientInfo.add(email);
		clientInfo.add(address);
		clientInfo.add(creditCardNumber);
		clientInfo.add(expiryDate);
	}

	/**
	 * Gets the last name of this client
	 * 
	 * @return lastName the last name of this client
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of this client
	 * 
	 * @param lastName
	 *            the last name of this client
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the first name of this client
	 * 
	 * @return firstName the first name of this client
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of this client
	 * 
	 * @param firstName
	 *            the first name of this client
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the email of this client
	 * 
	 * @return email the email of this client
	 * 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of this client
	 * 
	 * @param email
	 *            the email of this client
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the address of this client
	 * 
	 * @return address the address of this client
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of this client
	 * 
	 * @param address
	 *            the address of this client
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the credit card number of this client
	 * 
	 * @return creditCardNumber the credit card number of this client
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * Sets the credit card number of this client
	 * 
	 * @param creditCardNumber
	 *            the credit card number of this client
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * Gets the expiration date of this client's credit card
	 * 
	 * @return expiryDate the expiration date of this client's credit card
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiration date of this client's credit card
	 * 
	 * @param expiryDate
	 *            the expiration date of this client's credit card
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Cleans the old csv file first Adds the client's information as a string
	 * into a desired CSV file
	 * 
	 * @param path
	 *            the path of the csv file
	 */
	public void addToCsv(String path) {
		try {
			BufferedWriter fileWriter = new BufferedWriter(
					new FileWriter(path));
			for (String item : this.clientInfo)
				fileWriter.append(item);
			fileWriter.append(Constants.DELIMITER);
			fileWriter.append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wipes the old csv file at the given location
	 * 
	 * @param path
	 *            the path to the csv file
	 */
	public static void wipeCsv(String path) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(path);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return lastName + "," + firstName + "," + email + "," + address + ","
				+ creditCardNumber + "," + expiryDate;
	}
}
