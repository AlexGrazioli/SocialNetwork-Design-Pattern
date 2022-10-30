package socialNetwork.observer.builder;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import socialNetwork.decorator.template.CommentDecorator;
import socialNetwork.decorator.template.ImageDecorator;
import socialNetwork.decorator.template.TextMessage;
import socialNetwork.decorator.template.UserPost;
import socialNetwork.utilities.Images;

public class PostObserverTest {

	private Images profileImage;
	private Profile profile;
	private UserPost newPost;

	@Before
	public void setUp() {
		this.profileImage = new MockImage();
		this.profile = new Profile.ProfileBuilder("Pippo", profileImage).withPost("The Sun is shining!").createProfile();
		newPost = new ImageDecorator(new CommentDecorator(new TextMessage("Is a Good Day!"), "I Agree!"), "anImage.bng");
	}

	@Test
	public void testProfileSubjectPostAttach() {
		ProfilePostObserver follower = new ProfilePostObserver(profile);
		ProfilePostObserver followerOne = new ProfilePostObserver(profile);
		profile.attach(follower);
		profile.attach(followerOne);
		assertThat(profile.getObservers()).containsExactlyInAnyOrder(follower, followerOne);
	}

	@Test
	public void testProfileSubjectPostDetach() {
		ProfilePostObserver follower = new ProfilePostObserver(profile);
		ProfilePostObserver followerOne = new ProfilePostObserver(profile);
		profile.getObservers().add(follower);
		profile.getObservers().add(followerOne);
		assertThat(profile.getObservers()).containsExactlyInAnyOrder(follower, followerOne);

		profile.detach(followerOne);
		assertThat(profile.getObservers()).containsExactly(follower);
	}
	
	@Test
	public void testProfilePostObserver() {
		ProfilePostObserver follower = new ProfilePostObserver(profile);
		profile.getObservers().add(follower);
		assertThat(profile.getPostPublished()).isEqualTo(follower.getCurrentPost());
	}
	
	@Test
	public void testProfilePostObserverNotifyFollowers() {
		ProfilePostObserver follower = new ProfilePostObserver(profile);
		profile.getObservers().add(follower);
		profile.publishPost(newPost);
		assertThat(profile.getPostPublished()).isEqualTo(follower.getCurrentPost());
	}
	
	@Test
	public void testProfileBirthdayObserverNotifyUnfollower() {
		ProfilePostObserver follower = new ProfilePostObserver(profile);
		profile.getObservers().add(follower);
		String followerCurrentPost = profile.getPostPublished();
		assertThat(profile.getPostPublished()).isEqualTo(followerCurrentPost);

		profile.getObservers().remove(follower);
		profile.publishPost(newPost);
		assertThat(followerCurrentPost).isEqualTo(follower.getCurrentPost());
		assertThat(profile.getPostPublished()).isNotEqualTo(follower.getCurrentPost());
	}
}
