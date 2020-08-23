.class public Lcom/sitech/paas/decrypt/EncryptUtil;
.super Ljava/lang/Object;
.source "EncryptUtil.java"


# static fields
.field private static final CHARACTER:Ljava/lang/String; = "UTF-8"

.field private static final CIPHER_MODE:Ljava/lang/String; = "AES/ECB/PKCS5Padding"

.field private static final ENCRY_ALGORITHM:Ljava/lang/String; = "AES"

.field private static final IV_:Ljava/lang/String;

.field private static final PWD_SIZE:I = 0x10

.field public static final ivBytes:[B


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 32
    const/16 v0, 0x10

    new-array v0, v0, [B

    fill-array-data v0, :array_0

    sput-object v0, Lcom/sitech/paas/decrypt/EncryptUtil;->ivBytes:[B

    .line 38
    const/4 v0, 0x0

    sput-object v0, Lcom/sitech/paas/decrypt/EncryptUtil;->IV_:Ljava/lang/String;

    return-void

    .line 32
    nop

    :array_0
    .array-data 1
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
    .end array-data
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 17
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static byte2hex([B)Ljava/lang/String;
    .locals 5
    .param p0, "bytes"    # [B

    .prologue
    .line 222
    new-instance v1, Ljava/lang/StringBuffer;

    array-length v3, p0

    mul-int/lit8 v3, v3, 0x2

    invoke-direct {v1, v3}, Ljava/lang/StringBuffer;-><init>(I)V

    .line 223
    .local v1, "sb":Ljava/lang/StringBuffer;
    const-string v2, ""

    .line 224
    .local v2, "tmp":Ljava/lang/String;
    const/4 v0, 0x0

    .local v0, "n":I
    :goto_0
    array-length v3, p0

    if-ge v0, v3, :cond_1

    .line 226
    aget-byte v3, p0, v0

    and-int/lit16 v3, v3, 0xff

    invoke-static {v3}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object v2

    .line 227
    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v3

    const/4 v4, 0x1

    if-ne v3, v4, :cond_0

    .line 228
    const-string v3, "0"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 230
    :cond_0
    invoke-virtual {v1, v2}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 224
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 232
    :cond_1
    invoke-virtual {v1}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v3

    return-object v3
.end method

.method public static decrypt([B[B)[B
    .locals 5
    .param p0, "cipherTextBytes"    # [B
    .param p1, "pwdBytes"    # [B

    .prologue
    .line 133
    :try_start_0
    new-instance v3, Ljavax/crypto/spec/SecretKeySpec;

    const-string v4, "AES"

    invoke-direct {v3, p1, v4}, Ljavax/crypto/spec/SecretKeySpec;-><init>([BLjava/lang/String;)V

    .line 136
    .local v3, "keySpec":Ljavax/crypto/spec/SecretKeySpec;
    const-string v4, "AES/ECB/PKCS5Padding"

    invoke-static {v4}, Ljavax/crypto/Cipher;->getInstance(Ljava/lang/String;)Ljavax/crypto/Cipher;

    move-result-object v0

    .line 142
    .local v0, "cipher":Ljavax/crypto/Cipher;
    const/4 v4, 0x2

    invoke-virtual {v0, v4, v3}, Ljavax/crypto/Cipher;->init(ILjava/security/Key;)V

    .line 145
    invoke-virtual {v0, p0}, Ljavax/crypto/Cipher;->doFinal([B)[B
    :try_end_0
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/security/InvalidKeyException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljavax/crypto/NoSuchPaddingException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljavax/crypto/BadPaddingException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljavax/crypto/IllegalBlockSizeException; {:try_start_0 .. :try_end_0} :catch_4
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_5

    move-result-object v1

    .line 164
    .end local v0    # "cipher":Ljavax/crypto/Cipher;
    .end local v3    # "keySpec":Ljavax/crypto/spec/SecretKeySpec;
    :goto_0
    return-object v1

    .line 150
    :catch_0
    move-exception v2

    .line 151
    .local v2, "e":Ljava/security/NoSuchAlgorithmException;
    invoke-virtual {v2}, Ljava/security/NoSuchAlgorithmException;->printStackTrace()V

    .line 164
    .end local v2    # "e":Ljava/security/NoSuchAlgorithmException;
    :goto_1
    const/4 v1, 0x0

    goto :goto_0

    .line 152
    :catch_1
    move-exception v2

    .line 153
    .local v2, "e":Ljava/security/InvalidKeyException;
    invoke-virtual {v2}, Ljava/security/InvalidKeyException;->printStackTrace()V

    goto :goto_1

    .line 154
    .end local v2    # "e":Ljava/security/InvalidKeyException;
    :catch_2
    move-exception v2

    .line 155
    .local v2, "e":Ljavax/crypto/NoSuchPaddingException;
    invoke-virtual {v2}, Ljavax/crypto/NoSuchPaddingException;->printStackTrace()V

    goto :goto_1

    .line 156
    .end local v2    # "e":Ljavax/crypto/NoSuchPaddingException;
    :catch_3
    move-exception v2

    .line 157
    .local v2, "e":Ljavax/crypto/BadPaddingException;
    invoke-virtual {v2}, Ljavax/crypto/BadPaddingException;->printStackTrace()V

    goto :goto_1

    .line 158
    .end local v2    # "e":Ljavax/crypto/BadPaddingException;
    :catch_4
    move-exception v2

    .line 159
    .local v2, "e":Ljavax/crypto/IllegalBlockSizeException;
    invoke-virtual {v2}, Ljavax/crypto/IllegalBlockSizeException;->printStackTrace()V

    goto :goto_1

    .line 160
    .end local v2    # "e":Ljavax/crypto/IllegalBlockSizeException;
    :catch_5
    move-exception v2

    .line 161
    .local v2, "e":Ljava/lang/Exception;
    invoke-virtual {v2}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_1
.end method

.method public static decryptHex(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 5
    .param p0, "cipherText"    # Ljava/lang/String;
    .param p1, "password"    # Ljava/lang/String;

    .prologue
    .line 204
    :try_start_0
    invoke-static {p0}, Lcom/sitech/paas/decrypt/EncryptUtil;->hex2byte(Ljava/lang/String;)[B

    move-result-object v0

    .line 207
    .local v0, "cipherTextBytes":[B
    invoke-static {p1}, Lcom/sitech/paas/decrypt/EncryptUtil;->pwdHandler(Ljava/lang/String;)[B

    move-result-object v3

    invoke-static {v0, v3}, Lcom/sitech/paas/decrypt/EncryptUtil;->decrypt([B[B)[B

    move-result-object v1

    .line 210
    .local v1, "clearTextBytes":[B
    new-instance v3, Ljava/lang/String;

    const-string v4, "UTF-8"

    invoke-direct {v3, v1, v4}, Ljava/lang/String;-><init>([BLjava/lang/String;)V
    :try_end_0
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    .line 217
    .end local v0    # "cipherTextBytes":[B
    .end local v1    # "clearTextBytes":[B
    :goto_0
    return-object v3

    .line 211
    :catch_0
    move-exception v2

    .line 212
    .local v2, "e":Ljava/io/UnsupportedEncodingException;
    invoke-virtual {v2}, Ljava/io/UnsupportedEncodingException;->printStackTrace()V

    .line 217
    .end local v2    # "e":Ljava/io/UnsupportedEncodingException;
    :goto_1
    const/4 v3, 0x0

    goto :goto_0

    .line 213
    :catch_1
    move-exception v2

    .line 214
    .local v2, "e":Ljava/lang/Exception;
    invoke-virtual {v2}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_1
.end method

.method public static encrypt([B[B)[B
    .locals 5
    .param p0, "clearTextBytes"    # [B
    .param p1, "pwdBytes"    # [B

    .prologue
    .line 90
    :try_start_0
    new-instance v3, Ljavax/crypto/spec/SecretKeySpec;

    const-string v4, "AES"

    invoke-direct {v3, p1, v4}, Ljavax/crypto/spec/SecretKeySpec;-><init>([BLjava/lang/String;)V

    .line 93
    .local v3, "keySpec":Ljavax/crypto/spec/SecretKeySpec;
    const-string v4, "AES/ECB/PKCS5Padding"

    invoke-static {v4}, Ljavax/crypto/Cipher;->getInstance(Ljava/lang/String;)Ljavax/crypto/Cipher;

    move-result-object v0

    .line 99
    .local v0, "cipher":Ljavax/crypto/Cipher;
    const/4 v4, 0x1

    invoke-virtual {v0, v4, v3}, Ljavax/crypto/Cipher;->init(ILjava/security/Key;)V

    .line 102
    invoke-virtual {v0, p0}, Ljavax/crypto/Cipher;->doFinal([B)[B
    :try_end_0
    .catch Ljavax/crypto/NoSuchPaddingException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljavax/crypto/BadPaddingException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljavax/crypto/IllegalBlockSizeException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/security/InvalidKeyException; {:try_start_0 .. :try_end_0} :catch_4
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_5

    move-result-object v1

    .line 120
    .end local v0    # "cipher":Ljavax/crypto/Cipher;
    .end local v3    # "keySpec":Ljavax/crypto/spec/SecretKeySpec;
    :goto_0
    return-object v1

    .line 107
    :catch_0
    move-exception v2

    .line 108
    .local v2, "e":Ljavax/crypto/NoSuchPaddingException;
    invoke-virtual {v2}, Ljavax/crypto/NoSuchPaddingException;->printStackTrace()V

    .line 120
    .end local v2    # "e":Ljavax/crypto/NoSuchPaddingException;
    :goto_1
    const/4 v1, 0x0

    goto :goto_0

    .line 109
    :catch_1
    move-exception v2

    .line 110
    .local v2, "e":Ljava/security/NoSuchAlgorithmException;
    invoke-virtual {v2}, Ljava/security/NoSuchAlgorithmException;->printStackTrace()V

    goto :goto_1

    .line 111
    .end local v2    # "e":Ljava/security/NoSuchAlgorithmException;
    :catch_2
    move-exception v2

    .line 112
    .local v2, "e":Ljavax/crypto/BadPaddingException;
    invoke-virtual {v2}, Ljavax/crypto/BadPaddingException;->printStackTrace()V

    goto :goto_1

    .line 113
    .end local v2    # "e":Ljavax/crypto/BadPaddingException;
    :catch_3
    move-exception v2

    .line 114
    .local v2, "e":Ljavax/crypto/IllegalBlockSizeException;
    invoke-virtual {v2}, Ljavax/crypto/IllegalBlockSizeException;->printStackTrace()V

    goto :goto_1

    .line 115
    .end local v2    # "e":Ljavax/crypto/IllegalBlockSizeException;
    :catch_4
    move-exception v2

    .line 116
    .local v2, "e":Ljava/security/InvalidKeyException;
    invoke-virtual {v2}, Ljava/security/InvalidKeyException;->printStackTrace()V

    goto :goto_1

    .line 117
    .end local v2    # "e":Ljava/security/InvalidKeyException;
    :catch_5
    move-exception v2

    .line 118
    .local v2, "e":Ljava/lang/Exception;
    invoke-virtual {v2}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_1
.end method

.method public static encryptHex(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 5
    .param p0, "clearText"    # Ljava/lang/String;
    .param p1, "password"    # Ljava/lang/String;

    .prologue
    .line 179
    :try_start_0
    const-string v3, "UTF-8"

    invoke-virtual {p0, v3}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v3

    invoke-static {p1}, Lcom/sitech/paas/decrypt/EncryptUtil;->pwdHandler(Ljava/lang/String;)[B

    move-result-object v4

    invoke-static {v3, v4}, Lcom/sitech/paas/decrypt/EncryptUtil;->encrypt([B[B)[B

    move-result-object v1

    .line 182
    .local v1, "cipherTextBytes":[B
    invoke-static {v1}, Lcom/sitech/paas/decrypt/EncryptUtil;->byte2hex([B)Ljava/lang/String;
    :try_end_0
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    move-result-object v0

    .line 192
    .end local v1    # "cipherTextBytes":[B
    :goto_0
    return-object v0

    .line 186
    :catch_0
    move-exception v2

    .line 187
    .local v2, "e":Ljava/io/UnsupportedEncodingException;
    invoke-virtual {v2}, Ljava/io/UnsupportedEncodingException;->printStackTrace()V

    .line 192
    .end local v2    # "e":Ljava/io/UnsupportedEncodingException;
    :goto_1
    const/4 v0, 0x0

    goto :goto_0

    .line 188
    :catch_1
    move-exception v2

    .line 189
    .local v2, "e":Ljava/lang/Exception;
    invoke-virtual {v2}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_1
.end method

.method private static hex2byte(Ljava/lang/String;)[B
    .locals 6
    .param p0, "str"    # Ljava/lang/String;

    .prologue
    .line 237
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v4

    const/4 v5, 0x2

    if-ge v4, v5, :cond_2

    .line 238
    :cond_0
    const/4 v4, 0x0

    new-array v2, v4, [B

    .line 247
    :cond_1
    return-object v2

    .line 240
    :cond_2
    invoke-virtual {p0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p0

    .line 241
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v4

    div-int/lit8 v1, v4, 0x2

    .line 242
    .local v1, "l":I
    new-array v2, v1, [B

    .line 243
    .local v2, "result":[B
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_0
    if-ge v0, v1, :cond_1

    .line 244
    mul-int/lit8 v4, v0, 0x2

    mul-int/lit8 v5, v0, 0x2

    add-int/lit8 v5, v5, 0x2

    invoke-virtual {p0, v4, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v3

    .line 245
    .local v3, "tmp":Ljava/lang/String;
    const/16 v4, 0x10

    invoke-static {v3, v4}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result v4

    and-int/lit16 v4, v4, 0xff

    int-to-byte v4, v4

    aput-byte v4, v2, v0

    .line 243
    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method

.method public static main([Ljava/lang/String;)V
    .locals 3
    .param p0, "args"    # [Ljava/lang/String;

    .prologue
    .line 251
    const-string v1, "test"

    const-string v2, "1234567800000000"

    invoke-static {v1, v2}, Lcom/sitech/paas/decrypt/EncryptUtil;->encryptHex(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 252
    .local v0, "test":Ljava/lang/String;
    sget-object v1, Ljava/lang/System;->out:Ljava/io/PrintStream;

    invoke-virtual {v1, v0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 254
    sget-object v1, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v2, "1234567800000000"

    invoke-static {v0, v2}, Lcom/sitech/paas/decrypt/EncryptUtil;->decryptHex(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 255
    return-void
.end method

.method private static pwdHandler(Ljava/lang/String;)[B
    .locals 4
    .param p0, "password"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/UnsupportedEncodingException;
        }
    .end annotation

    .prologue
    const/16 v3, 0x10

    .line 61
    const/4 v0, 0x0

    .line 62
    .local v0, "data":[B
    if-nez p0, :cond_0

    .line 63
    const-string p0, ""

    .line 65
    :cond_0
    new-instance v1, Ljava/lang/StringBuffer;

    invoke-direct {v1, v3}, Ljava/lang/StringBuffer;-><init>(I)V

    .line 66
    .local v1, "sb":Ljava/lang/StringBuffer;
    invoke-virtual {v1, p0}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 67
    :goto_0
    invoke-virtual {v1}, Ljava/lang/StringBuffer;->length()I

    move-result v2

    if-ge v2, v3, :cond_1

    .line 68
    const-string v2, "0"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    goto :goto_0

    .line 70
    :cond_1
    invoke-virtual {v1}, Ljava/lang/StringBuffer;->length()I

    move-result v2

    if-le v2, v3, :cond_2

    .line 71
    invoke-virtual {v1, v3}, Ljava/lang/StringBuffer;->setLength(I)V

    .line 74
    :cond_2
    invoke-virtual {v1}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v2

    const-string v3, "UTF-8"

    invoke-virtual {v2, v3}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v0

    .line 76
    return-object v0
.end method
