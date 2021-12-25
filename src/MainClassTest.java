import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Метод getLocalNumber не возвращает число 14", 14, MainClass.getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("Метод getClassNumber меньше либо равен 45", MainClass.getClassNumber() > 45);
    }

}
