import 'dart:math';

import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flukit/flukit.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutterproject/rootpage.dart';
import 'package:flutter/cupertino.dart';
import 'package:shared_preferences/shared_preferences.dart';
class GuidePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _GuideState();
  }
}

class _GuideState extends State<GuidePage> {
  List<Widget> indicaters = [];
  List<String> guideList = [
    'img/guide1.png',
    'img/guide2.png',
    'img/guide3.png',
    'img/guide4.png',
  ];
  List<String> _guideInfoList = [
    "动漫陪你每一个夜晚",
    "同你去往每个地方",
    "懂你，更懂你所爱",
    "因为在意，所以用心",
  ];

  @override
  void initState() {
    super.initState();
    _initBannar();
  }

  void _initBannar() {
    int length = guideList.length;
    for (int i = 0; i < length; i++) {
      if (i == length - 1) {
        indicaters.add(Stack(
          children: <Widget>[
            Image.asset(
              guideList[i],
              fit: BoxFit.cover,
              width: double.infinity,
              height: double.infinity,
            ),
            Center(
              child: Text(
                _guideInfoList[i],
                style: TextStyle(fontSize: 20, color: Colors.white),
              ),
            ),
            Align(
              alignment: Alignment.bottomCenter,
              child: Container(
                margin: EdgeInsets.only(bottom: 50),
                child: GestureDetector(
                  onTap: () {
                    _goMain();
                  },
                  child: Container(
                    width: 185,
                    height: 48,
                    alignment: Alignment.center,
                    child: Text(
                      '立即启程',
                      style: TextStyle(color: Colors.white, fontSize: 20),
                    ),
                    decoration: BoxDecoration(
                        borderRadius: BorderRadius.all(Radius.circular(10)),
                        color: Color(0X19FFFFFF),
                        border: Border.all(color: Colors.white, width: 1)),
                  ),
                ),
              ),
            )
          ],
        ));
      } else {
        indicaters.add(Stack(
          children: <Widget>[
            Image.asset(
              guideList[i],
              fit: BoxFit.cover,
              width: double.infinity,
              height: double.infinity,
            ),
            Center(
              child: Text(
                _guideInfoList[i],
                style: TextStyle(fontSize: 20, color: Colors.white),
              ),
            )
          ],
        ));
      }
    }
  }

  _goMain()  {
    saveState();
    Navigator.of(context).pushAndRemoveUntil(
        CupertinoPageRoute(builder: (BuildContext context) => RootPage()),
        (Route<dynamic> route) => false);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Swiper(
            autoStart: false,
            circular: false,
            indicator: CircleSwiperIndicator(
                radius: 5,
                spacing: 5,
                itemColor: Colors.yellow,
                itemActiveColor: Colors.blue),
            children: indicaters));
  }

  Future saveState() async {
    final share = await SharedPreferences.getInstance();
    share.setBool("isNoFirst", true);
  }
}
