.class public Lcom/sitech/paas/decrypt/ProxyUtils;
.super Ljava/lang/Object;
.source "ProxyUtils.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 10
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static findField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/reflect/Field;
    .locals 6
    .param p0, "instance"    # Ljava/lang/Object;
    .param p1, "name"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/NoSuchFieldException;
        }
    .end annotation

    .prologue
    .line 34
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    .line 36
    .local v0, "clazz":Ljava/lang/Class;
    :goto_0
    if-eqz v0, :cond_1

    .line 38
    :try_start_0
    invoke-virtual {v0, p1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v2

    .line 40
    .local v2, "field":Ljava/lang/reflect/Field;
    invoke-virtual {v2}, Ljava/lang/reflect/Field;->isAccessible()Z

    move-result v3

    if-nez v3, :cond_0

    .line 41
    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Ljava/lang/reflect/Field;->setAccessible(Z)V
    :try_end_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_0

    .line 43
    :cond_0
    return-object v2

    .line 44
    .end local v2    # "field":Ljava/lang/reflect/Field;
    :catch_0
    move-exception v1

    .line 46
    .local v1, "e":Ljava/lang/NoSuchFieldException;
    invoke-virtual {v0}, Ljava/lang/Class;->getSuperclass()Ljava/lang/Class;

    move-result-object v0

    .line 47
    goto :goto_0

    .line 49
    .end local v1    # "e":Ljava/lang/NoSuchFieldException;
    :cond_1
    new-instance v3, Ljava/lang/NoSuchFieldException;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Field "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, " not found in "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v3, v4}, Ljava/lang/NoSuchFieldException;-><init>(Ljava/lang/String;)V

    throw v3
.end method

.method public static varargs findMethod(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    .locals 6
    .param p0, "instance"    # Ljava/lang/Object;
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "parameterTypes"    # [Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/NoSuchMethodException;
        }
    .end annotation

    .prologue
    .line 63
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    .line 64
    .local v0, "clazz":Ljava/lang/Class;
    :goto_0
    if-eqz v0, :cond_1

    .line 66
    :try_start_0
    invoke-virtual {v0, p1, p2}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    .line 67
    .local v2, "method":Ljava/lang/reflect/Method;
    invoke-virtual {v2}, Ljava/lang/reflect/Method;->isAccessible()Z

    move-result v3

    if-nez v3, :cond_0

    .line 68
    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Ljava/lang/reflect/Method;->setAccessible(Z)V
    :try_end_0
    .catch Ljava/lang/NoSuchMethodException; {:try_start_0 .. :try_end_0} :catch_0

    .line 70
    :cond_0
    return-object v2

    .line 71
    .end local v2    # "method":Ljava/lang/reflect/Method;
    :catch_0
    move-exception v1

    .line 73
    .local v1, "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/Class;->getSuperclass()Ljava/lang/Class;

    move-result-object v0

    .line 74
    goto :goto_0

    .line 76
    .end local v1    # "e":Ljava/lang/NoSuchMethodException;
    :cond_1
    new-instance v3, Ljava/lang/NoSuchMethodException;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Method "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, " with parameters "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    .line 77
    invoke-static {p2}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, " not found in "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v3, v4}, Ljava/lang/NoSuchMethodException;-><init>(Ljava/lang/String;)V

    throw v3
.end method

.method public static getBytes(Ljava/io/File;)[B
    .locals 4
    .param p0, "file"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 19
    new-instance v1, Ljava/io/RandomAccessFile;

    const-string v2, "r"

    invoke-direct {v1, p0, v2}, Ljava/io/RandomAccessFile;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 20
    .local v1, "r":Ljava/io/RandomAccessFile;
    invoke-virtual {v1}, Ljava/io/RandomAccessFile;->length()J

    move-result-wide v2

    long-to-int v2, v2

    new-array v0, v2, [B

    .line 21
    .local v0, "buffer":[B
    invoke-virtual {v1, v0}, Ljava/io/RandomAccessFile;->readFully([B)V

    .line 22
    invoke-virtual {v1}, Ljava/io/RandomAccessFile;->close()V

    .line 23
    return-object v0
.end method
