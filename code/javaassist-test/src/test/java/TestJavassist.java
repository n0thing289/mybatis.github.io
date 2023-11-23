import com.bjpowernode.dao.AccountDao;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestJavassist {
    @Test
    public void test() throws CannotCompileException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //获取类池
        ClassPool pool = ClassPool.getDefault();
        //创建类
        CtClass ctClass = pool.makeClass("CtClassByJavassist");
        //创建方法
        String methodCode =
                "public void hello(){" +
                "System.out.println(\"Hello World!\");" +
                "}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
        //添加到类中
        ctClass.addMethod(ctMethod);
        //在内存中生成class
        ctClass.toClass();

        //反射机制
        Class<?> clazz = Class.forName("CtClassByJavassist");
        //创建对象
        Object obj = clazz.newInstance();
        //获取其方法
        Method method = clazz.getDeclaredMethod("hello");
        //调用
        method.invoke(obj);
        //打印信息
        System.out.println(obj);

    }

    @Test
    public void testAccountDaoByJavassist() throws CannotCompileException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //获取类池
        ClassPool pool = ClassPool.getDefault();
        //制造类
        CtClass ctClass = pool.makeClass("com.bjpowernode.dao.impl.AccountDaoImpl");
        //制造接口 ?为什么要制造接口而不能获取一个已有的接口呢
        CtClass ctInterface = pool.makeInterface("com.bjpowernode.dao.AccountDao");
        //添加接口到类中 AccountDaoImpl implements AccountDao
        ctClass.addInterface(ctInterface);
        //实现接口中的方法-制造方法
        String methodCode =
                "public void hello(){" +
                        "System.out.println(\"Hello World2!\");" +
                        "}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
        //将方法添加到类中
        ctClass.addMethod(ctMethod);
        //在内存中生成类, 同时将生成的类加载到JVM当中
        Class<?> clazz = ctClass.toClass();
        AccountDao accountDao = (AccountDao) clazz.newInstance();
        accountDao.hello();
    }
}
