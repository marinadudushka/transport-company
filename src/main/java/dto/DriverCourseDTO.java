package dto;

import java.util.Collection;

public class DriverCourseDTO {
    private long id;
    private String name;
   private long numberOfCourses;

    public DriverCourseDTO() {
    }

    public DriverCourseDTO(long id, String name, long numberOfCourses) {
        this.id = id;
        this.name = name;
        this.numberOfCourses = numberOfCourses;
    }

    @Override
    public String toString() {
        return "DriverCourseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfCourses=" + numberOfCourses +
                '}';
    }
}
