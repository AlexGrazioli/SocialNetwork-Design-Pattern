package socialNetwork.observer.builder;

public class ProfileBirthdayObserver implements FollowersObserver {

	private Profile profile;
	private int currentAge;

	public ProfileBirthdayObserver(Profile profile) {
		this.profile = profile;
		this.currentAge = profile.getAge();
	}

	int getCurrentAge() {
		return currentAge;
	}

	@Override
	public void update() {
		if (profile.getAge() != currentAge)
			currentAge = profile.getAge();
	}
}
