package Tokens;

public class Token<T> {

	public enum TokenType
	{
		EXP,
		PRTH_O,
		PRTH_C,
		DIV,
		MUL,
		ADD,
		SUB,
		INT 
		
	}
	
	private T tokenValue;
	private TokenType token;	

	
	public Token(T value , TokenType tokenType)
	{
		this.tokenValue = value;
		this.token = tokenType;
	}	
	
	
	public T getTokenData() 
	{
		return tokenValue;
	}
	
	public TokenType getTokenType() 
	{
		return this.token;
	}
	
	
}

