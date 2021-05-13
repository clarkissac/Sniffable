package at.fhstp.sniffable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Sniffer implements Subject,java.io.Serializable {
	private static final long serialVersionUID = 1L;
	//protected List<Observer> observers = new ArrayList<Observer>();
	protected List<String> followerlist = new ArrayList<String>();
	protected int followercount=0;
	protected List<String> followinglist = new ArrayList<String>();
	protected int followingcount = 0;
	protected List<Tweet> tweets = new ArrayList<Tweet>();

	protected List<String> timeline = new ArrayList<String>();

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
	}

	public void updateFollowersTimeline(String event)
	{
		for(String followername:this.followerlist)
		{
			Sniffer follower=SniffableApplication.accountsearch(followername, null);
			follower.addToTimeline(event);
			SniffableApplication.updateObjH2(follower);
		}
	}

	public List<Tweet> getTweets() {
		List<Tweet> returntweets = tweets;
		Collections.reverse(returntweets);
		return returntweets;
	}
	public Tweet searchTweet(String id){
		for (Tweet tweet : tweets) {
			if (tweet.getContent()[1].compareTo(id) == 1) {
				return tweet;
			}
			else{
				return new Tweet();
			}
		}
		return null;

	}

	public void addTweets(String tweet) {
		this.tweets.add(new Tweet(tweet));
		updateFollowersTimeline(this.name+" hat getweetet:\n"+tweet);
	} 
	
	public List<String> getTimeline() {
		Collections.reverse(this.timeline);
		return this.timeline;
	}

	public void addToTimeline(String event) {
		this.timeline.add(event);
	} 

	public int getFollowercount() {
		return this.followercount;
	}

	public int getFollowingcount()
	{
		return this.followingcount;
	}
	
	public void addNewFollower(String follower)
	{
		this.followerlist.add(follower);
		this.followercount+=1;
	}

	public void addNewFollowing(String following)
	{
		this.followinglist.add(following);
		this.followingcount+=1;
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
