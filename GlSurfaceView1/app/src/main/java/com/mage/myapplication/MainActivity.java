package com.mage.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.io.IOException;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {
    private SurfaceTexture surfaceTexture;

    private Camera mCamera;
    GLSurfaceView.Renderer renderer = new GLSurfaceView.Renderer(){


        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glDisable(GL10.GL_DITHER);
            GLES20.glClearColor(0, 0, 0, 0);
            int mOESTextureId = OpenGLUtils.getExternalOESTextureID();
            surfaceTexture = new SurfaceTexture(mOESTextureId);
            surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
                @Override
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    surface.requestRender();
                }
            });

            // For camera preview on activity creation
            if (mCamera != null) {
                try {
                    mCamera.setPreviewTexture(surfaceTexture);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
            System.out.println("宽高：onSurfaceChange:"+width+":==="+height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            surfaceTexture.updateTexImage();
            surfaceTexture.getTransformMatrix(mtx);


            GLES20.glClearColor(0, 0, 0, 0);
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            surfaceTexture.updateTexImage();//更新纹理成为最新的数据
            surfaceTexture.getTransformMatrix(mtx);

            GLES20.glViewport(0, 0, width, height);//确定渲染的起始坐标，和宽高

            GLES20.glUseProgram(mProgramId);//使用着色器程序

            mGLVertexBuffer.position(0);
            GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 0, mGLVertexBuffer);//设置顶点数据
            GLES20.glEnableVertexAttribArray(vPosition);

            mGLTextureBuffer.position(0);
            GLES20.glVertexAttribPointer(vCoord, 2, GLES20.GL_FLOAT, false, 0, mGLTextureBuffer); //采样目标顶点坐标
            GLES20.glEnableVertexAttribArray(vCoord);

            //变换矩阵
            GLES20.glUniformMatrix4fv(vMatrix, 1, false, mtx, 0);

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0); //激活图层
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTexture[0]);//绑定采样器采样的目标纹理
            GLES20.glUniform1i(vTexture, 0);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4); //开始渲染

            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        }
    };
    private GLSurfaceView surface;
    private float[] mtx = new float[16];
    private int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        surface = findViewById(R.id.surface);
        startCamera();
        surface.setRenderer(renderer);
    }

    protected Camera openCamera() {
        int mCamId=0;
        Camera camera = null;
        if (mCamId < 0) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            int numCameras = Camera.getNumberOfCameras();
            int frontCamId = -1;
            int backCamId = -1;
            for (int i = 0; i < numCameras; i++) {
                Camera.getCameraInfo(i, info);
                if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    backCamId = i;
                } else if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    frontCamId = i;
                    break;
                }
            }
            if (frontCamId != -1) {
                mCamId = frontCamId;
            } else if (backCamId != -1) {
                mCamId = backCamId;
            } else {
                mCamId = 0;
            }
        }

        try {
            camera = Camera.open(mCamId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return camera;
    }
    public boolean startCamera() {
        if (mCamera == null) {
            mCamera = openCamera();
            if (mCamera == null) {
                return false;
            }
        }

        //params.setPictureSize(mPreviewWidth, mPreviewHeight);
        Camera.Parameters params = mCamera.getParameters();
        params.setPreviewSize(width, height);
//        Camera.Parameters params = setPreviewSizeCustom();
        params.setPreviewFormat(ImageFormat.NV21);
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        params.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
        params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
        params.setRecordingHint(true);

        List<String> supportedFocusModes = params.getSupportedFocusModes();
        if (supportedFocusModes != null && !supportedFocusModes.isEmpty()) {
            if (supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            } else if (supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                mCamera.autoFocus(null);
            } else {
                params.setFocusMode(supportedFocusModes.get(0));
            }
        }

        List<String> supportedFlashModes = params.getSupportedFlashModes();
        if (supportedFlashModes != null && !supportedFlashModes.isEmpty()) {
            if (supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
//                if (mIsTorchOn) {
//                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//                }
            } else {
                params.setFlashMode(supportedFlashModes.get(0));
            }
        }

        mCamera.setParameters(params);

//        mCamera.setDisplayOrientation(mPreviewRotation);

        try {
            mCamera.setPreviewTexture(surfaceTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();

        return true;
    }
}
