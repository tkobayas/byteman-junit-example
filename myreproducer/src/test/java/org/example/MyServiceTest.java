package org.example;

import static org.junit.Assert.assertFalse;

import org.example.app.MyService;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.jboss.byteman.contrib.bmunit.BMUnitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(org.jboss.byteman.contrib.bmunit.BMUnitRunner.class)
@BMUnitConfig(loadDirectory = "target/test-classes", debug = true)
public class MyServiceTest {

    @Test
    @BMScript(value = "byteman/throwExceptionOnMyInternalComponent.btm")
    public void testLockLeak() {
        
        MyService myService = new MyService();
        String result = myService.work();
        
        //System.out.println(result);

        assertFalse(myService.isLocked());
    }
}
