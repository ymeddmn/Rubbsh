import 'package:flutter/material.dart';
import 'package:flutterproject/book/FourTwoWidget.dart';
import 'package:flutterproject/model/bookmodule.dart';
import 'package:flutterproject/utils/screen.dart';

import '../ty_color.dart';

/**
 * 顶部的header
 */
class CellTitleWidget extends StatelessWidget {
  var title;

  CellTitleWidget(this.title);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      padding: EdgeInsets.only(top: 8),
      child: Column(
        children: <Widget>[
          SizedBox(
            height: 6,
          ),
          Row(
            children: <Widget>[
              Container(
                margin: EdgeInsets.only(left: 14),
                width: 4,
                height: 15,
                color: TYColor.primary,
              ),
              SizedBox(width: 10),
              Text(
                '$title',
                style: TextStyle(fontSize: 17, fontWeight: FontWeight.bold),
              )
            ],
          ),
          SizedBox(
            height: 6,
          ),
        ],
      ),
    );
  }
}

/**
 * 竖向的widget
 */
class CellVerticalWidget extends StatelessWidget {
  BookItem bookItem;

  CellVerticalWidget(this.bookItem);

  @override
  Widget build(BuildContext context) {
    var width = (Screen.width - 15 * 3 - 15 * 2) / 4;
    return ClipRRect(
      borderRadius: BorderRadius.circular(5),
      child: Container(
        color: Colors.white,
        width: BookScreen.vWidth,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Image.network(
              bookItem.imgUrl,
              width: width,
              height: width / 0.75,
            ),
            SizedBox(
              height: 5,
            ),
            Text(
              bookItem.bookName,
              style: TextStyle(fontSize: 14, fontWeight: FontWeight.bold),
              overflow: TextOverflow.ellipsis,
              maxLines: 1,
            ),
            SizedBox(
              height: 3,
            ),
            Text(
              bookItem.author,
              overflow: TextOverflow.ellipsis,
              maxLines: 1,
              style: TextStyle(fontSize: 12, color: Colors.grey),
            ),
            SizedBox(
              height: 5,
            )
          ],
        ),
      ),
    );
  }
}

/**
 * 横向的widget
 */
class CellHorizontalWidget extends StatelessWidget {
  BookItem bookItem;

  CellHorizontalWidget(this.bookItem);

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(5),
      child: Container(
        color: Colors.white,
        width: BookScreen.hWidth,
        child: Row(
          children: <Widget>[
            Image.network(
              bookItem.imgUrl,
              width: BookScreen.vWidth,
            ),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(
                    bookItem.bookName,
                    maxLines: 2,
                    overflow: TextOverflow.ellipsis,
                    style: TextStyle(fontSize: 14, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(
                    height: 6,
                  ),
                  Text(bookItem.author,
                      style: TextStyle(fontSize: 12, color: Colors.grey))
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}

/**
 * 横着的一条item
 */
class CellOneHorWidget extends StatelessWidget {
  BookItem bookItem;

  CellOneHorWidget(this.bookItem);

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(8),
      child: Container(
        color: Colors.white,
        child: Row(
          children: <Widget>[
            Image.network(
              bookItem.imgUrl,
              width: BookScreen.vWidth,
              height: BookScreen.vHeight,
            ),
            Expanded(
              child: Column(
                children: <Widget>[
                  Text(
                    bookItem.bookName,
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 14,
                    ),
                  ),
                  Text(
                    bookItem.introduce,
                    overflow: TextOverflow.ellipsis,
                    maxLines: 2,
                    style: TextStyle(color: Colors.grey),
                  ),
                  Row(
                    children: <Widget>[
                      Text(bookItem.author),
                      Expanded(
                        child: Container(),
                      ),
                      Text('完结'),
                      Text(bookItem.state)
                    ],
                  )
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}

class BookScreen {
  //书架页面图片的宽度
  static double get vWidth {
    var width = (Screen.width - 15 * 3 - 15 * 2) / 4;
    return width;
  }

  //书架页面图片的高度
  static double get vHeight {
    var height = ((Screen.width - 15 * 3 - 15 * 2) / 4) / 0.75;
    return height;
  }

  static double get hWidth {
    var width = (Screen.width - 15 * 3) / 2;
    return width;
  }

  static double get hHeight {
    var width = vWidth;
    return width / 0.75;
  }
}
