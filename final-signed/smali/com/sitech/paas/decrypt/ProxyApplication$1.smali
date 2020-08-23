.class Lcom/sitech/paas/decrypt/ProxyApplication$1;
.super Ljava/lang/Object;
.source "ProxyApplication.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/sitech/paas/decrypt/ProxyApplication;->onCreate()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/sitech/paas/decrypt/ProxyApplication;


# direct methods
.method constructor <init>(Lcom/sitech/paas/decrypt/ProxyApplication;)V
    .locals 0
    .param p1, "this$0"    # Lcom/sitech/paas/decrypt/ProxyApplication;

    .prologue
    .line 171
    iput-object p1, p0, Lcom/sitech/paas/decrypt/ProxyApplication$1;->this$0:Lcom/sitech/paas/decrypt/ProxyApplication;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .prologue
    .line 174
    iget-object v0, p0, Lcom/sitech/paas/decrypt/ProxyApplication$1;->this$0:Lcom/sitech/paas/decrypt/ProxyApplication;

    invoke-static {v0}, Lcom/sitech/paas/decrypt/ProxyApplication;->access$000(Lcom/sitech/paas/decrypt/ProxyApplication;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/sitech/paas/decrypt/ProxyApplication$1;->this$0:Lcom/sitech/paas/decrypt/ProxyApplication;

    invoke-static {v0}, Lcom/sitech/paas/decrypt/ProxyApplication;->access$100(Lcom/sitech/paas/decrypt/ProxyApplication;)Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lcom/sitech/paas/decrypt/ProxyApplication$1;->this$0:Lcom/sitech/paas/decrypt/ProxyApplication;

    invoke-static {v1}, Lcom/sitech/paas/decrypt/ProxyApplication;->access$000(Lcom/sitech/paas/decrypt/ProxyApplication;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 175
    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v1, "\u76d7\u7248\u5e94\u7528\u9000\u51fa\u7cfb\u7edf"

    invoke-virtual {v0, v1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 177
    invoke-static {}, Landroid/os/Process;->myPid()I

    move-result v0

    invoke-static {v0}, Landroid/os/Process;->killProcess(I)V

    .line 179
    :cond_0
    return-void
.end method
