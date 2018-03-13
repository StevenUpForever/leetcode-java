import array.TaskScheduler;

public class Main {
    public static void main(String[] args) {
        TaskScheduler obj = new TaskScheduler();
        System.out.println(obj.leastInterval(convert("ACCCEEE"), 2));
    }

    private static char[] convert(String s) {
        return s.toCharArray();
    }
}

