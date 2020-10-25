package training.errorAndExceptions;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws userException {
//       ArrayList listOfFaculty = manualDataBaseInputting("faculty");
        ArrayList listOfFaculty = inputOfFaculty();
//      ArrayList listOfGroup = manualDataBaseInputting("group");
        ArrayList listOfGroup =inputOfGroup();
//      ArrayList listOfSubjects = manualDataBaseInputting("subject");
        ArrayList listOfSubjects = inputOfSubjects();
//      ArrayList listOfStudent = manualStudentDataBaseInputting(listOfFaculty, listOfGroup, listOfSubjects);
        ArrayList listOfStudent = inputOfStudent();
//      ArrayList listOfStudentAssessments = manualStudentAssessmentsInputting(listOfStudent,listOfSubjects);
        ArrayList listOfStudentAssessments = inputOfStudentAssessment();
        valueOfAssessmentsCheck(listOfStudentAssessments);
        subjectsExistingCheck(listOfSubjects, listOfStudentAssessments, listOfStudent);
        studentInGroupMissingCheck(listOfStudent,listOfGroup);
        groupInFacultiesMissingCheck(listOfStudent,listOfGroup,listOfFaculty);
        facultiesMissingCheck(listOfStudent,listOfFaculty);
 //       menu(listOfStudent, listOfStudentAssessments, listOfFaculty, listOfGroup, listOfSubjects);
    }

    private static void facultiesMissingCheck(ArrayList listOfStudent, ArrayList listOfFaculty) throws userException {
        int facultyExisting=0;
        for(int indexOfStudent=0;indexOfStudent<listOfStudent.size();indexOfStudent++){
            for(int indexOfFaculty=0;indexOfFaculty<listOfFaculty.size();indexOfFaculty++){
                if(((Student) listOfStudent.get(indexOfStudent)).getFaculty().contentEquals(listOfFaculty.get(indexOfFaculty).toString()))
                    facultyExisting++;
            }
            if(facultyExisting==0) throw new userException ("There is no faculty assigned for student "
                    + ((Student) listOfStudent.get(indexOfStudent)).getStudentFullName());
            facultyExisting=0;
        }
    }

    private static void groupInFacultiesMissingCheck(ArrayList listOfStudent, ArrayList listOfGroup, ArrayList listOfFaculty) throws userException {
        int groupExisting=0;
        for (int indexOfStudent=0;indexOfStudent<listOfStudent.size();indexOfStudent++){
            for(int indexOfGroup=0; indexOfGroup<listOfGroup.size();indexOfGroup++){
                if (((Student) listOfStudent.get(indexOfStudent)).getGroup().contains(listOfGroup.get(indexOfGroup).toString()))
                    groupExisting++;
            }
            if (groupExisting==0) throw new userException("There is no group assigned for student "+ ((Student) listOfStudent.get(indexOfStudent)).getStudentFullName()
                    +" of faculty "+ ((Student) listOfStudent.get(indexOfStudent)).getFaculty());
            groupExisting=0;
        }
    }

    private static void studentInGroupMissingCheck(ArrayList listOfStudent, ArrayList listOfGroup) throws userException {
        int groupAssigned=0;
        for(int indexOfGroup=0; indexOfGroup<listOfGroup.size();indexOfGroup++){
            for (int indexOfStudent=0;indexOfStudent<listOfStudent.size();indexOfStudent++){
                if (listOfGroup.get(indexOfGroup).toString().contains(((Student) listOfStudent.get(indexOfStudent)).getGroup()))
                    groupAssigned++;
            }
            if (groupAssigned==0) throw new userException("There are no students assigned to group "+ listOfGroup.get(indexOfGroup));
            groupAssigned=0;
        }
    }

    private static void subjectsExistingCheck(ArrayList listOfSubjects, ArrayList listOfStudentAssessments, ArrayList listOfStudent) throws userException {
        int subjectExsisting=0;
        for (int indexStudent=0; indexStudent<listOfStudent.size();indexStudent++){
            for(int indexOfStudentInAssessmentArray=0; indexOfStudentInAssessmentArray<listOfStudentAssessments.size();indexOfStudentInAssessmentArray++){
                if (((SubjectsBase) listOfStudentAssessments.get(indexOfStudentInAssessmentArray)).getStudentName().contentEquals(((Student) listOfStudent.get(indexStudent)).getStudentFullName()))
                    for (int indexOfSubject=0; indexOfSubject<listOfSubjects.size();indexOfSubject++){
                        if (((SubjectsBase) listOfStudentAssessments.get(indexOfStudentInAssessmentArray)).getSubjectName().contentEquals(listOfSubjects.get(indexOfSubject).toString()))
                            subjectExsisting++;
                    }
            }
            if (subjectExsisting==0){
                throw new userException("The student "+ ((Student) listOfStudent.get(indexStudent)).getStudentFullName()+" has not assigned subjects");
            }
            subjectExsisting=0;
        }
    }

    private static void valueOfAssessmentsCheck(ArrayList listOfStudentAssessments) throws userException {
        for(int index=0;index<listOfStudentAssessments.size();index++){
            if (((SubjectsBase) listOfStudentAssessments.get(index)).getAssessment()<0 || ((SubjectsBase) listOfStudentAssessments.get(index)).getAssessment()>10)
                throw new userException("The subjext "+ ((SubjectsBase) listOfStudentAssessments.get(index)).getSubjectName()+" of student "
                        +((SubjectsBase) listOfStudentAssessments.get(index)).getStudentName() + " has a value "+
                        ((SubjectsBase) listOfStudentAssessments.get(index)).getAssessment() +" which is out of range 0-10");
        }
    }

    private static ArrayList inputOfStudentAssessment() {
        ArrayList listOfStudentAssessment= new ArrayList();
        listOfStudentAssessment .add(0 , new SubjectsBase("Igor","Geography",4));
        listOfStudentAssessment .add(1 , new SubjectsBase("Igor","Fitness",7));
        listOfStudentAssessment .add(2 , new SubjectsBase("Igor","Russian Language",2));

        listOfStudentAssessment .add(3 , new SubjectsBase("Tolik","Geography",3));
        listOfStudentAssessment .add(4 , new SubjectsBase("Tolik","Fitness",8));
        listOfStudentAssessment .add(5 , new SubjectsBase("Tolik","Russian Language",8));

        listOfStudentAssessment .add(6, new SubjectsBase("Oleg","Geography",3));
        listOfStudentAssessment .add(7 , new SubjectsBase("Oleg","Fitness",4));
        listOfStudentAssessment .add(8 , new SubjectsBase("Oleg","Russian Language",4));

        listOfStudentAssessment .add(9 , new SubjectsBase("Natallia","Geography",9));
        listOfStudentAssessment .add(10 , new SubjectsBase("Natallia","Fitness",10));
        listOfStudentAssessment .add(11, new SubjectsBase("Natallia","Russian Language",9));

        listOfStudentAssessment .add(9 , new SubjectsBase("Vitalik","Geography",7));
        listOfStudentAssessment .add(10 , new SubjectsBase("Vitalik","Fitness",8));
        listOfStudentAssessment .add(11, new SubjectsBase("Vitalik","Russian Language",7));
        return listOfStudentAssessment;
    }

    private static ArrayList inputOfStudent() {
        ArrayList listOfStudent= new ArrayList();
        listOfStudent .add(0 , new Student("Igor","100","FITR"));
        listOfStudent .add(1 , new Student("Tolik","101","FITR2"));
        listOfStudent .add(2 , new Student("Oleg","100","FITR2"));
        listOfStudent .add(3 , new Student("Natallia","101","FITR"));
        listOfStudent .add(4 , new Student("Vitalik","101","FITR3"));
        return listOfStudent ;
    }

    private static ArrayList inputOfSubjects() {
        ArrayList listOfSubjects = new ArrayList();
        listOfSubjects .add(0 , "Geography");
        listOfSubjects .add(1 , "Fitness");
        listOfSubjects .add(2 , "Russian Language");
        return listOfSubjects ;
    }

    private static ArrayList inputOfGroup() {
        ArrayList listOfGroup = new ArrayList();
        listOfGroup.add(0 , "100");
        listOfGroup.add(1 , "101");
//        listOfGroup.add(2 , "102");
//        listOfGroup.add(3 , "103");
        return listOfGroup;
    }

    private static ArrayList inputOfFaculty() {
        ArrayList listOfFaculty = new ArrayList();
        listOfFaculty.add(0 , "FITR");
        listOfFaculty.add(1 , "FITR2");
        listOfFaculty.add(2 , "FITR3");
        return listOfFaculty;
    }

    public static void menu(ArrayList litOfStudent, ArrayList listOfStudentAssessments, ArrayList listOdFaculty, ArrayList listOfGroup, ArrayList listOfSubjects){
        while(true){
            System.out.println("");
            System.out.println("Select an option:");
            System.out.println("1. calculate an average assessment value of all subject of certain student");
            System.out.println("2. calculate an average value of certain subject in certain faculty and group");
            System.out.println("3. calculate an average value of subject overall around university");
            System.out.println("0. finish");
            System.out.println("");

            Scanner selection = new Scanner(System.in);

            switch (selection.nextInt()){
                case(1):
                    averageValueCalculation(litOfStudent, listOfStudentAssessments);
                case(2):
                    averageValueByParameter(litOfStudent,listOfSubjects, listOdFaculty, listOfGroup, listOfStudentAssessments);
                case(3):
                    averageOfAssessmentAroundUniversity(listOfStudentAssessments);
            }
        }
    }

    private static void averageOfAssessmentAroundUniversity(ArrayList listOfStudentAssessments) {
        Double overalSumme=0.0;
        int count=0;
        for(int index=0;index<listOfStudentAssessments.size();index++){
            overalSumme = overalSumme + ((SubjectsBase) listOfStudentAssessments.get(index)).getAssessment();
            count++;
        }
        System.out.println("An average assessment of all subjects around the University is: "+overalSumme/count);
    }

    private static void averageValueByParameter(ArrayList listOfStudent, ArrayList listOfSubjects, ArrayList listOdFaculty, ArrayList listOfGroup, ArrayList listOfStudentAssessments) {
        System.out.println("Please pick out the subject: ");
        String selectedSubject = studentAssignment(listOfSubjects);
        System.out.println("Please pick out the faculty");
        String selectedFaculty = studentAssignment(listOdFaculty);
        System.out.println("Please pick out the group:");
        String selectedGroup = studentAssignment(listOfGroup);
        Double averageAssessment = 0.0;
        int count=0;
        for (int index=0;index<listOfStudent.size();index++){
            if (((Student) listOfStudent.get(index)).getFaculty().contains(selectedFaculty) && ((Student) listOfStudent.get(index)).getGroup().contains(selectedGroup))
                for (int indexStudentAssessments=0;indexStudentAssessments<listOfStudentAssessments.size();indexStudentAssessments++){
                    if (((SubjectsBase) listOfStudentAssessments.get(indexStudentAssessments)).getSubjectName().contains(selectedSubject)) {
                        averageAssessment = averageAssessment + ((SubjectsBase) listOfStudentAssessments.get(indexStudentAssessments)).getAssessment();
                        count++;
                    }
                }
        }
        System.out.println("The average assessment of "+selectedSubject+" subject "+" of "+selectedFaculty+" faculty of "+selectedGroup+" group is: "+averageAssessment/count);
    }

    private static void averageValueCalculation(ArrayList litOfStudent, ArrayList listOfStudentAssessments) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please select a student for whom will be calculated an average assessment of all subjects");
        String selectedStudent = studentSelection(litOfStudent);
        System.out.println("");
        Double assessmentsSume = 0.0;
        int count =0;
        for (int index=0;index<listOfStudentAssessments.size();index++){
           if (((SubjectsBase) listOfStudentAssessments.get(index)).getStudentName().contains(selectedStudent)){
               assessmentsSume = assessmentsSume + ((SubjectsBase) listOfStudentAssessments.get(index)).getAssessment();
               count++;
           }
        }
        System.out.println("An average assessment of all subjects: " + assessmentsSume/count);
    }

    public static ArrayList manualDataBaseInputting(String nameOfParameter) {
        ArrayList listOfElement = new ArrayList();
        int index = 0;
        String finish="";
        while (!finish.contains("n")){
            Scanner input = new Scanner(System.in);
            System.out.println("Input a " + nameOfParameter + " name");
            String elementName = input.next();
            listOfElement.add(index,elementName);
            //check for finish of inputting
            Scanner finishInput = new Scanner(System.in);
            System.out.println("Do you want add one more " + nameOfParameter + " y/n");
            finish = finishInput.next();
            if (!finish.contains("n")) index++;
           }
        System.out.println(listOfElement);
        return listOfElement;
     //  }
   }
    private static ArrayList manualStudentDataBaseInputting(ArrayList listOfFaculty, ArrayList listOfGroup, ArrayList listOfSubject) {
        ArrayList listOfStudent = new ArrayList();
       String finish="";
       while (!finish.contains("n")){
           System.out.println("Please input a student name and afterwards to assign the group and faculty for him");
           Scanner input = new Scanner(System.in);
           String studentName = input.nextLine();

           System.out.println("There are following faculty, please select one to assign for "+ studentName);
         //  System.out.println(listOfFaculty);
           String studentFaculty = studentAssignment(listOfFaculty);

           System.out.println("There are following groups, please select one to assign for "+ studentName);
         //  System.out.println(listOfGroup);
           String studentGroup = studentAssignment(listOfGroup);

           System.out.println(studentName + " belongs to " + studentGroup + " group of " + studentFaculty + " faculty");

           Student student = new Student(studentName, studentGroup, studentFaculty);

           listOfStudent.add(student);

           Scanner finishInput = new Scanner(System.in);
           System.out.println("Do you want add one more student y/n");
           finish = finishInput.next();
       }
       return listOfStudent;
   }

    private static String studentAssignment(ArrayList listOfAssignment) {
        Integer inputtedValue;
        while (true) {
            for (int index=0;index<listOfAssignment.size();index++){
                System.out.println(index+1 +". "+  listOfAssignment.get(index).toString());
            }
            System.out.println("array size: "+listOfAssignment.size());
            Scanner input= new Scanner(System.in);
            inputtedValue = input.nextInt();
            if (inputtedValue-1>= listOfAssignment.size()) {
                System.out.println ("The inputted number is out of bounds of list, please input a number within the range 0 - " + listOfAssignment.size());}
            else {break;};
        }
        return (String) listOfAssignment.get(inputtedValue-1);
    }

    private static String studentSelection(ArrayList listOfStudent) {
        Integer inputtedValue;
        while (true) {
            for (int index=0;index<listOfStudent.size();index++){
                System.out.println(index+1 +". "+ ((Student) listOfStudent.get(index)).getStudentFullName());
            }
            System.out.println("array size: "+listOfStudent.size());
            Scanner input= new Scanner(System.in);
            inputtedValue = input.nextInt();
            if (inputtedValue-1>= listOfStudent.size()) {
                System.out.println ("The inputted number is out of bounds of list, please input a number within the range 0 - " + listOfStudent.size());}
            else {break;};
        }
        System.out.println("Selected student is: "+((Student) listOfStudent.get(inputtedValue - 1)).getStudentFullName());
        return ((Student) listOfStudent.get(inputtedValue - 1)).getStudentFullName();
    }

    private static ArrayList manualStudentAssessmentsInputting(ArrayList listOfStudent, ArrayList listOfSubjects) {
        ArrayList studentSubjectsAssessments = new ArrayList();
        String studentName;
        while (true) {
            System.out.println("Please pick out a student for assign subject and assessment");
            studentName = studentSelection(listOfStudent);
            System.out.println("The next student is selected: "+studentName);

            System.out.println("Please pick out the subject and appreciated assessment for student " + studentName);
            String selectedSubject = studentAssignment(listOfSubjects);
            System.out.println("selected subject "+selectedSubject);

            boolean subjectExsiting=false;
            for (int index=0;index<studentSubjectsAssessments.size();index++){
                if ( ((SubjectsBase) studentSubjectsAssessments.get(index)).getStudentName().contains(studentName) &&
                        ((SubjectsBase) studentSubjectsAssessments.get(index)).getSubjectName().contains(selectedSubject)) subjectExsiting=true;
            }
            if(subjectExsiting){
                System.out.println("");
                System.out.println("The selected subject " +selectedSubject+" is already assigned, please select another one");
                System.out.println("");
                continue;
            }

            System.out.println("please input an assessment for subject " + selectedSubject);
            Scanner inputOfAssessment = new Scanner(System.in);
            Integer inputtedAssessment = inputOfAssessment.nextInt();

            SubjectsBase subjectsBase = new SubjectsBase(studentName,selectedSubject, inputtedAssessment);

            studentSubjectsAssessments.add(subjectsBase);

            System.out.println("");
            for (int index = 0; index<studentSubjectsAssessments.size();index++){
                System.out.println((((SubjectsBase) studentSubjectsAssessments.get(index)).getSubjectName())+ " - " + ((SubjectsBase) studentSubjectsAssessments.get(index)).getAssessment());
            }
            System.out.println("");

            Scanner finishInput = new Scanner(System.in);
            System.out.println("Do you want add one more y/n");
            if (finishInput.next().contains("n"))break;
        }
        return studentSubjectsAssessments;
    }

}