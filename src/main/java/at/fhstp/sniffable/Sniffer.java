package at.fhstp.sniffable;

import java.util.ArrayList;
import java.util.List;

public class Sniffer implements Subject {

	protected List<Observer> observers = new ArrayList<Observer>();
	protected String name;
	protected String dogname;
	protected String firstname;
	protected String lastname;
	
	protected String handle;

	public Sniffer(String name, String firstname, String lastname, String dogname, String handle) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dogname = dogname;
		this.handle = "#" + handle;

	}

	public String getName() {
		return name;
	}

	public String getDogname() {
		return this.dogname;
	}

	public void setDogname(String dogname) {
		this.dogname = dogname;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHandle() {
		return handle;
	}

	public void tweet(String tweet) {
		System.out.printf("\nName: %s, Tweet: %s\n", name, tweet);
		notifySubscribers(tweet);
	}

	@Override
	public synchronized void addSubscriber(Observer observer) {
		observers.add(observer);
	}

	@Override
	public synchronized void removeSubscriber(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifySubscribers(String tweet) {
		observers.forEach(observer -> observer.notification(handle, tweet));
	}

}
