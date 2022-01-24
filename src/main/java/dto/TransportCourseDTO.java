package dto;

import entity.*;

import java.time.LocalDate;
import java.util.Comparator;

public class TransportCourseDTO {
    private long id;
    private String startPoint;
    private String endPoint;
    private LocalDate startDate;
    private LocalDate endDate;
    private TransportType transportType;
    private TransportCourseStatus transportCourseStatus;

    public TransportCourseDTO(long id) {
        this.id = id;
    }

    public TransportCourseDTO(long id, String startPoint, String endPoint, LocalDate startDate, LocalDate endDate, TransportType transportType,TransportCourseStatus transportCourseStatus) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transportType = transportType;
        this.transportCourseStatus = transportCourseStatus;
    }

    @Override
    public String toString() {
        return "TransportCourseDTO{" +
                "id=" + id +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", transportType=" + transportType +
                ", transportCourseStatus=" + transportCourseStatus +
                '}';
    }

    public static Comparator<TransportCourseDTO> CompareByName = new Comparator<TransportCourseDTO>() {
        @Override
        public int compare(TransportCourseDTO o1, TransportCourseDTO o2) {
            return o1.endPoint.compareTo(o2.endPoint);
        }
    };
}
