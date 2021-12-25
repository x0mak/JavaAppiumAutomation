import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("метод getLocalNumber не возвращает число 14", 14, MainClass.getLocalNumber());
    }


}
