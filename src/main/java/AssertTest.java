public class AssertTest {


    public static void main(String[] args) {
        AssertTest at = new AssertTest();
        at.assertMe(true);
        at.assertMe(false);

    }

    private void assertMe(boolean boo) {
        assert boo ? true : false;
        System.out.println("true condition");
    }

}