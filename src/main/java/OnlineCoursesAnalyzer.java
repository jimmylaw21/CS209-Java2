import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OnlineCoursesAnalyzer {
    private final List<Course> courses;
    private final Set<String> instuctors;
    public OnlineCoursesAnalyzer(String datasetPath) {
        courses = new ArrayList<>();
        // read CSV file Using UTF-8 encoding

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(datasetPath),"UTF-8"))) {
            // skip header
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = csvSplit(line);
                // create Course object from CSV row
                Course course = new Course(
                        values[0],
                        values[1],
                        parseDate(values[2]),
                        values[3].replace("\"",""),
                        Arrays.asList(values[4].replace("\"","").split(", ")),
                        values[5].replace("\"",""),
                        Integer.parseInt(values[6]),
                        Boolean.parseBoolean(values[7]),
                        Integer.parseInt(values[8]),
                        Integer.parseInt(values[9]),
                        Integer.parseInt(values[10]),
                        Double.parseDouble(values[11]),
                        Double.parseDouble(values[12]),
                        Double.parseDouble(values[13]),
                        Double.parseDouble(values[14]),
                        Double.parseDouble(values[15]),
                        Double.parseDouble(values[16]),
                        Double.parseDouble(values[17]),
                        Double.parseDouble(values[18]),
                        Double.parseDouble(values[19]),
                        Double.parseDouble(values[20]),
                        Double.parseDouble(values[21]),
                        Double.parseDouble(values[22])
                );
                courses.add(course);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        instuctors = courses.stream().flatMap(course -> course.getInstructors().stream()).collect(Collectors.toSet());
    }

    public static LocalDate parseDate(String date) {
        String[] values = date.split("/");
        return LocalDate.of(Integer.parseInt(values[2]), Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

    public static String[] csvSplit(String str) {
        List<String> list = new ArrayList<>();
        int start = 0;
        int end = 0;
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '"') {
                flag = !flag;
            }
            if (str.charAt(i) == ',' && !flag) {
                end = i;
                list.add(str.substring(start, end));
                start = i + 1;
            }
        }
        list.add(str.substring(start));
        return list.toArray(new String[0]);
    }

    public Map<String, Integer> getPtcpCountByInst(){
        return courses.stream()
                .collect(Collectors.groupingBy(Course::getInstitution, Collectors.summingInt(Course::getParticipantsCount)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }
    public Map<String, Integer> getPtcpCountByInstAndSubject(){
        return courses.stream()
                .collect(Collectors.groupingBy(course -> course.getInstitution() + "-" + course.getCourseSubject(), Collectors.summingInt(Course::getParticipantsCount)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<String, List<List<String>>> getCourseListOfInstructor(){
        Map<String, List<List<String>>> map = new HashMap<>();
        for (String instuctor : instuctors) {
            List<String> independentCourses = courses.stream()
                    .filter(course -> course.getInstructors().contains(instuctor) && course.getInstructors().size() == 1)
                    .map(Course::getCourseTitle)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());
            List<String> coDevelopedCourses = courses.stream()
                    .filter(course -> course.getInstructors().contains(instuctor) && course.getInstructors().size() > 1)
                    .map(Course::getCourseTitle)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());
            List<List<String>> list = new ArrayList<>();
            list.add(independentCourses);
            list.add(coDevelopedCourses);
            map.put(instuctor, list);
        }
        return map;
    }
    public List<String> getCourses(int topK, String by){
        return courses.stream()
                .sorted((a, b) -> {
                    if (by.equals("hours")) {
                        if (a.getTotalCourseHours() == b.getTotalCourseHours()) {
                            return a.getCourseTitle().compareTo(b.getCourseTitle());
                        } else {
                            return Double.compare(b.getTotalCourseHours(), a.getTotalCourseHours());
                        }
                    } else {
                        if (a.getParticipantsCount() == b.getParticipantsCount()) {
                            return a.getCourseTitle().compareTo(b.getCourseTitle());
                        } else {
                            return Integer.compare(b.getParticipantsCount(), a.getParticipantsCount());
                        }
                    }
                })
                .map(Course::getCourseTitle)
                .distinct()
                .limit(topK)
                .collect(Collectors.toList());
    }

    public List<String> searchCourses(String courseSubject, double
            percentAudited, double totalCourseHours){
        return courses.stream()
                .filter(course -> course.getCourseSubject().toLowerCase().contains(courseSubject.toLowerCase()))
                .filter(course -> course.getAuditedPercent() >= percentAudited)
                .filter(course -> course.getTotalCourseHours() <= totalCourseHours)
                .map(Course::getCourseTitle)
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    public List<String> recommendCourses(int age, int gender, int isBachelorOrHigher){
        List<List<Course>> courseList = courses.stream()
                .collect(Collectors.groupingBy(Course::getCourseNumber))
                .entrySet().stream()
                .map(entry -> entry.getValue().stream()
                        .sorted(Comparator.comparing(Course::getLaunchDate).reversed())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        List<CourseStats> courseStatsList = courseList.stream()
                .map(list -> {
                    double avgMedianAge = 0.0;
                    double avgMalePercent = 0.0;
                    double avgBachelorsOrHigherPercent = 0.0;
                    double similarity;
                    Course latestCourse = list.get(0);
                    for (int i = 0; i < list.size(); i++) {
                        avgMedianAge += list.get(i).getMedianAge();
                        avgMalePercent += list.get(i).getMalePercent();
                        avgBachelorsOrHigherPercent += list.get(i).getBachelorsOrHigherPercent();
                        if (list.get(i).getLaunchDate().compareTo(latestCourse.getLaunchDate()) > 0) {
                            latestCourse = list.get(i);
                        }
                    }
                    avgMedianAge /= list.size();
                    avgMalePercent /= list.size();
                    avgBachelorsOrHigherPercent /= list.size();
                    similarity = Math.pow(age-avgMedianAge, 2) + Math.pow(gender*100-avgMalePercent, 2) + Math.pow(isBachelorOrHigher*100-avgBachelorsOrHigherPercent, 2);
                    CourseStats courseStats = new CourseStats(latestCourse,avgMalePercent, avgMedianAge, avgBachelorsOrHigherPercent, similarity);
                    return courseStats;
                })
                .collect(Collectors.toList());

        return courseStatsList.stream()
                .sorted(Comparator.comparing(CourseStats::getSimilarity).thenComparing(CourseStats::getCourseTitle))
                .map(CourseStats::getCourseTitle)
                .distinct()
                .limit(10)
                .collect(Collectors.toList());
    }

    private class CourseStats implements Comparable<CourseStats> {
        private Course course;
        private double medianAge;
        private double malePercent;
        private double bachelorsOrHigherPercent;
        private double similarity;

        public CourseStats(Course course) {
            this.course = course;
            this.medianAge = course.getMedianAge();
            this.malePercent = course.getMalePercent();
            this.bachelorsOrHigherPercent = course.getBachelorsOrHigherPercent();
        }

        public CourseStats(Course course, double medianAge, double malePercent, double bachelorsOrHigherPercent, double similarity) {
            this.course = course;
            this.medianAge = medianAge;
            this.malePercent = malePercent;
            this.bachelorsOrHigherPercent = bachelorsOrHigherPercent;
            this.similarity = similarity;
        }

        public String getCourseTitle() {
            return course.getCourseTitle();
        }

        public String getCourseNumber() {
            return course.getCourseNumber();
        }

        public double getMedianAge() {
            return medianAge;
        }

        public double getMalePercent() {
            return malePercent;
        }

        public double getBachelorsOrHigherPercent() {
            return bachelorsOrHigherPercent;
        }

        public Course getCourse() {
            return course;
        }

        public double getSimilarity() {
            return similarity;
        }

        public void setSimilarity(double similarity) {
            this.similarity = similarity;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        @Override
        public int compareTo(CourseStats other) {
            if (this.similarity == other.similarity) {
                return this.getCourseTitle().compareTo(other.getCourseTitle());
            }
            return Double.compare(this.similarity, other.similarity);
        }
    }

    private double similarity(int age, int gender, int isBachelorOrHigher,Course course){
        return Math.pow(age - course.getMedianAge(), 2)
                + Math.pow(gender * 100 - course.getMalePercent(), 2)
                + Math.pow(isBachelorOrHigher * 100 - course.getBachelorsOrHigherPercent(), 2);
    }

}
