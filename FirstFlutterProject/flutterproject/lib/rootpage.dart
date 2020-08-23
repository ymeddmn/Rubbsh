import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutterproject/book/book.dart';
import 'package:flutterproject/home/homepage.dart';
import 'package:flutterproject/shortvideo/shortvideo.dart';
import 'package:flutterproject/ty_color.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'me/me.dart';
class RootPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _RootPageState();
  }
}

class _RootPageState extends State<RootPage> {
  int _currentIndex = 0;
  List<Image> _tabImages = [
    Image.asset('img/tab_comic_home_n.png'),
    Image.asset('img/tab_video_home_n.png'),
    Image.asset('img/tab_book_home_n.png'),
    Image.asset('img/tab_mine_n.png'),
  ];
  List<Image> _tabSelectedImages = [
    Image.asset('img/tab_home_comic_p.png'),
    Image.asset('img/tab_video_home_p.png'),
    Image.asset('img/tab_book_home_p.png'),
    Image.asset('img/tab_mine_p.png'),
  ];

  getIcon(int index) {
    if (index == _currentIndex) {
      return _tabSelectedImages[index];
    } else {
      return _tabImages[index];
    }
  }

  Future<bool> _onWillPop() {
    return showDialog(
        context: context,
        builder: (context) => AlertDialog(
          title: Text("提示"),
          content: Text("确定要退出吗？"),
          actions: <Widget>[
            FlatButton(
              child: Text("放弃"),
              onPressed: (){
                Navigator.of(context).pop(false);
              },
            ),
            FlatButton(
              child: Text("退出"),
              onPressed: (){
                Navigator.of(context).pop(true);
              },
            )
          ],
        ));
  }
  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      child: Scaffold(
        body: IndexedStack(
          children: <Widget>[
            HomePage(),
            ShortVideoPage(),
            BookPage(),
            MePage()
          ],
          index: _currentIndex,
        ),
        bottomNavigationBar: CupertinoTabBar(
          backgroundColor: Colors.white,
          currentIndex: _currentIndex,
          activeColor: TYColor.primary,
          items: <BottomNavigationBarItem>[
            BottomNavigationBarItem(icon: getIcon(0)),
            BottomNavigationBarItem(icon: getIcon(1)),
            BottomNavigationBarItem(icon: getIcon(2)),
            BottomNavigationBarItem(icon: getIcon(3)),
          ],
          onTap: (index) {
            setState(() {
              _currentIndex = index;
            });
          },
        ),
      ),
      onWillPop: _onWillPop,
    );
  }

}
