package ex03;
import java.time.LocalDate;
import java.util.Scanner;
interface Ipo {
	void input();
	void process();
	void output();
}
class CompanyDto {	
	/*
	100,사나,20110101,300.0,10      
	200,모모,20120301,270.0,20      
	300,정연,20091003,250.0,30      
	400,나연,20110105,220.0,40      
	500,미나,20180401,170.0,60      
	600,쯔위,20150801,200.0,50      
	*/
	
	//입력 data : 사번,이름,입사일,월급,부서번호
	// 			empNo name hireDate salary deptId
	private int empNo;
	private String name;
	private int hireDate;
	private double salary;
	private int deptId;
	//출력 data  : 보너스,수령액,부서명
	//			 bonus netPay deptName 
	private double bonus;
	private double netPay;
	private String deptName;
	//constructor 
	public CompanyDto() {}
	public CompanyDto(int empNo, String name, int hireDate, double salary, int deptId) {
		this.empNo = empNo;
		this.name = name;
		this.hireDate = hireDate;
		this.salary = salary;
		this.deptId = deptId;
	}
	// Getter /Setter
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHireDate() {
		return hireDate;
	}
	public void setHireDate(int hireDate) {
		this.hireDate = hireDate;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "CompanyDto [empNo=" + empNo + ", name=" + name + ", hireDate=" + hireDate + ", salary=" + salary
				+ ", deptId=" + deptId + ", bonus=" + bonus + ", netPay=" + netPay + ", deptName=" + deptName + "]";
	}	
}
class Company 	implements Ipo {
	
	private CompanyDto	c;
	@Override
	public void input() {
		Scanner in =new Scanner(System.in);
		
		System.out.println("입력:사번,이름,입사일,월급,부서번호");
		String line   =	in.nextLine();
		String [] li  =	line.trim().split(",");
		int empNo	  =	Integer.parseInt(li[0].trim());
		String name   =	li[1].trim();
		int hireDate  =	Integer.parseInt(li[2].trim());
		double salary =	Double.parseDouble(li[3].trim());
		int deptId	  =	Integer.parseInt(li[4].trim());
		
		c = new CompanyDto(empNo, name, hireDate, salary, deptId);
	}
	@Override
	public void process() {
		// 보너스 : 월급의 0.5 bonus 
		int 	thisYear = LocalDate.now().getYear();
		int		hireDate = c.getHireDate();
		double	hireYear = hireDate * 0.0001;
		int		hireYears = thisYear - (int)hireYear;
		double 	salary = c.getSalary();
		double 	bonus = hireYears * salary * 0.0005;
		c.setBonus(bonus);
		//수령액 : 보너스 + 월급 netPay
		double netPay = c.getBonus() + c.getSalary();
		c.setNetPay(netPay);
		//부서명 deptName 10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스
		if( c.getDeptId() == 10) {
			c.setDeptName("인사");}
			else {
				if(c.getDeptId() == 20) {
					c.setDeptName("자재");}
				else {
					if (c.getDeptId() == 30) {
		            	c.setDeptName("총무");}
					else {
						if(c.getDeptId() == 40) {
							c.setDeptName("연구개발");}
						else {
							if(c.getDeptId() == 50) {
								c.setDeptName("생산");}
							else  {
								if(c.getDeptId() == 60) {
									c.setDeptName("서비스");}
							}
						}
					}
				}
		}
	}
	@Override
	public void output() {	
		System.out.println("사번,이름,입사일,월급,보너스,수령액,부서명");
		String frm = "%d %s %d %.2f %.2f %.2f %s";
		String str = String.format(frm,c.getEmpNo(),c.getName()
				,c.getHireDate(),c.getSalary(),c.getBonus(),c.getNetPay(),c.getDeptName());
		System.out.println(str);
	}
}
public class TestCompany {
	public static void main(String[] args) {
		Company comp =new Company();
		comp.input();
		comp.process();
		comp.output();
	}
}
