import depth_first_search.CourseSchedule;

public class Main {
    public static void main(String[] args) {
        CourseSchedule obj = new CourseSchedule();
        System.out.println(obj.canFinish(3, new int[][]{{2, 1},{1, 0}}));
    }
}

