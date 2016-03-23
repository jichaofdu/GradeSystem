package entity;

public class Grade {
	private String name;
	private double grade;
	
	public Grade(String name,int grade){
		this.name = name;
		this.grade = grade;
	}
	
	/** method  getName  ---------------------------------------------------------------------------------- 
	 *  获取到本次考试的名称
	 *  @return 返回值是这次考试的名称
	 *  Pseudo code:
	 *		1.返回本次考试的名称
     *
	 * Time estimate : O (1)
	 * Example: grade.getName()，返回grade的名称。
	 */
	public String getName() {
		return name;
	}
	
	/** method  getGrade  ---------------------------------------------------------------------------------- 
	 *  获取到本次考试的成绩
	 *  @return 返回值是这次考试的成绩
	 *  Pseudo code:
	 *		1.返回本次考试的成绩
     *
	 * Time estimate : O (1)
	 * Example: grade.getGrade()，返回grade的成绩。
	 */
	public double getGrade() {
		return grade;
	}
	
}
