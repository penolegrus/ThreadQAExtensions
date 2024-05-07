package thread.qa.callbacs;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class TestSaver implements AfterTestExecutionCallback, AfterAllCallback {

    private static final Set<String> failedTests = new HashSet<>();

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        String output = System.getProperty("user.dir") + "/src/test/resources/FailedTests.txt";
        Path out = Paths.get(output);
        String result = String.join(" ", failedTests);
        Files.writeString(out, result);
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        String className = extensionContext.getRequiredTestClass().getName();
        Method method = extensionContext.getRequiredTestMethod();
        String methodName = method.getName();
        String testToWrite = String.format("--tests %s.%s*", className, methodName);
        extensionContext.getExecutionException().ifPresent(x -> failedTests.add(testToWrite));
    }


}
