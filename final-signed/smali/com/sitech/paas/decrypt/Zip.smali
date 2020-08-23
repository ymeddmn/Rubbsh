.class public Lcom/sitech/paas/decrypt/Zip;
.super Ljava/lang/Object;
.source "Zip.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 15
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static compress(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V
    .locals 6
    .param p0, "srcFile"    # Ljava/io/File;
    .param p1, "zos"    # Ljava/util/zip/ZipOutputStream;
    .param p2, "basePath"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 101
    invoke-virtual {p0}, Ljava/io/File;->isDirectory()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 102
    invoke-virtual {p0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v1

    .line 103
    .local v1, "files":[Ljava/io/File;
    array-length v3, v1

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v3, :cond_1

    aget-object v0, v1, v2

    .line 105
    .local v0, "file":Ljava/io/File;
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {p0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "/"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v0, p1, v4}, Lcom/sitech/paas/decrypt/Zip;->compress(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V

    .line 103
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 108
    .end local v0    # "file":Ljava/io/File;
    .end local v1    # "files":[Ljava/io/File;
    :cond_0
    invoke-static {p0, p1, p2}, Lcom/sitech/paas/decrypt/Zip;->compressFile(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V

    .line 110
    :cond_1
    return-void
.end method

.method private static compressFile(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V
    .locals 13
    .param p0, "file"    # Ljava/io/File;
    .param p1, "zos"    # Ljava/util/zip/ZipOutputStream;
    .param p2, "dir"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    const/16 v12, 0x800

    const/4 v11, 0x1

    const/4 v10, 0x0

    .line 115
    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {p0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 117
    .local v4, "fullName":Ljava/lang/String;
    const-string v8, "/"

    invoke-virtual {v4, v8}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v2

    .line 119
    .local v2, "fileNames":[Ljava/lang/String;
    new-instance v7, Ljava/lang/StringBuffer;

    invoke-direct {v7}, Ljava/lang/StringBuffer;-><init>()V

    .line 120
    .local v7, "sb":Ljava/lang/StringBuffer;
    array-length v8, v2

    if-le v8, v11, :cond_0

    .line 121
    const/4 v5, 0x1

    .local v5, "i":I
    :goto_0
    array-length v8, v2

    if-ge v5, v8, :cond_1

    .line 122
    const-string v8, "/"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 123
    aget-object v8, v2, v5

    invoke-virtual {v7, v8}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 121
    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    .line 126
    .end local v5    # "i":I
    :cond_0
    const-string v8, "/"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 129
    :cond_1
    new-instance v1, Ljava/util/zip/ZipEntry;

    invoke-virtual {v7, v11}, Ljava/lang/StringBuffer;->substring(I)Ljava/lang/String;

    move-result-object v8

    invoke-direct {v1, v8}, Ljava/util/zip/ZipEntry;-><init>(Ljava/lang/String;)V

    .line 130
    .local v1, "entry":Ljava/util/zip/ZipEntry;
    invoke-virtual {p1, v1}, Ljava/util/zip/ZipOutputStream;->putNextEntry(Ljava/util/zip/ZipEntry;)V

    .line 132
    new-instance v3, Ljava/io/FileInputStream;

    invoke-direct {v3, p0}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    .line 134
    .local v3, "fis":Ljava/io/FileInputStream;
    new-array v0, v12, [B

    .line 135
    .local v0, "data":[B
    :goto_1
    invoke-virtual {v3, v0, v10, v12}, Ljava/io/FileInputStream;->read([BII)I

    move-result v6

    .local v6, "len":I
    const/4 v8, -0x1

    if-eq v6, v8, :cond_2

    .line 136
    invoke-virtual {p1, v0, v10, v6}, Ljava/util/zip/ZipOutputStream;->write([BII)V

    goto :goto_1

    .line 138
    :cond_2
    invoke-virtual {v3}, Ljava/io/FileInputStream;->close()V

    .line 139
    invoke-virtual {p1}, Ljava/util/zip/ZipOutputStream;->closeEntry()V

    .line 140
    return-void
.end method

.method private static deleteFile(Ljava/io/File;)V
    .locals 4
    .param p0, "file"    # Ljava/io/File;

    .prologue
    .line 18
    invoke-virtual {p0}, Ljava/io/File;->isDirectory()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 19
    invoke-virtual {p0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v1

    .line 20
    .local v1, "files":[Ljava/io/File;
    array-length v3, v1

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v3, :cond_1

    aget-object v0, v1, v2

    .line 21
    .local v0, "f":Ljava/io/File;
    invoke-static {v0}, Lcom/sitech/paas/decrypt/Zip;->deleteFile(Ljava/io/File;)V

    .line 20
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 24
    .end local v0    # "f":Ljava/io/File;
    .end local v1    # "files":[Ljava/io/File;
    :cond_0
    invoke-virtual {p0}, Ljava/io/File;->delete()Z

    .line 26
    :cond_1
    return-void
.end method

.method public static unZip(Ljava/io/File;Ljava/io/File;)V
    .locals 11
    .param p0, "zip"    # Ljava/io/File;
    .param p1, "dir"    # Ljava/io/File;

    .prologue
    .line 35
    :try_start_0
    invoke-static {p1}, Lcom/sitech/paas/decrypt/Zip;->deleteFile(Ljava/io/File;)V

    .line 36
    new-instance v9, Ljava/util/zip/ZipFile;

    invoke-direct {v9, p0}, Ljava/util/zip/ZipFile;-><init>(Ljava/io/File;)V

    .line 38
    .local v9, "zipFile":Ljava/util/zip/ZipFile;
    invoke-virtual {v9}, Ljava/util/zip/ZipFile;->entries()Ljava/util/Enumeration;

    move-result-object v2

    .line 40
    .local v2, "entries":Ljava/util/Enumeration;, "Ljava/util/Enumeration<+Ljava/util/zip/ZipEntry;>;"
    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v10

    if-eqz v10, :cond_3

    .line 41
    invoke-interface {v2}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/util/zip/ZipEntry;

    .line 43
    .local v8, "zipEntry":Ljava/util/zip/ZipEntry;
    invoke-virtual {v8}, Ljava/util/zip/ZipEntry;->getName()Ljava/lang/String;

    move-result-object v7

    .line 45
    .local v7, "name":Ljava/lang/String;
    const-string v10, "META-INF/CERT.RSA"

    invoke-virtual {v7, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-nez v10, :cond_0

    const-string v10, "META-INF/CERT.SF"

    invoke-virtual {v7, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-nez v10, :cond_0

    const-string v10, "META-INF/MANIFEST.MF"

    .line 46
    invoke-virtual {v7, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-nez v10, :cond_0

    .line 50
    invoke-virtual {v8}, Ljava/util/zip/ZipEntry;->isDirectory()Z

    move-result v10

    if-nez v10, :cond_0

    .line 51
    new-instance v3, Ljava/io/File;

    invoke-direct {v3, p1, v7}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 53
    .local v3, "file":Ljava/io/File;
    invoke-virtual {v3}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v10

    invoke-virtual {v10}, Ljava/io/File;->exists()Z

    move-result v10

    if-nez v10, :cond_1

    .line 54
    invoke-virtual {v3}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v10

    invoke-virtual {v10}, Ljava/io/File;->mkdirs()Z

    .line 57
    :cond_1
    new-instance v4, Ljava/io/FileOutputStream;

    invoke-direct {v4, v3}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    .line 58
    .local v4, "fos":Ljava/io/FileOutputStream;
    invoke-virtual {v9, v8}, Ljava/util/zip/ZipFile;->getInputStream(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;

    move-result-object v5

    .line 59
    .local v5, "is":Ljava/io/InputStream;
    const/16 v10, 0x800

    new-array v0, v10, [B

    .line 61
    .local v0, "buffer":[B
    :goto_1
    invoke-virtual {v5, v0}, Ljava/io/InputStream;->read([B)I

    move-result v6

    .local v6, "len":I
    const/4 v10, -0x1

    if-eq v6, v10, :cond_2

    .line 62
    const/4 v10, 0x0

    invoke-virtual {v4, v0, v10, v6}, Ljava/io/FileOutputStream;->write([BII)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    .line 69
    .end local v0    # "buffer":[B
    .end local v2    # "entries":Ljava/util/Enumeration;, "Ljava/util/Enumeration<+Ljava/util/zip/ZipEntry;>;"
    .end local v3    # "file":Ljava/io/File;
    .end local v4    # "fos":Ljava/io/FileOutputStream;
    .end local v5    # "is":Ljava/io/InputStream;
    .end local v6    # "len":I
    .end local v7    # "name":Ljava/lang/String;
    .end local v8    # "zipEntry":Ljava/util/zip/ZipEntry;
    .end local v9    # "zipFile":Ljava/util/zip/ZipFile;
    :catch_0
    move-exception v1

    .line 70
    .local v1, "e":Ljava/lang/Exception;
    invoke-virtual {v1}, Ljava/lang/Exception;->printStackTrace()V

    .line 72
    .end local v1    # "e":Ljava/lang/Exception;
    :goto_2
    return-void

    .line 64
    .restart local v0    # "buffer":[B
    .restart local v2    # "entries":Ljava/util/Enumeration;, "Ljava/util/Enumeration<+Ljava/util/zip/ZipEntry;>;"
    .restart local v3    # "file":Ljava/io/File;
    .restart local v4    # "fos":Ljava/io/FileOutputStream;
    .restart local v5    # "is":Ljava/io/InputStream;
    .restart local v6    # "len":I
    .restart local v7    # "name":Ljava/lang/String;
    .restart local v8    # "zipEntry":Ljava/util/zip/ZipEntry;
    .restart local v9    # "zipFile":Ljava/util/zip/ZipFile;
    :cond_2
    :try_start_1
    invoke-virtual {v5}, Ljava/io/InputStream;->close()V

    .line 65
    invoke-virtual {v4}, Ljava/io/FileOutputStream;->close()V

    goto :goto_0

    .line 68
    .end local v0    # "buffer":[B
    .end local v3    # "file":Ljava/io/File;
    .end local v4    # "fos":Ljava/io/FileOutputStream;
    .end local v5    # "is":Ljava/io/InputStream;
    .end local v6    # "len":I
    .end local v7    # "name":Ljava/lang/String;
    .end local v8    # "zipEntry":Ljava/util/zip/ZipEntry;
    :cond_3
    invoke-virtual {v9}, Ljava/util/zip/ZipFile;->close()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_2
.end method

.method public static zip(Ljava/io/File;Ljava/io/File;)V
    .locals 4
    .param p0, "dir"    # Ljava/io/File;
    .param p1, "zip"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 81
    invoke-virtual {p1}, Ljava/io/File;->delete()Z

    .line 83
    new-instance v0, Ljava/util/zip/CheckedOutputStream;

    new-instance v2, Ljava/io/FileOutputStream;

    invoke-direct {v2, p1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    new-instance v3, Ljava/util/zip/CRC32;

    invoke-direct {v3}, Ljava/util/zip/CRC32;-><init>()V

    invoke-direct {v0, v2, v3}, Ljava/util/zip/CheckedOutputStream;-><init>(Ljava/io/OutputStream;Ljava/util/zip/Checksum;)V

    .line 85
    .local v0, "cos":Ljava/util/zip/CheckedOutputStream;
    new-instance v1, Ljava/util/zip/ZipOutputStream;

    invoke-direct {v1, v0}, Ljava/util/zip/ZipOutputStream;-><init>(Ljava/io/OutputStream;)V

    .line 87
    .local v1, "zos":Ljava/util/zip/ZipOutputStream;
    const-string v2, ""

    invoke-static {p0, v1, v2}, Lcom/sitech/paas/decrypt/Zip;->compress(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V

    .line 88
    invoke-virtual {v1}, Ljava/util/zip/ZipOutputStream;->flush()V

    .line 89
    invoke-virtual {v1}, Ljava/util/zip/ZipOutputStream;->close()V

    .line 90
    return-void
.end method
