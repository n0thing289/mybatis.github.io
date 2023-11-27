package pojo;

/**
 * 学生信息
 * 如果要构建多对一映射关系, MyStudent是属于多的一方, 多对一多在前面,所以多是主表
 */
public class MyStudent {
    private Integer sid;
    private String sname;
    private MyClass myClass;

    public MyStudent() {
    }

    public MyStudent(Integer sid, String sname) {
        this.sid = sid;
        this.sname = sname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public MyClass getMyClass() {
        return myClass;
    }

    public void setMyClass(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public String toString() {
        return "MyStudent{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", myClass=" + myClass +
                '}';
    }
}
