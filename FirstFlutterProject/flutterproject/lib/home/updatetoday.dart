import 'package:flutter/material.dart';
import 'package:flutterproject/Constants.dart';
import 'package:flutterproject/commonheader.dart';
import 'package:flutterproject/home/todayitem.dart';
import 'package:flutterproject/model/updatetoday.dart';
import 'package:flutterproject/utils/screen.dart';

class UpdateTodayWidget extends StatefulWidget {
  List<UpdateToday> datas;

  UpdateTodayWidget(this.datas);

  @override
  State<StatefulWidget> createState() {
    return UpdateTodayState();
  }
}

class UpdateTodayState extends State<UpdateTodayWidget> {
  @override
  Widget build(BuildContext context) {
    if (widget.datas == null) {
      return Container();
    }
    var width = Screen.width * 0.4;
    var height = Screen.width * 0.93;
    var children = widget.datas.map((item) => TodayItem(item)).toList();
    return Container(
      child: Column(
        children: <Widget>[
          ItemHeaderWidget(Constants.updateToday),
          SizedBox(height: 8,),
          Row(
            children: <Widget>[
              Image.asset(
                "img/comic_update_today_left.png",
                width: width,
                height: height,
                fit: BoxFit.cover,
              ),
              Wrap(
                spacing: 15,
                runSpacing: 0,
                direction: Axis.vertical,
                children: children,
              )
            ],
          )
        ],
      ),
    );
  }
}
