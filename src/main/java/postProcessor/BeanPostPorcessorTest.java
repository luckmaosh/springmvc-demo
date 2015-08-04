package postProcessor;


import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanPostPorcessorTest extends TestCase {
    private ApplicationContext context;

    protected void setUp() throws Exception {
        super.setUp();
        String[] paths = {"classpath*:/springConfig/applicationContext-*.xml"};

        context = new ClassPathXmlApplicationContext(paths);

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBeanPostProcessor() {

    }

    public void testBeanFactoryPostProcessor() {
        //PostProcessorBean bean =(PostProcessorBean)ServiceLocator.getService("postProcessorBean");
        PostProcessorBean bean = (PostProcessorBean) context.getBean("postProcessorBean");
        System.out.println("---------------testBeanFactoryPostProcessor----------------");
        System.out.println("username:" + bean.getUsername());
        System.out.println("password:" + bean.getPassword());
        //
    }
}
