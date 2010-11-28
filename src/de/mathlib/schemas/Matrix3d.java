package de.mathlib.schemas;

/**
 * This class represents a 3x3 matrix which is a special square matrix
 * where the order is two. 
 * 
 * @author Christian Vogel
 */
public class Matrix3d extends SquareMatrix {

	/**
	 * Initialize a 3x3 matrix. Only arrays with a size of 3 are allowed!
	 */
	public Matrix3d() {
		super(3);
	}
	
	/**
	 * This method will calculate the determinant of a 3x3 matrix. The algorithm
	 * is leant on the <i>Rule of Sarrus</i>.
	 * <p>
	 * {@link http://en.wikipedia.org/wiki/Rule_of_Sarrus}
	 * </p>
	 * 
	 * @return determinant of the matrix
	 * 
	 * @author Christian Vogel
	 */
	@Override
	public double determinant() {
		if(get() == null) {
			throw new NullPointerException("matrix cannot be null when trying to calculate determinant");
		}
		
		double firstCalc = this.getValueOf(0, 0) * this.getValueOf(1, 1) 
			* this.getValueOf(2, 2);
		
		double secondCalc = this.getValueOf(0, 1) * this.getValueOf(1, 2) 
			* this.getValueOf(2, 0);
		
		double thirdCalc = this.getValueOf(0, 2) * this.getValueOf(1, 0) 
			* this.getValueOf(2, 1);

		double fourthCalc = this.getValueOf(2, 0) * this.getValueOf(1, 1) 
			* this.getValueOf(0, 2);
		
		double fifthCalc = this.getValueOf(2, 1) * this.getValueOf(1, 2) 
			* this.getValueOf(0, 0);
		
		double sixthCalc = this.getValueOf(2, 2) * this.getValueOf(1, 0) 
			* this.getValueOf(0, 2);
		
		return firstCalc + secondCalc + thirdCalc 
			- fourthCalc - fifthCalc - sixthCalc;
	}
}
