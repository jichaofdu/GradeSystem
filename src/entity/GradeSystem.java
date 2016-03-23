package entity;
import java.util.Vector;
import java.util.Scanner;  

public class GradeSystem {
	 Vector<Student> students; 
	 Scanner sc;  
	
	public GradeSystem(){
		 students = new Vector<Student>();   
		 sc = new Scanner(System.in);   
	}
	
	/** method  getInstruction  ---------------------------------------------------------------------------------- 
	 *  成绩系统的主程序
	 *  @return 该系统是否已经退出。如果是-1表示系统已经退出。
	 *  Pseudo code:
	 *		1.获取用户第一步指令，是输入ID继续还是退出
	 *		2.如果用户指令是Q，则直接退出程序，返回-1
	 *		3.如果用户输入了错误的ID，则程序重新开始
	 *		4.如果用户输入了正确的ID，则在控制台输出欢迎信息；
	 *		5.然后根据用户下一步的指令选择对应的操作。
     *
	 *  Time estimate : O (n)
	 *  Example: while(status == 1){
	 *               status = getInstruction
	 *           }
	 *           循环进行指令操作，直到用户要求退出
	 */
	public int getInstructon(){
		System.out.println("輸入ID或 Q (結束使用)");
		String inputString = sc.nextLine();
	    if("Q".equals(inputString)){
	    	System.out.println("結束了");
	    	return -1;
	    }
	    Student user = checkStudent(inputString);
	    if(user == null){
	    	System.out.println("ID錯了!");
	    	return 0;
	    }
	    System.out.println("Welcome," + user.getName());
	    int status = 1;
	    while(status == 1){
			System.out.println("\n輸入指令 \n"
					+ "1) G 顯示成績 (Grade)\n"
					+ "2) R 顯示排名 (Rank)\n"
					+ "3) A 顯示平均 (Average)\n"
					+ "4) W 更新配分 (Weight)\n"
					+ "5) E 離開選單 (Exit)\n");
			inputString = sc.nextLine();
			switch(inputString){
				case "G":
					showGrade(user.getId());
					break;
				case "R":
					showRank(user.getId());
					break;
				case "A":
					showAverage(user.getId());
					break;
				case "W":
					showRatio(user.getId());
					int resetStatus = 0;
					while(resetStatus <= 0){
						resetStatus = resetRatio(user.getId());
					}
					break;
				case "E":
					status = 0;
					return 0;
				default:
					System.out.println("指令錯了!");
			}
	    }
		return -1;
	}
	
	/** method  addStudent  ---------------------------------------------------------------------------------- 
	 *  录入一个学生的信息
	 * @param newStudent 是打包好的新学生的信息
	 *  Pseudo code:
	 *		1.调用vector的add方法将信息输入
     *
	 * Time estimate : O (1)
	 * Example: system.addStudent(student) 即可将学生信息加入系统
	 */
	public void addStudent(Student newStudent){
		students.add(newStudent);
	}
	/**
	 * Reset the student's score ratio.
	 * @param studentId The student you want to reset.
	 * @return The result.Fail is -1 and success is 1.
	 */
	private int resetRatio(String studentId){
		Student student = checkStudent(studentId);
		if(student == null){
			System.out.println("ID錯了!");
			return -1;
		}
		int total = 0;
		double[] newRatio = new double[student.getScore().length];
		int i = 0;
		for(Grade grade:student.getScore()){
			System.out.println("請重新設置配分（0-100）");
			System.out.println(grade.getName());
			String inputString = sc.nextLine();
			try{
				int thisRatio = Integer.parseInt(inputString);
				total += thisRatio;
				newRatio[i] = ((double)thisRatio / (double)100);
				System.out.println(newRatio[i]);
			}catch(Exception e){
				System.out.println("配分重置失敗：輸入錯誤");
				return -1;
			}
			i++;
		}
		if(total != 100){
			System.out.println("配分重置失敗：總和不是100");
			return -1;
		}else{
			int result = student.setRatio(newRatio);
			if(result == 1){
				System.out.println("配分重置成功");
				return 1;
			}else{
				System.out.println("失败");
				return -1;
			}
		}
	}
	
	/**
	 * Check whether the student exist.
	 * @param inputId The student's id you want to search.
	 * @return The student.If the student does not exist,will return null.
	 */
	private Student checkStudent(String inputId){
		for(Student student:this.students){
			if(inputId.equals(student.getId())){
				return student;
			}
		}
		return null;
	}
	
	/**
	 * Print the student's score.
	 * @param studentId The student you want to search.
	 * @return the result.If the student does not exist,will return -1.
	 */
	private int showGrade(String studentId){
		Student student = checkStudent(studentId);
		if(student == null){
			System.out.println("ID錯了!");
			return -1;
		}
		for(Grade grade:student.getScore()){
			String output = "";
			output = grade.getName() + ":" +  printGrade(grade.getGrade());;
			System.out.println(output);
		}
		System.out.println("total grade:" + printGrade(student.getTotalGrade()));
		return 1;
	}
	/**
	 * Print the score ratio of the student.
	 * @param studentId The student you want to print.
	 * @return the result.If the student does not exist,will return -1.
	 */
	private int showRatio(String studentId){
		Student student = checkStudent(studentId);
		if(student == null){
			System.out.println("ID錯了!");
			return -1;
		}
		for(int i = 0;i < student.getScore().length;i++){
			System.out.println(student.getScore()[i].getName() + "配分是：" + student.getRatio()[i]);
		}
		return 1;
	}
	
	/**
	 * Print the student's rank.
	 * @param studentId The student you want to print rank.
	 * @return return the result.If the student does not exist,-1 will be return.
	 */
	private int showRank(String studentId){
		int rank = 1;
		double total = 0;
		String name = "";
		Student student = checkStudent(studentId);
		if(student == null){
			System.out.println("ID錯了!");
			return -1;
		}
		total = student.getTotalGrade();
		name = student.getName();
		for(Student otherStudent:students){
			if(otherStudent.getTotalGrade() > total){
				rank++;
			}
		}
		System.out.println(name + "这名学生的排名是" + rank);
		return 1;
	}
	
	/**
	 * Print the student's final grade.
	 * @param studentId The student that will printl=.
	 * @return The result.If the student does not exist,will return -1.
	 */
	private int showAverage(String studentId){
		Student student = checkStudent(studentId);
		if(student == null){
			System.out.println("ID錯了!");
			return -1;
		}
		System.out.println("total grade:" + printGrade(student.getTotalGrade()));
		return 1;
	}
	
	/**
	 * Print the score.Add '*' is score lower than 60.
	 * @param grade The input grade.
	 * @return Output grade with or without '*'
	 */
	private String printGrade(double grade){
		if(grade > 60.0){
			return grade + "";
		}else{
			return grade + "*";
		}
	}

}



