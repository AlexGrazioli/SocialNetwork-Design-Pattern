package socialNetwork.chainOfResponsibility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import socialNetwork.observer.builder.MockImage;
import socialNetwork.observer.builder.Profile;
import socialNetwork.utilities.Images;

public class ProfileFinderTest {
	
	private Images profileImage;
	private Profile toSearch;
	private Profile profile;
	private Profile matchingProfile;
	
	@Before
	public void setUp() {
		this.profileImage = new MockImage();
		this.toSearch = new Profile.ProfileBuilder("Pippo", profileImage).withAge(29).createProfile();
		this.profile = new Profile.ProfileBuilder("Pluto", profileImage).withAge(25).createProfile();
		this.matchingProfile = new Profile.ProfileBuilder("Pippo", profileImage).withAge(25).createProfile();
	}

	@Test
	public void testSearchNameFailed() {
		assertThat(Profile.searchProfile(new SearchByName(null), toSearch, profile)).isFalse();
	}
	
	@Test
	public void testSearchAgeFailed() {
		assertThat(Profile.searchProfile(new SearchByAge(null), toSearch, profile)).isFalse();
	}
	
	@Test
	public void testSearchDoubleFailed() {
		assertThat(Profile.searchProfile(new SearchByName(new SearchByAge(null)), toSearch, profile)).isFalse();
	}
	
	@Test
	public void testMatchingName() {
		assertThat(Profile.searchProfile(new SearchByAge(new SearchByName(null)), toSearch, matchingProfile)).isTrue();
	}
	
	@Test
	public void testMatchingAge() {
		assertThat(Profile.searchProfile(new SearchByName(new SearchByAge(null)), profile, matchingProfile)).isTrue();
	}
}
