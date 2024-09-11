package StackEvaluator;

import EvalPostFix.PostFixEvaluator;
import ToPostFix.PostFixCreator;

public class ExpEval {

	static String Exp = "10*10-50*(5-(2/2))";
	
	
	public static void main(String[] args) {
		
		PostFixCreator creator = new PostFixCreator(Exp);
		PostFixCreator display = new PostFixCreator(Exp);
		display.toPostFx().printPostFixExpression(display);
		
		PostFixEvaluator PfEvaluator = new PostFixEvaluator(creator.toPostFx().PostFixTokens());
		
		PfEvaluator.evaluateExpression();
		
		

	}

}
