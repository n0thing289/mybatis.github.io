package bank.utils;

import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 给一个接口,自动给你生成实现类
 */
public class GenerateDaoProxy {
    public static Object generate(SqlSession sqlSession, Class<?> daoInterface) {
        // 获取类池
        ClassPool pool = ClassPool.getDefault();
        //制造类(com.powernode.bank.dao.AccountDao --> com.powernode.bank.dao.AccountDaoProxy)
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Proxy");
        //制造接口
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        //实现接口
        ctClass.addInterface(ctInterface);
        //制造方法-1实现接口中所有的方法
        Method[] methods = daoInterface.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            //method是接口中的抽象方法
            // 将method这个抽象方法进行实现
            try {
                // Account selectByActno(String actno);
                // public Account selectByActno(String actno){代码;}
                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");
                methodCode.append(method.getReturnType().getName());
                methodCode.append(" ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 拼参数列表
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getName());
                    methodCode.append(" ");
                    methodCode.append("arg" + i);
                    if (i != parameterTypes.length - 1) {
                        methodCode.append(",");
                    }
                }
                methodCode.append(")");
                methodCode.append("{");
                // 拼方法体代码
                methodCode.append("org.apache.ibatis.session.SqlSession sqlSession = bank.utils.SqlSessionUtil.openSession();");
                //  需要中的是什么类型的sql语句
                //  sql语句的id是框架的使用者提供的, 具有多变性, 对于我框架的开发人员来说. 我不知道
                //  既然我框架开发者不知道sqlId, 怎么办呢? mybatis框架的开发者于是出台一个规定: 凡是使用次框架机制的
                //  sqlId都不能随便写. namespace必须是dao接口的全限定名称.id必须是dao接口中的方法名
                String sqlId = daoInterface.getName() + "." + method.getName();
                SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
                if (sqlCommandType == SqlCommandType.INSERT) {

                }
                if (sqlCommandType == SqlCommandType.DELETE) {

                }
                if (sqlCommandType == SqlCommandType.UPDATE) {
                    methodCode.append("return sqlSession.update(\"" + sqlId + "\", arg0);");
                }
                if (sqlCommandType == SqlCommandType.SELECT) {
                    String returnType = method.getReturnType().getName();
                    methodCode.append("return (" + returnType + ")sqlSession.selectOne(\"" + sqlId + "\", arg0);");
                }
                methodCode.append("}");
                //制造方法-2
                String methodCode_Str = methodCode.toString();
                CtMethod ctMethod = CtMethod.make(methodCode_Str, ctClass);
                //将方法添加到类中
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        //在内存中生成类, 同时将生成的类加载到JVM中
        Object obj = null;
        try {
            Class<?> clazz = ctClass.toClass();
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
