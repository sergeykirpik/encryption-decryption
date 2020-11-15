/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        int[] numbers = new int[args.length - 1];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(args[i + 1]);
        }

        switch (operator.toUpperCase()) {
            case "MAX":
                System.out.println(max(numbers));
                break;
            case "MIN":
                System.out.println(min(numbers));
                break;
            case "SUM":
                System.out.println(sum(numbers));
                break;
            default:
                break;
        }
    }

    public static int max(int[] numbers) {
        int max = Integer.MIN_VALUE;
        for (int n: numbers) {
            max = Math.max(max, n);
        }
        return max;
    }

    public static int min(int[] numbers) {
        int min = Integer.MAX_VALUE;
        for (int n: numbers) {
            min = Math.min(min, n);
        }
        return min;
    }

    public static int sum(int[] numbers) {
        int sum = 0;
        for (int n: numbers) {
            sum += n;
        }
        return sum;
    }
}

