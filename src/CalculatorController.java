
public class CalculatorController {
	public static String multiply(String first, String second){
		float float_first=Float.parseFloat(first);
		float float_second=Float.parseFloat(second);
		float result = CalculatorModel.multiply(float_first,float_second);
		return Float.toString(result);
	}
	public static String add(String first, String second){
		float float_first=Float.parseFloat(first);
		float float_second=Float.parseFloat(second);
		float result = CalculatorModel.add(float_first,float_second);
		return Float.toString(result);
	}
	public static String subtract(String first, String second){
		float float_first=Float.parseFloat(first);
		float float_second=Float.parseFloat(second);
		float result = CalculatorModel.subtract(float_first,float_second);
		return Float.toString(result);
	}
	public static String divide(String first, String second){
		float float_first=Float.parseFloat(first);
		float float_second=Float.parseFloat(second);
		float result = CalculatorModel.divide(float_first,float_second);
		return Float.toString(result);
	}

}
