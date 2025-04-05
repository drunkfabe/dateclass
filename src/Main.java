import java.util.Objects;

public class Main {
    private int day;
    private int month;
    private int year;

    public static void main(String[] args) {
        if (!isValidDate(year, month, day)) {
            throw new IllegalArgumentException("Invalid date: " + formatDate(year, month, day));
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private static boolean isValidDate(int y, int m, int d) {
        if (m < 1 || m > 12 || d < 1) return false;

        int[] daysInMonth = getDaysInMonth(y);
        return d <= daysInMonth[m - 1];
    }

    private static int[] getDaysInMonth(int y) {
        return new int[]{31, isLeapYear(y) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    }

    private static boolean isLeapYear(int y) {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    public void update(int y, int m, int d) {
        if (isValidDate(y, m, d)) {
            this.year = y;
            this.month = m;
            this.day = d;
        } else {
            System.out.println("Invalid date provided. Update failed.");
        }
    }

    public String getDayOfWeek() {
        if (!isValidDate(year, month, day)) return "Invalid";

        int m = (month < 3) ? month + 12 : month;
        int y = (month < 3) ? year - 1 : year;

        int k = y % 100;
        int j = y / 100;

        int h = (day + 13 * (m + 1) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;

        String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        return days[h];
    }

    public int differenceInDays(Date other) {
        return Math.abs(this.toDays() - other.toDays());
    }

    private int toDays() {
        int days = 0;

        for (int y = 1; y < year; y++) {
            days += isLeapYear(y) ? 366 : 365;
        }

        int[] daysInMonth = getDaysInMonth(year);
        for (int i = 0; i < month - 1; i++) {
            days += daysInMonth[i];
        }

        return days + day;
    }

    public void display() {
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        System.out.println(monthNames[month - 1] + " " + day + ", " + year);
    }

    private static String formatDate(int y, int m, int d) {
        return String.format("%04d-%02d-%02d", y, m, d);
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) return this.year - other.year;
        if (this.month != other.month) return this.month - other.month;
