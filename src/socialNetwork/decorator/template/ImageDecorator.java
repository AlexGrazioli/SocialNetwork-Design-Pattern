package socialNetwork.decorator.template;

public class ImageDecorator extends PostDecorator {

	private final String anImage;

	public ImageDecorator(UserPost component, String anImage) {
		super(component);
		this.anImage = anImage;
	}

	@Override
	protected StringBuilder writeComment(StringBuilder builder) {
		return builder;
	}

	@Override
	protected StringBuilder insertImage(StringBuilder builder) {
		return builder.append(anImage).append(" ");
	}
}
