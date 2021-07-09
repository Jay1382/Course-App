package data;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.Course;

public class CourseData {

    private String[] courseNames = {"First Course", "Second Course", "Third Course", "Fourth Course", "Fifth Course", "Sixth Course"};

    public ArrayList<Course> courseList() {

        ArrayList<Course> list = new ArrayList<>();
        for (int i = 0; i < courseNames.length; i++) {
            Course course = new Course(courseNames[i], courseNames[i].replace(" ", "")
                    .toLowerCase(), "Happy_woman");
            list.add(course);
        }
        return list;
    }
}
