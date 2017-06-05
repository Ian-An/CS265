public class gInt {
	private int _r;
	private int _i;


	public gInt(int r){
		_r = r;
	}
	public gInt(int r, int i){
		_r = r;
		_i = i;	
	}
	public int real(){
		return _r;
	}
	public int imag(){
		return _i;
	}
	public gInt add(gInt rhs){
		gInt added = new gInt((_r + rhs.real()),(_i + rhs.imag()));
		return added;
	}
	public gInt multiply(gInt rhs) {
		gInt m = new
			gInt((_r*rhs.real())-(_i*rhs.imag()),(_r*rhs.imag()+_i*rhs.real()));
		return m;
	}
	public float norm() {
		return (float)(Math.sqrt((_r*_r)+(_i*_i)));
	}

	public static void main(String arg[]) {
	
		gInt g1 = new gInt(1,1);
		gInt g2 = new gInt (2,5);
		gInt added = null;
		added = g1.add(g2);
		gInt mul;
		mul = g1.multiply(g2);
		gInt normed = new gInt(4,3);
		System.out.println("add:"+added.real() + " + " + added.imag() + "i");
		System.out.println("multiply: " + mul.real() + " + " + mul.imag() + "i");
		System.out.println("norm of the 4+3i = " + normed.norm());

	}
}
