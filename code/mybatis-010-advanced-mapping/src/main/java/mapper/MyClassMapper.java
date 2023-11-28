package mapper;

import pojo.MyClass;
import pojo.MyStudent;

public interface MyClassMapper {
    /**
     * 一对多:分步查询第一步
     * @param cid
     * @return
     */
    MyClass selectByStep1(Integer cid);

    /**
     * 一对多:根据班级编号查询班级信息
     * @param cid
     * @return
     */
    MyClass selectByCollection(Integer cid);

    /**
     * 多对一:分布查询第二步:根据cid获取班级信息
     * @param cid
     * @return
     */
    MyClass selectByIdStep2(Integer cid);
}
