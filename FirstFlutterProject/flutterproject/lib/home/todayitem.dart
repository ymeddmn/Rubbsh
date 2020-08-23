import 'package:flutter/material.dart';
import 'package:flutterproject/model/updatetoday.dart';
import 'package:flutterproject/utils/screen.dart';

class TodayItem extends StatelessWidget {
  UpdateToday updateToday;

  TodayItem(this.updateToday);

  @override
  Widget build(BuildContext context) {
    if (updateToday == null) {
      return Container(
        child: Text("哈哈"),
      );
    }
    var itemWidth = Screen.width * 0.21;
    var imgWidth = Screen.width * 0.21;
    return Container(
      child: Row(
        children: <Widget>[
          Image.network(
            updateToday.cover,
            width: imgWidth,
            height: 1.32 * itemWidth,
            fit: BoxFit.cover,
          ),
          SizedBox(width: 8,),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Text(
                updateToday.title,
                overflow: TextOverflow.ellipsis,
                maxLines: 1,
                style: TextStyle(fontSize: 17,fontWeight: FontWeight.bold),
              ),
              Text(
                updateToday.author,
                overflow: TextOverflow.ellipsis,
                maxLines: 1,
                style: TextStyle(fontSize: 15, color: Color(0xff969696)),
              ),
              Text(
                updateToday.newUpdate,
                overflow: TextOverflow.ellipsis,
                maxLines: 1,
                style: TextStyle(fontSize: 15, color: Color(0xff969696)),
              ),
            ],
          )
        ],
      ),
    );
  }
}
