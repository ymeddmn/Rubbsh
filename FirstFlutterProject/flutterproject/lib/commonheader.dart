import 'package:flutter/material.dart';
import 'package:flutterproject/Constants.dart';
import 'package:flutterproject/utils/screen.dart';

class ItemHeaderWidget extends StatelessWidget {
  String title;

  ItemHeaderWidget(this.title);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: getHeaderWidget(),
    );
  }

  getHeaderWidget() {
    Widget widget;
    var width = Screen.width;
    switch (title) {
      case Constants.comicBlock:
        widget = Image.asset(
          "img/comic_block_header.png",
          width: width,
        );
        break;
      case Constants.recommendEveryDay:
        widget = Image.asset(
          "img/comic_recommend_everyday_header.png",
          width: width,
        );
        break;
      case Constants.updateToday:
        widget = Image.asset(
          "img/comic_update_today_header.png",
          width: width,
        );
        break;
    }
    return widget;
  }
}
