package EvalPostFix;

import java.util.LinkedList;
import java.util.Stack;

import Tokens.Token;

public class PostFixEvaluator {

	private LinkedList<Token<?>> postFixTokens;
	private Stack<Token<?>> evaluatorStack = new Stack<>();
	
	private double finalOutput;
	private double stackFirstElement;
	private double stackSecondElement;
	
	public PostFixEvaluator(LinkedList<Token<?>> postFixTokens)
	{
		this.postFixTokens = postFixTokens;
	}
	
	public void evaluateExpression()
	{
		for (Token<?> T :  postFixTokens) 
		{
			if(T.getTokenType().equals(T.getTokenType().INT)) 
			{
				evaluatorStack.push(T);
				continue;
			}
			
			if(T.getTokenType().equals(T.getTokenType().ADD)) 
			{
				stackFirstElement = (double) evaluatorStack.pop().getTokenData();
				stackSecondElement = (double) evaluatorStack.pop().getTokenData();
				finalOutput = stackFirstElement + stackSecondElement;
				evaluatorStack.push(new Token<>(finalOutput , Token.TokenType.INT));
				continue;
			}
			
			if(T.getTokenType().equals(T.getTokenType().SUB)) 
			{
				stackFirstElement = (double) evaluatorStack.pop().getTokenData();
				stackSecondElement = (double) evaluatorStack.pop().getTokenData();
				finalOutput = stackSecondElement - stackFirstElement;
				evaluatorStack.push(new Token<>(finalOutput , Token.TokenType.INT));
				continue;
			}
			
			
			if(T.getTokenType().equals(T.getTokenType().DIV)) 
			{
				stackFirstElement = (double) evaluatorStack.pop().getTokenData();
				stackSecondElement = (double) evaluatorStack.pop().getTokenData();
				finalOutput = stackSecondElement / stackFirstElement;
				evaluatorStack.push(new Token<>(finalOutput , Token.TokenType.INT));
				continue;
			}
			
			if(T.getTokenType().equals(T.getTokenType().MUL)) 
			{
				stackFirstElement = (double) evaluatorStack.pop().getTokenData();
				stackSecondElement = (double) evaluatorStack.pop().getTokenData();
				finalOutput = stackFirstElement * stackSecondElement;
				evaluatorStack.push(new Token<>(finalOutput , Token.TokenType.INT));
				continue;
			}
			
			
			if(T.getTokenType().equals(T.getTokenType().EXP)) 
			{
				stackFirstElement = (double) evaluatorStack.pop().getTokenData();
				stackSecondElement = (double) evaluatorStack.pop().getTokenData();
				finalOutput = Math.pow(stackSecondElement, stackFirstElement);
				evaluatorStack.push(new Token<>(finalOutput , Token.TokenType.INT));
				continue;
			}
			
			
			
		}
		
		finalOutput = (double)evaluatorStack.pop().getTokenData();
		
		System.out.println("Evaluated Answer:" + finalOutput);
		
	}
}
