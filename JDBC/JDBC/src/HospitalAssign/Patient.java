package HospitalAssign;

public class Patient {
	int pid, age;
	String pname, email;
	float weight;
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}
	public Patient(int pid, int age, float weight, String pname, String email) {
		super();
		this.pid = pid;
		this.age = age;
		this.weight = weight;
		this.pname = pname;
		this.email = email;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Patient [pid=" + pid + ", age=" + age + ", pname=" + pname + ", email=" + email + ", weight=" + weight
				+ "]";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
