package utilGraph;

@SuppressWarnings("serial")
public class BadVertexException extends RuntimeException {

	public BadVertexException(String tag) {
		super(tag);
	}
}
