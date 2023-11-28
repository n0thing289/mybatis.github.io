package pojo;

import java.util.List;

/**
 * 班级信息
 * 如果是一对多映射关系, 班级是一,一在前面,一是主表
 */
public class MyClass {
    private Integer cid;
    private String cname;
    private List<MyStudent> myStudents;

    public MyClass() {
    }

    public MyClass(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<MyStudent> getMyStudents() {
        return myStudents;
    }

    public void setMyStudents(List<MyStudent> myStudents) {
        this.myStudents = myStudents;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", myStudents=" + myStudents +
                '}';
    }
}
