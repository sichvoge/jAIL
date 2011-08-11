/**
 * 
 */
package de.jail.interfaces;

/**
 * Interface introducing default mathematical operations for numbers such as
 * addition, multiplication, division and subtraction.
 * 
 * @param <T> type or representation of the numbers on which the operators has to be executed
 * 
 * @author Christian Vogel
 */
public interface IDefaultOperator<T> {
	
	/**
	 * A representation of a number will be aggregated with a given argument!
	 * 
	 * @param arg the argument number which will raise a specific number
	 * @return the result of the addition
	 */
	T add(T arg);
	
	/**
	 * A representation of a number will be multiplied with a given number
	 * 
	 * @param arg the argument number which will multiply a specific number
	 * @return the result of the multiplication
	 */
	T mul(T arg);
	
	/**
	 * A representation of a number will be divided with a given number
	 * 
	 * @param arg the argument number which will divide a specific number
	 * @return the result of the division
	 */
	T div(T arg);
	
	/**
	 * A representation of a number will be subtracted with a given number
	 * 
	 * @param arg the argument number which will subtract a specific number
	 * @return the result of the subtraction
	 */
	T sub(T arg);
	
}
