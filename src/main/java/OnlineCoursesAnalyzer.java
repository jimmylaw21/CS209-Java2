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
                        values[3],
                        Arrays.asList(values[4].split(",")),
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
    //This method returns a <institution-course Subject, count> map, where the key is the string
    //concatenating the Institution and the course Subject (without quotation marks) using '-' while the value is
    //the total number of participants in a course Subject of an institution.
    //The map should be sorted by descending order of count (i.e., from most to least participants). If two
    //participants have the same count, then they should be sorted by the alphabetical order of the
    //institution-course Subject.
    public Map<String, Integer> getPtcpCountByInstAndSubject(){
        return courses.stream()
                .collect(Collectors.groupingBy(course -> course.getInstitution() + "-" + course.getCourseSubject(), Collectors.summingInt(Course::getParticipantsCount)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }
//    An instructor may be responsible for multiple courses, including independently responsible courses and codeveloped courses.
//    This method returns a <Instructor, [[course1, course2,...],[coursek,coursek+1,...]]>
//    map, where the key is the name of the instructor (without quotation marks) while the value is a list
//    containing 2-course lists, where List 0 is the instructor's independently responsible courses, if s/he has no
//    independently responsible courses, this list also needs to be created, but with no elements. List 1 is the
//    instructor's co-developed courses, if there are no co-developed courses, do the same as List 0. Note that
//    the course title (without quotation marks) should be sorted by alphabetical order in the list, and the case of
//    identical names should be treated as the same person.
    public Map<String, List<List<String>>> getCourseListOfInstructor(){
        Map<String, List<List<String>>> map = new HashMap<>();
        for (String instuctor : instuctors) {
            List<List<String>> list = new ArrayList<>();
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            for (Course course : courses) {
                if (course.getInstructors().contains(instuctor)) {
                    if (course.getInstructors().size() == 1) {
                        list1.add(course.getCourseTitle());
                    } else {
                        list2.add(course.getCourseTitle());
                    }
                }
            }
            list1.sort(String::compareTo);
            list2.sort(String::compareTo);
            list.add(list1);
            list.add(list2);
            map.put(instuctor, list);
        }
        return map;
    }
//    This method returns the top K courses (parameter topK) by the given criterion (parameter by). Specifically,
//    by="hours": the results should be courses sorted by descending order of Total Course Hours
//            (Thousands) (from the longest course to the shortest course).
//    by="participants": the results should be courses sorted by descending order of the number of
//    the Participants (Course Content Accessed) (from the most to the least).
//    Note that the results should be a list of Course titles. If two courses have the same total Course hours or
//    participants, then they should be sorted by alphabetical order of their titles. The same course title can only
//    occur once in the list.
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
//    This method searches courses based on three criteria:
//    courseSubject: Fuzzy matching is supported and case insensitive. If the inputcourseSubject is
//    "science", all courses whose course subject includes "science" or "Science" or whatever (case insensitive) meet the criteria.
//    percentAudited: the percent of the audited should >= percentAudited
//    totalCourseHours: the Total Course Hours (Thousands) should <= totalCourseHours
//    Note that the results should be a list of course titles that meet the given criteria, and sorted by alphabetical
//    order of the titles. The same course title can only occur once in the list.
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
//    This method recommends 10 courses based on the following input parameter:
//    age: age of the user
//    gender: 0-female, 1-male
//    isBachelorOrHigher: 0-Not get bachelor degree, 1- Bachelor degree or higher
//    First, calculate the average Median Age, average % Male, and average % Bachelor's Degree or
//    Higher for each course. Note that Course Number is the unique id of each course;
//    Secondly, the following formula:
//    $similarity value= (age -average Median Age)^2 + (gender100 - average Male)^2 + (isBachelorOrHigher100
//- average Bachelor's Degree or Higher)^2$
//    is used to calculate the similarity between the characteristics of the input user and the characteristics of
//    each course's participants. The higher the similarity, the smaller the value;
//    Finally, return the top 10 courses with the smallest similarity value.
//    Note that the results should be a list of course titles. A Course Number may correspond to different
//    course titles, please return the course title with the latest Launch Date and the same course
//    title can only occur once in the list. The courses should be sorted by their similarity values. If two courses
//    have the same similarity values, then they should be sorted by alphabetical order of their titles.
    public List<String> recommendCourses(int age, int gender, int
            isBachelorOrHigher){
        List<CourseStats> statsList = new ArrayList<>();
        for (Course course : courses) {
            CourseStats stats = new CourseStats(course);
            statsList.add(stats);
        }

        double avgAge = statsList.stream().mapToDouble(CourseStats::getMedianAge).average().orElse(0);
        double avgMale = statsList.stream().mapToDouble(CourseStats::getMalePercent).average().orElse(0);
        double avgBachelorsOrHigher = statsList.stream().mapToDouble(CourseStats::getBachelorsOrHigherPercent).average().orElse(0);

        List<CourseStats> recommendedStats = new ArrayList<>();
        for (CourseStats stats : statsList) {
            double similarity = Math.pow(age - stats.getMedianAge(), 2)
                    + Math.pow(gender * 100 - stats.getMalePercent(), 2)
                    + Math.pow(isBachelorOrHigher * 100 - stats.getBachelorsOrHigherPercent(), 2);
            stats.setSimilarity(similarity);
            recommendedStats.add(stats);
        }

        Collections.sort(recommendedStats);

        List<String> recommendedCourses = new ArrayList<>();
        for (int i = 0; i < Math.min(recommendedStats.size(), 10); i++) {
            recommendedCourses.add(recommendedStats.get(i).getCourseTitle());
        }

        return recommendedCourses;
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

        public String getCourseTitle() {
            return course.getCourseTitle();
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

        public void setSimilarity(double similarity) {
            this.similarity = similarity;
        }

        @Override
        public int compareTo(CourseStats other) {
            return Double.compare(similarity, other.similarity);
        }
    }



}
