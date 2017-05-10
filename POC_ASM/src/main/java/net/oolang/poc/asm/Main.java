package net.oolang.poc.asm;

//import jdk.internal.org.objectweb.asm.ClassVisitor;
import java.lang.reflect.InvocationTargetException;
import org.objectweb.asm.*;
import static org.objectweb.asm.Opcodes.*;

public class Main {

    private static class OwnClassLoader extends ClassLoader {

        public Class<?> defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }

    private static Class<?> createClass(byte[] b) {
        return new OwnClassLoader().defineClass("pkg.OwnClass", b);
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

        AST exp = new Print(new Add(new Add(new Number(7), new Number(8)), new Number(2)));

        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;
        cw.visit(V1_5, ACC_PUBLIC, "pkg/OwnClass", null, "java/lang/Object", null);
        {
            // constructor
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitMaxs(2, 1);
            mv.visitVarInsn(ALOAD, 0); // push `this` to the operand stack
            mv.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", "()V", false); // call the constructor of super class
            mv.visitInsn(RETURN);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);

            exp.compile(new CompilationContext(mv));

            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("args", "[Ljava/lang/String;", null, l0, l1, 0);
            mv.visitMaxs(exp.evalMaxStack(0), exp.evalMaxLocalVariables(0));
            mv.visitEnd();
        }
        cw.visitEnd();

        System.out.println("max stack: " + exp.evalMaxStack(0));
        System.out.println("max local variable: " + exp.evalMaxLocalVariables(0));

        Class<?> cls = createClass(cw.toByteArray());
        Object o = cls.newInstance();


        cls.getMethod("main", String[].class).invoke(null, (Object) null);
    }
}
