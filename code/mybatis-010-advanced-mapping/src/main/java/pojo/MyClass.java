package pojo;

/**
 * 班级信息
 */
public class MyClass {
    private Integer cid;
    private String cname;

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

    @Override
    public String toString() {
        return "MyClass{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
