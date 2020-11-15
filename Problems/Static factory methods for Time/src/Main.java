import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    public static Time noon() {
        Time time = new Time();
        time.hour = 12;
        return time;
    }

    public static Time midnight() {
        return new Time();
    }

    public static Time ofSeconds(long seconds) {
        long days = seconds / (3600 * 24);
        int remaining = (int) (seconds - days * 3600 * 24);
        int hour = remaining / 3600;
        remaining -= hour * 3600;
        int minute = remaining / 60;
        remaining -= minute * 60;
        int second = remaining;

        return of(hour, minute, second);
    }

    public static Time of(int hour, int minute, int second) {
        boolean valid =
            hour >= 0 && hour <= 23 &&
            minute >= 0 && minute <= 59 &&
            second >= 0 && second <= 59
        ;
        if (!valid) {
            return null;
        }
        Time time = new Time();
        time.hour = hour;
        time.minute = minute;
        time.second = second;

        return time;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }
}