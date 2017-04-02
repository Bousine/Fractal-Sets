package code;



/**
 * Class which calculates escape-time in its methods to generate a Julia fractal 
 * 
 * @author Mark Kayutkin
 * @author Asif Hasan
 * @author Xiangshuai Gao 
 */


public class JuliaSet {
	/** Stores updated value of x */
	private double _xCalc;
	/** Stores updated value of y */
	private double _yCalc;
	/** Number of rows */
	private int _row;
	/** Number of columns */
	private int _col;
	/** 2-d array which contains the respective escape-time of the points */
	int[][] _fractal;
	/** Maximum x-coordinate */
	private double _xHigh;
	/** Minimum x-coordinate */
	private double _xLow;
	/** Maximum y-coordinate */
	private double _yHigh;
	/** Minimum y-coordinate */
	private double _yLow;
	/** Constant added to x-coordinate */
	private double _xConstant;
	/** Constant added to y-coordinate */
	private double _yConstant;
	/** Stores the current x-coordinate of the point */
	private double _xCurr;
	/** Stores the current y-coordinate of the point */
	private double _yCurr;
	/** Escape Distance */
	private double _escDist;
	
	private double _escTime;
	
	
	/** Constructor to instantiate instance variables */
	public JuliaSet(double escDist, double escTime){
		_row = 512;
		_col = 512;
		_fractal = new int[_row][_col];
		_xHigh = 1.7;
		_xLow = -1.7;
		_yHigh = 1.0;
		_yLow = -1.0;
		_xConstant = -0.72689;
		_yConstant = 0.188887;
		_escDist = escDist;
		_escTime = escTime;
	}
	
	/**
	 * Calculates the escape-times of all points in the fractal
	 * 
	 * @return 2-d array which contains the respective escape-time of the points
	 */
	public int[][] fractalCalc(){
		int[][] grid = new int[_row][_col];
		for(int row = 0; row < _row; row++){
			for(int col = 0; col < _col; col++){
				double resultX = arrayToCoordinate(row, _xLow, _xHigh, _row);
				double resultY = arrayToCoordinate(col, _yLow, _yHigh, _col);
				int escTime = escapeTime(resultX, resultY);
				grid[row][col] = escTime;
			}
		}
		return grid;
	}
	
	/**
	 * Updates the values of _xCalc and _yCalc
	 */
	public void update(){
		double i = _xCalc;
		double j = _yCalc;
		_xCalc = i*i - j*j +_xConstant;
		_yCalc = (2*i*j) + _yConstant;
	}
	
	/**
	 * Uses escape-time algorithm to calculate the escape time of a point
	 * @param x-coordinate of the point
	 * @param y-coordinate of the point
	 * @return Escape-time for the corresponding point
	 */
	public int escapeTime(double x, double y){
		_xCurr = x;
		_yCurr = y;
		_xCalc = _xCurr;
		_yCalc = _yCurr;
		double dist = Math.sqrt(_xCalc*_xCalc + _yCalc*_yCalc);		
		int passes = 0;
		while(dist <= _escDist && passes < _escTime){
			update();
			passes++;
			dist = Math.sqrt(_xCalc*_xCalc + _yCalc*_yCalc);
		}
		int escTime = passes;
		return escTime;
	}
	
	/**
	 * Calculates increment of coordinates for each increment of row or column in 2-d array
	 * @param start- minimum coordinate
	 * @param end - maximum coordinate
	 * @param div - number of row or column in 2-d array
	 * @return increment of coordinates
	 */
	public double rangeInc(double start, double end, int div){
		double inc = (end - start) / div;
		return inc;
	}
	
	/**
	 * Translates array to coordinates
	 * @param i- row or column number in 2-d array
	 * @param start- minimum coordinate
	 * @param end - maximum coordinate
	 * @param div - number of row or column in 2-d array
	 * @return The corresponding x or y coordinate
	 */
	public double arrayToCoordinate(int i, double start, double end, int div){
		double result = start + i * rangeInc(start, end, div);
		return result;
	}	
	
	public double getEscapeDistance(){
		return _escDist;
	}
	
}
