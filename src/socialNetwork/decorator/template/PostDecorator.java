package socialNetwork.decorator.template;

import java.util.Objects;

public abstract class PostDecorator implements UserPost {

	private UserPost component;

	public PostDecorator(UserPost component) {
		Objects.requireNonNull(component, "Illegal Argument, component cannot be null!");
		this.component = component;
	}

	@Override
	public final StringBuilder post(StringBuilder builder) {
		insertImage(builder);
		component.post(builder);
		writeComment(builder);
		return builder;
	}

	protected abstract StringBuilder writeComment(StringBuilder builder);

	protected abstract StringBuilder insertImage(StringBuilder builder);
}