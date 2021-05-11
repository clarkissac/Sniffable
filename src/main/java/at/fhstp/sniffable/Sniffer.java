package at.fhstp.sniffable;

public class Sniffer implements Subject,java.io.Serializable {
	private static final long serialVersionUID = 1L;
	//protected List<Observer> observers = new ArrayList<Observer>();
	protected String name;
	protected String password;
	protected String dogname;
	protected String firstname;
	protected String lastname;
	
	protected String handle;


	public Sniffer() { //empty consructor
	}

	public Sniffer(String name, String firstname, String password, String lastname, String dogname) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dogname = dogname;
		this.password = password;
		//this.handle = "#" + handle;

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	/**
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
