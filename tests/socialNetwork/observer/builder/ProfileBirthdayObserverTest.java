package socialNetwork.observer.builder;

import org.junit.Before;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import socialNetwork.decorator.template.CommentDecorator;
import socialNetwork.decorator.template.ImageDecorator;
import socialNetwork.decorator.template.TextMessage;
import socialNetwork.decorator.template.UserPost;
import socialNetwork.utilities.Images;

public class ProfileBirthdayObserverTest {

	private Profile profile;
	private Images profileImage;
	private UserPost newPost;

	@Before
	public void setUp() {
		this.profileImage = new MockImage();
		this.profile = new Profile.ProfileBuilder("Pippo", profileImage).withAge(29).withPost("The Sun is shining!")
				.createProfile();
		newPost = new ImageDecorator(new CommentDecorator(new TextMessage("Is a Good Day!"), "I Agree!"),
				"anImage.bng");
	}

	@Test
	public void testProfileSubjectAttach() {
		ProfileBirthdayObserver follower = new ProfileBirthdayObserver(profile);
		ProfileBirthdayObserver followerOne = new ProfileBirthdayObserver(profile);
		profile.attach(follower);
		profile.attach(followerOne);
		assertThat(profile.getObservers()).containsExactlyInAnyOrder(follower, followerOne);
	}

	@Test
	public void testProfileSubjectDetach() {
		ProfileBirthdayObserver follower = new ProfileBirthdayObserver(profile);
		ProfileBirthdayObserver followerOne = new ProfileBirthdayObserver(profile);
		profile.getObservers().add(follower);
		profile.getObservers().add(followerOne);
		assertThat(profile.getObservers()).containsExactlyInAnyOrder(follower, followerOne);

		profile.detach(followerOne);
		assertThat(profile.getObservers()).containsExactly(follower);
	}

	@Test
	public void testProfileBirthdayObserver() {
		ProfileBirthdayObserver follower = new ProfileBirthdayObserver(profile);
		profile.getObservers().add(follower);
		assertThat(profile.getAge()).isEqualTo(follower.getCurrentAge());
	}

	@Test
	public void testProfileBirthdayObserverNotifyFollower() {
		ProfileBirthdayObserver follower = new ProfileBirthdayObserver(profile);
		profile.getObservers().add(follower);
		profile.isMyBirthday();
		assertThat(profile.getAge()).isEqualTo(follower.getCurrentAge());
	}

	@Test
	public void testProfileBirthdayObserverNotifyUnfollower() {
		ProfileBirthdayObserver follower = new ProfileBirthdayObserver(profile);
		profile.getObservers().add(follower);
		int followerCurrentAge = follower.getCurrentAge();
		assertThat(profile.getAge()).isEqualTo(followerCurrentAge);

		profile.getObservers().remove(follower);
		profile.isMyBirthday();
		assertThat(followerCurrentAge).isEqualTo(follower.getCurrentAge());
		assertThat(profile.getAge()).isNotEqualTo(follower.getCurrentAge());
	}

	@Test
	public void testDoNotUpdateAgeAfterNotifyPost() {
		ProfileBirthdayObserver ageFollower = new ProfileBirthdayObserver(profile);
		ProfilePostObserver postFollower = new ProfilePostObserver(profile);
		profile.attach(postFollower);
		profile.attach(ageFollower);

		assertThat(profile.getAge()).isEqualTo(ageFollower.getCurrentAge());
		assertThat(profile.getPostPublished()).isEqualTo(postFollower.getCurrentPost());

		profile.publishPost(newPost);

		assertThat(profile.getAge()).isEqualTo(ageFollower.getCurrentAge());
	}

	@Test
	public void testDoNotUpdatePostAfterNotifyAge() {
		ProfileBirthdayObserver ageFollower = new ProfileBirthdayObserver(profile);
		ProfilePostObserver postFollower = new ProfilePostObserver(profile);
		profile.attach(postFollower);
		profile.attach(ageFollower);
		
		assertThat(profile.getAge()).isEqualTo(ageFollower.getCurrentAge());
		assertThat(profile.getPostPublished()).isEqualTo(postFollower.getCurrentPost());
		
		profile.isMyBirthday();
		
		assertThat(profile.getPostPublished()).isEqualTo(postFollower.getCurrentPost());
	}
}
