import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int makeCourses(List<boolean[]> orderTable, StringBuilder course, List<String> courses, int courseMaxOrderNumber, int menuNumber) {
        if (course.length() == menuNumber && orderTable.size() >= courseMaxOrderNumber) {
            if (orderTable.size() > courseMaxOrderNumber) {
                courses.clear();
                courseMaxOrderNumber = orderTable.size();
            }

            courses.add(course.toString());

            return courseMaxOrderNumber;
        }

        int menuStartIndex = (course.length() == 0) ? (0) : (course.charAt(course.length() - 1) - 'A' + 1);

        for (int menuIndex = menuStartIndex; menuIndex < 26; menuIndex++) {
            List<boolean[]> remainingOrderTable = new ArrayList<>();

            for (boolean[] orderRow : orderTable) {
                if (orderRow[menuIndex]) {
                    remainingOrderTable.add(orderRow);
                }
            }

            if (remainingOrderTable.size() >= 2) {
                course.append((char) (menuIndex + 'A'));
                courseMaxOrderNumber = makeCourses(remainingOrderTable, course, courses, courseMaxOrderNumber, menuNumber);
                course.deleteCharAt(course.length() - 1);
            }
        }

        return courseMaxOrderNumber;
    }

    public String[] solution(String[] orders, int[] course) {
        List<boolean[]> orderTable = new ArrayList<>();

        for (String order : orders) {
            boolean[] orderRow = new boolean[26];

            for (int index = 0; index < order.length(); index++) {
                orderRow[order.charAt(index) - 'A'] = true;
            }

            orderTable.add(orderRow);
        }

        List<String> answer = new ArrayList<>();

        for (int menuNumber : course) {
            List<String> courses = new ArrayList<>();

            makeCourses(orderTable, new StringBuilder(), courses, 0, menuNumber);
            answer.addAll(courses);
        }

        answer.sort(Comparator.naturalOrder());

        return answer.toArray(String[]::new);
    }
}