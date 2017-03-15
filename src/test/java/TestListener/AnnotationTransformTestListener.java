package TestListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformTestListener implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		System.out.println("Transformer running for " + testMethod.getName());
		System.out.println("listener called");
		if (testMethod.getName().equals("testnglogin")) {
			annotation.setEnabled(false);
			System.out.println("listener executed");
		}
	}
}
