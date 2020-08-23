import 'package:flutter/material.dart';
import 'package:flutterproject/book/bookitemcellwidget.dart';
import 'package:flutterproject/model/bookmodule.dart';
import 'package:flutterproject/utils/screen.dart';

/**
 * 一行四个，一共两行
 */
class FourTwoWidget extends StatelessWidget {
  BookModule bookModule;

  FourTwoWidget(this.bookModule);

  item(BookItem item) {
    return Container(
      child: CellVerticalWidget(item),
    );
  }

  @override
  Widget build(BuildContext context) {
    if (bookModule == null) {
      return Container();
    }
    List<Widget> children = bookModule.books
        .map((it) => Container(
              child: item(it),
            ))
        .toList();
    return Container(
      child: Column(
        children: <Widget>[
          CellTitleWidget(bookModule.name),
          SizedBox(height: 8,),
          Container(
            child: Wrap(
              children: children,
              runSpacing: 8,
              spacing: 15,
            ),
          ),
          Container(color: Color(0xfff5f5f5),height: 10,)
        ],
      ),
    );
  }
}
