import 'package:flutter/material.dart';
import 'package:flutterproject/Constants.dart';
import 'package:flutterproject/commonheader.dart';
import 'package:flutterproject/home/wulditem.dart';
import 'package:flutterproject/model/homemodel.dart';

class WuLdRecommandWidget extends StatefulWidget {
  List<WuldDto> datas;

  WuLdRecommandWidget(this.datas);

  @override
  State<StatefulWidget> createState() {
    return WuLdRecommandState();
  }
}

class WuLdRecommandState extends State<WuLdRecommandWidget> {
  @override
  Widget build(BuildContext context) {
    var children = widget.datas.map((item) {
      return WuldItem(item);
    }).toList();
    return Container(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          SizedBox(
            height: 5,
          ),
          Container(
            child: ItemHeaderWidget(Constants.comicBlock),
          ),
          SizedBox(
            height: 5,
          ),
          Container(
            padding: EdgeInsets.only(left: 15),
            child: Wrap(
              spacing: 15,
              runSpacing: 15,
              children: children,
            ),
          )
        ],
      ),
    );
  }
}
