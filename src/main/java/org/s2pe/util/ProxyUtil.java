package org.s2pe.util;

import javafx.scene.control.Tab;
import org.s2pe.dao.IndexDao;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 创建动态代理对象的工具类
 *
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/11 0011下午 14:56
 */
public class ProxyUtil {
    //    定义代理对象类存储路径的静态表里
    private static final String pathName = "d:\\org\\s2pe\\";

    //    定义代理对象报名的静态变量
    private static final String pathPackage = "org.s2pe";

    //    定义代理对象简单名称的静态变量
    private static final String proxySimpleName = "$proxy";

    //    定义代理对象全名的静态变量
    private static final String proxyName = pathPackage + "." + proxySimpleName;

    /**
     * 创建代理示例的工具方法
     *
     * @param target 传入目标对象
     * @return 返回代理对象
     */
    public static Object newInstance(Object target) {
//        定义返回的代理对象，默认为空值，在方法最后返回
        Object proxy = null;


        Class<?> targetInfClass = target.getClass ().getInterfaces ()[0];
//      创建、拼接代理类的字符串内容
        String content = createProxyContent (targetInfClass);


//      将代理类生成的字符串内容写到指定磁盘路径下
        File file = writeProxyContent (content);
//      将写到磁盘中发Java文件编译成class文件
        compileProxyContent (file);

        String proxyClassName = "";
//      将磁盘上的代理对象字节码文件加到内存中，获取动态代理对象
        proxy = getProxyInstance (target);


//        返回代理对象
        return proxy;
    }

    /**
     * 创建、拼接代理类的字符串内容
     *
     * @param targetInfClass 目标对象所在类的接口
     * @return 拼接后代理类的字符串内容和
     */
    private static String createProxyContent(Class<?> targetInfClass) {
//        定义分号常量
        final String SEMICOLON = ";";
//        定义换行常量
        final String NEXT_LINE = "\n";
//        定义左花括号常量
        final String BRACKET_LEFT = "{";
//        定义左右括号常量
        final String BRACKET_RIGHT = "}";
//        定义缩进字符常量
        final String TAB = "\t";
//      拼接Java文件包定义行 package org.s2pe.sta.polymerization;
        String packageLine = "package " + pathPackage + SEMICOLON + NEXT_LINE;
//      在代理类生成的过程中利用报名加类名的形式进行拼接，在这里就不同对导包的代码行进行拼接
//      import org.s2pe.dao.IndexDao;
//        获取目标对象接口名称
        String targetInfClassName = targetInfClass.getName ();
//      拼接代理对象类定义行 public class LogProxy  implements IndexDao {
        String classLine = "public class " + proxySimpleName + " implements " + targetInfClassName + BRACKET_LEFT + NEXT_LINE;
//        定义代理对象属性名
        String targetParamName = "target";
//      拼接定义代理对象属性行  private IndexDao indexDao;
        String defindTargetParamLine = TAB + "private " + targetInfClassName + " " + targetParamName + SEMICOLON + NEXT_LINE;
//        新建代理对象构造方法字符对象
        StringBuffer proxyConstructorStirngBuffer = new StringBuffer ("");
//        拼接代理对象构造方法首行字符串  public LogProxy(final IndexDao indexDao) {
        String proxyConstructorFirst = TAB + "public " + proxySimpleName + "(" + targetInfClassName + " " + targetParamName + ")" + BRACKET_LEFT + NEXT_LINE;
//          拼接代理对象构造方法内部字符串  this.indexDao = indexDao;
        String proxyConstructor = TAB + TAB + "this." + targetParamName + " = " + targetParamName + SEMICOLON + NEXT_LINE;
//        拼接代理对象构造方法尾行字符串 }
        String proxyConstructorEnd = TAB + BRACKET_RIGHT + NEXT_LINE;
//        拼接代理对象构造方法代码
        proxyConstructorStirngBuffer.append (proxyConstructorFirst + proxyConstructor + proxyConstructorEnd);
//        创建覆盖所有目标对象方法的代理对象方法代码字符串对象
        StringBuffer procyDeclaredMethods = new StringBuffer ();
//        获取目标对象接口的公共方法
        Method[] targetDeclaredMethods = targetInfClass.getDeclaredMethods ();
        for (final Method targetDeclaredMethod : targetDeclaredMethods) {
//            创建覆盖目标对象方法的代理对象方法代码字符串对象   @Override
            StringBuffer proxyDeclaredMethod = new StringBuffer (TAB + "@Override" + NEXT_LINE);
//            获取目标对象接口的公共方法的返回值类型
            Class<?> targetDeclaredMethodReturnType = targetDeclaredMethod.getReturnType ();
//            获取目标对象接口的公共方法的名称
            String targetDeclaredMethodName = targetDeclaredMethod.getName ();
//            获取目标对象接口的公共方法的所有参数类
            Class<?>[] targetDeclaredMethodParameterTypes = targetDeclaredMethod.getParameterTypes ();
//            创建覆盖目标对象方法的代理对象方法代码字第一行符串对象
            StringBuffer proxyDeclaredMethodFirstLine = new StringBuffer ();
//            拼接覆盖目标对象方法的代理对象方法代码字第一行前部分内容  public void query(
            proxyDeclaredMethodFirstLine.append (TAB + "public " + targetDeclaredMethodReturnType.getName () + " " + targetDeclaredMethodName + "(");
//            定义覆盖目标对象方法的代理对象方法传入参数前缀
            String targetDeclaredMethodParameterPreName = "arg";
//            判断覆盖目标对象方法的代理对象方法传入是否参数内容
            if (targetDeclaredMethodParameterTypes.length > 0) {
//                创建覆盖目标对象方法的代理对象方法传入参数字符串对象
                StringBuffer targetDeclaredMethodParameterStr = new StringBuffer ();
//                遍历目标对象接口的公共方法的所有参数类，拼接覆盖目标对象方法的代理对象方法传入参数字符串
                for (int i = 0; i < targetDeclaredMethodParameterTypes.length; i++) {
//                    拼接覆盖目标对象方法的代理对象方法传入参数内容
                    targetDeclaredMethodParameterStr.append (targetDeclaredMethodParameterTypes[i].getName () + " " + targetDeclaredMethodParameterPreName + i + ", ");
                }
//                截取覆盖目标对象方法的代理对象方法传入参数字符串多余的逗号
                targetDeclaredMethodParameterStr.deleteCharAt (targetDeclaredMethodParameterStr.lastIndexOf (","));
//                将覆盖目标对象方法的代理对象方法传入参数字符串添加到覆盖目标对象方法的代理对象方法代码字第一行
                proxyDeclaredMethodFirstLine.append (targetDeclaredMethodParameterStr);
            }
//            拼接覆盖目标对象方法的代理对象方法代码字第一行后部分内容  ) {
            proxyDeclaredMethodFirstLine.append (")" + BRACKET_LEFT + NEXT_LINE);
//            ********************此处编写代理方法的前置逻辑代码*******************************
//        System.out.println ("------Log-------");

//            创建覆盖目标对象方法的代理对象方法内容代码对象
            StringBuffer proxyDeclaredMethodMess = new StringBuffer (TAB + TAB);
//            指目标对象方法返回值名称
            String tergerMethodRetrunObjectName = "targetReturn";
//            获取目标队形返回返回值类名
            String targetDeclaredMethodReturnName = targetDeclaredMethod.getReturnType ().getName ();
//            判断目标对象方法是否有返回值
            if (!"void".equals (targetDeclaredMethodReturnName)){
//                添加接收目标方法代理返回值对象
                proxyDeclaredMethodMess.append ( targetDeclaredMethodReturnName + " " + tergerMethodRetrunObjectName + "=("+ targetDeclaredMethodReturnName +") ");
            }
//            定义覆盖目标对象方法的代理对象方法内容代码前部分字符串  indexDao.query (
            String proxyDeclaredMethodMessFirst = targetParamName+ "." + targetDeclaredMethodName + "(";
//            创建覆盖目标对象方法的代理对象方法内容代码参数部分字符串对象
            StringBuffer proxyDeclaredMethodMessPara = new StringBuffer ();
//            判断覆盖目标对象方法的代理对象方法是否有参数
            if (targetDeclaredMethodParameterTypes.length > 0) {
                for (int i = 0; i < targetDeclaredMethodParameterTypes.length; i++) {
//                    拼接覆盖目标对象方法的代理对象方法内容
                    proxyDeclaredMethodMessPara.append (targetDeclaredMethodParameterPreName + i + ", ");
                }
//                截取覆盖目标对象方法的代理对象方法内容多余的逗号
                proxyDeclaredMethodMessPara.deleteCharAt (proxyDeclaredMethodMessPara.lastIndexOf (","));
            }
//            定义覆盖目标对象方法的代理对象方法内容代码后部分字符串 );
            String proxyDeclaredMethodMessEnd = ")" + SEMICOLON + NEXT_LINE;
//            连接覆盖目标对象方法的代理对象方法内容代码字符串
            proxyDeclaredMethodMess.append (proxyDeclaredMethodMessFirst + proxyDeclaredMethodMessPara + proxyDeclaredMethodMessEnd);
//            ********************此处编写代理方法的后置逻辑代码*******************************
//        System.out.println ("-----Log End---");
//            判断目标对象方法是否有返回值
            if (!"void".equals (targetDeclaredMethodReturnName)){
//                添加返回值对象代码
                proxyDeclaredMethodMess.append (TAB + TAB + "return " + tergerMethodRetrunObjectName + SEMICOLON + NEXT_LINE);
            }
//            创建覆盖目标对象方法的代理对象方法代码字第一行符串对象   }
            String proxyDeclaredMethodEndLine = TAB + BRACKET_RIGHT + NEXT_LINE;
            proxyDeclaredMethod.append (proxyDeclaredMethodFirstLine).append (proxyDeclaredMethodMess + proxyDeclaredMethodEndLine);
//            将覆盖目标对象方法的代理对象方法代码字符串对象添加到所有覆盖目标对象方法的代理对象方法代码字符串对象中
            procyDeclaredMethods.append (proxyDeclaredMethod);
        }

//         拼接类最后一行代码  }
        String classLineEnd = BRACKET_RIGHT + NEXT_LINE;
//        定义代理类代码内容
        String content = "";
//        拼接代理类代码
        content = packageLine + classLine + defindTargetParamLine + proxyConstructorStirngBuffer + procyDeclaredMethods + classLineEnd;
//        返回代理类字符串内容
        return content;
    }

    /**
     * 将代理类生成的字符串内容写到指定磁盘路径下
     * @param proxyContent 代理类生成的字符串内容
     * @return 生成的磁盘文件对象
     */
    private static File writeProxyContent(String proxyContent) {
//        创建问几点对象
        File prosyFile = new File (pathName + proxySimpleName + ".java");
        try {
            if (prosyFile.exists ()) {
                prosyFile.createNewFile ();
            }
            FileWriter writer = new FileWriter (prosyFile);
            writer.write (proxyContent);
            writer.flush ();
            writer.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return prosyFile;
    }

    /**
     * 将写到磁盘中发Java文件编译成class文件
     *
     * @param proxyContent 生成的磁盘文件对象
     */
    private static void compileProxyContent(File proxyContent) {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler ();
            StandardJavaFileManager standardFileManager = compiler.getStandardFileManager (null, null, null);
            Iterable<? extends JavaFileObject> untis = standardFileManager.getJavaFileObjects (proxyContent);
            JavaCompiler.CompilationTask task = compiler.getTask (null, standardFileManager, null, null, null, untis);
            task.call ();
            standardFileManager.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }

    /**
     * 将磁盘上的代理对象字节码文件加到内存中，获取动态代理对象
     *
     * @param target         目标对象
     * @return 代理对象
     */
    private static Object getProxyInstance(Object target) {
        Object proxyInctance = null;
        try {
            URL[] urls = {new URL ("file:D:\\\\")};
            URLClassLoader classLoader = new URLClassLoader (urls);
            Class<?> clazz = classLoader.loadClass (proxyName);
            Constructor<?> constructor = clazz.getConstructor (target.getClass ().getInterfaces ()[0]);
            proxyInctance = constructor.newInstance (target);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return proxyInctance;
    }

}
