package socialNetwork.observer.builder;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ProfileSubject {

	private Collection<FollowersObserver> followers = new ArrayList<FollowersObserver>();

	Collection<FollowersObserver> getObservers() {
		return followers;
	}

	public void attach(FollowersObserver follower) {
		followers.add(follower);
	}

	public void detach(FollowersObserver unfollower) {
		followers.remove(unfollower);
	}

	public void notifyFollowers() {
		followers.forEach(FollowersObserver::update);
	}
}
