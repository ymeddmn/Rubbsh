import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutterproject/guidepage.dart';
import 'package:flutterproject/rootpage.dart';
import 'package:flutterproject/ty_color.dart';
import 'package:shared_preferences/shared_preferences.dart';

class FirstContainer extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return FirstContainerState();
  }
}

class FirstContainerState extends State<FirstContainer> {
  Widget childWidget ;

  @override
  void initState() {
    super.initState();
//    childWidget=getBody();
    getBody();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "首页",
      theme: ThemeData(
        primaryColor: Colors.white,
        dividerColor: Color(0xffeeeeee),
        scaffoldBackgroundColor: TYColor.paper,
        textTheme: TextTheme(body1: TextStyle(color: TYColor.darkGray)),
      ),
      home: Container(
        child: childWidget,
      ),
    );
  }

  getBody() async {
    final share = await SharedPreferences.getInstance();
    bool isNoFirst = share.getBool("isNoFirst");
    share.setBool("isFirst", true);
    if(isNoFirst==null){
      isNoFirst=false;
    }
    setState(() {
      if (isNoFirst) {
        childWidget = RootPage();
      } else {
        childWidget = GuidePage();
      }
    });
  }
}