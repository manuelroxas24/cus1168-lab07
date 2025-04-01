package academy.javapro.lab7;

public class AlternativeArithmetic
{
    public static int addWithoutPlus(int a, int b)
    {
        while (b != 0)
        {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

    public static int divideWithoutDivideOperator(int dividend, int divisor)
    {
        if (divisor == 0)
        {
            throw new ArithmeticException("Cannot divide by zero");
        }
        if (dividend == 0)
        {
            return 0;
        }
        if (divisor == 1)
        {
            return dividend;
        }

        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        int result = 0;

        while (absDividend >= absDivisor)
        {
            long temp = absDivisor, multiple = 1;
            while (absDividend >= (temp << 1))
            {
                temp <<= 1;
                multiple <<= 1;
            }
            absDividend -= temp;
            result += multiple;
        }
        return isNegative ? -result : result;
    }

    public static int subtractWithoutMinusOperator(int a, int b)
    {
        return addWithoutPlus(a, ~b + 1);
    }

    public static void main(String[] args)
    {
        int[][] additionTests =
                {
                {5, 3}, {-2, 7}, {0, 0}, {-5, -3}, {100, 200}, {Integer.MAX_VALUE, 1}, {-100, 100}
        };
        System.out.println("Testing addition without '+' operator:");
        for (int[] test : additionTests)
        {
            int result = addWithoutPlus(test[0], test[1]);
            System.out.println(test[0] + " + " + test[1] + " = " + result);
        }

        int[][] divisionTests =
                {
                {10, 2}, {15, 3}, {8, 4}, {7, 2}, {100, 10}, {-15, 3}, {15, -3}, {0, 5}, {1024, 2}
        };
        System.out.println("\nTesting division without '/' operator:");
        for (int[] test : divisionTests)
        {
            try
            {
                int result = divideWithoutDivideOperator(test[0], test[1]);
                System.out.println(test[0] + " / " + test[1] + " = " + result);
            }
            catch (ArithmeticException e)
            {
                System.out.println(test[0] + " / " + test[1] + " = " + e.getMessage());
            }
        }

        int[][] subtractionTests =
                {
                {10, 3}, {5, 8}, {0, 0}, {-5, -3}, {100, 50}
        };
        System.out.println("\nTesting subtraction without '-' operator:");
        for (int[] test : subtractionTests)
        {
            int result = subtractWithoutMinusOperator(test[0], test[1]);
            System.out.println(test[0] + " - " + test[1] + " = " + result);
        }
    }
}
