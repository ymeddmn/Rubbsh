import 'dart:async';
import 'package:flutter/services.dart';
import 'dart:io';
import 'package:quiver/core.dart';
import 'dart:convert';

class Request {
  static const String baseUrl = 'http://192.168.4.32/';

  static Future<dynamic> get({String url, Map params}) async {
    return mock(url: url, params: params);
  }

  static Future<dynamic> post({String url, Map params}) async {
    return mock(url: url, params: params);
  }

  static Future<dynamic> mock({String url, Map params}) async {
    var loadString = await rootBundle.loadString('mock/$url.json');
    var responseJson = json.decode(loadString);
    return responseJson['data'];
  }
}
