package exam;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApplicationContext {
    // 1. 싱글턴 패턴 적용 - 자기 자신을 참조하는 static 필드를 선언한다. 바로 초기화
    private static ApplicationContext instance = new ApplicationContext();

    // 3. 1에서 만든 인스턴스를 반환하는 static 메소드를 만든다.
    public static ApplicationContext getInstance(){
        return instance;
    }

    private Properties props;
    private Map objectMap;

    // 2. 싱들텀 패턴 적용 - 생성자를 private로 변경
    private ApplicationContext(){
        props = new Properties();
        objectMap = new HashMap<String, Object>();
        try {
            props.load(new FileInputStream("src/main/resources/Beans.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object getBean(String id) throws Exception {
        Object o1 = objectMap.get(id);
        if (o1 == null){
            String className = props.getProperty(id);
            // class name에 해당하는 문자열을 가지고 인스턴스를 생성할 수 있다.
            // Class.forName(className)은 CLASSPATH로부터 className에 해당하는 클래스를 찾은 후 그 클래스 정보를 반환한다
            Class clazz = Class.forName(className);

            Method[] methods = clazz.getMethods();
            for(Method m : methods){
                System.out.println(m.getName());
            }

            // ClassLoader를 이용한 인스턴스 생성, 기본 생성자가 있어야 함
            Object o = clazz.newInstance(); // clazz정보를 이용해 인스턴스를 생성
            objectMap.put(id, o);
            o1 = objectMap.get(id);
        }
        return o1;
    }
}
