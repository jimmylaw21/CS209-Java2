import java.time.LocalDate;
import java.util.List;

public class Course {
    private String institution;
    private String courseNumber;
    private LocalDate launchDate;
    private String courseTitle;
    private List<String> instructors;
    private String courseSubject;
    private int year;
    private boolean hasHonorCodeCert;
    private int participantsCount;
    private int auditedCount;
    private int certifiedCount;
    private double auditedPercent;
    private double certifiedPercent;
    private double certifiedPercent50;
    private double videoPlayedPercent;
    private double forumPostedPercent;
    private double gradeHigherThanZeroPercent;
    private double totalCourseHours;
    private double medianHoursCertification;
    private double medianAge;
    private double malePercent;
    private double femalePercent;
    private double bachelorsOrHigherPercent;

    // constructor
    public Course(String institution, String courseNumber, LocalDate launchDate, String courseTitle,
                  List<String> instructors, String courseSubject, int year, boolean hasHonorCodeCert,
                  int participantsCount, int auditedCount, int certifiedCount, double auditedPercent,
                  double certifiedPercent, double certifiedPercent50, double videoPlayedPercent,
                  double forumPostedPercent, double gradeHigherThanZeroPercent, double totalCourseHours,
                  double medianHoursCertification, double medianAge, double malePercent, double femalePercent,
                  double bachelorsOrHigherPercent) {
        this.institution = institution;
        this.courseNumber = courseNumber;
        this.launchDate = launchDate;
        this.courseTitle = courseTitle;
        this.instructors = instructors;
        this.courseSubject = courseSubject;
        this.year = year;
        this.hasHonorCodeCert = hasHonorCodeCert;
        this.participantsCount = participantsCount;
        this.auditedCount = auditedCount;
        this.certifiedCount = certifiedCount;
        this.auditedPercent = auditedPercent;
        this.certifiedPercent = certifiedPercent;
        this.certifiedPercent50 = certifiedPercent50;
        this.videoPlayedPercent = videoPlayedPercent;
        this.forumPostedPercent = forumPostedPercent;
        this.gradeHigherThanZeroPercent = gradeHigherThanZeroPercent;
        this.totalCourseHours = totalCourseHours;
        this.medianHoursCertification = medianHoursCertification;
        this.medianAge = medianAge;
        this.malePercent = malePercent;
        this.femalePercent = femalePercent;
        this.bachelorsOrHigherPercent = bachelorsOrHigherPercent;
    }

    // getters
    public String getInstitution() {
        return institution;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public List<String> getInstructors() {
        return instructors;
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public int getYear() {
        return year;
    }

    public boolean hasHonorCodeCert() {
        return hasHonorCodeCert;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public int getAuditedCount() {
        return auditedCount;
    }

    public int getCertifiedCount() {
        return certifiedCount;
    }

    public double getAuditedPercent() {
        return auditedPercent;
    }

    public double getCertifiedPercent() {
        return certifiedPercent;
    }

    public double getCertifiedPercent50() {
        return certifiedPercent50;
    }

    public boolean isHasHonorCodeCert() {
        return hasHonorCodeCert;
    }

    public double getVideoPlayedPercent() {
        return videoPlayedPercent;
    }

    public double getForumPostedPercent() {
        return forumPostedPercent;
    }

    public double getGradeHigherThanZeroPercent() {
        return gradeHigherThanZeroPercent;
    }

    public double getTotalCourseHours() {
        return totalCourseHours;
    }

    public double getMedianHoursCertification() {
        return medianHoursCertification;
    }

    public double getMedianAge() {
        return medianAge;
    }

    public double getMalePercent() {
        return malePercent;
    }

    public double getFemalePercent() {
        return femalePercent;
    }

    public double getBachelorsOrHigherPercent() {
        return bachelorsOrHigherPercent;
    }
}



