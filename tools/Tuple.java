package tools;

public class Tuple<S,I> {
	private S arg1;
	private I arg2;
	
	public Tuple(S s, I i){
		arg1=s;
		arg2 = i;
	}

	public S getArg1() {
		return arg1;
	}

	public void setArg1(S arg1) {
		this.arg1 = arg1;
	}

	public I getArg2() {
		return arg2;
	}

	public void setArg2(I arg2) {
		this.arg2 = arg2;
	}
	
}
