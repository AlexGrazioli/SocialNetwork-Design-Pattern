package socialNetwork.decorator.template;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import socialNetwork.decorator.template.CommentDecorator;
import socialNetwork.decorator.template.ImageDecorator;
import socialNetwork.decorator.template.TextMessage;
import socialNetwork.decorator.template.UserPost;
import socialNetwork.observer.builder.MockImage;
import socialNetwork.observer.builder.Profile;

public class UserPostTest {

	private StringBuilder builder;
	private TextMessage post;
	private UserPost newPost;
	private Profile profile;

	@Before
	public void setUp() {
		newPost = new ImageDecorator(new CommentDecorator(new TextMessage("Is a Good Day!"), "I Agree!"), "anImage.bng");
		this.post = new TextMessage("Today is a good day!");
		this.builder = new StringBuilder();
		this.profile = new Profile.ProfileBuilder("Pippo", new MockImage()).createProfile();
	}

	@Test
	public void testTextMessagePost() {
		assertThat(post.post(builder).toString()).isEqualTo("Today is a good day! ");
	}

	@Test
	public void testNewPost() {
		assertThat(newPost.post(builder).toString()).isEqualTo("anImage.bng Is a Good Day! I Agree! ");
	}

	@Test
	public void testPublishPost() {
		assertThat(profile.publishPost(newPost)).isEqualTo("anImage.bng Is a Good Day! I Agree! ");
	}

	@Test
	public void testPublishPostComment() {
		UserPost postWithComment = new CommentDecorator(new TextMessage("Is a good day"), "It really is!");
		assertThat(profile.publishPost(postWithComment)).isEqualTo("Is a good day It really is! ");
	}
	
	@Test
	public void testPublishPostoDoubleComment() {
		UserPost postWithDoubleComment = new CommentDecorator(new CommentDecorator(new TextMessage("Is a good day"), "Not for me :(") , "It really is!");
		assertThat(profile.publishPost(postWithDoubleComment)).isEqualTo("Is a good day Not for me :( It really is! ");
	}
	
	@Test
	public void testPublishPostImage() {
		UserPost postWithImage = new ImageDecorator(new TextMessage("My dog is so cute!"), "aDogImage.jpg");
		assertThat(profile.publishPost(postWithImage)).isEqualTo("aDogImage.jpg My dog is so cute! ");
	}
	
	@Test
	public void testPublishPostDoubleImage() {
		UserPost postWithDoubleImage = new ImageDecorator(new ImageDecorator(new TextMessage("My pets are so cute!"), "aCatImage.jpg"), "aDogImage.jpg");
		assertThat(profile.publishPost(postWithDoubleImage)).isEqualTo("aDogImage.jpg aCatImage.jpg My pets are so cute! ");
	}
	
	@Test
	public void testPublishPostCompleteWithDoubleComment() {
		UserPost newPost = new CommentDecorator(
				new ImageDecorator(
						new CommentDecorator(
								new TextMessage("My dog is so cute!"), "It's the cutest"), "aDogImage.jpg"), "I want to hug it!");
		assertThat(profile.publishPost(newPost)).isEqualTo("aDogImage.jpg My dog is so cute! It's the cutest I want to hug it! ");
	}
}
