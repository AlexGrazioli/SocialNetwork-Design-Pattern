package socialNetwork.decorator.template;

public class TextMessage implements UserPost {

	private String message;

	public TextMessage(String message) {
		this.message = message;
	}

	@Override
	public StringBuilder post(StringBuilder builder) {
		return builder.append(message).append(" ");
	}
}
