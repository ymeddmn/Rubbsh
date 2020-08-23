import 'dart:core';
import 'dart:ui';

import 'package:flutter/cupertino.dart';

class Screen {
  static double get width {
    MediaQueryData mediaQueryData = MediaQueryData.fromWindow(window);
    return mediaQueryData.size.width;
  }

  static double get height {
    MediaQueryData mediaQueryData = MediaQueryData.fromWindow(window);
    return mediaQueryData.size.height;
  }
}
