package socialNetwork.observer.builder;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import socialNetwork.utilities.Images;

public class ProfileBuilderTest {
	private Images profileImage;
	private Profile profile;

	@Before
	public void setUp() {
		this.profileImage = new MockImage();
	}

	@Test
	public void testBuilderAgeException() {
		assertThatThrownBy(() -> {
			this.profile = new Profile.ProfileBuilder("Pippo", profileImage).withAge(-5).createProfile();
		}).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Age must be positive: -5");
	}

	@Test
	public void testBuilderWithAge() {
		this.profile = new Profile.ProfileBuilder("Pippo", profileImage).withAge(29).createProfile();
		assertThat(profile.getAge()).isEqualTo(29);
	}
	
	@Test
	public void testBuilderPostException() {
		assertThatThrownBy(() -> {
			this.profile = new Profile.ProfileBuilder("Pippo", profileImage).withPost(null).createProfile();
		}).isInstanceOf(NullPointerException.class).hasMessageContaining("A post can't be null");
	}
	
	@Test
	public void testBuilderWithPost() {
		this.profile = new Profile.ProfileBuilder("Pippo", profileImage).withPost("The Sun is shining!").createProfile();
		assertThat(profile.getPostPublished()).isEqualTo("The Sun is shining!");
	}
	
	@Test
	public void testBuilderName() {
		this.profile = new Profile.ProfileBuilder("Pippo", profileImage).createProfile();
		assertThat(profile.getName()).isEqualTo("Pippo");
	}
	
	@Test
	public void testBuilderProfileImage() {
		this.profile = new Profile.ProfileBuilder("Pippo", profileImage).createProfile();
		assertThat(profile.getProfileImage()).isEqualTo(profileImage);
	}
}
