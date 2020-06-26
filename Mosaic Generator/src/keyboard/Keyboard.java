package keyboard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Keyboard {
	static Scanner keyboard = new Scanner(System.in);

	public enum Limit {
		G, L, GE, LE
	}

	public enum Range {
		II, EE, IE, EI
	}

	/*CLOSE KEYBOARD*/
	//1. Close keyboard
	public static void ck() {
		keyboard.close();
	}

	/* READING A NUMBER
	 * 1. Ask for a number
	 *    1.1 Check and repeat petition if the entered number is not within the valid range of the respective numbers
	 * 2. Return number
	 */
	//This approach will be valid for making a simple reading of all types of numbers.
	//This method will simply ask for a byte number
	public static byte readByte() {
		byte b;

		try {
			//1. Ask for a number
			//1.1 Check and repeat petition if the entered number is not within the valid range of the respective numbers
			b = (byte) readIntCheckingLimits(Byte.MIN_VALUE, Byte.MAX_VALUE);
			keyboard.nextLine();
			//-------------------------------------------------------------------------LA LIMPIEZA DEL BUFFER MEJOR HACERLA CON UN FINALLY
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.println("Error: is not a valid number");
			b = readByte();
		}
		//2. Return number
		return b;
	}

	/*
	 * 1. Show the petition message
	 * 2. Read number
	 * 3. Return number
	 */
	//This approach will be valid for making a simple reading of all types of numbers with a petition message.
	//This method will ask for a byte number with a petition message as parameter
	public static byte readByte(String s) {
		byte b = 0;
		System.out.println(s);
		b = readByte();
		return b;
	}

	//This method will simply ask for a short number
	public static short readShort() {
		short sh;

		try {
			sh = (short) readIntCheckingLimits(Short.MIN_VALUE, Short.MAX_VALUE);
			keyboard.nextLine();
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.println("Error: is not a valid number");
			sh = readShort();
		}
		return sh;

	}

	//This method will ask for a short number with a petition message as parameter
	public static short readShort(String s) {
		short sh;
		System.out.println(s);
		sh = readShort();
		return sh;
	}

	//This method will simply ask for an int number
	public static int readInt() {
		int num;

		try {
			num = keyboard.nextInt();
			keyboard.nextLine();
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%nValid numbers are between %d and %d%n", Integer.MIN_VALUE,
					Integer.MAX_VALUE);
			num = readInt();
		}
		return num;
	}

	//This method will ask for an int number with a petition message as parameter
	public static int readInt(String s) {
		int num;
		System.out.println(s);
		num = readInt();
		return num;
	}

	//This method will simply ask for a long number
	public static long readLong() {
		long l;

		try {
			l = keyboard.nextLong();
			keyboard.nextLine();
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%nValid numbers are between %d and %d%n", Long.MIN_VALUE,
					Long.MAX_VALUE);
			l = readLong();
		}
		return l;
	}

	//This method will ask for a long number with a petition message as parameter
	public static long readLong(String s) {
		long l;

		System.out.println(s);
		l = readLong();
		return l;
	}

	//This method will simply ask for a float number
	public static float readFloat() {
		float f;
		try {
			f = (float) readDoubleCheckingLimits(-Float.MAX_VALUE, Float.MAX_VALUE);
			keyboard.nextLine();

		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.println("Error: is not a valid number");
			f = readFloat();
		}
		return f;
	}

	//This method will ask for a float number with a petition message as parameter
	public static float readFloat(String s) {
		float f;

		System.out.println(s);
		f = readFloat();
		return f;
	}

	//This method will simply ask for a double number
	public static double readDouble() {
		double d;
		try {
			d = keyboard.nextDouble();
			keyboard.nextLine();

		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%nValid numbers are between %f and %f%n", -Double.MAX_VALUE,
					Double.MAX_VALUE);
			d = readDouble();
		}
		return d;
	}

	//This method will ask for a double number with a petition message as parameter
	public static double readDouble(String s) {
		double d;

		System.out.println(s);
		d = readDouble();
		return d;
	}

	/* READING A NUMBER WITH LIMIT
	 * 
	 * 1. Ask for the number according to the range demarcated
	 *   1.1 Check and repeat petition if the entered number is not within the valid range of the respective number  
	 * 3. Return number
	 */

	//This approach will be valid for making a reading of all types of numbers demarcated within a range.
	//This method will ask for a byte number in a range of 1 parameter
	public static byte readLimit(Limit R, byte i) {
		byte b = 0;
		//The range of only 1 value will be delimited by the enum Limit
		//This will be used by all functions within a range of only 1 value

		try {
			switch (R) {
			case G:
				do {
					//---------------------------------------------------------------------------------MEJOR UTILIZAR LA FUNCION readByte()
					//-------------------------------------ENTONCES PODEMOS QUITAR LOS TRY CATCH PORQUE YA LO ESTAN CONTROLANDO LOS readByte()
					b = (byte) readIntCheckingLimits(Byte.MIN_VALUE, Byte.MAX_VALUE);
					keyboard.nextLine();
					if (b <= i) {
						System.out.printf("Greater than %d%n", i);
					}
				} while (b <= i);
				break;
			case L:
				do {
					b = (byte) readIntCheckingLimits(Byte.MIN_VALUE, Byte.MAX_VALUE);
					keyboard.nextLine();
					if (b >= i) {
						System.out.printf("Lower than %d%n", i);
					}
				} while (b >= i);
				break;
			case GE:
				do {
					b = (byte) readIntCheckingLimits(Byte.MIN_VALUE, Byte.MAX_VALUE);
					keyboard.nextLine();
					if (b < i) {
						System.out.printf("Greater or equal than %d%n", i);
					}
				} while (b < i);
				break;
			case LE:
				do {
					b = (byte) readIntCheckingLimits(Byte.MIN_VALUE, Byte.MAX_VALUE);
					keyboard.nextLine();
					if (b > i) {
						System.out.printf("Lower or equal than %d%n", i);
					}
				} while (b > i);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%n");
			b = readLimit(R, i);
		}
		//3. Return number
		return b;
	}

	/* 
	 * 1. Show the petition message
	 * 2. Read number with a limit
	 * 3. Return number
	 */

	//This approach will be valid for making a reading of all types of numbers demarcated within a range.
	//This method will ask for a byte number in a range of 1 parameter with a petition message as parameter
	public static byte readLimit(String s, Limit R, byte i) {
		byte b = 0;
		//1. Show the petition message
		System.out.println(s);
		//2. Read number with a limit
		b = readLimit(R, i);
		//3. Return number
		return b;
	}

	//This method will ask for a short number in a range of 1 parameter
	public static short readLimit(Limit R, short i) {
		short sh = 0;

		try {
			switch (R) {
			case G:
				do {
					sh = (short) readIntCheckingLimits(Short.MIN_VALUE, Short.MAX_VALUE);
					keyboard.nextLine();
					if (sh <= i) {
						System.out.printf("Greater than %d%n", i);
					}
				} while (sh <= i);
				break;
			case L:
				do {
					sh = (short) readIntCheckingLimits(Short.MIN_VALUE, Short.MAX_VALUE);
					keyboard.nextLine();
					if (sh >= i) {
						System.out.printf("Lower than %d%n", i);
					}
				} while (sh >= i);
				break;
			case GE:
				do {
					sh = (short) readIntCheckingLimits(Short.MIN_VALUE, Short.MAX_VALUE);
					keyboard.nextLine();
					if (sh < i) {
						System.out.printf("Greater or equal than %d%n", i);
					}
				} while (sh < i);
				break;
			case LE:
				do {
					sh = (short) readIntCheckingLimits(Short.MIN_VALUE, Short.MAX_VALUE);
					keyboard.nextLine();
					if (sh > i) {
						System.out.printf("Lower or equal than %d%n", i);
					}
				} while (sh > i);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%n");
			sh = readLimit(R, i);
		}
		return sh;
	}

	//This method will ask for a short number in a range of 1 parameter with a petition message as parameter
	public static short readLimit(String s, Limit R, short i) {
		short sh = 0;
		System.out.println(s);
		sh = readLimit(R, i);
		return sh;
	}

	//This method will ask for an int number in a range of 1 parameter
	public static int readLimit(Limit R, int i) {
		int num = 0;

		try {
			switch (R) {
			case G:
				do {
					num = keyboard.nextInt();
					keyboard.nextLine();
					if (num <= i) {
						System.out.printf("Greater than %d%n", i);
					}
				} while (num <= i);
				break;
			case L:
				do {
					num = keyboard.nextInt();
					keyboard.nextLine();
					if (num >= i) {
						System.out.printf("Lower than %d%n", i);
					}
				} while (num >= i);
				break;
			case GE:
				do {
					num = keyboard.nextInt();
					keyboard.nextLine();
					if (num < i) {
						System.out.printf("Greater or equal than %d%n", i);
					}
				} while (num < i);
				break;
			case LE:
				do {
					num = keyboard.nextInt();
					keyboard.nextLine();
					if (num > i) {
						System.out.printf("Lower or equal than %d%n", i);
					}
				} while (num > i);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%nValid numbers are between %d and %d%n", Integer.MIN_VALUE,
					Integer.MAX_VALUE);
			num = readLimit(R, i);
		}
		return num;
	}

	//This method will ask for an int number in a range of 1 parameter with a petition message as parameter
	public static int readLimit(String s, Limit R, int i) {
		int num = 0;
		System.out.println(s);
		num = readLimit(R, i);
		return num;
	}

	//This method will ask for a long number in a range of 1 parameter
	public static long readLimit(Limit R, long i) {
		long l = 0;

		try {
			switch (R) {
			case G:
				do {
					l = keyboard.nextLong();
					keyboard.nextLine();
					if (l <= i) {
						System.out.printf("Greater than %d%n", i);
					}
				} while (l <= i);
				break;
			case L:
				do {
					l = keyboard.nextLong();
					keyboard.nextLine();
					if (l >= i) {
						System.out.printf("Lower than %d%n", i);
					}
				} while (l >= i);
				break;
			case GE:
				do {
					l = keyboard.nextLong();
					keyboard.nextLine();
					if (l < i) {
						System.out.printf("Greater or equal than %d%n", i);
					}
				} while (l < i);
				break;
			case LE:
				do {
					l = keyboard.nextLong();
					keyboard.nextLine();
					if (l > i) {
						System.out.printf("Lower or equal than %d%n", i);
					}
				} while (l > i);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%nValid numbers are between %d and %d%n", Long.MIN_VALUE,
					Long.MAX_VALUE);
			l = readLimit(R, i);
		}
		return l;
	}

	//This method will ask for a long number in a range of 1 parameter with a petition message as parameter
	public static long readLimit(String s, Limit R, long i) {
		long l = 0;
		System.out.println(s);
		l = readLimit(R, i);
		return l;
	}

	//This method will ask for a float number in a range of 1 parameter
	public static float readLimit(Limit R, float i) {
		float f = 0.0f;

		try {
			switch (R) {
			case G:
				do {
					f = (float) readDoubleCheckingLimits(-Float.MAX_VALUE, Float.MAX_VALUE);
					keyboard.nextLine();
					if (f <= i) {
						System.out.printf("Greater than %f%n", i);
					}
				} while (f <= i);
				break;
			case L:
				do {
					f = (float) readDoubleCheckingLimits(-Float.MAX_VALUE, Float.MAX_VALUE);
					keyboard.nextLine();
					if (f >= i) {
						System.out.printf("Lower than %f%n", i);
					}
				} while (f >= i);
				break;
			case GE:
				do {
					f = (float) readDoubleCheckingLimits(-Float.MAX_VALUE, Float.MAX_VALUE);
					keyboard.nextLine();
					if (f < i) {
						System.out.printf("Greater or equal than %f%n", i);
					}
				} while (f < i);
				break;
			case LE:
				do {
					f = (float) readDoubleCheckingLimits(-Float.MAX_VALUE, Float.MAX_VALUE);
					keyboard.nextLine();
					if (f > i) {
						System.out.printf("Lower or equal than %f%n", i);
					}
				} while (f > i);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%n");
			f = readLimit(R, i);
		}
		return f;
	}

	//This method will ask for a float number in a range of 1 parameter with a petition message as parameter
	public static float readLimit(String s, Limit R, float i) {
		float f = 0.0f;
		System.out.println(s);
		f = readLimit(R, i);
		return f;
	}

	//This method will ask for a double number in a range of 1 parameter
	public static double readLimit(Limit R, double i) {
		double d = 0.0;

		try {
			switch (R) {
			case G:
				do {
					d = keyboard.nextDouble();
					keyboard.nextLine();
					if (d <= i) {
						System.out.printf("Greater than %f%n", i);
					}
				} while (d <= i);
				break;
			case L:
				do {
					d = keyboard.nextDouble();
					keyboard.nextLine();
					if (d >= i) {
						System.out.printf("Lower than %f%n", i);
					}
				} while (d >= i);
				break;
			case GE:
				do {
					d = keyboard.nextDouble();
					keyboard.nextLine();
					if (d < i) {
						System.out.printf("Greater or equal than %f%n", i);
					}
				} while (d < i);
				break;
			case LE:
				do {
					d = keyboard.nextDouble();
					keyboard.nextLine();
					if (d > i) {
						System.out.printf("Lower or equal than %f%n", i);
					}
				} while (d > i);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%nValid numbers are between %f and %f%n", -Double.MAX_VALUE,
					Double.MAX_VALUE);
			d = readLimit(R, i);
		}
		return d;
	}

	//This method will ask for a double number in a range of 1 parameter with a petition message as parameter
	public static double readLimit(String s, Limit R, double i) {
		double d = 0.0;
		System.out.println(s);
		d = readLimit(R, i);
		return d;
	}

	/* READ NUMBER WITH RANGE */
	//The function readRange will have the same approach as readLimit, but with 2 parameters in range
	//This method will ask for a byte number in a range of 2 parameter
	public static byte readRange(byte min, byte max, Range R) {
		byte b = 0;
		//The range of 2 values will be delimited by the enum Range
		//This will be used by all functions within a range of 2 values

		if (min > max) {
			//-------------------------------------------------------------LANZAR MENSAJE DENTRO DEL throw (DENTRO DE LOS PARENTESIS () )
			throw new IllegalArgumentException();
		}
		try {
			switch (R) {
			case II:
				do {
					//----------------------------------------------------------------------------MEJOR UTILIZAR LA FUNCION readByte()
					b = (byte) readIntCheckingLimits(Byte.MIN_VALUE, Byte.MAX_VALUE);
					keyboard.nextLine();
					if (b < min || b > max) {
						System.out.printf("Between %d and %d, both included%n", min, max);
					}
				} while (b < min || b > max);
				break;
			case EE:
				do {
					b = (byte) readIntCheckingLimits(Byte.MIN_VALUE, Byte.MAX_VALUE);
					keyboard.nextLine();
					if (b <= min || b >= max) {
						System.out.printf("Between %d and %d, both excluded%n", min, max);
					}
				} while (b <= min || b >= max);
				break;
			case IE:
				do {
					b = (byte) readIntCheckingLimits(Byte.MIN_VALUE, Byte.MAX_VALUE);
					keyboard.nextLine();
					if (b < min || b >= max) {
						System.out.printf("Between %d included and %d excluded%n", min, max);
					}
				} while (b < min || b >= max);
				break;
			case EI:
				do {
					b = (byte) readIntCheckingLimits(Byte.MIN_VALUE, Byte.MAX_VALUE);
					keyboard.nextLine();
					if (b <= min || b > max) {
						System.out.printf("Between %d excluded and %d included%n", min, max);
					}
				} while (b <= min || b > max);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%n");
			b = readRange(min, max, R);
		}
		return b;

	}

	//This method will ask for a byte number in a range of 2 parameter with a petition message as parameter
	public static byte readRange(String s, byte min, byte max, Range R) {
		byte b;
		System.out.println(s);
		b = readRange(min, max, R);
		return b;
	}

	//This method will ask for a short number in a range of 2 parameter
	public static short readRange(short min, short max, Range R) {
		short sh = 0;

		if (min > max) {
			throw new IllegalArgumentException();
		}
		try {
			switch (R) {
			case II:
				do {
					sh = (short) readIntCheckingLimits(Short.MIN_VALUE, Short.MAX_VALUE);
					keyboard.nextLine();
					if (sh < min || sh > max) {
						System.out.printf("Between %d and %d, both included%n", min, max);
					}
				} while (sh < min || sh > max);
				break;
			case EE:
				do {
					sh = (short) readIntCheckingLimits(Short.MIN_VALUE, Short.MAX_VALUE);
					keyboard.nextLine();
					if (sh <= min || sh >= max) {
						System.out.printf("Between %d and %d, both excluded%n", min, max);
					}
				} while (sh <= min || sh >= max);
				break;
			case IE:
				do {
					sh = (short) readIntCheckingLimits(Short.MIN_VALUE, Short.MAX_VALUE);
					keyboard.nextLine();
					if (sh < min || sh >= max) {
						System.out.printf("Between %d included and %d excluded%n", min, max);
					}
				} while (sh < min || sh >= max);
				break;
			case EI:
				do {
					sh = (short) readIntCheckingLimits(Short.MIN_VALUE, Short.MAX_VALUE);
					keyboard.nextLine();
					if (sh <= min || sh > max) {
						System.out.printf("Between %d excluded and %d included%n", min, max);
					}
				} while (sh <= min || sh > max);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%n");
			sh = readRange(min, max, R);
		}
		return sh;
	}

	//This method will ask for a short number in a range of 2 parameter with a petition message as parameter
	public static short readRange(String s, short min, short max, Range R) {
		short sh = 0;
		System.out.println(s);
		sh = readRange(min, max, R);
		return sh;
	}

	//This method will ask for an int number in a range of 2 parameter
	public static int readRange(int min, int max, Range R) {
		int num = 0;

		if (min > max) {
			throw new IllegalArgumentException();
		}
		try {
			switch (R) {
			case II:
				do {
					num = keyboard.nextInt();
					keyboard.nextLine();
					if (num < min || num > max) {
						System.out.printf("Between %d and %d, both included%n", min, max);
					}
				} while (num < min || num > max);
				break;
			case EE:
				do {
					num = keyboard.nextInt();
					keyboard.nextLine();
					if (num <= min || num >= max) {
						System.out.printf("Between %d and %d, both excluded%n", min, max);
					}
				} while (num <= min || num >= max);
				break;
			case IE:
				do {
					num = keyboard.nextInt();
					keyboard.nextLine();
					if (num < min || num >= max) {
						System.out.printf("Between %d included and %d excluded%n", min, max);
					}
				} while (num < min || num >= max);
				break;
			case EI:
				do {
					num = keyboard.nextInt();
					keyboard.nextLine();
					if (num <= min || num > max) {
						System.out.printf("Between %d excluded and %d included%n", min, max);
					}
				} while (num <= min || num > max);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%nValid numbers are between %d and %d%n", Integer.MIN_VALUE,
					Integer.MAX_VALUE);
			num = readRange(min, max, R);
		}
		return num;
	}

	//This method will ask for an int number in a range of 2 parameter with a petition message as parameter
	public static int readRange(String s, int min, int max, Range R) {
		int num = 0;
		System.out.println(s);
		num = readRange(min, max, R);
		return num;

	}

	//This method will ask for a long number in a range of 2 parameter
	public static long readRange(long min, long max, Range R) {
		long l = 0;

		if (min > max) {
			throw new IllegalArgumentException();
		}
		try {
			switch (R) {
			case II:
				do {
					l = keyboard.nextLong();
					keyboard.nextLine();
					if (l < min || l > max) {
						System.out.printf("Between %d and %d, both included%n", min, max);
					}
				} while (l < min || l > max);
				break;
			case EE:
				do {
					l = keyboard.nextLong();
					keyboard.nextLine();
					if (l <= min || l >= max) {
						System.out.printf("Between %d and %d, both excluded%n", min, max);
					}
				} while (l <= min || l >= max);
				break;
			case IE:
				do {
					l = keyboard.nextLong();
					keyboard.nextLine();
					if (l < min || l >= max) {
						System.out.printf("Between %d included and %d excluded%n", min, max);
					}
				} while (l < min || l >= max);
				break;
			case EI:
				do {
					l = keyboard.nextLong();
					keyboard.nextLine();
					if (l <= min || l > max) {
						System.out.printf("Between %d excluded and %d included%n", min, max);
					}
				} while (l <= min || l > max);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%nValid numbers are between %d and %d%n", Long.MIN_VALUE,
					Long.MAX_VALUE);
			l = readRange(min, max, R);
		}
		return l;
	}

	//This method will ask for a long number in a range of 2 parameter with a petition message as parameter
	public static long readRange(String s, long min, long max, Range R) {
		long l = 0;
		System.out.println(s);
		l = readRange(min, max, R);
		return l;
	}

	//This method will ask for a float number in a range of 2 parameter
	public static float readRange(float min, float max, Range R) {
		float f = 0.0f;

		if (min > max) {
			throw new IllegalArgumentException();
		}
		try {
			switch (R) {
			case II:
				do {
					f = (float) readDoubleCheckingLimits(-Float.MAX_VALUE, Float.MAX_VALUE);
					keyboard.nextLine();
					if (f < min || f > max) {
						System.out.printf("Between %f and %f, both included%n", min, max);
					}
				} while (f < min || f > max);
				break;
			case EE:
				do {
					f = (float) readDoubleCheckingLimits(-Float.MAX_VALUE, Float.MAX_VALUE);
					keyboard.nextLine();
					if (f <= min || f >= max) {
						System.out.printf("Between %f and %f, both excluded%n", min, max);
					}
				} while (f <= min || f >= max);
				break;
			case IE:
				do {
					f = (float) readDoubleCheckingLimits(-Float.MAX_VALUE, Float.MAX_VALUE);
					keyboard.nextLine();
					if (f < min || f >= max) {
						System.out.printf("Between %f included and %f excluded%n", min, max);
					}
				} while (f < min || f >= max);
				break;
			case EI:
				do {
					f = (float) readDoubleCheckingLimits(-Float.MAX_VALUE, Float.MAX_VALUE);
					keyboard.nextLine();
					if (f <= min || f > max) {
						System.out.printf("Between %f excluded and %f included%n", min, max);
					}
				} while (f <= min || f > max);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%n");
			f = readRange(min, max, R);
		}
		return f;
	}

	//This method will ask for a float number in a range of 2 parameter with a petition message as parameter
	public static float readRange(String s, float min, float max, Range R) {
		float f = 0.0f;
		System.out.println(s);
		f = readRange(min, max, R);
		return f;
	}

	//This method will ask for a double number in a range of 2 parameter
	public static double readRange(double min, double max, Range R) {
		double d = 0.0;

		if (min > max) {
			throw new IllegalArgumentException();
		}
		try {
			switch (R) {
			case II:
				do {
					d = keyboard.nextDouble();
					keyboard.nextLine();
					if (d < min || d > max) {
						System.out.printf("Between %f and %f, both included%n", min, max);
					}
				} while (d < min || d > max);
				break;
			case EE:
				do {
					d = keyboard.nextDouble();
					keyboard.nextLine();
					if (d <= min || d >= max) {
						System.out.printf("Between %f and %f, both excluded%n", min, max);
					}
				} while (d <= min || d >= max);
				break;
			case IE:
				do {
					d = keyboard.nextDouble();
					keyboard.nextLine();
					if (d < min || d >= max) {
						System.out.printf("Between %f included and %f excluded%n", min, max);
					}
				} while (d < min || d >= max);
				break;
			case EI:
				do {
					d = keyboard.nextDouble();
					keyboard.nextLine();
					if (d <= min || d > max) {
						System.out.printf("Between %f excluded and %f included%n", min, max);
					}
				} while (d <= min || d > max);
				break;
			}
		} catch (InputMismatchException e) {
			keyboard.next();
			System.out.printf("Error: is not a valid number%nValid numbers are between %f and %f%n", -Double.MAX_VALUE,
					Double.MAX_VALUE);
			d = readRange(min, max, R);
		}
		return d;
	}

	//This method will ask for a double number in a range of 2 parameter with a petition message as parameter
	public static double readRange(String s, double min, double max, Range R) {
		double d = 0.0;
		System.out.println(s);
		d = readRange(min, max, R);
		return d;
	}

	/* READING A CHAR
	 * 1. Ask for the character 
	 * 2. If the entered value is not a single character, ask again 
	 * 3. Return value
	 */
	public static char readChar() {
		//---------------------------------------------------------------------------------------SE PUEDE ELIMINAR ESTA VARIABLE
		char c;
		try {
			//1. Ask for the character 
			String s = readString();
			//2. If the entered value is not a single character, ask again 
			while (s.length() > 1) {
				System.out.println("It must be a single character, enter again please.");
				s = readString();
			}
			c = s.charAt(0);
			//---------------------------------------------------------------------------------------CAMBIAR A EQUALS, ES MEJOR
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Error: you have entered no value. Do it again please");
			c = readChar();
		}
		//3. Return value
		return c;
	}

	/* 
	 * 1. Show petition message
	 * 2. Ask for the character 
	 * 3. Return value
	
	 */
	public static char readChar(String message) {
		char c;
		//1. Show petition message
		System.out.println(message);
		//2. Ask for the character 
		c = readChar();
		//3. Return value
		return c;
	}

	/* READING A STRING
	 * 1. Ask for a String
	 * 2. Return String
	 */
	public static String readString() {
		String s;
		//1. Ask for a String
		//-------------------------------------------------------------------------SI METE CADENA VACIA ES MEJOR REPETIR
		do {
			s = keyboard.nextLine();
		} while (s == "");
		//2. Return String
		return s;
	}

	/* 
	 * 1. Show petition message
	 * 2. Ask for a String
	 * 3. Return value
	 */
	public static String readString(String message) {
		String s;
		//1. Show petition message
		System.out.println(message);
		//2. Ask for a String
		s = readString();
		//3. Return value
		return s;
	}

	/* READING A BOOLEAN
	 * 1. Show question and choices
	 * 2. Ask for value in menu. Repeat if value is invalid
	 * 3. Return value
	 */
	public static boolean readBoolean(String s, String s1, String s2) {
		int menu;
		//1. Show question and choices
		System.out.printf("%s%n1. %s%n2. %s%n", s, s1, s2);
		//IMPORTANT! Answer 1 will answer "true", answer 2 will answer "false"
		//2. Ask for value in menu. Repeat if value is invalid
		menu = readRange(1, 2, Range.II);
		//3. Return value
		return menu == 1;
	}

	/*
	 * 1. Show question
	 * 2. Ask for value in menu. Repeat if value is invalid
	 * 3. Return value
	 */
	public static boolean readBoolean(String s) {
		char menu = ' ';
		do {
			//Only Y, y, N, n are valid values
			//1. Show question
			System.out.printf("%s (y/n)%n", s);
			//2. Ask for value in menu. Repeat if value is invalid
			menu = Character.toLowerCase(readChar());
		} while (menu != 'y' && menu != 'n');
		//3. Return value
		return menu == 'y';
	}

	/*
	 * 1. Ask for number
	 * 2. If number is out of range, ask again
	 * 3. Return value
	 */
	public static int readIntCheckingLimits(int min, int max) throws InputMismatchException {
		int i;
		//1. Ask for number
		do {
			i = keyboard.nextInt();
			//2. If number is out of range, ask again
			if (i < min || i > max) {
				System.out.printf("A number between %d and %d please%n", min, max);
			}
		} while (i < min || i > max);
		//3. Return value
		return i;
	}

	/*
	 * 1. Ask for number
	 * 2. If number is out of range, ask again
	 * 3. Return value
	 */
	public static double readDoubleCheckingLimits(double min, double max) throws InputMismatchException {
		double d = 0.0;
		//1. Ask for number
		do {
			d = keyboard.nextDouble();
			//2. If number is out of range, ask again
			if (d < min || d > max) {
				System.out.printf("A number between %f and %f please%n", min, max);
			}
		} while (d < min || d > max);
		//3. Return value
		return d;
	}
}
