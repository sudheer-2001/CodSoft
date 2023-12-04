import java.util.Scanner;

class Grade{
    public String grade(int avg){
        switch (avg / 10) {
            case 10:
                return "A+";
            case 9:
                return "A";
            case 8:
                return "B+";
            case 7:
                return "B";
            case 6:
                return "C";
            case 5:
                return "D";
            default:
                return "F";
        }
    }
}

public class Gradecalc{
    public static void main(String[] args){
        Grade g = new Grade();
        Scanner sc = new Scanner(System.in);

        //Taking input of how many subjects are there
        System.out.println("Enter the number of subjects");
        int totalSubjects = sc.nextInt();
        int totalMarks = 0;

        //Taking and adding all the marks
        for (int i = 0; i < totalSubjects; i++) {
            System.out.println("Enter the marks obtained in subject " + (i + 1));
            int marks = sc.nextInt();
            totalMarks += marks;
        }

        //Calculating Percentage
        int avg = totalMarks / totalSubjects;

        //Calculating Grades
        String Grade = g.grade(avg);

        //Displaying all the Data
        System.out.println("Total marks obtained :- " + totalMarks);
        System.out.println("Percentage obtained :- " + avg+"%");
        System.out.println("Grade obtained :- " + Grade);

        sc.close();
    }
}
