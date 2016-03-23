package entity;

public class Student {
	private String id;
	private String name;
	private Grade[] score;
	private double[] ratio;
	private double total;
	
	public Student(String id,String name,Grade[] score,double[] ratio){
		this.id = id;
		this.name = name;
		this.score = new Grade[score.length];
		this.ratio = new double[score.length];
		for(int i = 0;i < score.length;i++){
			this.score[i] = score[i];
			this.ratio[i] = ratio[i];
		}
		this.total = 0.0;
		for(int i = 0;i < score.length;i++){
			this.total += score[i].getGrade() * ratio[i];
		}
	}
	
	/** method  getScore  ---------------------------------------------------------------------------------- 
	 *  获取到这名学生的所有考试分数
	 *  @return 一个double数组，包含按顺序为学生的每一次成绩。
	 *  Pseudo code:
	 *		1.返回学生成绩数组
     *
	 * Time estimate : O (1)
	 * Example: student.getScore()，返回student这个学生的成绩。
	 */
	public Grade[] getScore() {
		return score;
	}


	/** method  getRatio  ---------------------------------------------------------------------------------- 
	 *  获取到这名学生的所有考试分数比重
	 *  @return 一个double数组，包含按顺序为学生的每一次成绩的比重。
	 *  Pseudo code:
	 *		1.返回学生成绩比重数组
     *
	 * Time estimate : O (1)
	 * Example: student.getRatio()，返回student这个学生的成绩比重。
	 */
	public double[] getRatio() {
		return this.ratio;
	}
	
	/** method  getId  ---------------------------------------------------------------------------------- 
	 *  获取这名学生的总成绩
	 *  @return 一个double，内容是学生的总成绩.
	 *  Pseudo code:
	 *      1.将total返回
     *
	 * Time estimate : O (1)
	 * Example: student.getId()，返回student这个学生的total。
	 */
	public double getTotalGrade(){
		return this.total;
	}
	
	/** method  setRatio  ---------------------------------------------------------------------------------- 
	 *  重置这名学生的所有考试分数比重
	 *  @param ratio 要变更成的成绩配比
	 *  @return 整型数字，返回是否重置成功。-1为失败，1为成功
	 *  Pseudo code:
	 *      1.检查新的比重数组大小是否和原来的相同，如不同返回-1。
	 *		2.检查比重之和是否为1，如果不是，返回-1。
	 *		3.设置学生成绩比重，
	 *		4.重新计算学生总分，返回1.
     *
	 * Time estimate : O (1)
	 * Example: student.setRatio(newRatio)，student这个学生的分数配比将会变为newRatio中的内容。
	 */
	public int setRatio(double[] ratio) {
		if(ratio.length != this.ratio.length){
			return -1;
		}
		double total = 0;
		for(int i = 0;i < ratio.length;i++){
			total += ratio[i];
			System.out.println(total);
		}
		if(total != 1.0){
			return -1;
		}
		for(int i = 0;i < ratio.length;i++){
			this.ratio[i] = ratio[i];
		}
		this.total = 0.0;
		for(int i = 0;i < score.length;i++){
			this.total += score[i].getGrade() * ratio[i];
		}
		return 1;
	}

	/** method  getId  ---------------------------------------------------------------------------------- 
	 *  获取这名学生的ID
	 *  @return 一个String，内容是学生的Id.
	 *  Pseudo code:
	 *      1.将Id返回
     *
	 * Time estimate : O (1)
	 * Example: student.getId()，返回student这个学生的Id。
	 */
	public String getId() {
		return this.id;
	}

	/** method  getName  ---------------------------------------------------------------------------------- 
	 *  获取这名学生的姓名
	 *  @return 一个String，内容是学生的Name
	 *  Pseudo code:
	 *      1.将学生姓名返回
     *
	 * Time estimate : O (1)
	 * Example: student.getName()，返回student这个学生的姓名。
	 */
	public String getName() {
		return this.name;
	}
	
}
