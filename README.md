# Shunting-Yard-Algoridthm
---
### Evaluate mathematical expression after converting it to ```PostFix``` notation from ```InFix``` notation .
---
# ```> STEPS FOR EVALUATION```
### - **Tokenize Expression** - convert operators and numerical values (doubles) to tokens along with the type data `[ +,-,/,* => Operators ]` , `[10,13,32 => Operands]`.

## Token Types
```
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
```

### - **Convert To PostFix Notation** - Using [Rules](https://www.javatpoint.com/convert-infix-to-postfix-notation) of postfix conversion and stack convert token array to postfix order .


### - **Evaluate Expression** -> Again using stack and taking 2 values at a time , do the operation and put value back into the stack . At end one vlaue remains in stack which is the final answer.


