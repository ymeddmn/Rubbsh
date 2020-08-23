import 'package:flutter/material.dart';
import 'package:flutterproject/book/bookitemcellwidget.dart';
import 'package:flutterproject/model/bookmodule.dart';

/**
 * 每行一个，一共四行
 */
class OneFourWidget extends StatelessWidget {
  BookModule bookModule;

  OneFourWidget(this.bookModule);

  item(BookItem it) {
    return Container(
      child: Text('每行一个一共四行'),
    );
  }

  @override
  Widget build(BuildContext context) {
    var children = bookModule.books
        .map((it) => Container(
              child: CellOneHorWidget(it),
            ))
        .toList();
    return Column(
      children: <Widget>[
        CellTitleWidget(bookModule.name),
        SizedBox(
          height: 6,
        ),
        Container(
          margin: EdgeInsets.symmetric(horizontal: 15),
          child: Wrap(
            children: children,
            spacing: 15,
            runSpacing: 8,
          ),
        ),
        SizedBox(
          height: 8,
        )
      ],
    );
  }
}
