package com.example.plugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LifeCycleClassWriter extends ClassVisitor implements Opcodes {

    private String className;

    public LifeCycleClassWriter(int i, ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }


    @Override
    public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
        this.className= s;
        super.visit(i, i1, s, s1, s2, strings);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
        if("android/support/v4/app/FragmentActivity".equals(this.className)){
            if("onCreate".equals(name)){
                //处理onCreate
                System.out.println("LifecycleClassVisitor : change method ----> " + name);
                return new LifecycleOnCreateMethodVisitor(0,mv);
            }else if ("onDestroy".equals(name)) {
                //处理onDestroy
                System.out.println("LifecycleClassVisitor : change method ----> " + name);
                return new LifecycleOnDestroyMethodVisitor(mv);
            }
        }
        System.out.println("方法名为：----"+this.className+"--------"+name+"------");

        return mv;
    }
//      if("android/view/View/OnClickListener.class".equals(entryName)){
//        println("点击事件------------------")
//    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
