package utilGraph;

@SuppressWarnings("serial")
public class DuplicateTagException extends RuntimeException {

	public DuplicateTagException(String tag) {
		super(tag);
	}
}
