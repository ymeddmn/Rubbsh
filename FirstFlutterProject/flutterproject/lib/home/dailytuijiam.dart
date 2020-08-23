import 'package:flutter/material.dart';
import 'package:flutterproject/Constants.dart';
import 'package:flutterproject/commonheader.dart';
import 'package:flutterproject/model/dailytuimodel.dart';
import 'package:flutterproject/utils/screen.dart';

import '../ty_color.dart';

class DailyTuijmWidget extends StatefulWidget {
  DailyTuiDto dailyTuiDto;

  DailyTuijmWidget(this.dailyTuiDto);

  @override
  State<StatefulWidget> createState() {
    return DailyTuijmState();
  }
}

class DailyTuijmState extends State<DailyTuijmWidget> {
  @override
  Widget build(BuildContext context) {
    if (widget.dailyTuiDto == null) {
      return Container();
    }
    var width = (Screen.width - 15 * 2);
    return Container(
      child: Container(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            ItemHeaderWidget(Constants.recommendEveryDay),
            SizedBox(
              height: 5,
            ),
            Container(
              margin: EdgeInsets.only(left: 15),
              color: TYColor.white,
              width: width,
              child: GestureDetector(
                child: Column(
                  children: <Widget>[
                    Image.network(
                      widget.dailyTuiDto.cover,
                      width: width,
                    ),
                    SizedBox(
                      height: 6,
                    ),
                    Row(
                      children: <Widget>[
                        SizedBox(
                          width: 15,
                        ),
                        Text(
                          widget.dailyTuiDto.title,
                          style: TextStyle(
                              fontSize: 17,
                              fontWeight: FontWeight.w600,
                              color: Color(0xff535252)),
                        ),
                        Expanded(
                          child: Container(),
                        ),
                        Text(widget.dailyTuiDto.author,
                            style: TextStyle(
                                fontSize: 12, color: Color(0xffC5C5C5))),
                        SizedBox(
                          width: 15,
                        )
                      ],
                    ),
                    SizedBox(
                      height: 6,
                    ),
                  ],
                ),
              ),
            ),
            SizedBox(
              height: 5,
            )
          ],
        ),
      ),
    );
  }

  getImage() {
    if (widget.dailyTuiDto != null) {
      return Image.network(widget.dailyTuiDto.cover);
    } else {
      return Container();
    }
  }
}
