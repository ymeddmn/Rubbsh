import 'package:flutter/material.dart';
import 'package:flutterproject/book/bookitemcellwidget.dart';
import 'package:flutterproject/model/bookmodule.dart';

/**
 * 上面四个，下面是田字形
 */
class FourFieldWidget extends StatelessWidget {
  BookModule bookModule;

  FourFieldWidget(this.bookModule);

  item(BookItem it) {}

  @override
  Widget build(BuildContext context) {
    var books = bookModule.books;
    List<Widget> children = [];
    books.sublist(0, 4).forEach((it) {
      children.add(CellVerticalWidget(it));
    });
    books.sublist(4).forEach((it) {
      children.add(CellHorizontalWidget(it));
    });

    return Column(
      children: <Widget>[
        CellTitleWidget(bookModule.name),
        SizedBox(height: 5,),
        Container(
//          margin: EdgeInsets.only(left: 15),
          child: Wrap(
            children: children.toList(),
            runSpacing: 8,
            spacing: 15,
          ),
        ),
        Container(height: 10,)
      ],
    );
  }
}
