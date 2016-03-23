package userUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import entity.Grade;
import entity.GradeSystem;
import entity.Student;
/** ##################################################################
	本Grade system 讓使用者(學生)取得他的總成績total grade 及排名 rank.
    Total grade 基於配分weights 來算 而 weights可以update. 
	Rank 表示此 total grade 在全班學生的分數排序

	Input file: 全班學生的分數 例如
           962001044 凌宗廷 87 86 98 88 87
		   962001051 李威廷 81 32 50 90 93
         注意 data field names 如下: 
        ID     name lab1 lab2 lab3 midTerm finalExam
#################################################################### */
public class Launcher {
	
	public static void main(String[] args){
		GradeSystem gradeSystem = new GradeSystem();
		try {     	
	        String pathname = "F:\\Users\\Chao Ji\\Desktop\\软件工程\\HW1_grp\\reference\\gradeinput.txt"; 
	        File filename = new File(pathname);
	        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));   
	        String line = "";  
	        String[] split;
	        String newId;
	        String newName;
	        Grade[] grades = new Grade[5];
	        double[] ratio = {0.1,0.1,0.1,0.3,0.4};
	        while ((line = br.readLine()) != null) {  
	        	split = line.split(" ");
	        	newId = split[0];
	        	newName = "";
	        	for(int i = 1;i < split.length - 5;i++){
	        		newName += split[i];
	        	}
	        	grades[0] = new Grade("Lab1",Integer.parseInt(split[split.length - 5]));
	        	grades[1] = new Grade("Lab2",Integer.parseInt(split[split.length - 4]));
	        	grades[2] = new Grade("Lab3",Integer.parseInt(split[split.length - 3]));
	        	grades[3] = new Grade("mid-term",Integer.parseInt(split[split.length - 2]));
	        	grades[4] = new Grade("final exam",Integer.parseInt(split[split.length - 1]));
	        	Student newStudent = new Student(newId,newName,grades,ratio);
	        	gradeSystem.addStudent(newStudent);
	        }  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		int status = 1;
		while(status >= 0){
			status = gradeSystem.getInstructon();
		}
	}
}
