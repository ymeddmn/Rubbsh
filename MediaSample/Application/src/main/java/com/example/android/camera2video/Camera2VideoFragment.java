/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2video;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.CameraProfile;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaCodec;
import android.media.MediaRecorder;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.huawei.emui.himedia.camera.HwCamera;

import com.huawei.emui.himedia.camera.HwCameraCaptureSession;
import com.huawei.emui.himedia.camera.HwCameraDevice;
import com.huawei.emui.himedia.camera.HwCameraEngineDieRecipient;
import com.huawei.emui.himedia.camera.HwCameraEngineUtils;
import com.huawei.emui.himedia.camera.HwCameraInitSuccessCallback;
import com.huawei.emui.himedia.camera.HwCameraManager;
import com.huawei.emui.himedia.camera.HwCameraManagerImpl;
import com.huawei.emui.himedia.camera.HwCameraMetadata;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


@RequiresApi(api = Build.VERSION_CODES.M)
public class Camera2VideoFragment extends Fragment
        implements View.OnClickListener, View.OnTouchListener, FragmentCompat.OnRequestPermissionsResultCallback {

    private static final int SENSOR_ORIENTATION_DEFAULT_DEGREES = 90;
    private static final int SENSOR_ORIENTATION_INVERSE_DEGREES = 270;
    private static final SparseIntArray DEFAULT_ORIENTATIONS = new SparseIntArray();
    private static final SparseIntArray INVERSE_ORIENTATIONS = new SparseIntArray();

    private static final String TAG = "Camera2VideoFragment";
    private static final int REQUEST_VIDEO_PERMISSIONS = 1;
    private static final String FRAGMENT_DIALOG = "dialog";

    private static final String[] VIDEO_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
    };

    static {
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_0, 90);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_90, 0);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_180, 270);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    static {
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_0, 270);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_90, 180);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_180, 90);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_270, 0);
    }

    /**
     * An {@link AutoFitTextureView} for camera preview.
     */
    private AutoFitTextureView mTextureView;

    /**
     * Button to record video
     */
    private Button mButtonVideo, mButtionSuperSlow, mBtnFlash;
    private TextView mFpsTextView;
    private int mCurrentCameraFacing = CameraCharacteristics.LENS_FACING_BACK;
    private String[] mCameraIdList;
    private HwCameraManager manager;

    /**
     * A reference to the opened {@link android.hardware.camera2.CameraDevice}.
     */
    private HwCameraDevice mCameraDevice;

    /**
     * A reference to the current {@link android.hardware.camera2.CameraCaptureSession} for
     * preview.
     */
    private HwCameraCaptureSession mPreviewSession;
    private CameraConstrainedHighSpeedCaptureSession mPreviewHighSpeedSession;
    private ConditionVariable mSlowMotionReadyCondition = new ConditionVariable();

    /**
     * {@link TextureView.SurfaceTextureListener} handles several lifecycle events on a
     * {@link TextureView}.
     */
    private TextureView.SurfaceTextureListener mSurfaceTextureListener
            = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture,
                                              int width, int height) {
            openCamera(width, height);
            Log.d(TAG, "onSurfaceTextureAvailable: raw texture_width = " + width + " raw texture height = " + height);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture,
                                                int width, int height) {
            configureTransform(width, height);
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            //Bitmap bitmap = mTextureView.getBitmap();
            /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] datas = baos.toByteArray();
            datas.hashCode();*/
            Log.d(TAG, "onSurfaceTextureUpdated: hashcode = ");
        }

    };

    /**
     * The {@link android.util.Size} of camera preview.
     */
    private Size mPreviewSize;

    /**
     * The {@link android.util.Size} of video recording.
     */
    private Size mVideoSize;

    /**
     * MediaRecorder
     */
    private MediaRecorder mMediaRecorder;

    /**
     * mImageReader
     */
    private ImageReader mImageReader;
    /**
     * Whether the app is recording video now
     */
    private boolean mIsRecordingVideo;

    /**
     * An additional thread for running tasks that shouldn't block the UI.
     */
    private HandlerThread mBackgroundThread;
    private HandlerThread mSaveThread;

    /**
     * A {@link Handler} for running tasks in the background.
     */
    private Handler mBackgroundHandler;
    private Handler mSaveHandler;
    /**
     * Save Image From Stream
     */
    private File mFile;

    private Surface mPreviewSurface = null;

    Surface mMediaCodecSurface;
    private boolean mDoneFlag, mFinishFlag = false;


    /**
     * A {@link Semaphore} to prevent the app from exiting before closing the camera.
     */
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);

    /**
     * {@link CameraDevice.StateCallback} is called when {@link CameraDevice} changes its status.
     */
    private HwCameraDevice.StateCallback mStateCallback = new HwCameraDevice.StateCallback() {

        @Override
        public void onOpened(HwCameraDevice hwCameraDevice) {
            Utils.tickLog("OpenCameraCallback");
            mCameraDevice = hwCameraDevice;
            try {
                startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCameraOpenCloseLock.release();
            if (null != mTextureView) {
                configureTransform(mTextureView.getWidth(), mTextureView.getHeight());
            }
        }

        @Override
        public void onError(HwCameraDevice hwCameraDevice, int i) {

        }

        @Override
        public void onClosed(HwCameraDevice camera) {
            super.onClosed(camera);
        }

        @Override
        public void onDisconnected(HwCameraDevice hwCameraDevice) {

        }
    };
    private Integer mSensorOrientation;
    private String mNextVideoAbsolutePath;
    private CaptureRequest.Builder mPreviewBuilder, mCaputreBuilder;

    public static Camera2VideoFragment newInstance() {
        return new Camera2VideoFragment();
    }

    private void showToast(final String text) {
        final Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * In this sample, we choose a video size with 3x4 aspect ratio. Also, we don't use sizes
     * larger than 1080p, since MediaRecorder cannot handle such a high-resolution video.
     *
     * @param choices The list of available sizes
     * @return The video size
     */
    private static Size chooseVideoSize(Size[] choices) {
        for (Size size : choices) {
            Log.d(TAG, "chooseVideoSize: width = " + size.getWidth() + " height = " + size.getHeight());
            if (size.getWidth() == size.getHeight() * 4 / 3 && size.getWidth() <= 1080) {
                return size;
            }
        }
        Log.e(TAG, "Couldn't find any suitable video size");
        return choices[choices.length - 1];
    }

    private static Size chooseHighSpeedVideoSize(Size[] choices) {
        Size biggestSize = choices[0];
        for (Size size : choices) {
             if (size.getWidth() > biggestSize.getWidth()) {
                 biggestSize = size;
             }
            Log.d(TAG, "chooseHighSpeedVideoSize: width = " + size.getWidth() + " height = " + size.getHeight());
        }
        Log.d(TAG, "chooseHighSpeedVideoSize: width = " + biggestSize.getWidth() + " height = " + biggestSize.getHeight());
        return biggestSize;
    }
    /**
     * Given {@code choices} of {@code Size}s supported by a camera, chooses the smallest one whose
     * width and height are at least as large as the respective requested values, and whose aspect
     * ratio matches with the specified value.
     *
     * @param choices     The list of sizes that the camera supports for the intended output class
     * @param width       The minimum desired width
     * @param height      The minimum desired height
     * @param aspectRatio The aspect ratio
     * @return The optimal {@code Size}, or an arbitrary one if none were big enough
     */
    private static Size chooseOptimalSize(Size[] choices, int width, int height, Size aspectRatio) {
        // Collect the supported resolutions that are at least as big as the preview Surface
        List<Size> bigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices) {
            Log.d(TAG, "chooseOptimalSize: width = " + option.getWidth() + " height = " + option.getHeight());
            if (option.getHeight() == option.getWidth() * h / w &&
                    option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }

        // Pick the smallest of those, assuming we found any
        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizesByArea());
        } else {
            Log.e(TAG, "Couldn't find any suitable preview size");
            return choices[0];
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera2_video, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        mTextureView = (AutoFitTextureView) view.findViewById(R.id.texture);
        mButtonVideo = (Button) view.findViewById(R.id.video);
        mButtionSuperSlow = (Button)view.findViewById(R.id.superslowmotion);
        mButtionSuperSlow.setOnClickListener(this);
        mButtonVideo.setOnClickListener(this);
        mFpsTextView = (TextView)view.findViewById(R.id.fpstext);
        mBtnFlash = (Button)view.findViewById(R.id.flash);
        mBtnFlash.setOnClickListener(this);

        view.findViewById(R.id.info).setOnClickListener(this);
        view.findViewById(R.id.texture).setOnClickListener(this);
        view.findViewById(R.id.changeCamera).setOnClickListener(this);
        mTextureView.setOnTouchListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFileFor = getActivity().getExternalFilesDir(null);
        mFile = new File(getActivity().getExternalFilesDir(null), System.currentTimeMillis() +".mp4");
    }

    @Override
    public void onResume() {
        super.onResume();
        startBackgroundThread();
        if (mTextureView.isAvailable()) {
            openCamera(mTextureView.getWidth(), mTextureView.getHeight());
            Log.d(TAG, "onResume: raw texture_width = " + mTextureView.getWidth() + " raw texture height = " + mTextureView.getHeight());
        } else {
            Log.d(TAG, "onResume: Texture not available");
            mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
        }
    }

    @Override
    public void onPause() {
        closeCamera();
        stopBackgroundThread();
        super.onPause();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.info: {
                Activity activity = getActivity();
                if (null != activity) {
                    new AlertDialog.Builder(activity)
                            .setMessage(R.string.intro_message)
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }
                break;
            }
            case R.id.superslowmotion: {
                if (mIsRecordingVideo == false) {
                    try {
                        mDoneFlag = mFinishFlag = true;
                        takeImageStream();
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case R.id.changeCamera: {
                int nextCameraId = Utils.getOtherCameraId(mCurrentCameraFacing);
                for (int i = 0; i < mCameraIdList.length; i++) {
                    try {
                        CameraCharacteristics characteristics = manager.getCameraCharacteristics(mCameraIdList[i]);
                        if (nextCameraId == characteristics.get(CameraCharacteristics.LENS_FACING)) {
                            Byte ret = manager.isFeatureSupported(i, HwCameraMetadata.CharacteristicKey.HUAWEI_IS_60FPS_SUPPORTED);
                            if (ret != 1) {
                                showToast("60fps not supported");
                                return;
                            }
                            break;
                        }
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
                mCurrentCameraFacing = nextCameraId;
                closeCamera();
                openCamera(mPreviewSize.getWidth(), mPreviewSize.getWidth());
                break;
            }
            case R.id.flash: {

                mPreviewBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_ALWAYS_FLASH);
                mPreviewBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_TORCH);

                try {
                    mPreviewSession.abortCaptures();
                    mPreviewSession.stopRepeating();
                    mPreviewSession.capture(mPreviewBuilder.build(), new HwCameraCaptureSession.CaptureCallback() {
                        @Override
                        public void onCaptureCompleted(HwCameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
//                            try {
//                                startPreview();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
                        }
                    }, mBackgroundHandler);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private int clamp(int x, int min, int max) {
        if (x > max) {
            return max;
        }
        if (x < min) {
            return min;
        }
        return x;
    }

    private void focusOnTouch(double x, double y, float coefficient) {
        float focusAreaSize = 200;
        int areaSize = Float.valueOf(focusAreaSize * coefficient).intValue();
        Log.i("calculateTapArea", "areaSize---> " + areaSize);//300
        Log.i("calculateTapArea", "x---> " + x + ",,,y---> " + y);//对的
        int displayRotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();


        int realWidth = mPreviewSize.getWidth();
        int realHeight = mPreviewSize.getHeight();
        if (Surface.ROTATION_90 == displayRotation || Surface.ROTATION_270 == displayRotation) {
            realWidth = mPreviewSize.getHeight();
            realHeight = mPreviewSize.getWidth();
        }
        double imageScale = 1.0, verticalOffset = 0.0, horizontalOffset = 0.0, tmp;
        int viewWidth = mTextureView.getWidth();
        int viewHeight = mTextureView.getHeight();

        if (realHeight * viewWidth > realWidth * viewHeight) {
            imageScale = viewWidth * 1.0 / realWidth;
            verticalOffset = (realHeight - viewHeight / imageScale) / 2;
        } else {
            imageScale = viewHeight * 1.0 / realHeight;
            horizontalOffset = (realWidth - viewWidth / imageScale) / 2;
        }

        x = x / imageScale + horizontalOffset;
        y = y / imageScale + verticalOffset;
        if (Surface.ROTATION_90 == displayRotation) {
            tmp = x; x = y; y = mPreviewSize.getHeight() - tmp;
        } else if (Surface.ROTATION_270 == displayRotation) {
            tmp = x; x = mPreviewSize.getWidth() - y; y = tmp;
        }

        CaptureRequest cr = mPreviewBuilder.build();
        Rect cropRegion = cr.get(CaptureRequest.SCALER_CROP_REGION);
        if (null == cropRegion) {
            Log.e(TAG, "can't get crop region");
            cropRegion = characteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        }
        Log.d("calculateTapArea", "crop region " + "left " + cropRegion.left + " right " + cropRegion.right + " top " + cropRegion.top + " bottom " + cropRegion.bottom);

        int cropWidth = cropRegion.width(), cropHeight = cropRegion.height();
        if (mPreviewSize.getHeight() * cropWidth > mPreviewSize.getWidth() * cropHeight) {
            imageScale = cropHeight * 1.0 / mPreviewSize.getHeight();
            verticalOffset = 0;
            horizontalOffset = (cropWidth - imageScale * mPreviewSize.getWidth()) / 2;
        } else {
            imageScale = cropWidth * 1.0 / mPreviewSize.getWidth();
            horizontalOffset = 0;
            verticalOffset = (cropHeight - imageScale * mPreviewSize.getHeight()) / 2;
        }

        x = x * imageScale + horizontalOffset + cropRegion.left;
        y = y * imageScale + verticalOffset + cropRegion.top;

        double tapAreaRatio = 0.1;
        Rect rect = new Rect();
        rect.left = clamp((int) (x - tapAreaRatio / 2 * cropRegion.width()), 0, cropRegion.width());
        rect.right = clamp((int) (x + tapAreaRatio / 2 * cropRegion.width()), 0, cropRegion.width());
        rect.top = clamp((int) (y - tapAreaRatio / 2 * cropRegion.height()), 0, cropRegion.height());
        rect.bottom = clamp((int) (y + tapAreaRatio / 2 * cropRegion.height()), 0, cropRegion.height());


        Log.d("calculateTapArea", "tap region " + "left " + rect.left + " right " + rect.right + " top " + rect.top + " bottom " + rect.bottom);

        mPreviewBuilder.set(CaptureRequest.CONTROL_AF_REGIONS, new MeteringRectangle[] {new MeteringRectangle(rect, 1000)});
        mPreviewBuilder.set(CaptureRequest.CONTROL_AE_REGIONS, new MeteringRectangle[] {new MeteringRectangle(rect, 1000)});
        mPreviewBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_AUTO);
        mPreviewBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_START);
        mPreviewBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, CameraMetadata.CONTROL_AE_PRECAPTURE_TRIGGER_START);


        try {
            mPreviewSession.setRepeatingRequest(mPreviewBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            Log.e(TAG, "setRepeatingRequest failed, " + e.getMessage());
        }

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN : {
                float touchX = event.getX();
                float touchY = event.getY();
                focusOnTouch(touchX, touchY, 1);
                break;
            }
        }
        return true;
    }
    private int getOrientation(int rotation) {
        // Sensor orientation is 90 for most devices, or 270 for some devices (eg. Nexus 5X)
        // We have to take that into account and rotate JPEG properly.
        // For devices with orientation of 90, we simply return our mapping from ORIENTATIONS.
        // For devices with orientation of 270, we need to rotate the JPEG 180 degrees.
        return (DEFAULT_ORIENTATIONS.get(rotation) + mSensorOrientation + 270) % 360;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    private void takeImageStream() throws CameraAccessException {

        mPreviewBuilder.addTarget(mImageReader.getSurface());
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        mPreviewBuilder.set(CaptureRequest.JPEG_ORIENTATION, DEFAULT_ORIENTATIONS.get(rotation));

        capture(captureCallBack, mPreviewBuilder.build());
    }
    /**
     * Starts a background thread and its {@link Handler}.
     */
    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
        mSaveThread = new HandlerThread("ImageSave");
        mSaveThread.start();
        mSaveHandler = new Handler(mSaveThread.getLooper());
    }

    /**
     * Stops the background thread and its {@link Handler}.
     */
    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets whether you should show UI with rationale for requesting permissions.
     *
     * @param permissions The permissions your app wants to request.
     * @return Whether you can show permission rationale UI.
     */
    private boolean shouldShowRequestPermissionRationale(String[] permissions) {
        for (String permission : permissions) {
            if (FragmentCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Requests permissions needed for recording video.
     */
    private void requestVideoPermissions() {
        if (shouldShowRequestPermissionRationale(VIDEO_PERMISSIONS)) {
            new ConfirmationDialog().show(getChildFragmentManager(), FRAGMENT_DIALOG);
        } else {
            FragmentCompat.requestPermissions(this, VIDEO_PERMISSIONS, REQUEST_VIDEO_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult");
        if (requestCode == REQUEST_VIDEO_PERMISSIONS) {
            if (grantResults.length == VIDEO_PERMISSIONS.length) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        ErrorDialog.newInstance(getString(R.string.permission_request))
                                .show(getChildFragmentManager(), FRAGMENT_DIALOG);
                        break;
                    }
                }
            } else {
                ErrorDialog.newInstance(getString(R.string.permission_request))
                        .show(getChildFragmentManager(), FRAGMENT_DIALOG);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private boolean hasPermissionsGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(getActivity(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    class EngineDieCallback implements HwCameraEngineDieRecipient {

        @Override
        public void onEngineDie() {
            showToast("engieng die");
            closeCamera();
            mPreviewSession = null;
        }
    }
    /**
     * Tries to open a {@link CameraDevice}. The result is listened by `mStateCallback`.
     */
    @SuppressWarnings("MissingPermission")
    HwCamera hwCamera;
    CameraCharacteristics characteristics;
    private void openCamera(int width, int height) {
        if (!hasPermissionsGranted(VIDEO_PERMISSIONS)) {
            requestVideoPermissions();
            return;
        }
        final Activity activity = getActivity();
        if (null == activity || activity.isFinishing()) {
            return;
        }
        Log.d(TAG, "openCamera: SDK: " + Build.VERSION.SDK_INT);
        Log.d(TAG, "openCamera: brand: " + android.os.Build.BRAND);
        Log.d(TAG, "openCamera: model: " + android.os.Build.MODEL);
        byte retcode = HwCamera.isDeviceSupported(getActivity());
        if (retcode != HwCamera.HWCAMERA_SDK_AVAILABLE) {

            switch(retcode) {
                case HwCamera.HWCAMERA_DEVICE_NOT_SUPPORTED:
                    showToast("Device not support.");
                    break;
                case HwCamera.HWCAMERA_PLATFORM_NOT_SUPPORTED:
                    showToast("Camera Engine not match.");
                    break;
                case HwCamera.HWCAMERA_VENDOR_NOT_SUPPORTED:
                    showToast("Not a HUAWEI Device.");
                    break;
                default:
            }
            return;
        }
        hwCamera = new HwCamera();
        hwCamera.setInitSuccessCallback(new HwCameraInitSuccessCallback() {
            @Override
            public void onInitSuccess() {

                hwCamera.setHwCameraEngineDieCallBack(new EngineDieCallback());
                hwCamera.getHwCameraManager();
                manager =  hwCamera.getHwCameraManager();


                try {
                    Log.d(TAG, "tryAcquire");
                    if (!mCameraOpenCloseLock.tryAcquire(2500, TimeUnit.MILLISECONDS)) {
                        throw new RuntimeException("Time out waiting to lock camera opening.");
                    }
                    String cameraId;
                    int cameraIndex = -1;
                    mCameraIdList = manager.getCameraIdList();

                    //get backCameraId
                    for (int i = 0; i < mCameraIdList.length; i++) {
                        characteristics = manager.getCameraCharacteristics(mCameraIdList[i]);
                        if (mCurrentCameraFacing == characteristics.get(CameraCharacteristics.LENS_FACING)) {
                            Byte ret = manager.isFeatureSupported(i, HwCameraMetadata.CharacteristicKey.HUAWEI_IS_60FPS_SUPPORTED);
                            if (ret != 1) {
                                showToast("60fps not supported");
                                return;
                            }
                            cameraIndex = i;
                            break;
                        }
                    }
                    assert (characteristics != null && cameraIndex != -1);
                    // Choose the sizes for camera preview and video recording
                    characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    cameraId = mCameraIdList[cameraIndex];
                    StreamConfigurationMap map = characteristics
                            .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

                    characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
                    Log.d(TAG, "camera faceing : " + characteristics.get(CameraCharacteristics.LENS_FACING));
                    mSensorOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                    if (map == null) {
                        throw new RuntimeException("Cannot get available preview/video sizes");
                    }

                    mVideoSize = chooseHighSpeedVideoSize(map.getHighSpeedVideoSizes());
                    //mVideoSize = new Size(1280, 720);
                    mPreviewSize = mVideoSize;

                    int orientation = getResources().getConfiguration().orientation;
                    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        mTextureView.setAspectRatio(mPreviewSize.getWidth(), mPreviewSize.getHeight());
                    } else {
                        mTextureView.setAspectRatio(mPreviewSize.getHeight(), mPreviewSize.getWidth());
                    }
                    Utils.tickLog("opencamera");
                    manager.openCamera(cameraId, mStateCallback, null, HwCameraManager.VIDEO_FPS_60_MODE);
                } catch (CameraAccessException e) {
                    Toast.makeText(activity, "Cannot access the camera.", Toast.LENGTH_SHORT).show();
                    activity.finish();
                } catch (NullPointerException e) {
                    // Currently an NPE is thrown when the Camera2API is used but not supported on the
                    // device this code runs.
                    ErrorDialog.newInstance(getString(R.string.camera_error))
                            .show(getChildFragmentManager(), FRAGMENT_DIALOG);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Interrupted while trying to lock camera opening.");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        hwCamera.initialize(getActivity());
    }

    private void closeCamera() {
        try {
            mCameraOpenCloseLock.acquire();
            closePreviewSession();
            if (null != mCameraDevice) {
                Utils.tickLog("closecamera start");
                mCameraDevice.close();
                Utils.tickLog("closecamera end");
                mCameraDevice = null;
            }
            if (hwCamera != null) {
                hwCamera.deInitialize();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock camera closing.");
        } finally {
            mCameraOpenCloseLock.release();
        }
    }

    /**
     * Start the camera preview.
     */

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startPreview() throws IOException,RemoteException {
        if (null == mCameraDevice || !mTextureView.isAvailable() || null == mPreviewSize) {
            return;
        }
        try {
            closePreviewSession();

            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());

            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//            mPreviewBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON);
//            //mPreviewBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_ALWAYS_FLASH);
//            //mPreviewBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_TORCH);
//            mPreviewBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_OFF);
//            mPreviewBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);

            setUpOutPutStream();
            mPreviewSurface = new Surface(texture);
            mPreviewBuilder.addTarget(mPreviewSurface);
            List<Surface> surfaces = new ArrayList<>();
            surfaces.add(mPreviewSurface);
            surfaces.add(mImageReader.getSurface());
            //mPreviewBuilder.addTarget(imageReaderSurface);
            Utils.tickLog("startPreview createCaptureSession");
            mCameraDevice.createCaptureSession(surfaces,
                    new HwCameraCaptureSession.StateCallback() {
                        @Override
                        public void onConfigured(HwCameraCaptureSession hwCameraCaptureSession) {
                            Log.d(TAG, "onConfigured: createConstrainedHighSpeedCaptureSession callback");
                            mPreviewSession = hwCameraCaptureSession;
                            try {
                                updatePreview();
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(HwCameraCaptureSession hwCameraCaptureSession) {

                        }
                    }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    private void capture(HwCameraCaptureSession.CaptureCallback callback, CaptureRequest request) throws CameraAccessException {

        mPreviewSession.setRepeatingRequest(request, callback, mBackgroundHandler);
    }
    long currentTimeReserve = 0;
    int fpsCount = 0;
    HwCameraCaptureSession.CaptureCallback captureCallBack = new HwCameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(HwCameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            Log.d(TAG, "onCaptureCompleted: 60fps test");
            if (firstComplete == true) {
                firstComplete = false;
                Utils.tickLog("first complete 60fps");
            }
            if (currentTimeReserve == 0) {
                currentTimeReserve = System.currentTimeMillis();
            }
            else if (fpsCount % 15 == 0){
                long currentMillis = System.currentTimeMillis();
                long  captureInterval = currentMillis - currentTimeReserve;
                if (captureInterval != 0) {
                    double fpsRate = 1000.0*15/(captureInterval);
                    final String strFpaRate = String.format("%6.2f", fpsRate);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mFpsTextView.setText("Streamfps = " + strFpaRate);
                        }
                    });
                }
                currentTimeReserve = currentMillis;
            }
            fpsCount++;
        }

        @Override
        public void onCaptureStarted(HwCameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
            super.onCaptureStarted(session, request, timestamp, frameNumber);
            if (firstCapture == true) {
                firstCapture = false;
                Utils.tickLog("first capture 60fps");
            }
        }
    };


    /**
     * Update the camera preview. {@link #startPreview()} needs to be called in advance.
     */
    boolean firstCapture = true;
    boolean firstComplete = true;
    private void updatePreview() throws CameraAccessException, IOException {
        if (null == mCameraDevice) {
            Log.d(TAG, "updatePreview: mCameraDevice null!");
            return;
        }
        Log.d(TAG, "updatePreview: ");
        Utils.tickLog("capture 60fps");
        try {
            capture(captureCallBack, mPreviewBuilder.build());
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


    }

    /**
     * Configures the necessary {@link android.graphics.Matrix} transformation to `mTextureView`.
     * This method should not to be called until the camera preview size is determined in
     * openCamera, or until the size of `mTextureView` is fixed.
     *
     * @param viewWidth  The width of `mTextureView`
     * @param viewHeight The height of `mTextureView`
     */
    private void configureTransform(int viewWidth, int viewHeight) {
        Activity activity = getActivity();
        if (null == mTextureView || null == mPreviewSize || null == activity) {
            return;
        }
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        Matrix matrix = new Matrix();
        RectF viewRect = new RectF(0, 0, viewWidth, viewHeight);
        RectF bufferRect = new RectF(0, 0, mPreviewSize.getHeight(), mPreviewSize.getWidth());
        float centerX = viewRect.centerX();
        float centerY = viewRect.centerY();
        if (Surface.ROTATION_90 == rotation || Surface.ROTATION_270 == rotation) {
            bufferRect.offset(centerX - bufferRect.centerX(), centerY - bufferRect.centerY());
            matrix.setRectToRect(viewRect, bufferRect, Matrix.ScaleToFit.FILL);
            float scale = Math.max(
                    (float) viewHeight / mPreviewSize.getHeight(),
                    (float) viewWidth / mPreviewSize.getWidth());
            matrix.postScale(scale, scale, centerX, centerY);
            matrix.postRotate(90 * (rotation - 2), centerX, centerY);
        }
        mTextureView.setTransform(matrix);
    }


    private String getVideoFilePath(Context context) {
        final File dir = context.getExternalFilesDir(null);
        return (dir == null ? "" : (dir.getAbsolutePath() + "/"));
    }


    private void closePreviewSession() {
        if (mPreviewSession != null) {
            Utils.tickLog("close session start");
            mPreviewSession.close();
            Utils.tickLog("close session end");
            mPreviewSession = null;
        }

    }

    /**
     * Compares two {@code Size}s based on their areas.
     */
    static class CompareSizesByArea implements Comparator<Size> {

        @Override
        public int compare(Size lhs, Size rhs) {
            // We cast here to ensure the multiplications won't overflow
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                    (long) rhs.getWidth() * rhs.getHeight());
        }

    }

    public static class ErrorDialog extends DialogFragment {

        private static final String ARG_MESSAGE = "message";

        public static ErrorDialog newInstance(String message) {
            ErrorDialog dialog = new ErrorDialog();
            Bundle args = new Bundle();
            args.putString(ARG_MESSAGE, message);
            dialog.setArguments(args);
            return dialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Activity activity = getActivity();
            return new AlertDialog.Builder(activity)
                    .setMessage(getArguments().getString(ARG_MESSAGE))
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.finish();
                        }
                    })
                    .create();
        }

    }

    public static class ConfirmationDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Fragment parent = getParentFragment();
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.permission_request)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FragmentCompat.requestPermissions(parent, VIDEO_PERMISSIONS,
                                    REQUEST_VIDEO_PERMISSIONS);
                        }
                    })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    parent.getActivity().finish();
                                }
                            })
                    .create();
        }

    }

    private int currentNum = 0;

    private void setUpOutPutStream() {
        mImageReader = ImageReader.newInstance(mPreviewSize.getWidth(), mPreviewSize.getHeight(), ImageFormat.YUV_420_888, 4);
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {

                Log.d(TAG, "setUpOutPutStream: Recording");
                if (currentNum % 360 == 0) {
                    Image image = reader.acquireNextImage();
                    Log.d(TAG, "stream image: width = " + image.getWidth() + " height = " + image.getHeight());
                    Image.Plane[] planes = image.getPlanes();
                    if (ImageFormat.YUV_420_888 == image.getFormat() && 3 == planes.length) {
                        mSaveHandler.post(new ImageSaver(image));

                    }
                } else {
                    Image image1 = reader.acquireNextImage();
                    Log.d(TAG, "stream image1: width = " + image1.getWidth() + " height = " + image1.getHeight());
                    Image.Plane[] planes = image1.getPlanes();
                    image1.close();
                }
                currentNum++;
            }
        }, mBackgroundHandler);
    }

    private static File mFileFor;
    private static class ImageSaver implements Runnable {

        /**
         * The JPEG image
         */
        private  final Image mImage;
        /**
         * The file we save the image into.
         */
        private final File mFile;

        private final int mSize;
        private int mWidth, mHeight;

        ImageSaver(Image image) {
            mImage = image;
            mFile =  new File(mFileFor, System.currentTimeMillis() +"pic.jpg");;
            mWidth = image.getWidth();
            mHeight = image.getHeight();
            Log.e(TAG, "mFile:" + mFile + " mWidth:" + mWidth + " mHeight:" + mHeight);
            mSize = mWidth * mHeight;
        }

        @Override
        public void run() {
            Log.e(TAG, "ImageSaver save");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ByteBuffer bufferY = mImage.getPlanes()[0].getBuffer();
            byte[] data0 = new byte[bufferY.remaining()];
            bufferY.get(data0);

            ByteBuffer bufferU = mImage.getPlanes()[1].getBuffer();
            byte[] data1 = new byte[bufferU.remaining()];
            bufferU.get(data1);

            ByteBuffer bufferV = mImage.getPlanes()[2].getBuffer();
            byte[] data2 = new byte[bufferV.remaining()];
            bufferV.get(data2);
            Log.e(TAG, "bufferY:" + bufferY.remaining() + " bufferU:" + bufferU.remaining() + " bufferV:" + bufferV.remaining());
            Log.e(TAG, "bufferY:" + data0.length + " bufferU:" + data1.length + " bufferV:" + data2.length);
            try {
                outputStream.write(data0);
                outputStream.write(data1);
                outputStream.write(data2);

            } catch (IOException e) {
                e.printStackTrace();
            }

            final YuvImage yuvImage = new YuvImage(outputStream.toByteArray(), ImageFormat.NV21, mWidth,mHeight, null);
            ByteArrayOutputStream outBitmap = new ByteArrayOutputStream();

            yuvImage.compressToJpeg(new Rect(0, 0,mWidth, mHeight), 95, outBitmap);

            FileOutputStream outputfile = null;
            try {
                outputfile = new FileOutputStream(mFile);
                outputfile.write(outBitmap.toByteArray());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                mImage.close();
                Log.e(TAG, "compressToJpeg END");
            }
        }

    }
}
