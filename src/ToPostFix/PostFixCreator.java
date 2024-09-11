package ToPostFix;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Stack;

import Tokens.Token;


public class PostFixCreator{

	private LinkedList<Token<?>> Tokens = new LinkedList<>();
	private LinkedList<Token<?>> tokenOrderPostFix = new LinkedList<>();
	private Stack<Token<?>> stack = new Stack<>();
	private char exp[];
	
	private int topOperatorP = 0;
	private int crntOperatorP = 0;
	
	

	public PostFixCreator(String exp)
	{
		this.exp = exp.toCharArray();

	}

	
	private void Tokenize(char exp[] , LinkedList<Token<?>> tokenArray)
	{
		int expSize = exp.length;
		String numberBuilder = "";

		
		for (int i = 0; i < expSize; i++) 
		{
			
			
			//switch to token operator and operands 
			switch(exp[i]) 
			{
				case ' ':
					break;
				case '+':
					Tokens.add(new Token<>(String.valueOf(exp[i]) , Token.TokenType.ADD));
					break;
				case '-':
					Tokens.add(new Token<>(String.valueOf(exp[i]) , Token.TokenType.SUB));
					break;
				case '*':
					Tokens.add(new Token<>(String.valueOf(exp[i]) , Token.TokenType.MUL));
					break;
				case '/':
					Tokens.add(new Token<>(String.valueOf(exp[i]) , Token.TokenType.DIV));
					break;
				case '(':
					Tokens.add(new Token<>(String.valueOf(exp[i]) , Token.TokenType.PRTH_O));
					break;
				case ')':
					Tokens.add(new Token<>(String.valueOf(exp[i]) , Token.TokenType.PRTH_C));
					break;
				case '^':
					Tokens.add(new Token<>(String.valueOf(exp[i]) , Token.TokenType.EXP));
					break;
				default:
					numberBuilder = String.valueOf(exp[i]);
										
					while(i < expSize - 1 && Character.isDigit(exp[i + 1])) 
					{
						numberBuilder += exp[i + 1];
						i++;
					}
					
					Tokens.add(new Token<>(Double.parseDouble(numberBuilder), Token.TokenType.INT));
					break;
						
			}	
						
		}
		
	}
	
	
	public PostFixCreator toPostFx()  
	{
		Tokenize(exp , Tokens);
		
		EnumMap<Token.TokenType , Integer> operatorPresedence = new EnumMap<>(Token.TokenType.class);
		operatorPresedence.put(Token.TokenType.PRTH_O , 3);
		operatorPresedence.put(Token.TokenType.PRTH_C , 3);
		
		operatorPresedence.put(Token.TokenType.EXP , 2);
		
		operatorPresedence.put(Token.TokenType.DIV , 1);
		operatorPresedence.put(Token.TokenType.MUL , 1);

		operatorPresedence.put(Token.TokenType.ADD , 0);	
		operatorPresedence.put(Token.TokenType.SUB , 0);
		
		
		
		for (Token<?> T :  Tokens) 
		{
			//add operands to PostFix array 
			if(T.getTokenType().equals(Token.TokenType.INT)) 
			{
				tokenOrderPostFix.add(T);
				continue;
			}
			
			//if element is open parenthesis then push onto stack 
			if(T.getTokenType().equals(Token.TokenType.PRTH_O)) 
			{
				stack.push(T);
				continue;
			}
			
			//If token is open parenthesis then pop all elements till close 
			//is found and skip close parenthesis 
			if(T.getTokenType().equals(Token.TokenType.PRTH_C)) 
			{
				while(!stack.peek().getTokenType().equals(Token.TokenType.PRTH_O)) 
				{
					tokenOrderPostFix.add(stack.pop());
				}
				//skipping close parenthesis 
				stack.pop();
				continue;
			}
			
			//If token is operator
			if(!T.getTokenType().equals(Token.TokenType.INT)) 
			{
				
				
				if(stack.isEmpty() || stack.peek().getTokenType().equals(Token.TokenType.PRTH_O)) 
				{
					stack.push(T);
					continue;
				}
				
				crntOperatorP = operatorPresedence.get(T.getTokenType());								
				if(!stack.empty()) 
				{					
					topOperatorP = operatorPresedence.get(stack.peek().getTokenType());
										
				}
				

				
				//less precedence then add the operator on stack top 
				if(topOperatorP < crntOperatorP) 
				{					
					stack.push(T);
					continue;
				}
				//higher precedence then pop till less then add
				if(topOperatorP > crntOperatorP) 
			   {
					
					
					while(topOperatorP > crntOperatorP && !stack.empty())
					{
						tokenOrderPostFix.add(stack.pop());
						
						if(!stack.empty()) 
						{
							
							topOperatorP = operatorPresedence.get(stack.peek().getTokenType());
							
						}
						
						
					}
					
					
					if(topOperatorP < crntOperatorP || stack.empty()) 
					{
						stack.push(T);
					}
					
					
				}
				
				
				
				//same then pop and push 
				if(topOperatorP == crntOperatorP && !stack.empty()) 
				{
					
					if(stack.peek().getTokenType().equals(Token.TokenType.EXP)) 
					{
						stack.push(T);
					}else{
						
						tokenOrderPostFix.add(stack.pop());
						stack.push(T);						
					}
					
				}
				
			}
		}
		
		//put all elements of stack onto list 
		while(!stack.empty()) 
		{
			
			tokenOrderPostFix.add(stack.pop());
		}
		

		return this;
		
	}
	
	
	public void printPostFixExpression(PostFixCreator postFix)
	{
	
		for (Token<?> T :  tokenOrderPostFix) 
		{
			System.out.print(T.getTokenData() + " ");
		}
		
	}
	
	
	public LinkedList<Token<?>> PostFixTokens()
	{
		return this.tokenOrderPostFix;
	}
	
	

	

}
