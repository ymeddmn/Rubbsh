package com.example.plugin;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LifecycleOnCreateMethodVisitor extends MethodVisitor {

    public LifecycleOnCreateMethodVisitor(int i, MethodVisitor methodVisitor) {
        super(Opcodes.ASM4, methodVisitor);
    }


    @Override
    public void visitCode() {
        super.visitCode();
        //方法执行前插入
//        mv.visitLdcInsn("TAG");
//        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
//        mv.visitInsn(Opcodes.DUP);
//        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
//        mv.visitLdcInsn("-------> onCreate : ");
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//        mv.visitVarInsn(Opcodes.ALOAD, 0);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
//        mv.visitInsn(Opcodes.POP);

        mv.visitLdcInsn("sdfsdf");
        mv.visitVarInsn(Opcodes.ISTORE,3);
        mv.visitLdcInsn("sssss");
        mv.visitVarInsn(Opcodes.ISTORE,4);
        mv.visitVarInsn(Opcodes.ALOAD,3);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/example/igradleplugin/S", "e", "(Ljava/lang/String;)V", false);

    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var);
    }

    @Override
    public void visitInsn(int i) {
        super.visitInsn(i);
    }
}
