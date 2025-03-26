class ExpressionParser {
   // ---------------------------
   public ExpressionNode parse(String phrase) {
   final char LEFT_PARENTHESIS = '(';
   final char RIGHT_PARENTHESIS = ')';
   final char ZERO = '0';
   int index;
   char symbol;
   char secondSymbol;
   int parenthesesCount;
   char operator;
   StringBuilder leftSide;
   String rightSide;
   ExpressionNode nodeX;

   index = 1;
   secondSymbol = phrase.charAt(index);

   // Check to see if this is a simple (i.e., leaf) expression
   if (Character.isDigit(secondSymbol))
     nodeX = new ExpressionNode(secondSymbol - ZERO);
   else {
      // Otherwise, process it as a complex expression
      operator = secondSymbol;
      index++;
      // Read the left parenthesis
      leftSide = new StringBuilder("" + phrase.charAt(index));
      parenthesesCount = 1;
      while (parenthesesCount > 0) {
         index++;
         symbol = phrase.charAt(index);
         if (symbol == LEFT_PARENTHESIS)
            parenthesesCount++;
         if (symbol == RIGHT_PARENTHESIS)
            parenthesesCount--;
         leftSide.append(symbol);
         } // End while
      index++;
      rightSide = phrase.substring(index, (phrase.length() - 1) );
      nodeX = new ExpressionNode(operator, parse(leftSide.toString()), parse(rightSide));
      } // End else

   return nodeX;
   } // End method


   // -------------------------------
   // For testing purposes only
   public static void main(String[] args) {
   ExpressionParser myParser = new ExpressionParser();
   ExpressionNode myNode;

   myNode = myParser.parse("(+(*(+(2)(3))(-(4)(5)))(6))");

   System.out.println(myNode);
   System.out.println(myNode.evaluate());

   } // End main


} // End class
