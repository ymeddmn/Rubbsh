package org.example

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import com.example.plugin.LifeCycleClassWriter
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES


class Visitor extends Transform implements Plugin<Project> {

    @Override
    void apply(Project project) {
//        def android = project.extensions.getByType(AppExtension)
        def android = project.extensions.getByType(AppExtension.class)
        //对插件进行注册，添加插桩入口
        android.registerTransform(this)
    }

    @Override
    String getName() {
        return "visitor"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) {
        Collection<TransformInput> inputs = transformInvocation.inputs
        TransformOutputProvider provider = transformInvocation.outputProvider
        if (provider != null)
            provider.deleteAll()
        inputs.each { TransformInput input ->
            input.directoryInputs.each {
                DirectoryInput directoryInput ->
                    handDirectoyInput(directoryInput, provider)

            }
            //遍历jarInputs
            input.jarInputs.each { JarInput jarInput ->
                handleJarInputs(jarInput, provider)
            }
        }
    }

    static void handDirectoyInput(DirectoryInput directoryInput, TransformOutputProvider provider) {
        if (directoryInput.file.isDirectory()) {
            directoryInput.file.eachFileRecurse {File file->
                def name = file.name
                if(isClassCanInsert(name)){
                    if(isActivity(name)){
                        println("--------deal class--------" +name+"------------")
                        ClassReader classReader = new ClassReader(file.bytes)
                        ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS)
                        ClassVisitor cv = new LifeCycleClassWriter(0,classWriter)
                        classReader.accept(cv,ClassReader.EXPAND_FRAMES)
                        byte [] code = classWriter.toByteArray()
                        FileOutputStream fileOutputStream = new FileOutputStream(file.parentFile.absolutePath+File.separator+name)
                        fileOutputStream.write(code)
                        fileOutputStream.close()
                    }
                }
            }
        }
        //处理完输入文件之后，要把输出给下一个任务
        def dest = provider.getContentLocation(directoryInput.name,
                directoryInput.contentTypes, directoryInput.scopes,
                Format.DIRECTORY)
        FileUtils.copyDirectory(directoryInput.file, dest)
    }
    /**
     * 处理Jar中的class文件
     */
    static void handleJarInputs(JarInput jarInput, TransformOutputProvider outputProvider) {
        if (jarInput.file.getAbsolutePath().endsWith(".jar")) {
            //重名名输出文件,因为可能同名,会覆盖
            def jarName = jarInput.name
            def md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
            if (jarName.endsWith(".jar")) {
                jarName = jarName.substring(0, jarName.length() - 4)
            }
            JarFile jarFile = new JarFile(jarInput.file)
            Enumeration enumeration = jarFile.entries()
            File tmpFile = new File(jarInput.file.getParent() + File.separator + "classes_temp.jar")
            //避免上次的缓存被重复插入
            if (tmpFile.exists()) {
                tmpFile.delete()
            }
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(tmpFile))
            //用于保存
            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement()
                String entryName = jarEntry.getName()
                ZipEntry zipEntry = new ZipEntry(entryName)
                InputStream inputStream = jarFile.getInputStream(jarEntry)
                //插桩class
                if (isClassCanInsert(entryName)) {

                    if(isActivity(entryName)){
                        //class文件处理
                        println '----------- deal with "jar" class file <' + entryName + '> -----------'
                        jarOutputStream.putNextEntry(zipEntry)
                        ClassReader classReader = new ClassReader(IOUtils.toByteArray(inputStream))
                        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                        ClassVisitor cv = new LifeCycleClassWriter(0,classWriter)
                        classReader.accept(cv, EXPAND_FRAMES)
                        byte[] code = classWriter.toByteArray()
                        jarOutputStream.write(code)
                    }

                } else {
                    jarOutputStream.putNextEntry(zipEntry)
                    jarOutputStream.write(IOUtils.toByteArray(inputStream))
                }
                jarOutputStream.closeEntry()
            }
            //结束
            jarOutputStream.close()
            jarFile.close()
            def dest = outputProvider.getContentLocation(jarName + md5Name,
                    jarInput.contentTypes, jarInput.scopes, Format.JAR)
            FileUtils.copyFile(tmpFile, dest)
            tmpFile.delete()
        }
    }
//    static boolean checkClassName(String name){
//        println("文件名：-------"+name+"-------------")
////        return (name.endsWith(".class") && !name.startsWith("R\$")
////                && !"R.class".equals(name) && !"BuildConfig.class".equals(name)
////                && "android/support/v4/app/FragmentActivity.class".equals(name))
//        return (name.endsWith(".class")
//                && "android/support/v4/app/FragmentActivity.class".equals(name))
//    }

    static boolean isActivity(String name){
        return "android/support/v4/app/FragmentActivity.class".equals(name)
    }
    static boolean isClassCanInsert(String name){
        return (name.endsWith(".class") && !name.startsWith("R\$")
                && !"R.class".equals(name) && !"BuildConfig.class".equals(name))
    }
}















