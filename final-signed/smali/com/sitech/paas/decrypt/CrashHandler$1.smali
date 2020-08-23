.class Lcom/sitech/paas/decrypt/CrashHandler$1;
.super Ljava/lang/Thread;
.source "CrashHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/sitech/paas/decrypt/CrashHandler;->handleException(Ljava/lang/Throwable;)Z
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/sitech/paas/decrypt/CrashHandler;


# direct methods
.method constructor <init>(Lcom/sitech/paas/decrypt/CrashHandler;)V
    .locals 0
    .param p1, "this$0"    # Lcom/sitech/paas/decrypt/CrashHandler;

    .prologue
    .line 119
    iput-object p1, p0, Lcom/sitech/paas/decrypt/CrashHandler$1;->this$0:Lcom/sitech/paas/decrypt/CrashHandler;

    invoke-direct {p0}, Ljava/lang/Thread;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    .prologue
    .line 123
    const-wide/16 v2, 0x7d0

    :try_start_0
    invoke-static {v2, v3}, Ljava/lang/Thread;->sleep(J)V
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0

    .line 128
    :goto_0
    return-void

    .line 124
    :catch_0
    move-exception v0

    .line 125
    .local v0, "e":Ljava/lang/InterruptedException;
    invoke-virtual {v0}, Ljava/lang/InterruptedException;->printStackTrace()V

    goto :goto_0
.end method
