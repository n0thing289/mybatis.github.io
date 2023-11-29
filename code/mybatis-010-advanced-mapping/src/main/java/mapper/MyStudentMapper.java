package mapper;

import pojo.MyStudent;

import java.util.List;

public interface MyStudentMapper {
    /**
     * 一对多:分布查询第二步,根据班级编号查询学生信息
     * @param cid
     * @return
     */
    List<MyStudent> selectByCidStep2(Integer cid);

    /**
     * 多对一:分布查询第一步:先根据学生的sid查询
     * @param sid
     * @return
     */
    MyStudent selectByIdStep1(Integer sid);

    /**
     * 多对一:一条SQL语句,association
     * @param sid
     * @return
     */
    MyStudent selectByIdAssociation(Integer sid);

    /**
     * 多对一:根据id获取学生信息。同时获取学生关联的班级信息
     * @param sid
     * @return
     */
    MyStudent selectById(Integer sid);
}
