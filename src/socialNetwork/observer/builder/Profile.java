package socialNetwork.observer.builder;

import java.util.Objects;

import socialNetwork.chainOfResponsibility.ProfileFinder;
import socialNetwork.decorator.template.UserPost;
import socialNetwork.utilities.Images;

public class Profile extends ProfileSubject {

	private String name;
	private int age;
	private Images profileImage;
	private String postPublished;

	private Profile() {
	}

	private void setAge(int age) {
		this.age = age;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setProfileImage(Images profileImage) {
		this.profileImage = profileImage;
	}

	private void setPostPublished(String postPublished) {
		this.postPublished = postPublished;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public String getPostPublished() {
		return postPublished;
	}

	Images getProfileImage() {
		return profileImage;
	}

	public void isMyBirthday() {
		this.age = age + 1;
		notifyFollowers();
	}

	public String publishPost(UserPost newPost) {
		StringBuilder builder = new StringBuilder();
		StringBuilder posted = newPost.post(builder);
		setPostPublished(posted.toString());
		notifyFollowers();
		return postPublished;
	}

	public static boolean searchProfile(ProfileFinder finder, Profile toSearch, Profile toCompare) {
		return finder.matchProfile(toSearch, toCompare);
	}

	public static class ProfileBuilder {

		private String name;
		private int age;
		private Images profileImage;
		private String postPublished;

		public ProfileBuilder(String name, Images profileImage) {
			this.name = name;
			this.profileImage = profileImage;
		}

		public ProfileBuilder withAge(int age) {
			if (age <= 0)
				throw new IllegalArgumentException("Age must be positive: " + age);
			this.age = age;
			return this;
		}

		public ProfileBuilder withPost(String postPublished) {
			Objects.requireNonNull(postPublished, "A post can't be null");
			this.postPublished = postPublished;
			return this;
		}

		public Profile createProfile() {
			Profile profile = new Profile();
			profile.setName(name);
			profile.setAge(age);
			profile.setProfileImage(profileImage);
			profile.setPostPublished(postPublished);
			return profile;
		}
	}
}
