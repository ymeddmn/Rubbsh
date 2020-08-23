//
// Created by 王永梅 on 2019-05-24.
//

#include "com_example_sitechcrash_SitechCrash.h"
#include "breakpad/src/client/linux/handler/exception_handler.h"
#include "breakpad/src/client/linux/handler/minidump_descriptor.h"

JNIEXPORT void JNICALL Java_com_example_sitechcrash_SitechCrash_install
  (JNIEnv *, jclass){
    google_breakpad::MinidumpDescriptor descriptor("/sdcard");
   static google_breakpad::ExceptionHandler *handler = new google_breakpad::ExceptionHandler(descriptor,
              NULL,
              NULL,
              NULL,
              true,
              -1);
  }
JNIEXPORT void JNICALL Java_com_example_sitechcrash_SitechCrash_testcarsh
  (JNIEnv *, jclass){
     char *ptr = NULL; *ptr = 1;
  }
