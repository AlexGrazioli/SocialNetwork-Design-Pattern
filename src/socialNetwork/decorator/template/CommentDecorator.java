package socialNetwork.decorator.template;

public class CommentDecorator extends PostDecorator {

	private final String comment;

	public CommentDecorator(UserPost component, String comment) {
		super(component);
		this.comment = comment;
	}

	@Override
	protected StringBuilder writeComment(StringBuilder builder) {
		return builder.append(comment).append(" ");
	}

	@Override
	protected StringBuilder insertImage(StringBuilder builder) {
		return builder;
	}
}
