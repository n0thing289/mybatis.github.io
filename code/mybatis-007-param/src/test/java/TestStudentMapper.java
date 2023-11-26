import mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Student;
import utils.SqlSessionUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestStudentMapper {
    /**
     * 测试使用Param注解优化多参数的可读性
     */
    @Test
    public void testSelectByNameAndSex2(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectByNameAndSex2("张伟峰", '嬲');
        students.forEach(student -> System.out.println(student));
        //
        SqlSessionUtil.close();
    }


    /**
     * 测试传入多个参数
     */
    @Test
    public void testSelectByNameAndSex(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectByNameAndSex("张伟峰", '嬲');
        students.forEach(student -> System.out.println(student));
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试传入一个pojo
     */
    @Test
    public void testInsertStudentByPojo() throws ParseException {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setName("张伟峰");
        student.setAge(18);
        student.setHeight(1.81);
        student.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-02"));
        student.setSex('嬲');
        int count = studentMapper.insertStudentByPojo(student);
        System.out.println("插入了" + count + "条数据");
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试传入一个Map类型
     */
    @Test
    public void testInsertStudentByMap() throws ParseException {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("名字", "朱志成");
        map.put("年龄", 20);
        map.put("身高", 1.80);
        map.put("生日", new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-02"));
        map.put("性别", '男');
        int count = studentMapper.insertStudentByMap(map);
        System.out.println("插入了" + count + "条数据");
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();


    }

    /**
     * 测试传入Character类型
     */
    @Test
    public void testSelectBySex() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Character character = '男';
        List<Student> students = studentMapper.selectBySex(character);
        students.forEach(System.out::println);
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试传入一个java.util.Date类型的数据(自动类型推断)
     * java.util.Date和java.sql.Date都是简单类型
     */
    @Test
    public void testSelectByBirth() throws ParseException {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//"yyyy-mm-dd"
        Date date = sdf.parse("1980-01-02");
        List<Student> students = studentMapper.selectByBirth(date);
        students.forEach(student -> {
            System.out.println(student);
        });
        //
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectByName() {

    }

    /**
     * 测试传入一个Long类型的数据(自动类型推断)
     */
    @Test
    public void testSelectById() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectById(1L);
        students.forEach(student -> {
            System.out.println(student);
        });
        //
        SqlSessionUtil.close();
    }
}
