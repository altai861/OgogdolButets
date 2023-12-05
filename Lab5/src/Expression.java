import dataStructures.ArrayStack;
import dataStructures.LinkedBinaryTree;
import java.util.Scanner;

enum ExpressionType {
	INFIX,
	PREFIX,
	POSTFIX;
}

public class Expression extends LinkedBinaryTree {
	
	private String notationExp;
	private ExpressionType eType;
	Scanner scan;
	
	public Expression() {
		super();
		notationExp = "";
		eType = ExpressionType.INFIX;
		scan = new Scanner(System.in);
	}
	
	public void readInfixExpression() {
		eType = ExpressionType.INFIX;
		System.out.println(eType + " ilerhiilel oruulna uu! Operand bolon operatoruudiig 1 hooson zaitai oruulaarai!");
		this.notationExp = scan.nextLine();
	}
	
	public void readPostfixExpression() {
		eType = ExpressionType.POSTFIX;
		System.out.println(eType + " ilerhiilel oruulna uu! Operand bolon operatoruudiig 1 hooson zaitai oruulaarai!");
		this.notationExp = scan.nextLine();
	}
	
	public void readPrefixExpression() {
		eType = ExpressionType.PREFIX;
		System.out.println(eType + " ilerhiilel oruulna uu! Operand bolon operatoruudiig 1 hooson zaitai oruulaarai!");
		this.notationExp = scan.nextLine();
	}
	
	public int Prec(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;
		
		case '*':
		case '/':
			return 2;
		
		case '^':
			return 3;
			
		}
		return -1;
	}
	
	public void infixToPostfix() {
		if (eType == ExpressionType.INFIX) {
			eType = ExpressionType.POSTFIX;
		
			String result = new String("");
			
			ArrayStack stack = new ArrayStack();
			
			String [] elems = notationExp.split(" ");
			
			for (int i = 0; i < elems.length; i++) {
				if (Character.isLetterOrDigit(elems[i].charAt(0))) {
					result += elems[i] + " ";
				}
				else if (elems[i].charAt(0) == '(') {
					stack.push(elems[i]);
				} else if (elems[i].charAt(0) == ')') {
					while (!stack.empty() && stack.peek().toString().charAt(0) != '(') {
						result += stack.peek() + " ";
						stack.pop();
					}
					stack.pop();
				} else {
					while (!stack.empty() && Prec(elems[i].charAt(0)) <= Prec(stack.peek().toString().charAt(0))) {
						result += stack.peek() + " ";
						stack.pop();
					}
					stack.push(elems[i]);
				}
			}
			
			while (!stack.empty()) {
				if (stack.peek().toString().charAt(0) == '(') {
					System.out.println("Buruu ilerhiilel");
					
				}
				result += stack.peek() + " ";
				stack.pop();
			}
			this.notationExp = result;
		}
		
	}
	
	public void prefixToPostfix() {
		if (eType == ExpressionType.PREFIX) {
			eType = ExpressionType.POSTFIX;
			String result = new String("");
			
			ArrayStack stack = new ArrayStack();
			String [] elems = notationExp.split(" ");
			
			for (int i = elems.length - 1; i >= 0; i--) {
				if (elems[i].length() == 1 && !Character.isLetterOrDigit(elems[i].charAt(0))) {
					String op1 = stack.peek().toString();
					stack.pop();
					String op2 = stack.peek().toString();
					stack.pop();
					
					String temp = op1 + op2 + elems[i].charAt(0) + " ";
					
					stack.push(temp);
					
					
				}
				
				else {
					stack.push(elems[i] + " ");
				}
			}
			
			this.notationExp = stack.peek().toString();
		}
	}
	
	public void expressionTreeFromPostfix() {
		ArrayStack stN = new ArrayStack();
		
		Expression t1, t2, temp;
		String [] elems = notationExp.split(" ");
		
		for (int i = 0; i < elems.length; i++) {
			if (Character.isLetterOrDigit(elems[i].charAt(0))) {
				temp = new Expression();
				temp.makeTree(elems[i], temp, temp);
				stN.push(temp);
			} else {
				temp = new Expression();
				
				t1 = (Expression)stN.pop();
				t2 = (Expression)stN.pop();
				temp.makeTree(elems[i].charAt(0), t2, t1);
				stN.push(temp);
			}
		}
		temp = (Expression)stN.pop();
		this.root = temp.root;
	}
	
	public String postfixToInfix(String postFix) {
		ArrayStack stack = new ArrayStack();
		String [] elems = postFix.split(" ");
		
		
		for (int i = 0; i < elems.length; i++) {
			String element = elems[i];
			if (Character.isLetterOrDigit(element.charAt(0))) {
				stack.push(element);
			} else {
				String operand2 = (String)stack.pop();
				String operand1 = (String)stack.pop();
				
				String infixExpression = "(" + operand1 + " " + element + " " + operand2 + ")";
				
				stack.push(infixExpression);
				
			}
		}
		
		return (String)stack.pop();
	}
	
	public void bodoh() {
		ArrayStack stack = new ArrayStack();
		if (this.eType == ExpressionType.INFIX) {
			this.infixToPostfix();
		} else if (this.eType == ExpressionType.PREFIX) {
			this.prefixToPostfix();
		}
		
		String [] elems = this.notationExp.split(" ");
		for (int i = 0; i < elems.length; i++) {
			
			if (Character.isLetterOrDigit(elems[i].charAt(0))) {
				stack.push(Character.getNumericValue(elems[i].charAt(0)));
			} else {
				int operand2 = (Integer)stack.pop();
				int operand1 = (Integer)stack.pop();
				
				int result = performOperation(operand1, operand2, elems[i].charAt(0));
				stack.push(result);
			}
		}
		System.out.println("Bodlogo: " + this.notationExp);
		System.out.println("Hariu: " + stack.pop());
		
	}
	
	private static int performOperation(int operand1, int operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
	
	public void menu() {
		System.out.println("\n******* menu *********");
		System.out.println("1) Infix ilerhiilel oruulah");
		System.out.println("2) Prefix ilerhiilel oruulah");
		System.out.println("3) Postfix ilerhiilel oruulah");
		System.out.println("4) Postfix ilerhiilel hevleh");
		System.out.println("5) Prefix ilerhiilel hevleh");
		System.out.println("6) Infix ilerhiilel hevleh");
		System.out.println("7) Ilerhiilel bodoh");
		System.out.println("0) Garah");
		System.out.println("---- Uildliin dugaar songooroi");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Expression myexp = new Expression();
			int command;
			while (true) {
				myexp.menu();
				command = Integer.parseInt(myexp.scan.nextLine());
				switch (command) {
				case 0:
					System.exit(0);
					break;
				case 1:
					myexp.readInfixExpression();
					myexp.infixToPostfix();
					myexp.expressionTreeFromPostfix();
					break;
				case 2:
					myexp.readPrefixExpression();
					myexp.prefixToPostfix();
					myexp.expressionTreeFromPostfix();
					break;
				case 3:
					myexp.readPostfixExpression();
					myexp.expressionTreeFromPostfix();
					break;
				case 4:
					System.out.println("\n postfix ilerhiilel: ");
					myexp.postOrderOutput();
					break;
				case 5:
					System.out.println("\n prefix ilerhiilel: ");
					myexp.preOrderOutput();
					break;
				case 6:
					
					if (myexp.eType == ExpressionType.INFIX) {
						myexp.infixToPostfix();
					} else if (myexp.eType == ExpressionType.PREFIX) {
						myexp.prefixToPostfix();
					}
					String infix = myexp.postfixToInfix(myexp.notationExp);
					System.out.println("Infix ilerhiilel: " + infix);
					break;
				case 7:
					myexp.bodoh();
					break;
				default:
					System.out.println("uildliin dugaar buruu 0-7 utga oruulna uu");
				}
			}
		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());
		}
	}

}
