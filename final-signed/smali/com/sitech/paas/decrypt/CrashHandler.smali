.class public Lcom/sitech/paas/decrypt/CrashHandler;
.super Ljava/lang/Object;
.source "CrashHandler.java"

# interfaces
.implements Ljava/lang/Thread$UncaughtExceptionHandler;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/sitech/paas/decrypt/CrashHandler$ICrashHandlerListener;
    }
.end annotation


# static fields
.field private static INSTANCE:Lcom/sitech/paas/decrypt/CrashHandler; = null

.field public static final TAG:Ljava/lang/String; = "CrashHandler"


# instance fields
.field private dir:Ljava/lang/String;

.field private formatter:Ljava/text/DateFormat;

.field private iCrashHandlerListener:Lcom/sitech/paas/decrypt/CrashHandler$ICrashHandlerListener;

.field private infos:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private logPath:Ljava/lang/String;

.field private mContext:Landroid/content/Context;

.field private mDefaultHandler:Ljava/lang/Thread$UncaughtExceptionHandler;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 36
    new-instance v0, Lcom/sitech/paas/decrypt/CrashHandler;

    invoke-direct {v0}, Lcom/sitech/paas/decrypt/CrashHandler;-><init>()V

    sput-object v0, Lcom/sitech/paas/decrypt/CrashHandler;->INSTANCE:Lcom/sitech/paas/decrypt/CrashHandler;

    return-void
.end method

.method private constructor <init>()V
    .locals 2

    .prologue
    .line 53
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 40
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/sitech/paas/decrypt/CrashHandler;->infos:Ljava/util/Map;

    .line 43
    new-instance v0, Ljava/text/SimpleDateFormat;

    const-string v1, "yyyy-MM-dd-HH-mm-ss"

    invoke-direct {v0, v1}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/sitech/paas/decrypt/CrashHandler;->formatter:Ljava/text/DateFormat;

    .line 54
    return-void
.end method

.method public static getInstance()Lcom/sitech/paas/decrypt/CrashHandler;
    .locals 1

    .prologue
    .line 60
    sget-object v0, Lcom/sitech/paas/decrypt/CrashHandler;->INSTANCE:Lcom/sitech/paas/decrypt/CrashHandler;

    return-object v0
.end method

.method private handleException(Ljava/lang/Throwable;)Z
    .locals 3
    .param p1, "ex"    # Ljava/lang/Throwable;

    .prologue
    .line 116
    if-nez p1, :cond_0

    .line 117
    const/4 v1, 0x0

    .line 137
    :goto_0
    return v1

    .line 119
    :cond_0
    new-instance v1, Lcom/sitech/paas/decrypt/CrashHandler$1;

    invoke-direct {v1, p0}, Lcom/sitech/paas/decrypt/CrashHandler$1;-><init>(Lcom/sitech/paas/decrypt/CrashHandler;)V

    .line 129
    invoke-virtual {v1}, Lcom/sitech/paas/decrypt/CrashHandler$1;->start()V

    .line 131
    const-string v1, "\u5f02\u5e38\u662f=============\uff1a"

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 133
    iget-object v1, p0, Lcom/sitech/paas/decrypt/CrashHandler;->mContext:Landroid/content/Context;

    invoke-virtual {p0, v1}, Lcom/sitech/paas/decrypt/CrashHandler;->collectDeviceInfo(Landroid/content/Context;)V

    .line 135
    invoke-direct {p0, p1}, Lcom/sitech/paas/decrypt/CrashHandler;->saveCrashInfo2File(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v0

    .line 137
    .local v0, "s":Ljava/lang/String;
    const/4 v1, 0x1

    goto :goto_0
.end method

.method private saveCrashInfo2File(Ljava/lang/Throwable;)Ljava/lang/String;
    .locals 21
    .param p1, "ex"    # Ljava/lang/Throwable;

    .prologue
    .line 177
    new-instance v12, Ljava/lang/StringBuffer;

    invoke-direct {v12}, Ljava/lang/StringBuffer;-><init>()V

    .line 178
    .local v12, "sb":Ljava/lang/StringBuffer;
    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/sitech/paas/decrypt/CrashHandler;->infos:Ljava/util/Map;

    move-object/from16 v18, v0

    invoke-interface/range {v18 .. v18}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v18

    invoke-interface/range {v18 .. v18}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v18

    :goto_0
    invoke-interface/range {v18 .. v18}, Ljava/util/Iterator;->hasNext()Z

    move-result v19

    if-eqz v19, :cond_0

    invoke-interface/range {v18 .. v18}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/Map$Entry;

    .line 179
    .local v5, "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Ljava/lang/String;>;"
    invoke-interface {v5}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/String;

    .line 180
    .local v8, "key":Ljava/lang/String;
    invoke-interface {v5}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v16

    check-cast v16, Ljava/lang/String;

    .line 181
    .local v16, "value":Ljava/lang/String;
    new-instance v19, Ljava/lang/StringBuilder;

    invoke-direct/range {v19 .. v19}, Ljava/lang/StringBuilder;-><init>()V

    move-object/from16 v0, v19

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v19

    const-string v20, "="

    invoke-virtual/range {v19 .. v20}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v19

    move-object/from16 v0, v19

    move-object/from16 v1, v16

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v19

    const-string v20, "\n"

    invoke-virtual/range {v19 .. v20}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v19

    invoke-virtual/range {v19 .. v19}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v19

    move-object/from16 v0, v19

    invoke-virtual {v12, v0}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    goto :goto_0

    .line 184
    .end local v5    # "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Ljava/lang/String;>;"
    .end local v8    # "key":Ljava/lang/String;
    .end local v16    # "value":Ljava/lang/String;
    :cond_0
    new-instance v17, Ljava/io/StringWriter;

    invoke-direct/range {v17 .. v17}, Ljava/io/StringWriter;-><init>()V

    .line 185
    .local v17, "writer":Ljava/io/Writer;
    new-instance v10, Ljava/io/PrintWriter;

    move-object/from16 v0, v17

    invoke-direct {v10, v0}, Ljava/io/PrintWriter;-><init>(Ljava/io/Writer;)V

    .line 186
    .local v10, "printWriter":Ljava/io/PrintWriter;
    move-object/from16 v0, p1

    invoke-virtual {v0, v10}, Ljava/lang/Throwable;->printStackTrace(Ljava/io/PrintWriter;)V

    .line 187
    invoke-virtual/range {p1 .. p1}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object v2

    .line 188
    .local v2, "cause":Ljava/lang/Throwable;
    :goto_1
    if-eqz v2, :cond_1

    .line 189
    invoke-virtual {v2, v10}, Ljava/lang/Throwable;->printStackTrace(Ljava/io/PrintWriter;)V

    .line 190
    invoke-virtual {v2}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object v2

    goto :goto_1

    .line 192
    :cond_1
    invoke-virtual {v10}, Ljava/io/PrintWriter;->close()V

    .line 193
    invoke-virtual/range {v17 .. v17}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v11

    .line 194
    .local v11, "result":Ljava/lang/String;
    invoke-virtual {v12, v11}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 195
    const-string v18, "CrashHandler\u9519\u8bef\u65e5\u5fd7----"

    invoke-virtual {v12}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v19

    invoke-static/range {v18 .. v19}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 197
    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v14

    .line 198
    .local v14, "timestamp":J
    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/sitech/paas/decrypt/CrashHandler;->formatter:Ljava/text/DateFormat;

    move-object/from16 v18, v0

    new-instance v19, Ljava/util/Date;

    invoke-direct/range {v19 .. v19}, Ljava/util/Date;-><init>()V

    invoke-virtual/range {v18 .. v19}, Ljava/text/DateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v13

    .line 199
    .local v13, "time":Ljava/lang/String;
    new-instance v18, Ljava/lang/StringBuilder;

    invoke-direct/range {v18 .. v18}, Ljava/lang/StringBuilder;-><init>()V

    const-string v19, "BeiJing"

    invoke-virtual/range {v18 .. v19}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v18

    move-object/from16 v0, v18

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v18

    const-string v19, "-"

    invoke-virtual/range {v18 .. v19}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v18

    move-object/from16 v0, v18

    invoke-virtual {v0, v14, v15}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v18

    const-string v19, ".txt"

    invoke-virtual/range {v18 .. v19}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v18

    invoke-virtual/range {v18 .. v18}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    .line 200
    .local v6, "fileName":Ljava/lang/String;
    invoke-static {}, Landroid/os/Environment;->getExternalStorageState()Ljava/lang/String;

    move-result-object v18

    const-string v19, "mounted"

    invoke-virtual/range {v18 .. v19}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v18

    if-eqz v18, :cond_3

    .line 201
    new-instance v18, Ljava/lang/StringBuilder;

    invoke-direct/range {v18 .. v18}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v19

    invoke-virtual/range {v18 .. v19}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v18

    const-string v19, "/yangkun/demo/"

    invoke-virtual/range {v18 .. v19}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v18

    invoke-virtual/range {v18 .. v18}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    .line 202
    .local v9, "path":Ljava/lang/String;
    new-instance v3, Ljava/io/File;

    invoke-direct {v3, v9}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 203
    .local v3, "dir":Ljava/io/File;
    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v18

    if-nez v18, :cond_2

    .line 204
    invoke-virtual {v3}, Ljava/io/File;->mkdirs()Z

    .line 206
    :cond_2
    new-instance v7, Ljava/io/FileOutputStream;

    new-instance v18, Ljava/lang/StringBuilder;

    invoke-direct/range {v18 .. v18}, Ljava/lang/StringBuilder;-><init>()V

    move-object/from16 v0, v18

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v18

    move-object/from16 v0, v18

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v18

    invoke-virtual/range {v18 .. v18}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v18

    move-object/from16 v0, v18

    invoke-direct {v7, v0}, Ljava/io/FileOutputStream;-><init>(Ljava/lang/String;)V

    .line 207
    .local v7, "fos":Ljava/io/FileOutputStream;
    new-instance v18, Ljava/lang/StringBuilder;

    invoke-direct/range {v18 .. v18}, Ljava/lang/StringBuilder;-><init>()V

    move-object/from16 v0, v18

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v18

    move-object/from16 v0, v18

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v18

    invoke-virtual/range {v18 .. v18}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v18

    move-object/from16 v0, v18

    move-object/from16 v1, p0

    iput-object v0, v1, Lcom/sitech/paas/decrypt/CrashHandler;->logPath:Ljava/lang/String;

    .line 208
    invoke-virtual {v12}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v18

    invoke-virtual/range {v18 .. v18}, Ljava/lang/String;->getBytes()[B

    move-result-object v18

    move-object/from16 v0, v18

    invoke-virtual {v7, v0}, Ljava/io/FileOutputStream;->write([B)V

    .line 209
    invoke-virtual {v7}, Ljava/io/FileOutputStream;->close()V

    .line 212
    .end local v3    # "dir":Ljava/io/File;
    .end local v7    # "fos":Ljava/io/FileOutputStream;
    .end local v9    # "path":Ljava/lang/String;
    :cond_3
    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/sitech/paas/decrypt/CrashHandler;->iCrashHandlerListener:Lcom/sitech/paas/decrypt/CrashHandler$ICrashHandlerListener;

    move-object/from16 v18, v0

    if-eqz v18, :cond_4

    .line 213
    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/sitech/paas/decrypt/CrashHandler;->iCrashHandlerListener:Lcom/sitech/paas/decrypt/CrashHandler$ICrashHandlerListener;

    move-object/from16 v18, v0

    invoke-virtual {v12}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v19

    invoke-interface/range {v18 .. v19}, Lcom/sitech/paas/decrypt/CrashHandler$ICrashHandlerListener;->onCrash(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 219
    .end local v6    # "fileName":Ljava/lang/String;
    .end local v13    # "time":Ljava/lang/String;
    .end local v14    # "timestamp":J
    :cond_4
    :goto_2
    return-object v6

    .line 216
    :catch_0
    move-exception v4

    .line 217
    .local v4, "e":Ljava/lang/Exception;
    const-string v18, "CrashHandler"

    const-string v19, "an error occured while writing file..."

    move-object/from16 v0, v18

    move-object/from16 v1, v19

    invoke-static {v0, v1, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 219
    const/4 v6, 0x0

    goto :goto_2
.end method

.method private sendMail(Ljava/lang/Throwable;)V
    .locals 0
    .param p1, "message"    # Ljava/lang/Throwable;

    .prologue
    .line 107
    return-void
.end method


# virtual methods
.method public collectDeviceInfo(Landroid/content/Context;)V
    .locals 12
    .param p1, "ctx"    # Landroid/content/Context;

    .prologue
    .line 147
    :try_start_0
    invoke-virtual {p1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v4

    .line 148
    .local v4, "pm":Landroid/content/pm/PackageManager;
    invoke-virtual {p1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x1

    invoke-virtual {v4, v7, v8}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v3

    .line 149
    .local v3, "pi":Landroid/content/pm/PackageInfo;
    if-eqz v3, :cond_0

    .line 150
    iget-object v7, v3, Landroid/content/pm/PackageInfo;->versionName:Ljava/lang/String;

    if-nez v7, :cond_1

    const-string v6, "null"

    .line 151
    .local v6, "versionName":Ljava/lang/String;
    :goto_0
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    iget v8, v3, Landroid/content/pm/PackageInfo;->versionCode:I

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v7

    const-string v8, ""

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    .line 152
    .local v5, "versionCode":Ljava/lang/String;
    iget-object v7, p0, Lcom/sitech/paas/decrypt/CrashHandler;->infos:Ljava/util/Map;

    const-string v8, "versionName"

    invoke-interface {v7, v8, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 153
    iget-object v7, p0, Lcom/sitech/paas/decrypt/CrashHandler;->infos:Ljava/util/Map;

    const-string v8, "versionCode"

    invoke-interface {v7, v8, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 158
    .end local v3    # "pi":Landroid/content/pm/PackageInfo;
    .end local v4    # "pm":Landroid/content/pm/PackageManager;
    .end local v5    # "versionCode":Ljava/lang/String;
    .end local v6    # "versionName":Ljava/lang/String;
    :cond_0
    :goto_1
    const-class v7, Landroid/os/Build;

    invoke-virtual {v7}, Ljava/lang/Class;->getDeclaredFields()[Ljava/lang/reflect/Field;

    move-result-object v2

    .line 159
    .local v2, "fields":[Ljava/lang/reflect/Field;
    array-length v8, v2

    const/4 v7, 0x0

    :goto_2
    if-ge v7, v8, :cond_2

    aget-object v1, v2, v7

    .line 161
    .local v1, "field":Ljava/lang/reflect/Field;
    const/4 v9, 0x1

    :try_start_1
    invoke-virtual {v1, v9}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 162
    iget-object v9, p0, Lcom/sitech/paas/decrypt/CrashHandler;->infos:Ljava/util/Map;

    invoke-virtual {v1}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object v10

    const/4 v11, 0x0

    invoke-virtual {v1, v11}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v11

    invoke-virtual {v11}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v11

    invoke-interface {v9, v10, v11}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 163
    const-string v9, "CrashHandler"

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    const-string v11, " : "

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    const/4 v11, 0x0

    invoke-virtual {v1, v11}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v11

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-static {v9, v10}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    .line 159
    :goto_3
    add-int/lit8 v7, v7, 0x1

    goto :goto_2

    .line 150
    .end local v1    # "field":Ljava/lang/reflect/Field;
    .end local v2    # "fields":[Ljava/lang/reflect/Field;
    .restart local v3    # "pi":Landroid/content/pm/PackageInfo;
    .restart local v4    # "pm":Landroid/content/pm/PackageManager;
    :cond_1
    :try_start_2
    iget-object v6, v3, Landroid/content/pm/PackageInfo;->versionName:Ljava/lang/String;
    :try_end_2
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_0

    .line 155
    .end local v3    # "pi":Landroid/content/pm/PackageInfo;
    .end local v4    # "pm":Landroid/content/pm/PackageManager;
    :catch_0
    move-exception v0

    .line 156
    .local v0, "e":Landroid/content/pm/PackageManager$NameNotFoundException;
    const-string v7, "CrashHandler"

    const-string v8, "an error occured when collect package info"

    invoke-static {v7, v8, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1

    .line 164
    .end local v0    # "e":Landroid/content/pm/PackageManager$NameNotFoundException;
    .restart local v1    # "field":Ljava/lang/reflect/Field;
    .restart local v2    # "fields":[Ljava/lang/reflect/Field;
    :catch_1
    move-exception v0

    .line 165
    .local v0, "e":Ljava/lang/Exception;
    const-string v9, "CrashHandler"

    const-string v10, "an error occured when collect crash info"

    invoke-static {v9, v10, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_3

    .line 168
    .end local v0    # "e":Ljava/lang/Exception;
    .end local v1    # "field":Ljava/lang/reflect/Field;
    :cond_2
    return-void
.end method

.method public init(Landroid/content/Context;)Lcom/sitech/paas/decrypt/CrashHandler;
    .locals 1
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 69
    iput-object p1, p0, Lcom/sitech/paas/decrypt/CrashHandler;->mContext:Landroid/content/Context;

    .line 71
    invoke-static {}, Ljava/lang/Thread;->getDefaultUncaughtExceptionHandler()Ljava/lang/Thread$UncaughtExceptionHandler;

    move-result-object v0

    iput-object v0, p0, Lcom/sitech/paas/decrypt/CrashHandler;->mDefaultHandler:Ljava/lang/Thread$UncaughtExceptionHandler;

    .line 73
    invoke-static {p0}, Ljava/lang/Thread;->setDefaultUncaughtExceptionHandler(Ljava/lang/Thread$UncaughtExceptionHandler;)V

    .line 74
    return-object p0
.end method

.method public setCrashHandlerListener(Lcom/sitech/paas/decrypt/CrashHandler$ICrashHandlerListener;)V
    .locals 0
    .param p1, "iCrashHandlerListener"    # Lcom/sitech/paas/decrypt/CrashHandler$ICrashHandlerListener;

    .prologue
    .line 228
    iput-object p1, p0, Lcom/sitech/paas/decrypt/CrashHandler;->iCrashHandlerListener:Lcom/sitech/paas/decrypt/CrashHandler$ICrashHandlerListener;

    .line 229
    return-void
.end method

.method public uncaughtException(Ljava/lang/Thread;Ljava/lang/Throwable;)V
    .locals 1
    .param p1, "thread"    # Ljava/lang/Thread;
    .param p2, "ex"    # Ljava/lang/Throwable;

    .prologue
    .line 83
    invoke-direct {p0, p2}, Lcom/sitech/paas/decrypt/CrashHandler;->handleException(Ljava/lang/Throwable;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/sitech/paas/decrypt/CrashHandler;->mDefaultHandler:Ljava/lang/Thread$UncaughtExceptionHandler;

    if-eqz v0, :cond_0

    .line 85
    iget-object v0, p0, Lcom/sitech/paas/decrypt/CrashHandler;->mDefaultHandler:Ljava/lang/Thread$UncaughtExceptionHandler;

    invoke-interface {v0, p1, p2}, Ljava/lang/Thread$UncaughtExceptionHandler;->uncaughtException(Ljava/lang/Thread;Ljava/lang/Throwable;)V

    .line 102
    :cond_0
    return-void
.end method
