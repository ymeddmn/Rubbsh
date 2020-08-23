.class public Lcom/sitech/paas/decrypt/ProxyApplication;
.super Landroid/app/Application;
.source "ProxyApplication.java"


# instance fields
.field private TAG:Ljava/lang/String;

.field private appName:Ljava/lang/String;

.field private app_name:Ljava/lang/String;

.field private app_version:Ljava/lang/String;

.field delegate:Landroid/app/Application;

.field isBindReal:Z

.field private md5Actural:Ljava/lang/String;

.field private md5Real:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 29
    invoke-direct {p0}, Landroid/app/Application;-><init>()V

    .line 35
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/sitech/paas/decrypt/ProxyApplication;->TAG:Ljava/lang/String;

    return-void
.end method

.method static synthetic access$000(Lcom/sitech/paas/decrypt/ProxyApplication;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/sitech/paas/decrypt/ProxyApplication;

    .prologue
    .line 29
    iget-object v0, p0, Lcom/sitech/paas/decrypt/ProxyApplication;->md5Real:Ljava/lang/String;

    return-object v0
.end method

.method static synthetic access$100(Lcom/sitech/paas/decrypt/ProxyApplication;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/sitech/paas/decrypt/ProxyApplication;

    .prologue
    .line 29
    iget-object v0, p0, Lcom/sitech/paas/decrypt/ProxyApplication;->md5Actural:Ljava/lang/String;

    return-object v0
.end method

.method private bindRealApplicatin()V
    .locals 19

    .prologue
    .line 261
    const-string v13, "android.app.ActivityThread"

    const-string v14, "currentActivityThread"

    const/4 v15, 0x0

    new-array v15, v15, [Ljava/lang/Class;

    const/16 v16, 0x0

    move/from16 v0, v16

    new-array v0, v0, [Ljava/lang/Object;

    move-object/from16 v16, v0

    invoke-static/range {v13 .. v16}, Lcom/sitech/paas/decrypt/RefInvoke;->invokeStaticMethod(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    .line 264
    .local v4, "currentActivityThread":Ljava/lang/Object;
    const-string v13, "android.app.ActivityThread"

    const-string v14, "mBoundApplication"

    invoke-static {v13, v4, v14}, Lcom/sitech/paas/decrypt/RefInvoke;->getFieldOjbect(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    .line 267
    .local v9, "mBoundApplication":Ljava/lang/Object;
    const-string v13, "android.app.ActivityThread$AppBindData"

    const-string v14, "info"

    invoke-static {v13, v9, v14}, Lcom/sitech/paas/decrypt/RefInvoke;->getFieldOjbect(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    .line 271
    .local v6, "loadedApkInfo":Ljava/lang/Object;
    const-string v13, "android.app.LoadedApk"

    const-string v14, "mApplication"

    const/4 v15, 0x0

    invoke-static {v13, v14, v6, v15}, Lcom/sitech/paas/decrypt/RefInvoke;->setFieldOjbect(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    .line 275
    const-string v13, "android.app.ActivityThread"

    const-string v14, "mInitialApplication"

    invoke-static {v13, v4, v14}, Lcom/sitech/paas/decrypt/RefInvoke;->getFieldOjbect(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v11

    .line 280
    .local v11, "oldApplication":Ljava/lang/Object;
    const-string v13, "android.app.ActivityThread"

    const-string v14, "mAllApplications"

    .line 281
    invoke-static {v13, v4, v14}, Lcom/sitech/paas/decrypt/RefInvoke;->getFieldOjbect(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/util/ArrayList;

    .line 283
    .local v8, "mAllApplications":Ljava/util/ArrayList;, "Ljava/util/ArrayList<Landroid/app/Application;>;"
    invoke-virtual {v8, v11}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 285
    const-string v13, "android.app.LoadedApk"

    const-string v14, "mApplicationInfo"

    .line 286
    invoke-static {v13, v6, v14}, Lcom/sitech/paas/decrypt/RefInvoke;->getFieldOjbect(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/content/pm/ApplicationInfo;

    .line 289
    .local v3, "appinfo_In_LoadedApk":Landroid/content/pm/ApplicationInfo;
    const-string v13, "android.app.ActivityThread$AppBindData"

    const-string v14, "appInfo"

    .line 290
    invoke-static {v13, v9, v14}, Lcom/sitech/paas/decrypt/RefInvoke;->getFieldOjbect(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/content/pm/ApplicationInfo;

    .line 293
    .local v2, "appinfo_In_AppBindData":Landroid/content/pm/ApplicationInfo;
    move-object/from16 v0, p0

    iget-object v13, v0, Lcom/sitech/paas/decrypt/ProxyApplication;->appName:Ljava/lang/String;

    iput-object v13, v3, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    .line 294
    move-object/from16 v0, p0

    iget-object v13, v0, Lcom/sitech/paas/decrypt/ProxyApplication;->appName:Ljava/lang/String;

    iput-object v13, v2, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    .line 295
    new-instance v13, Ljava/lang/StringBuilder;

    invoke-direct {v13}, Ljava/lang/StringBuilder;-><init>()V

    const-string v14, "\u7c7b\u540d\u4e3a\uff1a"

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v13

    move-object/from16 v0, p0

    iget-object v14, v0, Lcom/sitech/paas/decrypt/ProxyApplication;->appName:Ljava/lang/String;

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v13

    invoke-virtual {v13}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v13

    invoke-static {v13}, Lcom/sitech/paas/decrypt/CLog;->e(Ljava/lang/String;)V

    .line 296
    new-instance v13, Ljava/lang/StringBuilder;

    invoke-direct {v13}, Ljava/lang/StringBuilder;-><init>()V

    const-string v14, "\u771f\u5b9e\u7684application\u540d\u5b57\u4e3a\uff1a"

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v13

    iget-object v14, v3, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v13

    const-string v14, "-----"

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v13

    iget-object v14, v2, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v13

    invoke-virtual {v13}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v13

    invoke-static {v13}, Lcom/sitech/paas/decrypt/CLog;->e(Ljava/lang/String;)V

    .line 298
    const-string v13, "android.app.LoadedApk"

    const-string v14, "makeApplication"

    const/4 v15, 0x2

    new-array v15, v15, [Ljava/lang/Class;

    const/16 v16, 0x0

    sget-object v17, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    aput-object v17, v15, v16

    const/16 v16, 0x1

    const-class v17, Landroid/app/Instrumentation;

    aput-object v17, v15, v16

    const/16 v16, 0x2

    move/from16 v0, v16

    new-array v0, v0, [Ljava/lang/Object;

    move-object/from16 v16, v0

    const/16 v17, 0x0

    const/16 v18, 0x0

    .line 301
    invoke-static/range {v18 .. v18}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v18

    aput-object v18, v16, v17

    const/16 v17, 0x1

    const/16 v18, 0x0

    aput-object v18, v16, v17

    .line 298
    move-object/from16 v0, v16

    invoke-static {v13, v14, v6, v15, v0}, Lcom/sitech/paas/decrypt/RefInvoke;->invokeMethod(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/app/Application;

    .line 304
    .local v1, "app":Landroid/app/Application;
    const-string v13, "android.app.ActivityThread"

    const-string v14, "mInitialApplication"

    invoke-static {v13, v14, v4, v1}, Lcom/sitech/paas/decrypt/RefInvoke;->setFieldOjbect(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    .line 307
    const-string v13, "android.app.ActivityThread"

    const-string v14, "mProviderMap"

    invoke-static {v13, v4, v14}, Lcom/sitech/paas/decrypt/RefInvoke;->getFieldOjbect(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Landroid/util/ArrayMap;

    .line 311
    .local v10, "mProviderMap":Landroid/util/ArrayMap;
    invoke-virtual {v10}, Landroid/util/ArrayMap;->values()Ljava/util/Collection;

    move-result-object v13

    invoke-interface {v13}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v5

    .line 312
    .local v5, "it":Ljava/util/Iterator;
    :goto_0
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v13

    if-eqz v13, :cond_0

    .line 313
    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v12

    .line 314
    .local v12, "providerClientRecord":Ljava/lang/Object;
    const-string v13, "android.app.ActivityThread$ProviderClientRecord"

    const-string v14, "mLocalProvider"

    invoke-static {v13, v12, v14}, Lcom/sitech/paas/decrypt/RefInvoke;->getFieldOjbect(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v7

    .line 317
    .local v7, "localProvider":Ljava/lang/Object;
    const-string v13, "android.content.ContentProvider"

    const-string v14, "mContext"

    invoke-static {v13, v14, v7, v1}, Lcom/sitech/paas/decrypt/RefInvoke;->setFieldOjbect(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_0

    .line 321
    .end local v7    # "localProvider":Ljava/lang/Object;
    .end local v12    # "providerClientRecord":Ljava/lang/Object;
    :cond_0
    move-object/from16 v0, p0

    iget-object v13, v0, Lcom/sitech/paas/decrypt/ProxyApplication;->TAG:Ljava/lang/String;

    new-instance v14, Ljava/lang/StringBuilder;

    invoke-direct {v14}, Ljava/lang/StringBuilder;-><init>()V

    const-string v15, "Srcapp:"

    invoke-virtual {v14, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v14

    invoke-virtual {v14, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v14

    invoke-virtual {v14}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v14

    invoke-static {v13, v14}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 323
    invoke-virtual {v1}, Landroid/app/Application;->onCreate()V

    .line 327
    return-void
.end method

.method private getMD5()Ljava/lang/String;
    .locals 4

    .prologue
    .line 350
    invoke-virtual {p0}, Lcom/sitech/paas/decrypt/ProxyApplication;->getPackageName()Ljava/lang/String;

    move-result-object v3

    invoke-direct {p0, p0, v3}, Lcom/sitech/paas/decrypt/ProxyApplication;->getRawSignature(Landroid/content/Context;Ljava/lang/String;)[Landroid/content/pm/Signature;

    move-result-object v2

    .line 352
    .local v2, "rawSignature":[Landroid/content/pm/Signature;
    const/4 v1, 0x0

    .line 354
    .local v1, "localMessageDigest":Ljava/security/MessageDigest;
    :try_start_0
    const-string v3, "MD5"

    invoke-static {v3}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;
    :try_end_0
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v1

    .line 358
    :goto_0
    const/4 v3, 0x0

    aget-object v3, v2, v3

    invoke-virtual {v3}, Landroid/content/pm/Signature;->toByteArray()[B

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/security/MessageDigest;->update([B)V

    .line 359
    invoke-virtual {v1}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v3

    invoke-virtual {p0, v3}, Lcom/sitech/paas/decrypt/ProxyApplication;->toHexString([B)Ljava/lang/String;

    move-result-object v3

    return-object v3

    .line 355
    :catch_0
    move-exception v0

    .line 356
    .local v0, "e":Ljava/security/NoSuchAlgorithmException;
    invoke-virtual {v0}, Ljava/security/NoSuchAlgorithmException;->printStackTrace()V

    goto :goto_0
.end method

.method private getMetaData()V
    .locals 6

    .prologue
    .line 147
    :try_start_0
    invoke-virtual {p0}, Lcom/sitech/paas/decrypt/ProxyApplication;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v3

    .line 148
    invoke-virtual {p0}, Lcom/sitech/paas/decrypt/ProxyApplication;->getPackageName()Ljava/lang/String;

    move-result-object v4

    const/16 v5, 0x80

    .line 147
    invoke-virtual {v3, v4, v5}, Landroid/content/pm/PackageManager;->getApplicationInfo(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;

    move-result-object v0

    .line 149
    .local v0, "applicationInfo":Landroid/content/pm/ApplicationInfo;
    iget-object v2, v0, Landroid/content/pm/ApplicationInfo;->metaData:Landroid/os/Bundle;

    .line 150
    .local v2, "metaData":Landroid/os/Bundle;
    if-eqz v2, :cond_1

    .line 151
    const-string v3, "app_name"

    invoke-virtual {v2, v3}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 152
    const-string v3, "app_name"

    invoke-virtual {v2, v3}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p0, Lcom/sitech/paas/decrypt/ProxyApplication;->app_name:Ljava/lang/String;

    .line 154
    :cond_0
    const-string v3, "app_version"

    invoke-virtual {v2, v3}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 155
    const-string v3, "app_version"

    invoke-virtual {v2, v3}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p0, Lcom/sitech/paas/decrypt/ProxyApplication;->app_version:Ljava/lang/String;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 162
    .end local v0    # "applicationInfo":Landroid/content/pm/ApplicationInfo;
    .end local v2    # "metaData":Landroid/os/Bundle;
    :cond_1
    :goto_0
    return-void

    .line 159
    :catch_0
    move-exception v1

    .line 160
    .local v1, "e":Ljava/lang/Exception;
    invoke-virtual {v1}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_0
.end method

.method private getRawSignature(Landroid/content/Context;Ljava/lang/String;)[Landroid/content/pm/Signature;
    .locals 5
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "packageName"    # Ljava/lang/String;

    .prologue
    const/4 v2, 0x0

    .line 363
    if-eqz p2, :cond_0

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result v3

    if-nez v3, :cond_1

    .line 375
    :cond_0
    :goto_0
    return-object v2

    .line 367
    :cond_1
    :try_start_0
    invoke-virtual {p1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v3

    const/16 v4, 0x40

    invoke-virtual {v3, p2, v4}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v1

    .line 368
    .local v1, "info":Landroid/content/pm/PackageInfo;
    if-eqz v1, :cond_0

    .line 369
    iget-object v2, v1, Landroid/content/pm/PackageInfo;->signatures:[Landroid/content/pm/Signature;
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 373
    .end local v1    # "info":Landroid/content/pm/PackageInfo;
    :catch_0
    move-exception v0

    .line 375
    .local v0, "e":Landroid/content/pm/PackageManager$NameNotFoundException;
    goto :goto_0
.end method

.method private loadDex(Ljava/util/List;Ljava/io/File;)V
    .locals 12
    .param p2, "versionDir"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List",
            "<",
            "Ljava/io/File;",
            ">;",
            "Ljava/io/File;",
            ")V"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 125
    .local p1, "dexFiles":Ljava/util/List;, "Ljava/util/List<Ljava/io/File;>;"
    invoke-virtual {p0}, Lcom/sitech/paas/decrypt/ProxyApplication;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v8

    const-string v9, "pathList"

    invoke-static {v8, v9}, Lcom/sitech/paas/decrypt/ProxyUtils;->findField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v6

    .line 126
    .local v6, "pathListField":Ljava/lang/reflect/Field;
    invoke-virtual {p0}, Lcom/sitech/paas/decrypt/ProxyApplication;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v8

    invoke-virtual {v6, v8}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    .line 128
    .local v5, "pathList":Ljava/lang/Object;
    const-string v8, "dexElements"

    invoke-static {v5, v8}, Lcom/sitech/paas/decrypt/ProxyUtils;->findField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v2

    .line 129
    .local v2, "dexElementsField":Ljava/lang/reflect/Field;
    invoke-virtual {v2, v5}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, [Ljava/lang/Object;

    move-object v1, v8

    check-cast v1, [Ljava/lang/Object;

    .line 131
    .local v1, "dexElements":[Ljava/lang/Object;
    const-string v8, "makePathElements"

    const/4 v9, 0x3

    new-array v9, v9, [Ljava/lang/Class;

    const/4 v10, 0x0

    const-class v11, Ljava/util/List;

    aput-object v11, v9, v10

    const/4 v10, 0x1

    const-class v11, Ljava/io/File;

    aput-object v11, v9, v10

    const/4 v10, 0x2

    const-class v11, Ljava/util/List;

    aput-object v11, v9, v10

    invoke-static {v5, v8, v9}, Lcom/sitech/paas/decrypt/ProxyUtils;->findMethod(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v3

    .line 133
    .local v3, "makeDexElements":Ljava/lang/reflect/Method;
    new-instance v7, Ljava/util/ArrayList;

    invoke-direct {v7}, Ljava/util/ArrayList;-><init>()V

    .line 134
    .local v7, "suppressedExceptions":Ljava/util/ArrayList;, "Ljava/util/ArrayList<Ljava/io/IOException;>;"
    const/4 v8, 0x3

    new-array v8, v8, [Ljava/lang/Object;

    const/4 v9, 0x0

    aput-object p1, v8, v9

    const/4 v9, 0x1

    aput-object p2, v8, v9

    const/4 v9, 0x2

    aput-object v7, v8, v9

    invoke-virtual {v3, v5, v8}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, [Ljava/lang/Object;

    move-object v0, v8

    check-cast v0, [Ljava/lang/Object;

    .line 137
    .local v0, "addElements":[Ljava/lang/Object;
    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/Class;->getComponentType()Ljava/lang/Class;

    move-result-object v8

    array-length v9, v1

    array-length v10, v0

    add-int/2addr v9, v10

    invoke-static {v8, v9}, Ljava/lang/reflect/Array;->newInstance(Ljava/lang/Class;I)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, [Ljava/lang/Object;

    move-object v4, v8

    check-cast v4, [Ljava/lang/Object;

    .line 138
    .local v4, "newElements":[Ljava/lang/Object;
    const/4 v8, 0x0

    const/4 v9, 0x0

    array-length v10, v1

    invoke-static {v1, v8, v4, v9, v10}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 139
    const/4 v8, 0x0

    array-length v9, v1

    array-length v10, v0

    invoke-static {v0, v8, v4, v9, v10}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 142
    invoke-virtual {v2, v5, v4}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 143
    return-void
.end method


# virtual methods
.method protected attachBaseContext(Landroid/content/Context;)V
    .locals 22
    .param p1, "base"    # Landroid/content/Context;

    .prologue
    .line 46
    invoke-super/range {p0 .. p1}, Landroid/app/Application;->attachBaseContext(Landroid/content/Context;)V

    .line 47
    invoke-static {}, Lcom/sitech/paas/decrypt/CrashHandler;->getInstance()Lcom/sitech/paas/decrypt/CrashHandler;

    move-result-object v17

    move-object/from16 v0, v17

    move-object/from16 v1, p1

    invoke-virtual {v0, v1}, Lcom/sitech/paas/decrypt/CrashHandler;->init(Landroid/content/Context;)Lcom/sitech/paas/decrypt/CrashHandler;

    .line 49
    invoke-direct/range {p0 .. p0}, Lcom/sitech/paas/decrypt/ProxyApplication;->getMetaData()V

    .line 53
    new-instance v3, Ljava/io/File;

    invoke-virtual/range {p0 .. p0}, Lcom/sitech/paas/decrypt/ProxyApplication;->getApplicationInfo()Landroid/content/pm/ApplicationInfo;

    move-result-object v17

    move-object/from16 v0, v17

    iget-object v0, v0, Landroid/content/pm/ApplicationInfo;->sourceDir:Ljava/lang/String;

    move-object/from16 v17, v0

    move-object/from16 v0, v17

    invoke-direct {v3, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 56
    .local v3, "apkFile":Ljava/io/File;
    const-string v17, "yangkun"

    const/16 v18, 0x0

    move-object/from16 v0, p0

    move-object/from16 v1, v17

    move/from16 v2, v18

    invoke-virtual {v0, v1, v2}, Lcom/sitech/paas/decrypt/ProxyApplication;->getDir(Ljava/lang/String;I)Ljava/io/File;

    move-result-object v16

    .line 57
    .local v16, "versionDir":Ljava/io/File;
    new-instance v4, Ljava/io/File;

    const-string v17, "app"

    move-object/from16 v0, v16

    move-object/from16 v1, v17

    invoke-direct {v4, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 58
    .local v4, "appDir":Ljava/io/File;
    new-instance v7, Ljava/io/File;

    const-string v17, "dexDir"

    move-object/from16 v0, v17

    invoke-direct {v7, v4, v0}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 61
    .local v7, "dexDir":Ljava/io/File;
    new-instance v8, Ljava/util/ArrayList;

    invoke-direct {v8}, Ljava/util/ArrayList;-><init>()V

    .line 63
    .local v8, "dexFiles":Ljava/util/List;, "Ljava/util/List<Ljava/io/File;>;"
    invoke-virtual {v7}, Ljava/io/File;->exists()Z

    move-result v17

    if-eqz v17, :cond_0

    invoke-virtual {v7}, Ljava/io/File;->list()[Ljava/lang/String;

    move-result-object v17

    move-object/from16 v0, v17

    array-length v0, v0

    move/from16 v17, v0

    if-nez v17, :cond_5

    .line 65
    :cond_0
    invoke-static {v3, v4}, Lcom/sitech/paas/decrypt/Zip;->unZip(Ljava/io/File;Ljava/io/File;)V

    .line 67
    invoke-virtual {v4}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v11

    .line 68
    .local v11, "files":[Ljava/io/File;
    array-length v0, v11

    move/from16 v18, v0

    const/16 v17, 0x0

    :goto_0
    move/from16 v0, v17

    move/from16 v1, v18

    if-ge v0, v1, :cond_6

    aget-object v10, v11, v17

    .line 69
    .local v10, "file":Ljava/io/File;
    invoke-virtual {v10}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v14

    .line 70
    .local v14, "name":Ljava/lang/String;
    const-string v19, ".dex"

    move-object/from16 v0, v19

    invoke-virtual {v14, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v19

    if-eqz v19, :cond_2

    const-string v19, "classes.dex"

    move-object/from16 v0, v19

    invoke-static {v14, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v19

    if-nez v19, :cond_2

    .line 73
    :try_start_0
    invoke-static {v10}, Lcom/sitech/paas/decrypt/ProxyUtils;->getBytes(Ljava/io/File;)[B

    move-result-object v5

    .line 75
    .local v5, "bytes":[B
    sget-object v19, Lcom/sitech/paas/decrypt/EncryptUtil;->ivBytes:[B

    move-object/from16 v0, v19

    invoke-static {v5, v0}, Lcom/sitech/paas/decrypt/EncryptUtil;->decrypt([B[B)[B

    move-result-object v6

    .line 77
    .local v6, "decrypt":[B
    new-instance v12, Ljava/io/FileOutputStream;

    invoke-direct {v12, v10}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    .line 78
    .local v12, "fos":Ljava/io/FileOutputStream;
    invoke-virtual {v12, v6}, Ljava/io/FileOutputStream;->write([B)V

    .line 79
    invoke-virtual {v12}, Ljava/io/FileOutputStream;->flush()V

    .line 80
    invoke-virtual {v12}, Ljava/io/FileOutputStream;->close()V

    .line 81
    invoke-interface {v8, v10}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 68
    .end local v5    # "bytes":[B
    .end local v6    # "decrypt":[B
    .end local v12    # "fos":Ljava/io/FileOutputStream;
    :cond_1
    :goto_1
    add-int/lit8 v17, v17, 0x1

    goto :goto_0

    .line 83
    :catch_0
    move-exception v9

    .line 84
    .local v9, "e":Ljava/lang/Exception;
    invoke-virtual {v9}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_1

    .line 86
    .end local v9    # "e":Ljava/lang/Exception;
    :cond_2
    const-string v19, ".txt"

    move-object/from16 v0, v19

    invoke-virtual {v14, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v19

    if-eqz v19, :cond_1

    .line 88
    :try_start_1
    invoke-static {v10}, Lcom/sitech/paas/decrypt/ProxyUtils;->getBytes(Ljava/io/File;)[B

    move-result-object v5

    .line 89
    .restart local v5    # "bytes":[B
    new-instance v13, Ljava/lang/String;

    invoke-direct {v13, v5}, Ljava/lang/String;-><init>([B)V

    .line 90
    .local v13, "infoStr":Ljava/lang/String;
    const-string v19, ","

    move-object/from16 v0, v19

    invoke-virtual {v13, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v15

    .line 91
    .local v15, "split":[Ljava/lang/String;
    array-length v0, v15

    move/from16 v19, v0

    if-lez v19, :cond_3

    .line 92
    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/sitech/paas/decrypt/ProxyApplication;->appName:Ljava/lang/String;

    move-object/from16 v19, v0

    invoke-static/range {v19 .. v19}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v19

    if-eqz v19, :cond_3

    .line 93
    const/16 v19, 0x0

    aget-object v19, v15, v19

    move-object/from16 v0, v19

    move-object/from16 v1, p0

    iput-object v0, v1, Lcom/sitech/paas/decrypt/ProxyApplication;->appName:Ljava/lang/String;

    .line 96
    :cond_3
    array-length v0, v15

    move/from16 v19, v0

    const/16 v20, 0x1

    move/from16 v0, v19

    move/from16 v1, v20

    if-le v0, v1, :cond_4

    .line 97
    const/16 v19, 0x1

    aget-object v19, v15, v19

    move-object/from16 v0, v19

    move-object/from16 v1, p0

    iput-object v0, v1, Lcom/sitech/paas/decrypt/ProxyApplication;->md5Real:Ljava/lang/String;

    .line 98
    invoke-direct/range {p0 .. p0}, Lcom/sitech/paas/decrypt/ProxyApplication;->getMD5()Ljava/lang/String;

    move-result-object v19

    move-object/from16 v0, v19

    move-object/from16 v1, p0

    iput-object v0, v1, Lcom/sitech/paas/decrypt/ProxyApplication;->md5Actural:Ljava/lang/String;

    .line 101
    :cond_4
    sget-object v19, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v20, Ljava/lang/StringBuilder;

    invoke-direct/range {v20 .. v20}, Ljava/lang/StringBuilder;-><init>()V

    const-string v21, "applicationName="

    invoke-virtual/range {v20 .. v21}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v20

    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/sitech/paas/decrypt/ProxyApplication;->appName:Ljava/lang/String;

    move-object/from16 v21, v0

    invoke-virtual/range {v20 .. v21}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v20

    invoke-virtual/range {v20 .. v20}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v20

    invoke-virtual/range {v19 .. v20}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_1

    .line 102
    .end local v5    # "bytes":[B
    .end local v13    # "infoStr":Ljava/lang/String;
    .end local v15    # "split":[Ljava/lang/String;
    :catch_1
    move-exception v9

    .line 103
    .restart local v9    # "e":Ljava/lang/Exception;
    invoke-virtual {v9}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_1

    .line 108
    .end local v9    # "e":Ljava/lang/Exception;
    .end local v10    # "file":Ljava/io/File;
    .end local v11    # "files":[Ljava/io/File;
    .end local v14    # "name":Ljava/lang/String;
    :cond_5
    invoke-virtual {v7}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v18

    move-object/from16 v0, v18

    array-length v0, v0

    move/from16 v19, v0

    const/16 v17, 0x0

    :goto_2
    move/from16 v0, v17

    move/from16 v1, v19

    if-ge v0, v1, :cond_6

    aget-object v10, v18, v17

    .line 109
    .restart local v10    # "file":Ljava/io/File;
    invoke-interface {v8, v10}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 108
    add-int/lit8 v17, v17, 0x1

    goto :goto_2

    .line 115
    .end local v10    # "file":Ljava/io/File;
    :cond_6
    :try_start_2
    move-object/from16 v0, p0

    move-object/from16 v1, v16

    invoke-direct {v0, v8, v1}, Lcom/sitech/paas/decrypt/ProxyApplication;->loadDex(Ljava/util/List;Ljava/io/File;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    .line 121
    :goto_3
    return-void

    .line 116
    :catch_2
    move-exception v9

    .line 117
    .restart local v9    # "e":Ljava/lang/Exception;
    invoke-virtual {v9}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_3
.end method

.method public getPackageName()Ljava/lang/String;
    .locals 1

    .prologue
    .line 340
    invoke-super {p0}, Landroid/app/Application;->getPackageName()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public onCreate()V
    .locals 6

    .prologue
    .line 169
    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    .line 171
    :try_start_0
    new-instance v1, Landroid/os/Handler;

    invoke-direct {v1}, Landroid/os/Handler;-><init>()V

    new-instance v2, Lcom/sitech/paas/decrypt/ProxyApplication$1;

    invoke-direct {v2, p0}, Lcom/sitech/paas/decrypt/ProxyApplication$1;-><init>(Lcom/sitech/paas/decrypt/ProxyApplication;)V

    const-wide/16 v4, 0x12c

    invoke-virtual {v1, v2, v4, v5}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 186
    :goto_0
    invoke-direct {p0}, Lcom/sitech/paas/decrypt/ProxyApplication;->bindRealApplicatin()V

    .line 187
    return-void

    .line 183
    :catch_0
    move-exception v0

    .line 184
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_0
.end method

.method public toHexString([B)Ljava/lang/String;
    .locals 5
    .param p1, "paramArrayOfByte"    # [B

    .prologue
    .line 381
    if-nez p1, :cond_0

    .line 382
    const/4 v3, 0x0

    .line 388
    :goto_0
    return-object v3

    .line 384
    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    array-length v3, p1

    mul-int/lit8 v3, v3, 0x2

    invoke-direct {v1, v3}, Ljava/lang/StringBuilder;-><init>(I)V

    .line 385
    .local v1, "localStringBuilder":Ljava/lang/StringBuilder;
    const/4 v0, 0x0

    .line 386
    .local v0, "i":I
    :goto_1
    array-length v3, p1

    if-lt v0, v3, :cond_1

    .line 387
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->length()I

    move-result v3

    add-int/lit8 v3, v3, -0x1

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->deleteCharAt(I)Ljava/lang/StringBuilder;

    .line 388
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v3

    goto :goto_0

    .line 390
    :cond_1
    aget-byte v3, p1, v0

    and-int/lit16 v3, v3, 0xff

    const/16 v4, 0x10

    invoke-static {v3, v4}, Ljava/lang/Integer;->toString(II)Ljava/lang/String;

    move-result-object v2

    .line 391
    .local v2, "str":Ljava/lang/String;
    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v3

    const/4 v4, 0x1

    if-ne v3, v4, :cond_2

    .line 392
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "0"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 394
    :cond_2
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 395
    const-string v3, ":"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 385
    add-int/lit8 v0, v0, 0x1

    goto :goto_1
.end method
