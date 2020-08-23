import 'package:flutter/material.dart';
import 'package:flutterproject/model/homemodel.dart';
import 'package:flutterproject/utils/screen.dart';

class WuldItem extends StatelessWidget {
  WuldDto data;

  WuldItem(this.data);

  @override
  Widget build(BuildContext context) {
    var width = (Screen.width - 15 * 4) / 3;
    return GestureDetector(
      child: Container(
        width: width,
        child: Column(
          children: <Widget>[
            Image.network(
              data.cover,
              width: width,
            ),
            Text(
              data.title,
              overflow: TextOverflow.ellipsis,
              style: TextStyle(
                  fontSize: 14,
                  fontWeight: FontWeight.bold,
                  color: Colors.black),
              maxLines: 1,
            ),
            Text(
              data.description,
              overflow: TextOverflow.ellipsis,
              style: TextStyle(fontSize: 12, color: Colors.grey),
              maxLines: 1,
            ),
          ],
        ),
      ),
    );
  }
}
