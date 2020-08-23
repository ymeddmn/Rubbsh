.class public Lcom/sitech/paas/decrypt/RefInvoke;
.super Ljava/lang/Object;
.source "RefInvoke.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 20
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static getFieldOjbect(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    .locals 4
    .param p0, "class_name"    # Ljava/lang/String;
    .param p1, "obj"    # Ljava/lang/Object;
    .param p2, "filedName"    # Ljava/lang/String;

    .prologue
    .line 105
    :try_start_0
    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 106
    .local v2, "obj_class":Ljava/lang/Class;
    invoke-virtual {v2, p2}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v1

    .line 107
    .local v1, "field":Ljava/lang/reflect/Field;
    const/4 v3, 0x1

    invoke-virtual {v1, v3}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 108
    invoke-virtual {v1, p1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_4

    move-result-object v3

    .line 125
    .end local v1    # "field":Ljava/lang/reflect/Field;
    .end local v2    # "obj_class":Ljava/lang/Class;
    :goto_0
    return-object v3

    .line 109
    :catch_0
    move-exception v0

    .line 111
    .local v0, "e":Ljava/lang/SecurityException;
    invoke-virtual {v0}, Ljava/lang/SecurityException;->printStackTrace()V

    .line 125
    .end local v0    # "e":Ljava/lang/SecurityException;
    :goto_1
    const/4 v3, 0x0

    goto :goto_0

    .line 112
    :catch_1
    move-exception v0

    .line 114
    .local v0, "e":Ljava/lang/NoSuchFieldException;
    invoke-virtual {v0}, Ljava/lang/NoSuchFieldException;->printStackTrace()V

    goto :goto_1

    .line 115
    .end local v0    # "e":Ljava/lang/NoSuchFieldException;
    :catch_2
    move-exception v0

    .line 117
    .local v0, "e":Ljava/lang/IllegalArgumentException;
    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->printStackTrace()V

    goto :goto_1

    .line 118
    .end local v0    # "e":Ljava/lang/IllegalArgumentException;
    :catch_3
    move-exception v0

    .line 120
    .local v0, "e":Ljava/lang/IllegalAccessException;
    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    goto :goto_1

    .line 121
    .end local v0    # "e":Ljava/lang/IllegalAccessException;
    :catch_4
    move-exception v0

    .line 123
    .local v0, "e":Ljava/lang/ClassNotFoundException;
    invoke-virtual {v0}, Ljava/lang/ClassNotFoundException;->printStackTrace()V

    goto :goto_1
.end method

.method public static getStaticFieldOjbect(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    .locals 5
    .param p0, "class_name"    # Ljava/lang/String;
    .param p1, "filedName"    # Ljava/lang/String;

    .prologue
    const/4 v3, 0x0

    .line 138
    :try_start_0
    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 139
    .local v2, "obj_class":Ljava/lang/Class;
    invoke-virtual {v2, p1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v1

    .line 140
    .local v1, "field":Ljava/lang/reflect/Field;
    const/4 v4, 0x1

    invoke-virtual {v1, v4}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 141
    const/4 v4, 0x0

    invoke-virtual {v1, v4}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_4

    move-result-object v3

    .line 158
    .end local v1    # "field":Ljava/lang/reflect/Field;
    .end local v2    # "obj_class":Ljava/lang/Class;
    :goto_0
    return-object v3

    .line 142
    :catch_0
    move-exception v0

    .line 144
    .local v0, "e":Ljava/lang/SecurityException;
    invoke-virtual {v0}, Ljava/lang/SecurityException;->printStackTrace()V

    goto :goto_0

    .line 145
    .end local v0    # "e":Ljava/lang/SecurityException;
    :catch_1
    move-exception v0

    .line 147
    .local v0, "e":Ljava/lang/NoSuchFieldException;
    invoke-virtual {v0}, Ljava/lang/NoSuchFieldException;->printStackTrace()V

    goto :goto_0

    .line 148
    .end local v0    # "e":Ljava/lang/NoSuchFieldException;
    :catch_2
    move-exception v0

    .line 150
    .local v0, "e":Ljava/lang/IllegalArgumentException;
    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->printStackTrace()V

    goto :goto_0

    .line 151
    .end local v0    # "e":Ljava/lang/IllegalArgumentException;
    :catch_3
    move-exception v0

    .line 153
    .local v0, "e":Ljava/lang/IllegalAccessException;
    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    goto :goto_0

    .line 154
    .end local v0    # "e":Ljava/lang/IllegalAccessException;
    :catch_4
    move-exception v0

    .line 156
    .local v0, "e":Ljava/lang/ClassNotFoundException;
    invoke-virtual {v0}, Ljava/lang/ClassNotFoundException;->printStackTrace()V

    goto :goto_0
.end method

.method public static invokeMethod(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4
    .param p0, "class_name"    # Ljava/lang/String;
    .param p1, "method_name"    # Ljava/lang/String;
    .param p2, "obj"    # Ljava/lang/Object;
    .param p3, "pareTyple"    # [Ljava/lang/Class;
    .param p4, "pareVaules"    # [Ljava/lang/Object;

    .prologue
    .line 70
    :try_start_0
    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 71
    .local v2, "obj_class":Ljava/lang/Class;
    invoke-virtual {v2, p1, p3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    .line 72
    .local v1, "method":Ljava/lang/reflect/Method;
    invoke-virtual {v1, p2, p4}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/NoSuchMethodException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/reflect/InvocationTargetException; {:try_start_0 .. :try_end_0} :catch_4
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_5

    move-result-object v3

    .line 92
    .end local v1    # "method":Ljava/lang/reflect/Method;
    .end local v2    # "obj_class":Ljava/lang/Class;
    :goto_0
    return-object v3

    .line 73
    :catch_0
    move-exception v0

    .line 75
    .local v0, "e":Ljava/lang/SecurityException;
    invoke-virtual {v0}, Ljava/lang/SecurityException;->printStackTrace()V

    .line 92
    .end local v0    # "e":Ljava/lang/SecurityException;
    :goto_1
    const/4 v3, 0x0

    goto :goto_0

    .line 76
    :catch_1
    move-exception v0

    .line 78
    .local v0, "e":Ljava/lang/IllegalArgumentException;
    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->printStackTrace()V

    goto :goto_1

    .line 79
    .end local v0    # "e":Ljava/lang/IllegalArgumentException;
    :catch_2
    move-exception v0

    .line 81
    .local v0, "e":Ljava/lang/IllegalAccessException;
    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    goto :goto_1

    .line 82
    .end local v0    # "e":Ljava/lang/IllegalAccessException;
    :catch_3
    move-exception v0

    .line 84
    .local v0, "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_1

    .line 85
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    :catch_4
    move-exception v0

    .line 87
    .local v0, "e":Ljava/lang/reflect/InvocationTargetException;
    invoke-virtual {v0}, Ljava/lang/reflect/InvocationTargetException;->printStackTrace()V

    goto :goto_1

    .line 88
    .end local v0    # "e":Ljava/lang/reflect/InvocationTargetException;
    :catch_5
    move-exception v0

    .line 90
    .local v0, "e":Ljava/lang/ClassNotFoundException;
    invoke-virtual {v0}, Ljava/lang/ClassNotFoundException;->printStackTrace()V

    goto :goto_1
.end method

.method public static invokeStaticMethod(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    .locals 5
    .param p0, "class_name"    # Ljava/lang/String;
    .param p1, "method_name"    # Ljava/lang/String;
    .param p2, "pareTyple"    # [Ljava/lang/Class;
    .param p3, "pareVaules"    # [Ljava/lang/Object;

    .prologue
    const/4 v3, 0x0

    .line 32
    :try_start_0
    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 33
    .local v2, "obj_class":Ljava/lang/Class;
    invoke-virtual {v2, p1, p2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    .line 34
    .local v1, "method":Ljava/lang/reflect/Method;
    const/4 v4, 0x0

    invoke-virtual {v1, v4, p3}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/NoSuchMethodException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/reflect/InvocationTargetException; {:try_start_0 .. :try_end_0} :catch_4
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_5

    move-result-object v3

    .line 54
    .end local v1    # "method":Ljava/lang/reflect/Method;
    .end local v2    # "obj_class":Ljava/lang/Class;
    :goto_0
    return-object v3

    .line 35
    :catch_0
    move-exception v0

    .line 37
    .local v0, "e":Ljava/lang/SecurityException;
    invoke-virtual {v0}, Ljava/lang/SecurityException;->printStackTrace()V

    goto :goto_0

    .line 38
    .end local v0    # "e":Ljava/lang/SecurityException;
    :catch_1
    move-exception v0

    .line 40
    .local v0, "e":Ljava/lang/IllegalArgumentException;
    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->printStackTrace()V

    goto :goto_0

    .line 41
    .end local v0    # "e":Ljava/lang/IllegalArgumentException;
    :catch_2
    move-exception v0

    .line 43
    .local v0, "e":Ljava/lang/IllegalAccessException;
    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    goto :goto_0

    .line 44
    .end local v0    # "e":Ljava/lang/IllegalAccessException;
    :catch_3
    move-exception v0

    .line 46
    .local v0, "e":Ljava/lang/NoSuchMethodException;
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    goto :goto_0

    .line 47
    .end local v0    # "e":Ljava/lang/NoSuchMethodException;
    :catch_4
    move-exception v0

    .line 49
    .local v0, "e":Ljava/lang/reflect/InvocationTargetException;
    invoke-virtual {v0}, Ljava/lang/reflect/InvocationTargetException;->printStackTrace()V

    goto :goto_0

    .line 50
    .end local v0    # "e":Ljava/lang/reflect/InvocationTargetException;
    :catch_5
    move-exception v0

    .line 52
    .local v0, "e":Ljava/lang/ClassNotFoundException;
    invoke-virtual {v0}, Ljava/lang/ClassNotFoundException;->printStackTrace()V

    goto :goto_0
.end method

.method public static setFieldOjbect(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 4
    .param p0, "classname"    # Ljava/lang/String;
    .param p1, "filedName"    # Ljava/lang/String;
    .param p2, "obj"    # Ljava/lang/Object;
    .param p3, "filedVaule"    # Ljava/lang/Object;

    .prologue
    .line 171
    :try_start_0
    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 172
    .local v2, "obj_class":Ljava/lang/Class;
    invoke-virtual {v2, p1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v1

    .line 173
    .local v1, "field":Ljava/lang/reflect/Field;
    const/4 v3, 0x1

    invoke-virtual {v1, v3}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 174
    invoke-virtual {v1, p2, p3}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_4

    .line 191
    .end local v1    # "field":Ljava/lang/reflect/Field;
    .end local v2    # "obj_class":Ljava/lang/Class;
    :goto_0
    return-void

    .line 175
    :catch_0
    move-exception v0

    .line 177
    .local v0, "e":Ljava/lang/SecurityException;
    invoke-virtual {v0}, Ljava/lang/SecurityException;->printStackTrace()V

    goto :goto_0

    .line 178
    .end local v0    # "e":Ljava/lang/SecurityException;
    :catch_1
    move-exception v0

    .line 180
    .local v0, "e":Ljava/lang/NoSuchFieldException;
    invoke-virtual {v0}, Ljava/lang/NoSuchFieldException;->printStackTrace()V

    goto :goto_0

    .line 181
    .end local v0    # "e":Ljava/lang/NoSuchFieldException;
    :catch_2
    move-exception v0

    .line 183
    .local v0, "e":Ljava/lang/IllegalArgumentException;
    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->printStackTrace()V

    goto :goto_0

    .line 184
    .end local v0    # "e":Ljava/lang/IllegalArgumentException;
    :catch_3
    move-exception v0

    .line 186
    .local v0, "e":Ljava/lang/IllegalAccessException;
    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    goto :goto_0

    .line 187
    .end local v0    # "e":Ljava/lang/IllegalAccessException;
    :catch_4
    move-exception v0

    .line 189
    .local v0, "e":Ljava/lang/ClassNotFoundException;
    invoke-virtual {v0}, Ljava/lang/ClassNotFoundException;->printStackTrace()V

    goto :goto_0
.end method

.method public static setStaticOjbect(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    .locals 4
    .param p0, "class_name"    # Ljava/lang/String;
    .param p1, "filedName"    # Ljava/lang/String;
    .param p2, "filedVaule"    # Ljava/lang/Object;

    .prologue
    .line 201
    :try_start_0
    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 202
    .local v2, "obj_class":Ljava/lang/Class;
    invoke-virtual {v2, p1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v1

    .line 203
    .local v1, "field":Ljava/lang/reflect/Field;
    const/4 v3, 0x1

    invoke-virtual {v1, v3}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 204
    const/4 v3, 0x0

    invoke-virtual {v1, v3, p2}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_4

    .line 221
    .end local v1    # "field":Ljava/lang/reflect/Field;
    .end local v2    # "obj_class":Ljava/lang/Class;
    :goto_0
    return-void

    .line 205
    :catch_0
    move-exception v0

    .line 207
    .local v0, "e":Ljava/lang/SecurityException;
    invoke-virtual {v0}, Ljava/lang/SecurityException;->printStackTrace()V

    goto :goto_0

    .line 208
    .end local v0    # "e":Ljava/lang/SecurityException;
    :catch_1
    move-exception v0

    .line 210
    .local v0, "e":Ljava/lang/NoSuchFieldException;
    invoke-virtual {v0}, Ljava/lang/NoSuchFieldException;->printStackTrace()V

    goto :goto_0

    .line 211
    .end local v0    # "e":Ljava/lang/NoSuchFieldException;
    :catch_2
    move-exception v0

    .line 213
    .local v0, "e":Ljava/lang/IllegalArgumentException;
    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->printStackTrace()V

    goto :goto_0

    .line 214
    .end local v0    # "e":Ljava/lang/IllegalArgumentException;
    :catch_3
    move-exception v0

    .line 216
    .local v0, "e":Ljava/lang/IllegalAccessException;
    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    goto :goto_0

    .line 217
    .end local v0    # "e":Ljava/lang/IllegalAccessException;
    :catch_4
    move-exception v0

    .line 219
    .local v0, "e":Ljava/lang/ClassNotFoundException;
    invoke-virtual {v0}, Ljava/lang/ClassNotFoundException;->printStackTrace()V

    goto :goto_0
.end method
