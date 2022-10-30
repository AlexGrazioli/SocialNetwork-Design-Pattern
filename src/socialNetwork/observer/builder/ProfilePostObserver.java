package socialNetwork.observer.builder;

public class ProfilePostObserver implements FollowersObserver {

	private Profile profile;
	private String currentPost;

	public ProfilePostObserver(Profile profile) {
		this.profile = profile;
		this.currentPost = profile.getPostPublished();
	}

	String getCurrentPost() {
		return currentPost;
	}

	@Override
	public void update() {
		if (profile.getPostPublished() != currentPost)
			currentPost = profile.getPostPublished();
	}
}
