package at.fhstp.sniffable;

//import java.util.ArrayList;
//import java.util.List;

public class Sniffer implements Subject {

	//protected List<Observer> observers = new ArrayList<Observer>();
	private String name;
	private String password;
	private String dogname;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getDogName() {
		return dogname;
	}
	public void setDogName(String dogName) {
		this.dogname = dogName;
	}

	
	/*public Sniffer(String name, String handle) {
		super();
		this.name = name;
		this.handle = "#" + handle;
	}

	public String getName() {
		return name;
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
	}*/

}
